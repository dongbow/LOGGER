var ROOT = "/logger";

var Logger = {
	URL: {
		getName: function() {
			return ROOT + "/system/get/name";
		},
		getAdd: function() {
			return ROOT + "/system/user/addpanel";
		},
		postAdd: function() {
			return ROOT + "/system/user/add/do";
		},
		nameExist: function() {
			return ROOT + "/system/user/exist";
		},
		userDelete: function() {
			return ROOT + "/system/user/delete";
		}
	},
	validate: function (name, password, nickname) {
	    if (name && password && nickname) {
	        return true;
	    } else {
	        return false;
	    }
	}
};

$(function(){
	
	$.ajax({
		url: Logger.URL.getName(),
		type: 'post',
		dataType: 'json',
		success: function(result) {
			if(result.rc == 0) {
				$('.login').html(result.data);
			}
		}
	});
	
	$('#add').click(function() {
		$.get(Logger.URL.getAdd(), function(result) {
			if(result.rc != undefined && result.rc == 9001){
				bootbox.alert(result.error, function() {
        			window.location = result.data;
        		});
			} else {
				$('.modal-title').html('添加用户');
				$('.modal-body').html(result);
				$('#myModal').modal();
				$('#registerform').on('blur', '#name', exist);
				$('#registerform').on('click', '#save', save);
				$('#registerform').on('click', '#cancel', close);
			}
		});
	});
	
	$('.close').bind('click', close);
	
	$('#selectAll').click(function() {
		if ($(this).is(':checked')) {
			$("input[name='uid']").each(function() {
				$(this).prop("checked", true);
			});
		} else {
			$("input[name='uid']").each(function() {
				$(this).prop("checked", false);
			});
		}
	});
	
	$('#delete').click(function(){
		var count = 0;
		var ids = new Array();
		$("input[name='uid']").each(function() {
			if ($(this).is(':checked')) {
				count++;
				ids.push($(this).val());
			}
		});
		if(parseInt(count) == 0){
		    bootbox.alert({
		        message: "至少选择一条数据",
		        size: 'small'
		    });
		} else {
			bootbox.confirm({
		        title: "系统提示",
		        message: "确定删除选中的" + count + "条数据?",
		        buttons: {
		            cancel: {
		                label: '取消'
		            },
		            confirm: {
		                label: '确定'
		            }
		        },
		        callback: function (result) {
		        	if(result == true) {
		        		$.post(Logger.URL.userDelete(), {
			            	"ids": ids
			            }, function(data) {
			            	if(data.rc == 9001) {
			            		bootbox.alert(result.error, function() {
			            			window.location = result.data;
			            		});
			            	} else if(data.rc == 0) {
			            		bootbox.alert(data.data, function() {
			            			window.location = window.location;
			            		});
			            	} else {
			            		bootbox.alert(data.error);
			            	}
			            });
		        	}
		        }
		    });
		}
	});
});

function close() {
	$('.modal-body').children('div').remove();
	$('#myModal').modal('hide');
};

function exist() {
	var name = $('#name').val();
	if(name) {
		$.post(Logger.URL.nameExist(), {
			"name": name,
		}, function(result) {
			if(result.rc == 9001) {
        		bootbox.alert(result.error, function() {
        			window.location = result.data;
        		});
        	} else if(result.rc == -1) {
        		bootbox.alert(result.error);
        	}
		}); 
	}
}

function save() {
	var name = $('#name').val();
	var password = $('#password').val();
	var nickname = $('#nickname').val();
	
	if(Logger.validate(name, password, nickname)) {
		$.post(Logger.URL.postAdd(), {
			"name": name,
			"password": $.md5(password),
			"nickname": nickname
		}, function(data) {
			if(data.rc == 9001) {
        		bootbox.alert(result.error, function() {
        			window.location = result.data;
        		});
        	} else if(data.rc == 0) {
        		$('#myModal').modal('hide');
        		bootbox.alert(data.data, function() {
        			window.location = window.location;
        		});
        	} else {
        		bootbox.alert(data.error);
        	}
		});
	} else {
		bootbox.alert("不能为空");
	}
}