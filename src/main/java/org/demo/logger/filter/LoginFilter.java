package org.demo.logger.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.demo.logger.constant.LoggerConstant;
import org.demo.logger.result.Result;
import org.demo.logger.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class LoginFilter implements Filter {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void destroy() {
		logger.info("filter destory...");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		if (!(req instanceof HttpServletRequest) || !(resp instanceof HttpServletResponse)) {  
            throw new ServletException("OncePerRequestFilter just supports HTTP requests");  
        }  
        HttpServletRequest request = (HttpServletRequest) req;  
        HttpServletResponse response = (HttpServletResponse) resp;
        String cookie = CookieUtils.getCookieValue(CookieUtils.LOGGER_COOKIE, request);
        if(StringUtils.isNotBlank(cookie)) {
        	return;
        }
        StringBuffer url = request.getRequestURL();
        if(url.indexOf(LoggerConstant.FILTER) > 0) {
        	this.result(request, response);
        	return;
        }
		chain.doFilter(request, response);
		return;
	}

	private void result(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		if(isAjax(request)) {
			Result result = new Result(LoggerConstant.NOTLOGIN, "尚未登录", LoggerConstant.ROOT + LoggerConstant.LOGIN);
			response.setCharacterEncoding("UTF-8");  
		    response.setContentType("application/json; charset=utf-8");  
		    PrintWriter out = null;  
		    try {  
		        out = response.getWriter();  
		        out.append(JSON.toJSONString(result));  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    } finally {  
		        if (out != null) {  
		            out.close();  
		        }  
		    }  
		} else {
			response.sendRedirect(LoggerConstant.ROOT + LoggerConstant.LOGIN);
		}
	}
	
	private boolean isAjax(HttpServletRequest request) {
		String requestType = request.getHeader("X-Requested-With");  
		return requestType != null && requestType.equals("XMLHttpRequest");  
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
		logger.info("filter init...");
	}

}
