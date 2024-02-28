var accountOrgchart;
var managerOrgchart;

$(document).ready(function() {
	$('#accountDirectoryCardBody').hide();
	$('#accountOrgchartCardBody').hide();
	$('#managerOrgchartCardBody').hide();

    var initAccountData = $("#initAccountOrgchart").val();
    var initManagerData = $("#initManagerOrgchart").val();

    accountOrgchart = $('#accountChart').orgchart({
         'data' : JSON.parse(initAccountData),
         'nodeContent': 'title',
         'verticalLevel':5,
         'visibleLevel' :5,
         'initCompleted': function ($chart) {
                 var $container = $('#accountChart');
                 $container.scrollLeft(($container[0].scrollWidth - $container.width())/2);
               }
    });

    managerOrgchart = $('#managerChart').orgchart({
             'data' : JSON.parse(initManagerData),
             'nodeContent': 'title',
             'verticalLevel':3,
             'visibleLevel' :5,
             'initCompleted': function ($chart) {
                     var $container = $('#managerChart');
                     $container.scrollLeft(($container[0].scrollWidth - $container.width())/2);
                   }
    });
});

function doAccountDirectory(obj) {
    var accountDirectoryName = $("#accountDirectoryName option:selected").val();
	if (accountDirectoryName == '') {
		alertModal("Orgchart Admin:Please provide account name to search.");
		return false;
	} else {
		$(obj).prop('disabled', 'disabled');
		$('#accountDirectoryCardBody').hide();
		$("#accountDirectoryTableBody").empty();
		var forAccount = $('#portletNamespace').val() + 'account';
		var postData = {
			[forAccount]: accountDirectoryName
		}
		var url = $('#getAccountDirectoryUrl').val();
		AUI().use('aui-io-request', function(A) {
			A.io.request(url, {
				method: 'post',
				data: postData,
				on: {
					success: function() {
						var response = JSON.parse(this.get('responseData'));
						if (response.data.length > 0) {
							var tableRow = "";
							for (i = 0; i < response.data.length; i++) {
								tableRow += '<tr><td>' + response.data[i].ecode + '</td><td>' + response.data[i].name + '</td><td>' + response.data[i].email + '</td><td>' + response.data[i].designation + '</td><td>' + response.data[i].manager + '</td><td>' + response.data[i].reviewer + '</td></tr>';
							}
							$("#accountDirectoryTableBody").append(tableRow);
							$('#totalEmployees').val(response.data.length);
							$("#accountDirectoryCardBody").show();
							$(obj).prop('disabled', false);
						} else {
							alertModal("Orgchart Admin:No employee were found for selected account.");
							$(obj).prop('disabled', false);
						}
					}
				}
			});
		});
	}
}

function doAccountOrgchart(obj) {
    var accountOrgchartName = $("#accountOrgchartName option:selected").val();
	if (accountOrgchartName == '') {
		alertModal("Orgchart Admin:Please provide account name to search.");
		return false;
	} else {
		$(obj).prop('disabled', 'disabled');
		$('#accountOrgchartCardBody').hide();
		var forAccount = $('#portletNamespace').val() + 'account';
		var postData = {
			[forAccount]: accountOrgchartName
		}
		var url = $('#getAccountOrgchartUrl').val();
		AUI().use('aui-io-request', function(A) {
			A.io.request(url, {
				method: 'post',
				data: postData,
				on: {
					success: function() {
						var response = JSON.parse(this.get('responseData'));
						var accountOrgchartData = response.data;
						accountOrgchart.init({
							"data": accountOrgchartData
						});
						$("#accountOrgchartCardBody").show();
						$(obj).prop('disabled', false);
					}
				}
			});
		});
	}
}

function doManagerOrgchart(obj) {
    var managerOrgchartEcode = $("#managerOrgchartEcode option:selected").val();
	if (managerOrgchartEcode == '') {
		alertModal("Orgchart Admin:Please provide manager name to search.");
		return false;
	} else {
		$(obj).prop('disabled', 'disabled');
		$('#managerOrgchartCardBody').hide();
		var forManager = $('#portletNamespace').val() + 'managerEcode';
		var postData = {
			[forManager]: managerOrgchartEcode
		}
		var url = $('#getManagerOrgchartUrl').val();
		AUI().use('aui-io-request', function(A) {
			A.io.request(url, {
				method: 'post',
				data: postData,
				on: {
					success: function() {
						var response = JSON.parse(this.get('responseData'));
						var managerOrgchartData = response.data;
						managerOrgchart.init({
							"data": managerOrgchartData
						});
						$("#managerOrgchartCardBody").show();
						$(obj).prop('disabled', false);
					}
				}
			});
		});
	}
}