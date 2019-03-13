package com.example.repertory.service;

import com.example.repertory.dto.RepertoryDto;

import java.util.List;

/**
 * @author 刘海鹏
 * @iphone 13714872954
 * @date 2019/3/10下午6:56
 */
public interface InitRepertoryRedisService {
    //初始化库存信息
    String initRepertoryRedis(List<RepertoryDto> list);
    //获取所有的库存信息
    List<RepertoryDto> getRepertoryRedis();
    //下单，修改库存
    String setRepertoryRedis(List<RepertoryDto> list);
}
