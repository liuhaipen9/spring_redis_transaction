package com.example.repertory.controllers;

import com.example.repertory.client.OrderClient;
import com.example.repertory.dto.RepertoryDto;
import com.example.repertory.service.InitRepertoryRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 刘海鹏
 * @iphone 13714872954
 * @date 2019/3/9下午1:49
 */
@RestController
public class TestController {

    @Autowired
    private OrderClient orderClient;
    @Autowired
    private InitRepertoryRedisService initRepertoryRedisService;

    @GetMapping("/getMsg")
    public String getMsg() {
        return orderClient.orderMsg();
    }

    @GetMapping("/testFeign")
    public String testFeign() {
        return "this is testFeign!";
    }


    /**
     *
     * 手动初始化库存
     * @param repertoryDtoList
     * @return: java.lang.String
     * @Author: haipeng.liu@hand-china.com
     * @method  initRepertoryRedis
     * @Date: 2019/3/10 下午9:06
     */
    @PostMapping("/initRepertoryRedis")
    public String initRepertoryRedis(@RequestBody List<RepertoryDto> repertoryDtoList) {

        return initRepertoryRedisService.initRepertoryRedis(repertoryDtoList);

    }

    /**
     *
     * 取出redis数据库
     * @return: java.lang.String
     * @Author: haipeng.liu@hand-china.com
     * @method  initRepertoryRedis
     * @Date: 2019/3/10 下午9:06
     */
    @GetMapping("/getRepertoryRedis")
    public List<RepertoryDto> getRedis() {
        return initRepertoryRedisService.getRepertoryRedis();
    }

    /**
     *
     * 设置redis的数量
     * @param repertoryDtoList
     * @return: java.lang.String
     * @Author: haipeng.liu@hand-china.com
     * @method  initRepertoryRedis
     * @Date: 2019/3/10 下午9:06
     */
    @PostMapping("/setRepertoryRedis")
    public String setRepertoryRedis(@RequestBody List<RepertoryDto> repertoryDtoList) {
        return initRepertoryRedisService.setRepertoryRedis(repertoryDtoList);

    }

}
