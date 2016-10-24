var ROOT = "/logger";

var Logger = {
	URL: {
		login: function() {
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
	
	$(document).keydown(function(event){  
	   if(event.which == '13') {  
		   $('#login').click();
	   }  
	});
	
	$('#login').click(function(){
		if(Logger.validate($('#name').val(), $('#password').val())) {
			$.post(Logger.URL.login(), {
				'username': $('#name').val(),
				'password': $.md5($('#password').val())
			}, function(result) {
				if(result.rc != -1) {
					window.location = ROOT + result.data;
				} else {
					$('.modal-body').html(result.error);
					$('#myModal').modal();
				}
			});
		} else {
			$('.modal-body').html('用户名和密码不能为空');
			$('#myModal').modal();
		}
	});
});