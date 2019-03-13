package com.example.eureka_client.dto;



import lombok.Getter;
import lombok.Setter;
import org.springframework.core.serializer.Deserializer;

import java.io.Serializable;

/**
 * @author 刘海鹏
 * @iphone 13714872954
 * @date 2019/3/10下午9:09
 */
@Getter
@Setter
public class RepertoryDto{
    private int id;
    private String productName;
    private double price;
    private int number;
}
