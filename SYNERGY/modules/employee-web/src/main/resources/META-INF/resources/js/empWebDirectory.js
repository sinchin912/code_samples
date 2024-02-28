function doSearchUser(obj) {
	var employeeDirectorySearchResourceUrl = $('#employeeDirectorySearchResourceUrl').val();

	if ($("#name").val() == '' && $("#ecode").val() == '') {
	    alertModal('Employee Search:Please provide either name or ecode to search.');
		return false;
	} else if ($("#name").val() != '' && $("#name").val().length < 3) {
	    alertModal('Employee Search:Please provide at least 3 characters for searching by name.');
		return false;
	} else {
		$(obj).prop('disabled', 'disabled');
		$('#employeeTable').hide();
		$("#employeeTableBody").empty();
		var portal = $('#portletNamespace').val();
		var ecodeKey = portal + 'ecode';
		var nameKey = portal + 'name';
		var postData = {
			[nameKey]: $("#name").val(),
			[ecodeKey]: $("#ecode").val()
		};

		AUI().use('aui-io-request', function(A) {
			A.io.request(employeeDirectorySearchResourceUrl, {
				method: 'post',
				data: postData,
				on: {
					success: function() {
						var employeeData = JSON.parse(this.get('responseData'));
						if (employeeData != null && employeeData.data.length > 0) {
							var tableRow = "";
							for (i = 0; i < employeeData.data.length; i++) {
								tableRow += '<tr><td>' + employeeData.data[i].ecode + '</td><td>' + employeeData.data[i].name + '</td><td>' + employeeData.data[i].email + '</td><td>' + employeeData.data[i].skype + '</td><td>' + employeeData.data[i].account + '</td><td>' + employeeData.data[i].managerName + '</td><td>' + employeeData.data[i].designation + '</td></tr>';
							}
							$("#employeeTableBody").append(tableRow);
							$("#employeeTable").show();
							$(obj).prop('disabled', false);
						} else {
						    alertModal('Employee Search:No employee found with given search values.');
							$(obj).prop('disabled', false);
						}
					}
				}
			});
		});
	}
}