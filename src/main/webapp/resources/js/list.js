var ROOT = "/logger";

var Logger = {
	URL: {
		getName: function() {
			return ROOT + "/system/get/name";
		},
		getAdd: function() {
			return ROOT + "/system/user/addpanel";
		},
		userDelete: function() {
			return ROOT + "/system/user/delete";
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
				$('.modal-body').html(result);
				$(document).on('click', '#save', save);
				$(document).on('click', '#cancel', close);
				$('#myModal').modal();
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
			            	if(data.rc == 0) {
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

function save() {
	bootbox.alert('没写');
}

function close() {
	$('.modal-body').children('div').remove();
};