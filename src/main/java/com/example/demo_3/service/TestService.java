package com.example.demo_3.service;

import com.example.demo_3.domain.Test;
import com.example.demo_3.mapper.TestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TestService {
//    jdk自带的注解：@Resource
//    spring自带的注解：@Autowired

    @Resource
    private TestMapper testMapper;

    public List<Test> list(){
        return testMapper.list();
    }

}
