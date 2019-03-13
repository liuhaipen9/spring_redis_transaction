package com.example.eureka_client.controllers;

import com.example.eureka_client.client.OrderClient;
import com.example.eureka_client.client.RepertoryClient;
import com.example.eureka_client.dto.OrderDto;
import com.example.eureka_client.dto.RepertoryDto;
import com.example.eureka_client.dto.TranscationGroupDto;
import com.example.eureka_client.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author 刘海鹏
 * @iphone 13714872954
 * @date 2019/3/9下午1:22
 */
@RestController
public class TestController {
    @Autowired
    private RepertoryClient repertoryClient;
    @Autowired
    private OrderClient orderClient;
    @Autowired
    private OrderService orderService;
    @Autowired
    private StringRedisTemplate template;


    @RequestMapping(value = "/msg",method = RequestMethod.GET)
    public String msg(){
        return "this is msg!";
    }

    @GetMapping("/testRepertory")
    public String testRepertory(){
        return repertoryClient.testFeign();
    }

    @GetMapping("/put")
    public String redisSet() throws Exception{
        try{
            return defaultTx();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

    }

    @Transactional
    public String redisService(){
        int i = (int)(Math.random() * 100);
        template.opsForValue().set("key"+i, "value"+i, 300, TimeUnit.SECONDS);
        int res=5/0;
        return "success "+"key"+i;

    }

    public String defaultTx() throws Exception{
        template.multi();
            template.opsForValue().set("k1","v1");
        template.opsForValue().set("k2","v2");
        template.opsForValue().set("k3","v3");
        template.opsForValue().set("k4","海鹏");
        //int i=5/0;
        template.opsForValue().set("k5","v5");
        /*if (0==0) {
            throw new Exception("redis异常了");
        }*/
        template.opsForValue().set("k6","v6");

        template.exec();
        return "success:"+template.opsForValue().get("k4");
    }





    final static BlockingQueue<String> queue=new LinkedBlockingDeque<String>();
    @GetMapping("/isTranscationRes/{res}")
    public String getTxRes(@PathVariable(name="res") String res){

        synchronized (queue) {
            try {
                if (res .equalsIgnoreCase("start")) {
                    queue.offer(res);
                    queue.wait();
                }else if (!res .equalsIgnoreCase("end")&&!res .equalsIgnoreCase("start")) {
                    queue.offer(res);
                    queue.wait();
                } else {
                    queue.offer(res);
                    queue.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i=0;i<queue.size();i++){
            System.out.println("队列里拿到值是:"+queue.poll());
        }
        return "commit";
    }


    /**
     *
     * 定义一个方法用来汇总各个应用的事务
     * @param transcationGroupDto
     * @return: java.lang.String
     * @Author: haipeng.liu@hand-china.com
     * @method  isTranscationRes
     * @Date: 2019/3/10 下午6:51
     */

    final static BlockingQueue<TranscationGroupDto> txQueue=new LinkedBlockingDeque<TranscationGroupDto>();
    @PostMapping("/commitTranscation")
    public String isTranscationRes(@RequestBody TranscationGroupDto transcationGroupDto){

        synchronized (txQueue) {
            try {
                if (transcationGroupDto.index .equalsIgnoreCase("start")) {
                    txQueue.offer(transcationGroupDto);
                    txQueue.wait();
                }else if (!transcationGroupDto.index  .equalsIgnoreCase("end")&&!transcationGroupDto.index .equalsIgnoreCase("start")) {
                    txQueue.offer(transcationGroupDto);
                    txQueue.wait();
                } else {
                    txQueue.offer(transcationGroupDto);
                    txQueue.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (int i=0;i<txQueue.size();i++){
            if (txQueue.poll().getStatus().equals("rollback"))
            {
                txQueue.clear();
               return "rollback";
            }
            System.out.println("队列里拿到值是:"+txQueue.poll().index);
        }
        return "commit";
    }

    //测试自己能不能调通
    @GetMapping("/testOrderClient/{res}")
    public String testOrderClient(@PathVariable(name="res") String res){
        return orderClient.isTranscationRes(res);
    }
    /**
     *
     * 初始化redis
     * @return: java.lang.String
     * @Author: haipeng.liu@hand-china.com
     * @method  initRedis
     * @Date: 2019/3/10 下午6:52
     */
    @GetMapping("/testGetAllRepertoryRedis")
    public List<RepertoryDto> initRedis(){
        return orderService.getAllRepertory();
    }

    //下单，调用订单系统扣库存，库存不够不让扣，返回提示信息
    @PostMapping("/placeAnOrder")
    public String placeAnOrder(@RequestBody OrderDto orderDto){

        return orderService.placeAnOrder(orderDto);
    }

    }

