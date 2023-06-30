package com.example.demo_3.service;

import com.example.demo_3.domain.Ebook;
import com.example.demo_3.domain.EbookExample;
import com.example.demo_3.mapper.EbookMapper;
import com.example.demo_3.req.EbookReq;
import com.example.demo_3.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        criteria.andNameLike("%"+req.getName()+"%");

        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        List<EbookResp> respList = new ArrayList<>();
        for (Ebook ebook : ebookList) {
            EbookResp ebookResp = new EbookResp();

            BeanUtils.copyProperties(ebook,ebookResp);

            respList.add(ebookResp);
        }
        return respList;
    }

}
