package com.example.repertory.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author 刘海鹏
 * @iphone 13714872954
 * @date 2019/3/10下午6:58
 */
@Setter
@Getter
public class RepertoryDto {
    //private static final long serialVersionUID = -1087718184172681211L;

    private int id;
    private String productName;
    private double price;
    private int number;


}
