package org.demo.logger.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.demo.logger.annotation.SysLog;
import org.demo.logger.dao.SystemLogDao;
import org.demo.logger.entity.SystemLog;
import org.demo.logger.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SysLogAop {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private SystemLogDao systemLogDao;
	
	@Resource
	private UserService userService;

	@Pointcut("@annotation(org.demo.logger.annotation.SysLog)")
	public void LogAspect() {
	}

	/**
	 * 操作异常记录
	 * @throws Exception 
	 * 
	 */
	@AfterThrowing(pointcut = "LogAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint point, Throwable e) throws Exception {
		SystemLog systemLog = new SystemLog();
		Map<String, Object> map = this.getMethodDescription(point);
		systemLog.setModule(map.get("module").toString());
		systemLog.setMethod("<font color=\"red\">执行方法异常:-->" + map.get("methods").toString() + "</font>");
		systemLog.setDesc("<font color=\"red\">执行方法异常:-->" + e + "</font>");
		systemLog.setArgs("");
		systemLog.setId(1065);
		systemLog.setUserNickname("dongbow");
		systemLog.setCreateTime("2016-10-21 11:50:22");
		systemLogDao.insert(systemLog);
	}

	@Around("LogAspect()")
	public Object doAround(ProceedingJoinPoint point) {
		Object result = null;
		try {
			SystemLog systemLog = new SystemLog();
			Map<String, Object> map = this.getMethodDescription(point);
			systemLog.setModule(map.get("module").toString());
			systemLog.setMethod(map.get("methods").toString());
			systemLog.setDesc(map.get("description").toString());
			systemLog.setArgs("");
			systemLog.setId(1065);
			systemLog.setUserNickname("dongbow");
			systemLog.setCreateTime("2016-10-21 11:50:22");
			systemLogDao.insert(systemLog);
			result = point.proceed();
		} catch (Exception e) {
			logger.error("异常信息:{}", e.getMessage());
		} catch (Throwable e) {
			logger.error("异常信息:{}", e.getMessage());
		}
		return result;
	}

	/**
	 * 获取注解中对方法的描述信息
	 * 
	 * @return 方法描述
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public Map<String, Object> getMethodDescription(JoinPoint joinPoint) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class<?> targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					map.put("module", method.getAnnotation(SysLog.class).module());
					map.put("methods", method.getAnnotation(SysLog.class).methods());
					String de = method.getAnnotation(SysLog.class).description();
					if (StringUtils.isEmpty(de))
						de = "执行成功!";
					map.put("description", de);
					break;
				}
			}
		}
		return map;
	}
}