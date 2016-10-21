<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>list</title>
	<%@include file="common/head.jsp" %>
</head>
<body>
	<div class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h2>用户列表</h2>
            </div>
            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th><input type="checkbox" id="all"></th>
                            <th>用户名</th>
                            <th>用户昵称</th>
                            <th>状态</th>
                            <th>创建时间</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                            	<td><c:if test="${user.status eq 0}"><input type="checkbox" value="${user.userId}"></c:if></td>
                                <td>${user.userName}</td>
                                <td>${user.nickname}</td>
                                <td>
                                	<c:if test="${user.status eq 0}">
                                		正常
                                	</c:if>
                                	<c:if test="${user.status eq 1}">
                                		锁定
                                	</c:if>
                                </td>
                                <td>
                                    <fmt:formatDate value="${user.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/list.js"></script>
</html>