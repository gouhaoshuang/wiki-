package com.example.demo_3.controller;

import com.example.demo_3.req.DocQueryReq;
import com.example.demo_3.req.DocSaveReq;
import com.example.demo_3.resp.DocQueryResp;
import com.example.demo_3.resp.CommonResp;
import com.example.demo_3.resp.PageResp;
import com.example.demo_3.service.DocService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
public class DocController {
    @Resource
    private DocService docService;

    @GetMapping("/doc/list")
    public CommonResp list(@Valid  DocQueryReq req){
        CommonResp<PageResp<DocQueryResp>> resp = new CommonResp<>();

        PageResp<DocQueryResp> list =  docService.list(req);
        resp.setContent(list);
        return resp;
    }
    @GetMapping("/doc/all")
    public CommonResp all(){
        CommonResp<List<DocQueryResp>> resp = new CommonResp<>();
        List<DocQueryResp> list =  docService.all();
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/doc/save")
    public CommonResp save(@Valid @RequestBody DocSaveReq req){
        CommonResp resp = new CommonResp<>();
        docService.save(req);
        return resp;
    }

    @DeleteMapping("/doc/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        docService.delete(id);
        return resp;
    }
}
