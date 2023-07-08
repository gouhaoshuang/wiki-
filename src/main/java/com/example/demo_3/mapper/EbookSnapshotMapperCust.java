package com.example.demo_3.mapper;

//import com.example.demo_3.resp.StatisticResp;

import com.example.demo_3.resp.StatisticResp;

import java.util.List;

public interface EbookSnapshotMapperCust {

    public void genSnapshot();

    List<StatisticResp> getStatistic();

//    List<StatisticResp> get30Statistic();
}
