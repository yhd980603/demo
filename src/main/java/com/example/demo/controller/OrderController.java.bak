package com.example.demo.controller;

import com.example.demo.dto.VideoOrderDto;
import com.example.demo.service.VideoOrderService;
import com.example.demo.utils.IpUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 订单接口
 */
@RestController
//@RequestMapping("/user/api/v1/order")//登录
@RequestMapping("/api/v1/order")//支付
public class OrderController {

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    private Logger dataLogger=LoggerFactory.getLogger("dataLogger");

    @Autowired
    private VideoOrderService videoOrderService;

    //模拟登录
//    @GetMapping("/add")
//    public JsonData saveOrder(){
//        return JsonData.buildSuccess("下单成功");
//    }

    @GetMapping("/add")
    public void saveOrder(@RequestParam(value = "video_id",required = true)int videoId, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String ip= IpUtils.getIpAddr(request);
//        int userId= (int) request.getAttribute("user_id");
        int userId = 1;    //临时写死的配置
        String ip = "120.25.1.43";
        VideoOrderDto videoOrderDto = new VideoOrderDto();
        videoOrderDto.setUserId(userId);
        videoOrderDto.setVideoId(videoId);
        videoOrderDto.setIp(ip);
        String codeUrl = videoOrderService.save(videoOrderDto);
        if(codeUrl == null) {
            throw new  NullPointerException();
        }
        try{
            //生成二维码配置
            Map<EncodeHintType,Object> hints =  new HashMap<>();
            //设置纠错等级
            hints.put(EncodeHintType.ERROR_CORRECTION,ErrorCorrectionLevel.L);
            //编码类型
            hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
            BitMatrix bitMatrix = new MultiFormatWriter().encode(codeUrl,BarcodeFormat.QR_CODE,400,400,hints);
            OutputStream out =  response.getOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix,"png",out);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
