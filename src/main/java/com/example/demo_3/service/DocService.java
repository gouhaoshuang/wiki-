package com.example.demo_3.service;

import com.example.demo_3.domain.Content;
import com.example.demo_3.domain.Doc;
import com.example.demo_3.domain.DocExample;
import com.example.demo_3.mapper.ContentMapper;
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
import java.util.stream.Collectors;


@Service
public class DocService {

    @Resource
    private DocMapper docMapper;
    @Resource
    private ContentMapper contentMapper;

    private static final Logger LOG = LoggerFactory.getLogger(DocService.class);

    @Resource
    private SnowFlake snowFlake;

    public List<DocQueryResp> all(Long ebookId) {
        DocExample docExample = new DocExample();
        docExample.createCriteria().andEbookIdEqualTo(ebookId);
        docExample.setOrderByClause("sort asc");
        List<Doc> docList = docMapper.selectByExample(docExample);

        // 列表复制
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
        Content content = CopyUtil.copy(req, Content.class);

        if(ObjectUtils.isEmpty(req.getId())){
            //新增
            doc.setId(snowFlake.nextId());
            docMapper.insert(doc);

            content.setId(doc.getId());
            contentMapper.insert(content);

        }else {
            //更新
            docMapper.updateByPrimaryKey(doc);

            int count = contentMapper.updateByPrimaryKeyWithBLOBs(content);
            if(count==0){
                contentMapper.insert(content);
            }
        }
    }
    public void delete(Long id){
        docMapper.deleteByPrimaryKey((id));
    }

    public void delete(List<String> ids){
        List<Long> longIds = ids.stream().map(Long::valueOf).collect(Collectors.toList());

        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();

        criteria.andIdIn(longIds);
        docMapper.deleteByExample((docExample));
    }

    public String findContent(Long id){
        Content content = contentMapper.selectByPrimaryKey(id);
        return content.getContent();

    }



}
