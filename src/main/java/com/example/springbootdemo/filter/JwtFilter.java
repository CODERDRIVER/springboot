package com.example.springbootdemo.filter;

import com.example.springbootdemo.bean.Audience;
import com.example.springbootdemo.utils.JwtHelper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {

    @Autowired
    private Audience audience;

    /**
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     *
     *
     * iss(Issuser)：代表这个JWT的签发主体；
        sub(Subject)：代表这个JWT的主体，即它的所有人；
        aud(Audience)：代表这个JWT的接收对象；
        exp(Expiration time)：是一个时间戳，代表这个JWT的过期时间；
        nbf(Not Before)：是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的；
        iat(Issued at)：是一个时间戳，代表这个JWT的签发时间；
        jti(JWT ID)：是JWT的唯一标识。
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //转成http请求
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String authHeader = request.getHeader("authorization");
        if("OPTIONS".equals(request.getMethod()))
        {
            response.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(request,response);
        }else{
            if(authHeader==null||!authHeader.startsWith("jwt;"))
            {
                response.getWriter().write("请重写登录");
                return;
            }

            String token = authHeader.substring(4);

            if(audience==null){
                BeanFactory beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
                audience = (Audience)beanFactory.getBean("audience");
            }

            Claims claims = JwtHelper.parseJWT(token,audience.getBase64Secret());
            if(claims==null)
            {
                response.getWriter().write("请重新登录");
                return;
            }
            filterChain.doFilter(request,response);
        }

    }
}
