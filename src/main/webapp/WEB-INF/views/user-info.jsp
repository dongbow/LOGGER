<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form id="registerform" class="form-horizontal text-center" role="form">
	<div style="margin-bottom: 25px" class="input-group col-sm-12 controls">
		<input id="name" class="form-control" name="username" value="" placeholder="输入用户名" type="text">
	</div>
	<div style="margin-bottom: 25px" class="input-group col-sm-12">
		<input id="password" class="form-control" name="password" placeholder="输入密码" type="password">
	</div>
	<div style="margin-bottom: 25px" class="input-group col-sm-12">
		<input id="nickname" class="form-control" name="nickname" placeholder="输入昵称" type="text">
	</div>
	<div style="margin-top: 10px" class="form-group">
		<div class="col-sm-12 controls">
			<a id="save" type="button" class="btn btn-primary">提交</a> 
			<a id="cancel" type="button" class="btn btn-primary">取消</a>
		</div>
	</div>
</form>
