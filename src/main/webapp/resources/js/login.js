var ROOT = "/logger";

var Logger = {
	URL: {
		login : function(){
			return ROOT + "/account/login/do";
		}
	},
	
	validate: function (name, password) {
	    if (name && password) {
	        return true;
	    } else {
	        return false;
	    }
	}
};

$(function(){
	$('#login').click(function(){
		if(Logger.validate($('#name').val(), $('#password').val())) {
			$.post(Logger.URL.login(), {
				'username': $('#name').val(),
				'password': $('#password').val()
			}, function(result) {
				if(result.rc != -1) {
					window.location = result.data;
				} else {
					$('.alert').append(result.error);
					$('.alert').alert();
				}
			});
		} else {
			$('.alert').append('用户名和密码不能为空');
			$('.alert').alert();
		}
	});
});