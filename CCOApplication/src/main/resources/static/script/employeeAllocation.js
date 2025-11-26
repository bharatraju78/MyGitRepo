
$(function() {
	$("#empAllForm").validate({
		rules: {
			name: {
				required: true,
				minlength: 2
			},
			userName: {
				required: true,
			},
			roleName: {
				required: true,
			},
			startDate: {
				required: true
			},
			endDate: {
				required: true,
				greaterThan: '#startDate'
			}

		},
		messages: {
			name: {
				required: "Please enter a project name",
			},
			userName: {
				required: "Please Select a User Name",
			},
			roleName: {
				required: "Please Select a Role Name",
			},
			startDate: {
				required: "Please enter a start date"
			},
			endDate: {
				required: "Please enter a end date"
			}
		},
		errorElement: "em",
		errorPlacement: function(error, element) {
			// Add the `invalid-feedback` class to the error element
			error.addClass("invalid-feedback");

			if (element.prop("type") === "checkbox") {
				error.insertAfter(element.next("label"));
			} else {
				error.insertAfter(element);
			}
		},
		highlight: function(element, errorClass, validClass) {
			$(element).addClass("is-invalid").removeClass("is-valid");
		},
		unhighlight: function(element, errorClass, validClass) {
			$(element).addClass("is-valid").removeClass("is-invalid");
		}
	});
	
	$('#addEmployeeForm').validate({
		rules: {
            employeeName: {
                required: true,
            },
            accountName: {
                required: true,
            },
            projectName: {
                required: true,
            },
            projectRoleName: {
                required: true,
            },
            verticalName: {
                required: true
            },
			startDate: {
				required: true
			},
			endDate: {
				required: true
				//greaterThan: '#startDate'
			}
        },
        messages: {
            employeeName: {
                required: "Please enter a employee name",
            },
            accountName: {
                required: "Please Select an Account Name",
            },
            projectName: {
                required: "Please Select a Project Name",
            },
            projectRoleName: {
                required: "Please Select a Project Role Name",
            },
            verticalName: {
                required: "Please Select a Vertical Name"
            },
			startDate: {
				required: "Please enter a start date"
			},
			endDate: {
				required: "Please enter a end date"
			}		
        },
        errorElement: "em",
        errorPlacement: function(error, element) {
            error.addClass("invalid-feedback");
            if (element.prop("type") === "checkbox") {
                error.insertAfter(element.next("label"));
            } else {
                error.insertAfter(element);
            }
        },
        highlight: function(element, errorClass, validClass) {
            $(element).addClass("is-invalid").removeClass("is-valid");
        },
        unhighlight: function(element, errorClass, validClass) {
            $(element).addClass("is-valid").removeClass("is-invalid");
        }
	});
			
	$('#accountName').typeahead({
		source: function(query, process) {
			accountNames = [];
			accountMaps = {};
			console.log("=-----query--->" + query);
			$.ajax({
				type: "GET",
				url: $('#contextPath').val() + "/admin/projects-allocation/fetchAccountsByName/" + query,
				success: function(data) {
					$.each(data, function(i, acc) {
						accountMaps[acc.accName] = acc;
						accountNames.push(acc.accName);
					});
					process(accountNames);
				},
				dataType: "JSON",
				error: showError
			});
		},
		minLength: 3,
		updater: function (item, i) {
			console.log('=---item----->'+item);
			console.log('=-------->'+accountMaps[item].accountId);
			console.log('=-------->'+$('#accountName').parent().attr('class'));
			$('#accountName').parent().find('#accountId:hidden').val(accountMaps[item].accountId);
			return item;
		}
	});
	
	$('#projectName').typeahead({
		minLength: 3,
		source: function(query, process) {
			projectNames = [];
			projectMaps = {};
			console.log("=-----query--->" + query);
			$.ajax({
				type: "GET",
				url: $('#contextPath').val() + "/admin/projects-allocation/fetchProjects/" + query + "/" + $('#accountId').val(),
				success: function(data) {
					$.each(data, function(i, pro) {
						projectMaps[pro.name] = pro;
						projectNames.push(pro.name);
					});
					process(projectNames);
				},
				dataType: "JSON",
				error: showError
			});
		},
		updater: function(item, i) {
			console.log('=---item----->' + item);
			console.log('=-------->' + projectMaps[item].id);
			$('#projectName').parent().find('#projectId:hidden').val(projectMaps[item].id);
			return item;
		},
		afterSelect: function(data) {
			showAddEmployeeAllocationModal();
		}
	});
	  
	//employeeName
	$('#searchValue').typeahead({
			minLength: 3,
			source: function(query, process) {
				employeeNames = [];
				employeeMaps = {};
				console.log("=-----query--->" + query);
				$.ajax({
					type: "GET",
					url: $('#contextPath').val() + "/admin/projects-allocation/searchEmployees/" + query + "/" + $('#searchBy').val(),
					success: function(data) {
						$('#employeeSearch tbody').empty();
						$('#employeeSearch tbody').append(data);
					},
					dataType: "HTML",
					error: showError
				});
			},
			updater: function(item, i) {
				return item;
			},
			afterSelect: function(data) {
			}
		});
		//
});


function loadProjects(accountId) {
    $.ajax({
        url: $('#contextPath').val()+'/admin/projects-allocation/ferchProjects/' + accountId,
        type: 'GET',
        success: function (projects) {
            let projectSelect = $('#projectIdSer');
            projectSelect.empty();
			projectSelect.append(new Option('Select', ''));
            projects.forEach(function (project) {
                projectSelect.append(new Option(project.name, project.id));
            });
        },
        error: function (error) {
            console.error('Error loading projects:', error);
        }
    });
}

function searchUsers() {
	var username = $('#username').val();
	$.ajax({
		url: $('#contextPath').val()+'/admin/projects-allocation/searchUsers/' + username,
        type: 'GET',
        success: function (users) {
            renderData(users);
        },
        error: function (error) {
            console.error('Error loading users:', error);
        }
    });
}

function showAddEmployeeAllocationModal() {
	var projectId = $("#projectId").val();
	var accountName = $('#accountName').val();
	var projectName = $('#projectName').val();
   $.ajax({
	    url: $('#contextPath').val()+'/admin/projects-allocation/searchEmployeeAllocations/' + projectId,
        type: 'GET',
		dataType: "HTML",
	   	beforeSend: function(xhr) {
		   xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
	  	},
        success:function (data) {
		    renderData(data);
			$('#accountName').val(accountName);
			$('#projectName').val(projectName);
		},
        error: function (error) {
            console.error('Error loading accounts:', error);
        }
    });
}

function showAddAllocation(){
	var projectId = $('#projectIdSer').val();
	$("#empAllForm").trigger('reset');
	/*$.ajax({
	    url: $('#contextPath').val()+'/admin/projects-allocation/showAddAllocation/' + projectId,
	       type: 'GET',
	       success:function (data) {
		    $('#name').val(data.projectName);
			$('#projectId').val(projectId);
			$('#userName').val(data.userName);
			$('#userId').val(data.userId);
			$('#roleName').val(data.roleName);
			$('#roleId').val(data.roleId);
            $('#startDate').val(data.startDate);	
			$('#endDate').val(data.endDate);
			$('#releaseType').val(data.releaseType);
			$('#releaseDate').val(data.releaseDate);	
			$('#releaseComments').val(data.releaseComments);	
			$('#replacementOnBoardDate').val(data.replacementOnBoardDate);
			$('#replName').val(data.replName);
			$('#replVAMId').val(data.replVAMId);
			
			let userNameSelect = $('#userName');
			userNameSelect.empty();
			userNameSelect.append(new Option('Select', ''));
			data.users.forEach(function (user) {
				userNameSelect.append(new Option(user.name, user.id));
			});
			
			let roleNameSelect = $('#roleName');
			roleNameSelect.empty();
			roleNameSelect.append(new Option('Select', ''));
			data.roles.forEach(function (role) {
                roleNameSelect.append(new Option(role.name, role.id));
            });
		},
	       error: function (error) {
	           console.error('Error loading accounts:', error);
	       }
	   });*/
}

function addAllocation(){
	var accountName = $('#searchProject').find('#accountName:text').val();
	var accountId = $('#searchProject').find('#accountId:hidden').val();
	var projectName = $('#searchProject').find('#projectName:text').val();
	var projectId = $('#searchProject').find('#projectId:hidden').val();
	if($("#empAllForm").valid()){
		var reqData = JSON.stringify(serializeObject($('#empAllForm')));
		console.log('=----reqData---->'+reqData);
		$.ajax({
              type: "post",
              url: $('#contextPath').val()+"/admin/projects-allocation/addEmployeeAllocation",
              beforeSend: function(xhr) {
                    xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
                },
              contentType: "application/json; charset=utf-8",
              dataType: "HTML",
              data: reqData,
			success: function(data) {
				$('#searchProject').find('#accountName:text').val(accountName);
				$('#searchProject').find('#accountId:hidden').val(accountId);
				$('#searchProject').find('#projectName:text').val(projectName);
				$('#searchProject').find('#projectId:hidden').val(projectId);
				
				$('.modal-backdrop').remove();
				// renderData(data);
				$('#containerMain').removeClass('modal-open');
				$('.modal-backdrop').hide();
				$('#empAllForm').trigger('reset');
					
				showAddEmployeeAllocationModal();
			}
        });
	}
}

function closeAddAlloPopup(data){
	$('.modal-backdrop').remove();
	renderData(data);
	$('#containerMain').removeClass('modal-open');
	$('.modal-backdrop').hide();
}

function setRoleId(roleId){
    $('#projectRoleId').val(roleId);
}

function setUserId(userId){
    $('#userId').val(userId);
}

function showUpdateAllocation(allocationId){
    $.ajax({
        url: $('#contextPath').val()+'/admin/projects-allocation/showUpdateAllocation/' + allocationId,
           type: 'GET',
           success:function (data) {
			console.log('=---data----->' + JSON.stringify(data));
            $('#name').val(data.projectName);
            $('#projectId').val(data.projectId);
			$('#employeeName').val(data.name);
			$('#allocationType').val(data.allocationType);
			$('#allocationPercentage').val(data.allocationPercentage);
            $('#userId').val(data.userId);
            $('#roleId').val(data.roleId);
            $('#startDate').prop('valueAsDate', new Date(data.startDate));	
            $('#endDate').prop('valueAsDate', new Date(data.endDate));
            $('#addProjectModel').find('#releaseType:text').val(data.releaseType);
            $('#addProjectModel').find('#releaseDate:text').prop('valueAsDate', new Date(data.releaseDate));	
           	$('#addProjectModel').find('#releaseComments:text').val(data.releaseComments);	
            $('#addProjectModel').find('#replacementOnBoardDate:text').prop('valueAsDate', new Date(data.replacementOnBoardDate));
           	$('#addProjectModel').find('#replName:text').val(data.replName);
           	$('#addProjectModel').find('#replVAMId:text').val(data.replVAMId);
            $('#empId').val(data.employeeId);
			$('#projectRoleId').val(data.projectRoleId);
			$('#addProjectModel').find('#accountId:hidden').val(data.accountId);
			$('#addProjectModel').find('#projectId:hidden').val(data.projectId);
			$('#verticalId').val(data.verticalId);
			$('#empAllId').val(data.empAllId);
			$('#ror').val(data.ror);
			$('#rorDate').prop('valueAsDate', new Date(data.rorDate));
			$('#rorComments').val(data.rorComments);
			$('#rorOnBoadingDate').prop('valueAsDate', new Date(data.rorOnBoadingDate));
			$('#performanceAsPerPM').val(data.performanceAsPerpm);
			$('#addProjectModel').find('#replName:text').val(data.replName);
			$('#addProjectModel').find('#replVAMId:text').val(data.replVAMId);
		   let roleNameSelect = $('#roleName');
		   roleNameSelect.empty();
		   roleNameSelect.append(new Option('Select', ''));
		   data.projectRoles.forEach(function(role) {
			   roleNameSelect.append(new Option(role.roleName, role.projectRoleId));
		   });
		   $('#roleName').val(data.projectRoleId);
					
		   $('#addProjectModel').modal('show');
        },
           error: function (request, status, error) {
			   $('#msgErr').text(request.responseJSON.error);
           }
       });
	
}

function deleteAllocation(allocationId){
	var accountName = $('#searchProject').find('#accountName:text').val();
	var accountId =	$('#searchProject').find('#accountId:hidden').val();
	var projectName = $('#searchProject').find('#projectName:text').val();
	var projectId =	$('#searchProject').find('#projectId:hidden').val();
    $.ajax({
          type: "GET",
          url: $('#contextPath').val()+"/admin/projects-allocation/deleteEmployeeAllocation/"+allocationId,
          beforeSend: function(xhr) {
                xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
            },
          success: function(data){
			  renderData(data);
			  $('#searchProject').find('#accountName:text').val(accountName);
			  $('#searchProject').find('#accountId:hidden').val(accountId);
			  $('#searchProject').find('#projectName:text').val(projectName);
			  $('#searchProject').find('#projectId:hidden').val(projectId);
		  },
          dataType: "HTML"
    });
	
}

function showDetails(employeeId){
	$.ajax({
		type: "GET",
		url: $('#contextPath').val() + "/admin/projects-allocation/fetchEmployeeById/" + employeeId,
		success: function(data) {
			$('#showEmployeeDetailsModel').modal('show');
			$('#dojDsp').prop('valueAsDate', new Date(data.doj));
			$('#currentSkillDsp').val(data.currentSkill);
			$('#skillDataFromLDDsp').val(data.skillDataFromLD);
			$('#statusDsp').val(data.status);
			$('#totalExpDsp').val(data.totalExp);
			$('#allocationTypeDsp').val(data.allocationType);
			$('#allocationPercentageDsp').val(data.allocationPercentage);
		},
		dataType: "JSON",
		error: showError
	});
}

function showAddPopup(employeeId, employeeName) {
	console.log('=---employeeId----->' + employeeId);
	console.log('=---employeeName----->' + employeeName);
	$('#addEmployeeForm').find('#empId:hidden').val(employeeId);
	$('#addEmployeeForm').find('#empName:text').val(employeeName);
	
	$('#addEmployeeForm').find('#verticalName:text').typeahead({
		source: function(query, process) {
			verticalNames = [];
			verticalMaps = {};
			console.log("=-----query--->" + query);
			$.ajax({
				type: "GET",
				url: $('#contextPath').val() + "/admin/verticals/searchVerticals/" + query,
				success: function(data) {
					$.each(data, function(i, ver) {
						verticalMaps[ver.verticalName] = ver;
						verticalNames.push(ver.verticalName);
					});
					process(verticalNames);
				},
				dataType: "JSON",
				error: showError
			});
		},
		minLength: 3,
		updater: function(item, i) {
			console.log('=---item----->' + item);
			console.log('=-------->' + verticalMaps[item].verticalId);
			$('#addEmployeeForm').find('#verticalId:hidden').val(verticalMaps[item].verticalId);
			return item;
		}
	});
			
	$('#addEmployeeForm').find('#accountName:text').typeahead({
		source: function(query, process) {
			accountNames = [];
			accountMaps = {};
			console.log("=-----query--->" + query);
			$.ajax({
				type: "GET",
				url: $('#contextPath').val() + "/admin/projects-allocation/fetchAccountsByName/" + query,
				success: function(data) {
					$.each(data, function(i, acc) {
						accountMaps[acc.accName] = acc;
						accountNames.push(acc.accName);
					});
					process(accountNames);
				},
				dataType: "JSON",
				error: showError
			});
		},
		minLength: 3,
		updater: function(item, i) {
			console.log('=---item----->' + item);
			console.log('=-------->' + accountMaps[item].accountId);
			console.log('=-------->' + $('#accountName').parent().attr('class'));
			$('#addEmployeeForm').find('#accountId:hidden').val(accountMaps[item].accountId);
			return item;
		}
	});
		
	$('#addEmployeeForm').find('#projectName:text').typeahead({
		minLength: 3,
		source: function(query, process) {
			projectNames = [];
			projectMaps = {};
			console.log("=-----query--->" + query);
			$.ajax({
				type: "GET",
				url: $('#contextPath').val() + "/admin/projects-allocation/fetchProjects/" + query + "/" + $('#addEmployeeForm').find('#accountId:hidden').val(),
				success: function(data) {
					$.each(data, function(i, pro) {
						projectMaps[pro.name] = pro;
						projectNames.push(pro.name);
					});
					process(projectNames);
				},
				dataType: "JSON",
				error: showError
			});
		},
		updater: function(item, i) {
			console.log('=---item----->' + item);
			console.log('=-------->' + projectMaps[item].id);
			$('#addEmployeeForm').find('#projectId:hidden').val(projectMaps[item].id);
			return item;
		},
		afterSelect: function(data) {

		}
	});
		
	$('#addEmployeeForm').find('#projectRoleName:text').typeahead({
		minLength: 3,
		source: function(query, process) {
			projectRoleNames = [];
			projectRoleMaps = {};
			console.log("=-----query--->" + query);
			$.ajax({
				type: "GET",
				url: $('#contextPath').val() + "/admin/projectRoles/searchProjectRolesByName/" + query,
				success: function(data) {
					$.each(data, function(i, pro) {
						projectRoleMaps[pro.roleName] = pro;
						projectRoleNames.push(pro.roleName);
					});
					process(projectRoleNames);
				},
				dataType: "JSON",
				error: showError
			});
		},
		updater: function(item, i) {
			console.log('=---item----->' + item);
			console.log('=-------->' + projectRoleMaps[item].projectRoleId);
			$('#projectRoleName').parent().find('#projectRoleId:hidden').val(projectRoleMaps[item].projectRoleId);
			return item;
		},
		afterSelect: function(data) {

		}
	});
	
}

function addEmployeeAllocation() {
    if($('#addEmployeeForm').valid()) {
        var reqData = JSON.stringify(serializeObject($('#addEmployeeForm')));
		console.log('=---reqData----->' + reqData);
        $.ajax({
            type: "post",
            url: $('#contextPath').val()+"/admin/projects-allocation/addEmployeeAllocation",
            beforeSend: function(xhr) {
                xhr.setRequestHeader('X-CSRF-TOKEN', $('[name="_csrf"]').val());
            },
            contentType: "application/json; charset=utf-8",
            dataType: "JSON",
            data: reqData,
            success: closeAddEmpAlloPopup
        });
    }
}

function closeAddEmpAlloPopup(data) {
	$('#addEmployeeModel').modal('hide')
	$('.modal-backdrop').remove();
   // renderData(data);
    $('#containerMain').removeClass('modal-open');
    $('.modal-backdrop').hide();
    $('#addEmployeeForm').trigger('reset');
	
	$('#searchProjectLI').trigger('click');
	
	$('#searchProject').find('#accountName:text').val(data.accountName);
	$('#searchProject').find('#accountId:hidden').val(data.accountId);
	$('#searchProject').find('#projectName:text').val(data.projectName);
	$('#searchProject').find('#projectId:hidden').val(data.projectId);
	
	showAddEmployeeAllocationModal();
}