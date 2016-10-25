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
        <div class="panel panel-primary" style="margin-top:100px;">
            <div class="panel-heading text-center">
               <div class="panel-heading">
                    <div class="panel-title"><h4>用户列表</h4></div>
                    <div style="float:right; font-size: 100%; position: relative; top:-10px">
                    	<span class="login" style="margin-right:10px;"></span>
                    	<a href="${path}/account/logout" style="color:#fff;">退出</a>
                    </div>
                </div> 
            </div>
            <div class="panel-body">
            	<div class="row">
	            	<div class="panel">
	            		<div class="panel-body">
	            			<div class="col-sm-12 controls">
	            				<a id="add" type="button" class="btn btn-primary">添加</a>
	                    		<a id="delete" type="button" class="btn btn-danger">删除</a>
	                    	</div>
	                    </div>
	                </div>
            	</div>
                <table class="table table-center table-hover">
                    <thead>
                        <tr>
                            <th><input type="checkbox" id="selectAll"></th>
                            <th>用户名</th>
                            <th>用户昵称</th>
                            <th>状态</th>
                            <th>创建时间</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                            	<td><c:if test="${user.status eq 0}"><input name="uid" type="checkbox" value="${user.userId}"></c:if></td>
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
        <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
		    <div class="modal-dialog" style="height: auto;">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel"></h4>
		            </div>
		            <div class="modal-body"></div>
		        </div>
		    </div>
		</div>
    </div>
</body>
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootbox.js/4.4.0/bootbox.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/jquery.md5.js"></script>
<script type="text/javascript" src="${path}/resources/js/list.js"></script>
</html>