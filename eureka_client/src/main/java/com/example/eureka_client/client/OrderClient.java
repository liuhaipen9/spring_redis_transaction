package com.example.eureka_client.client;

import com.example.eureka_client.dto.TranscationGroupDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 刘海鹏
 * @iphone 13714872954
 * @date 2019/3/10下午7:04
 */
@FeignClient(name = "order")
public interface OrderClient {

    @GetMapping("isTranscationRes/{res}")
    String isTranscationRes(@PathVariable(name="res") String res);

    @PostMapping("commitTranscation")
    String commitTranscation(@RequestBody TranscationGroupDto transcationGroupDto);
}
