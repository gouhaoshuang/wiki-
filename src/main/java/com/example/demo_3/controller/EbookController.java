package com.example.demo_3.controller;

import com.example.demo_3.req.EbookQueryReq;
import com.example.demo_3.req.EbookSaveReq;
import com.example.demo_3.resp.CommonResp;
import com.example.demo_3.resp.EbookQueryResp;
import com.example.demo_3.resp.PageResp;
import com.example.demo_3.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/ebook/list")
    public CommonResp list(@Valid  EbookQueryReq req){
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();

        PageResp<EbookQueryResp> list =  ebookService.list(req);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/ebook/save")
    public CommonResp save(@Valid @RequestBody EbookSaveReq req){
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }

    @DeleteMapping("/ebook/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        ebookService.delete(id);
        return resp;
    }
}
