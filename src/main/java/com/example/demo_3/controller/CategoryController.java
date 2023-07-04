package com.example.demo_3.controller;

import com.example.demo_3.req.CategoryQueryReq;
import com.example.demo_3.req.CategorySaveReq;
import com.example.demo_3.resp.CommonResp;
import com.example.demo_3.resp.CategoryQueryResp;
import com.example.demo_3.resp.PageResp;
import com.example.demo_3.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
public class CategoryController {
    @Resource
    private CategoryService categoryService;

    @GetMapping("/category/list")
    public CommonResp list(@Valid  CategoryQueryReq req){
        CommonResp<PageResp<CategoryQueryResp>> resp = new CommonResp<>();

        PageResp<CategoryQueryResp> list =  categoryService.list(req);
        resp.setContent(list);
        return resp;
    }
    @PostMapping("/category/save")
    public CommonResp save(@Valid @RequestBody CategorySaveReq req){
        CommonResp resp = new CommonResp<>();
        categoryService.save(req);
        return resp;
    }

    @DeleteMapping("/category/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        categoryService.delete(id);
        return resp;
    }
}
