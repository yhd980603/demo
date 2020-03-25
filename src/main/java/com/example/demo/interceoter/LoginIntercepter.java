package com.example.demo.interceoter;

import com.example.demo.model.JsonData;
import com.example.demo.utils.JwtUtils;
import com.google.gson.Gson;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginIntercepter implements HandlerInterceptor {

    private static final Gson gson=new Gson();

    /**
     * 进入controller之前进行拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token=request.getHeader("token");
        if (token==null){
            token=request.getParameter("token");
        }
        if (token!=null){
            Claims claims =JwtUtils.checkJWT(token);
            if (claims!=null){
                Integer userId=(Integer)claims.get("id");
                String name=(String)claims.get("name");
                request.setAttribute("user_id",userId);
                request.setAttribute("name",name);
                return true;
            }
        }
        sendJsonMessage(response, JsonData.buildError("请登录"));
        return false;
    }

    /**
     * 相应数据给前端
     * @param response
     * @param obj
     */
    public static void sendJsonMessage(HttpServletResponse response,Object obj) throws IOException {
        response.setContentType("application/json; charset=utf-8");//返回的是json格式的所有请求头换掉
        PrintWriter writer =response.getWriter();
        writer.print(gson.toJson(obj));
        writer.close();
        response.flushBuffer();
    }
}