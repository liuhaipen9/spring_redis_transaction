package com.example.eureka_client.service.impl;





import com.example.eureka_client.annotion.DefaultTranscantional;
import com.example.eureka_client.client.RepertoryClient;
import com.example.eureka_client.dto.OrderDto;
import com.example.eureka_client.dto.ProductRecord;
import com.example.eureka_client.dto.RepertoryDto;
import com.example.eureka_client.service.OrderService;

import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 刘海鹏
 * @iphone 13714872954
 * @date 2019/3/10下午6:54
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RepertoryClient repertoryClient;
    @Autowired
    private RedisTemplate<String,OrderDto> redisTemplate;
    @Override
    public List<RepertoryDto> getAllRepertory() {
//        String str=repertoryClient.getRepertoryRedis();
//
//        List<RepertoryDto> repertoryDtos=JSONArray.toList(JSONArray.fromObject(str),RepertoryDto.class);

        return repertoryClient.getRepertoryRedis();
    }

    @Override
    public String placeAnOrder(OrderDto orderDto) {
        List<RepertoryDto> repertoryDtos=repertoryClient.getRepertoryRedis();
        List<ProductRecord> records=orderDto.getProductRecords();
        StringBuffer sb=new StringBuffer();
        Double totalPrice=0.0;
        boolean isPlaceAnOrder=false;
        for (ProductRecord productRecord:records){
            for (RepertoryDto repertoryDto:repertoryDtos){
                if (productRecord.getProductId()==repertoryDto.getId()){
                    int surplus=repertoryDto.getNumber()-productRecord.getNum();
                    if (surplus<0){
                        sb.append(repertoryDto.getProductName()+"库存不够，还有"+repertoryDto.getNumber()+"件商品|");
                        continue;
                    }
                    isPlaceAnOrder=true;
                    repertoryDto.setNumber(surplus);
                    totalPrice+=(repertoryDto.getPrice()*productRecord.getNum());
                }
            }
        }
        orderDto.setTotalPrice(totalPrice);






        Runnable runnable=()->{repertoryClient.setRepertoryRedis(repertoryDtos);};

        Thread thread=new Thread(runnable);

        thread.start();

        try {
            thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setOrder(sb,orderDto,isPlaceAnOrder);
       /* try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

//        try {
//            thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        return sb.toString();
    }

    @Override
    public String setOrder(StringBuffer sb, OrderDto orderDto,boolean isPlaceAnOrder){
        redisTemplate.multi();
        ValueOperations<String, OrderDto> operations=redisTemplate.opsForValue();
        operations.set("order_"+orderDto.getUsername(),orderDto);

        String result=isPlaceAnOrder?sb.append("\n     下单成功").toString():sb.append("\n     下单失败").toString();
        redisTemplate.exec();
        return result;
    }
}
