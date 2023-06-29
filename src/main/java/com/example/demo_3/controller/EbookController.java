package com.example.demo_3.controller;

import com.example.demo_3.domain.Ebook;
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
    public List<Ebook> list(){
        return ebookService.list();
    }
}
