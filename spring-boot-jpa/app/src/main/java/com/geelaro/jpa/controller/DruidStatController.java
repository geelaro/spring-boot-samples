package com.geelaro.jpa.controller;

import com.alibaba.druid.stat.DruidStatManagerFacade;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/druid/stat")
public class DruidStatController {

    @GetMapping("/druid/stat")
    public Object druidStat() {
        // DruidStatManagerFacade#getDataSourceStatDataList 该方法可以获取所有数据源的监控数据
        return DruidStatManagerFacade.getInstance().getDataSourceStatDataList();
    }
}
