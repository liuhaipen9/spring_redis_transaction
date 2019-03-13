package com.example.eureka_client.client;

import com.example.eureka_client.dto.RepertoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


/**
 * @author 刘海鹏
 * @iphone 13714872954
 * @date 2019/3/9下午1:58
 */
@FeignClient(name = "repertory")
public interface RepertoryClient {
    @GetMapping("testFeign")
    String testFeign();

    @GetMapping("getRepertoryRedis")
    List<RepertoryDto> getRepertoryRedis();

    @PostMapping("setRepertoryRedis")
    String setRepertoryRedis(@RequestBody List<RepertoryDto> repertoryDtos);
}
