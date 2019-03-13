package com.example.eureka_client.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author 刘海鹏
 * @iphone 13714872954
 * @date 2019/3/10下午9:09
 */
@Getter
@Setter
public class OrderDto implements Serializable {
    private int id;
    private String username;
    private Double totalPrice;
    List<ProductRecord> productRecords;
}
