<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>login</title>
	<%@include file="common/head.jsp" %>
</head>
<body>
	<div class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                <h2>用户登录</h2>
            </div>
            <div class="panel-body">
                <div class="form-group">
					<label for="name" class="col-sm-2 control-label">用户名</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="name" name="name" placeholder="请输入用户名">
					</div>
				</div>
				<div class="form-group">
					<label for="lastname" class="col-sm-2 control-label">密码</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="password" name="password" placeholder="请输入密码">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<a id="login" type="button" class="btn btn-success">登录</a>
					</div>
				</div>
            </div>
        </div>
		<div class="alert alert-warning">
		    <a href="#" class="close" data-dismiss="alert">&times;</a>
		</div>
    </div>
</body>
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/resources/js/login.js"></script>
</html>