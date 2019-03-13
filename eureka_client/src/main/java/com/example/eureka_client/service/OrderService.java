package com.example.eureka_client.service;

import com.example.eureka_client.dto.OrderDto;
import com.example.eureka_client.dto.RepertoryDto;

import java.util.List;

/**
 * @author 刘海鹏
 * @iphone 13714872954
 * @date 2019/3/10下午6:54
 */
public interface OrderService {
   List<RepertoryDto> getAllRepertory();

    //下单
    String placeAnOrder(OrderDto orderDto);


    String setOrder(StringBuffer sb, OrderDto orderDto,boolean isPlaceAnOrder);
}
