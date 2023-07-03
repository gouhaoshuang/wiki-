package com.example.demo_3.controller;

import com.example.demo_3.req.EbookReq;
import com.example.demo_3.resp.CommonResp;
import com.example.demo_3.resp.EbookResp;
import com.example.demo_3.resp.PageResp;
import com.example.demo_3.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/ebook/list")
    public CommonResp list(EbookReq req){
        CommonResp<PageResp<EbookResp>> resp = new CommonResp<>();

        PageResp<EbookResp> list =  ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
}
