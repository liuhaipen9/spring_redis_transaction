package com.example.repertory.client;

import com.example.repertory.dto.TranscationGroupDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author 刘海鹏
 * @iphone 13714872954
 * @date 2019/3/9下午1:46
 */
@FeignClient(name = "order")
public interface OrderClient {
    @GetMapping("/msg")
    String orderMsg();
    @PostMapping("commitTranscation")
    String commitTranscation(@RequestBody TranscationGroupDto transcationGroupDto);

}
