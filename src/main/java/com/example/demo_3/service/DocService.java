package com.example.demo_3.service;

import com.example.demo_3.domain.Doc;
import com.example.demo_3.domain.DocExample;
import com.example.demo_3.mapper.DocMapper;
import com.example.demo_3.req.DocQueryReq;
import com.example.demo_3.req.DocSaveReq;
import com.example.demo_3.resp.DocQueryResp;
import com.example.demo_3.resp.PageResp;
import com.example.demo_3.util.CopyUtil;
import com.example.demo_3.util.SnowFlake;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;


@Service
public class DocService {

    @Resource
    private DocMapper docMapper;
    private static final Logger LOG = LoggerFactory.getLogger(DocService.class);

    @Resource
    private SnowFlake snowFlake;

    public List<DocQueryResp> all(){

        DocExample docExample = new DocExample();
        docExample.setOrderByClause("sort asc");
        List<Doc> docList = docMapper.selectByExample(docExample);

        //列表复制
        List<DocQueryResp> list = CopyUtil.copyList(docList, DocQueryResp.class);
        return list;
    }

    public PageResp<DocQueryResp> list(DocQueryReq req){

        DocExample docExample = new DocExample();

        docExample.setOrderByClause("sort asc");
        DocExample.Criteria criteria = docExample.createCriteria();
        if(!ObjectUtils.isEmpty(req.getName())){
            criteria.andNameLike("%"+req.getName()+"%");
        }

        PageHelper.startPage(req.getPage(), req.getSize());
        List<Doc> docList = docMapper.selectByExample(docExample);


        PageInfo<Doc> pageInfo = new PageInfo<>(docList);
        LOG.info("总行数:{}",pageInfo.getTotal());
        LOG.info("总页数:{}",pageInfo.getPages());


        List<DocQueryResp> list = CopyUtil.copyList(docList, DocQueryResp.class);
        PageResp<DocQueryResp> pageResp = new PageResp();
        pageResp.setTotal(pageInfo.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void save(DocSaveReq req){
        Doc doc = CopyUtil.copy(req, Doc.class);
        if(ObjectUtils.isEmpty(req.getId())){
            doc.setId(snowFlake.nextId());
            docMapper.insert(doc);

        }else {
            docMapper.updateByPrimaryKey(doc);
        }
    }
    public void delete(Long id){
        docMapper.deleteByPrimaryKey((id));
    }



}
