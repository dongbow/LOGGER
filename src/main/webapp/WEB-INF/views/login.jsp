<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="common/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>login</title>
	<%@include file="common/head.jsp" %>
</head>
<body>
	<div class="container">    
        <div id="loginbox" style="margin-top:100px;" class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">                    
            <div class="panel panel-primary">
                <div class="panel-heading">
                    <div class="panel-title">用户登录</div>
                </div>     
                <div style="padding-top:30px" class="panel-body">
                    <form id="loginform" class="form-horizontal" role="form">
                        <div style="margin-bottom: 25px" class="input-group">
                           <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                           <input id="name" class="form-control" name="username" value="" placeholder="输入用户名" type="text">                                        
                       </div>
                        <div style="margin-bottom: 25px" class="input-group">
                            <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                            <input id="password" class="form-control" name="password" placeholder="输入密码" type="password">
                        </div>
                        <div style="margin-top:10px" class="form-group">
                            <div class="col-sm-12 controls">
                            	<a id="login" type="button" class="btn btn-primary">登录</a>
                            </div>
                        </div>
                    </form>
                </div>  
        	</div>
         </div> 
         <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                <h4 class="modal-title" id="myModalLabel">系统提示</h4>
		            </div>
		            <div class="modal-body"></div>
		        </div>
		    </div>
		</div>
    </div>
</body>
<script type="text/javascript" src="http://cdn.bootcss.com/jquery/3.1.1/jquery.min.js"></script>
<script type="text/javascript" src="http://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${path}/resources/js/jquery.md5.js"></script>
<script type="text/javascript" src="${path}/resources/js/login.js"></script>
</html>