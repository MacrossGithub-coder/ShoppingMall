package org.macross.shopping_mall.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang3.StringUtils;
import org.macross.shopping_mall.utils.JWTUtils;
import org.macross.shopping_mall.utils.JsonData;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            String accessToken = request.getHeader("token");
            if (accessToken == null){
                accessToken = request.getParameter("token");
            }
            if (StringUtils.isNotBlank(accessToken)){

                Claims claims = JWTUtils.checkJWT(accessToken);
                if (claims != null){
                    Integer id = (Integer) claims.get("id");
                    String name = (String) claims.get("name");

                    request.setAttribute("user_id",id);
                    request.setAttribute("name",name);
                    return true;
                }
                sendJsonMessage(response, JsonData.buildError(-5,"登录过期，请重新登录！"));
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        sendJsonMessage(response, JsonData.buildError(-5,"登录过期，请重新登录！"));
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    public static  void sendJsonMessage(HttpServletResponse response,Object object){

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            response.setContentType("application/json;charset=utf-8");

            PrintWriter writer = response.getWriter();
            writer.print(objectMapper.writeValueAsString(object));
            writer.close();
            response.flushBuffer();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
