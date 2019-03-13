package com.example.repertory.service.impl;

import com.example.repertory.dto.Repertory;
import com.example.repertory.dto.RepertoryDto;
import com.example.repertory.service.InitRepertoryRedisService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘海鹏
 * @iphone 13714872954
 * @date 2019/3/10下午6:57
 */
@Service
public class InitRepertoryRedisServiceImpl implements InitRepertoryRedisService{

    @Autowired
    private RedisTemplate<String, List<RepertoryDto>> redisTemplate;


    @Override
    public String initRepertoryRedis(List<RepertoryDto> list) {
        ValueOperations<String, List<RepertoryDto>> operations=redisTemplate.opsForValue();
        operations.set("repertory",list);
        return "success";
    }

    @Override
    public List<RepertoryDto> getRepertoryRedis() {
        ValueOperations<String, List<RepertoryDto>> operations=redisTemplate.opsForValue();
        List<RepertoryDto> list=operations.get("repertory");

        return list;
    }

    @Override
    public String setRepertoryRedis(List<RepertoryDto> list) {
        ValueOperations<String, List<RepertoryDto>> operations=redisTemplate.opsForValue();
        operations.set("repertory",list);
        return "success";
    }
}
