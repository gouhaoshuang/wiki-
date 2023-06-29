package com.example.demo_3.controller;

import com.example.demo_3.domain.Ebook;
import com.example.demo_3.resp.CommonResp;
import com.example.demo_3.service.EbookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class EbookController {
    @Resource
    private EbookService ebookService;

    @GetMapping("/ebook/list")
    public CommonResp list(){
        CommonResp<List<Ebook>> resp = new CommonResp<>();
        List<Ebook> list =  ebookService.list();
        resp.setContent(list);
        return resp;
    }
}
