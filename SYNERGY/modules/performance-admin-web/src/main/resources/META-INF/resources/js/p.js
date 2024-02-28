 var leadRole ;
 $(document).ready(function () {

 	    leadRole = $('#isTeamlead').val();
 	   var managerRole = $('#isManager').val();
 	   var hrRole = $('#isHR').val();
 	   var leadershipRole = $('#isLeader').val();
 	   var recruiterRole = $('#isRecruiter').val();
 	   var performanceRole = $('#isPerformance').val();
 	   if (leadRole == 'true') {
 	      $('#leadRole').show();
 	      $('#first-tab').addClass("active");
 	      $('#first').addClass("show active");

 	   }
 	   if (managerRole == 'true') {
 	      $('#managerRole').show();
 	      if (leadRole == 'false') {
 	         $('#second-tab').addClass("active");
 	         $('#second').addClass("show active");
 	      }
 	   }
 	   if (hrRole == 'true') {

 	      $('#hrRole').show();
 	      if (leadRole == 'false' && managerRole == 'false') {
 	         $('#third-tab').addClass("active");
 	         $('#third').addClass("show active");
 	      }
 	   }
 	   if (performanceRole == 'true') {
        	      $('#performanceRole').show();
        	      if (leadRole == 'false' && managerRole == 'false' && hrRole == 'false') {
        	         $('#fourth-tab').addClass("active");
        	         $('#fourth').addClass("show active");
        	      }
        	   }
 	});
    function isEmail(email) {
		var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (!regex.test(email)) {
			$(".email-has-error ").text("Not a valid email address.");
			$("#assigneeEmail").val("");
			return false;
		} else if (!(email.includes("@trantorinc.com") || email .includes("@chd.trantorinc.com"))) {
			$(".email-has-error").text( "Not a valid Trantor email address.");
			$("#assigneeEmail").val("");
			return false;
		} else {
		    $(".email-has-error").text("");
			return true;
		}
	}
    function checkReviewerRole() {
		var reviewerEmail = $('#assigneeEmail').val();
		              var reviewerEmailKey = $('#portletNamespace').val() + 'reviewerEmail';
                       var postData = {
                          [reviewerEmailKey]: reviewerEmail,
                       };
		if (isEmail(reviewerEmail)) {
		 var url = $('#getReviewerNameResourceUrl').val();
			AUI().use('aui-io-request',function(A) {
				A.io.request(url,{
					method : 'post',
					data: postData,
					on : {
						success : function() {
							var employeeData = JSON.parse(this.get('responseData'));
							if (employeeData.data != "" && employeeData.data != null) {
								$("#assigneeButton").attr("disabled",false);
							} else  {
								alertModal("Performance:Not a valid Reviewer Email Id");
							}
						}
					}
				});
			});
		}
	}
	    function saveMidyearForm(obj) {
	    var id = obj.id;
                if(id == "managerSave") {
                var urlForm = $('#setManagerFormResourceUrl').val().toString();
                    AUI().use('aui-io-request', function(A){
    				A.io.request(urlForm,{
    					method : 'post',
    					on : {
    						success : function() {
    							var employeeData = JSON.parse(this.get('responseData'));
    							if (employeeData.data != "" && employeeData.data != null) {
    								$("#assigneeButton").attr("disabled",false);
    							} else  {
    								alertModal("Performance:Not a valid Reviewer Email Id");
    							}
    						}
    					}
    				});
    			});
    		}
    	}
	    function saveMidyearForm(obj){
            var id = obj.id;
            if(id == "managerSave") {
             var urlForm = $('#setManagerFormResourceUrl').val().toString();
                    $.ajax({url:urlForm,
                           type: 'POST',
                           form: {
                               id: 'managerMidyearRatingForm'
                           },
                           on: {
                                success: function() {
                                alertModal("Performance:Data saved successfully");
                              }
                           }
                        });
            }

        }
            function submitMidyearForm(obj){
                var id = obj.id;
                if(id == "managerReject") {
                        $('#actionTaken').val(1);
                        $('#managerFinalRating').prop('required',false);
                        $('#apraiseeAchievements').prop('required',false);
                        $('#improvementComment').prop('required',false);
                        $('#managerOverallComment').prop('required',false);
                        $('#rejectObjRsn').prop('required',true);
                        $('#terms').prop('required',false);
                        $('#terms').setCustomValidity('');
                } else{
                        $('#actionTaken').val(2);
                        $('#managerFinalRating').prop('required','required');
                        $('#improvementComment').prop('required','required');
                        $('#managerOverallComment').prop('required','required');
                        $('#rejectObjRsn').prop('required',false);
                        if($('#undertakingDiv').is(':visible')){
                            $("#terms").required = true;
                        }
                }
            }

        function collapseAll(){
            $( "tr[id^='reviewLineId']" ).removeClass('show');
            return false;
        }
        function toggleOverlay() {
          var value = $('#overlay').css('opacity');
          if (value == '1')
               $('#overlay').css("opacity", "0.0");
          else
               $('#overlay').css("opacity", "1");
        }
        function expandAll(){
            $( "tr[id^='reviewLineId']" ).addClass('show');
           return false;
        }
        function toggleReplacementDetail(){
                var replacementRadio  = $('#replacementRadioDiv input[type=radio]:checked').val();
                    if (replacementRadio == 'Yes') {
                        $('#replacementDropdownDiv').show();
                        $('#replacementDropdown').prop("required","required");
                        $('#replacementReasonDiv').hide();
                        $('#replacementReason').prop("required",false);
                        $('#replacementReasonDropdownDiv').show();
                        $('#replacementReasonId').prop("required","required");
                        $('#replacementReasonId').val() == 'Other' ? $('#replacementOtherReason').prop('required',"required") :$('#replacementOtherReason').prop('required',false);
                    }
                    else if (replacementRadio == 'No') {
                        $('#replacementDropdownDiv').hide();
                        $('#replacementDropdown').prop("required",false);
                        $('#replacementReasonDiv').show();
                        $('#replacementReason').prop("required","required");
                        $('#replacementReasonDropdownDiv').hide();
                        $('#replacementReasonId').prop("required",false);
                        $('#replacementOtherReason').prop('required',false);
                    } else {
                        $('#replacementDropdownDiv').hide();
                        $('#replacementDropdown').prop("required",false);
                        $('#replacementReasonDiv').hide();
                        $('#replacementReason').prop("required",false);
                        $('#replacementReasonDropdownDiv').hide();
                        $('#replacementReasonId').prop("required",false);
                        $('#replacementOtherReason').prop('required',false);
                    }
            }
            $('#promotionReqd').change(function(){
                         if(this.checked) {
                              $('#promotionRequiredBody').show();
                              $('#excelledAreaAns').prop('required','required');
                              $('#rolePlayedAns').prop('required','required');
                              $('#reasonRecommendAns').prop('required','required');
                              $('#readyAns').prop('required','required');
                         } else {
                              $('#promotionRequiredBody').hide();
                              $('#excelledAreaAns').prop('required',false);
                              $('#rolePlayedAns').prop('required',false);
                              $('#reasonRecommendAns').prop('required',false);
                              $('#readyAns').prop('required',false);
                         }
                    });
                        function confirmMidyearWithUser(obj){
                            var action = $('#actionTaken').val();
                            if(action == 1){
                                 obj.action = $('#rejectReviewLinesResourceUrl').val();
                                 event.preventDefault();
                                confirmModal('Performance Admin:Are you sure you want employee to update KPIs ?','managerMidyearRatingForm',null);
                            } else{
                            obj.action = $('#submitManagerReviewResourceUrl').val();
                                    if(leadRole  === true)
                                    {   event.preventDefault();
                                        confirmModal("Performance Admin:Are you sure you want to submit reviews for this review and once forms is submitted it will move to manager view and no longer visible to lead.",'managerMidyearRatingForm',null);
                                    }
                                    else
                                    {  event.preventDefault();
                                       confirmModal("Performance Admin:Are you sure you want to submit reviews for this review ?",'managerMidyearRatingForm',null);
                                    }
                            }
                        }
                       function confirmWithUser(obj){
                            var action = $('#actionTaken').val();
                            var totalCount =  $("#managerReviewBody tr").length/2;
                            if(action == 1){
                            obj.action = $('#rejectReviewLinesResourceUrl').val();
                             event.preventDefault();
                             confirmModal('Performance Admin:Are you sure you want employee to update KPIs ?','annualSubmissionForm',null);
                            } else if(action == 2) {
                                var invalidForm = false;
                                for(i=0;i<totalCount;i++){
                                    if($('#managerComment'+i).val() == ''){
                                        invalidForm = true;
                                        break;
                                    }
                                }
                                if(invalidForm){
                                   alertModal("Performance Admin:Please provide manager comments for all KPIs.");
                                   var expanding =  expandAll();
                                   window.scrollTo(0, 0);
                                   return expanding;
                                } else {
                                   obj.action = $('#submitManagerReviewResourceUrl').val();
                                    if(leadRole == 'true')
                                    {
                                        event.preventDefault();
                                       confirmModal("Performance Admin:Are you sure you want to submit reviews for this assessment and once forms is submitted it will move to manager view and no longer visible to lead.",'annualSubmissionForm',null);
                                    }
                                    else
                                    {
                                       event.preventDefault();
                                       confirmModal("Performance Admin:Are you sure you want to submit reviews for this assessment ?",'annualSubmissionForm',null);
                                    }
                                }
                            } else{
                                obj.action = $('#submitHrReviewResourceUrl').val();
                                event.preventDefault();
                                confirmModal('Performance Admin:Are you sure you want to submit reviews for this assessment ?','annualSubmissionForm',null);
                            }
                        }
                        function submitForm(obj){
                        var primaryAccount =$('#primaryAccount').val();
                                var id = obj.id;
                                var totalCount =  $("#managerReviewBody tr").length/2;
                                if(id == "managerReject") {
                                        $('#actionTaken').val(1);
                                        if($('#reviewState').val() > 1)
                                        {
                                            for(i=0;i<totalCount;i++){
                                                $('#managerRating'+i).prop('required',false);
                                            }
                                            $('#managerOverallComment').prop('required',false);
                                         $('.fn_criticalToAcct').prop("required",false);
                                          $('.fn_criticalToCompany').prop("required",false);
                                            if(primaryAccount == 'true'){
                                               $('.fn_replacement').prop("required",false);
                                                $('#replacementDropdown').prop("required",false);
                                                $('#replacementReason').prop("required",false);
                                            }
                                            $('#terms').prop('required',false);
                                            $('#terms').setCustomValidity('');
                                            $('#excelledAreaAns').prop('required',false);
                                            $('#rolePlayedAns').prop('required',false);
                                            $('#reasonRecommendAns').prop('required',false);
                                            $('#readyAns').prop('required',false);
                                        }
                                        $('#rejectObjRsn').prop('required',true);
                                } else if(id == "managerSubmit") {
                                        $('#actionTaken').val(2);
                                        for(i=0;i<totalCount;i++){
                                            $('#managerRating'+i).prop('required','required');
                                        }
                                        $('#improvementComment').prop('required','required');
                                        $('#managerOverallComment').prop('required','required');
                                        $('.fn_criticalToAcct').prop("required","required");
                                        $('.fn_criticalToCompany').prop("required","required");
                                        if(primaryAccount == 'true'){
                                            $('.fn_replacement').prop("required","required");
                                        }
                                        $('#rejectObjRsn').prop('required',false);
                                        if($('#undertakingDiv').is(':visible')){
                                            $('#terms').prop('required','required');
                                        }
                                }else {
                                        $('#actionTaken').val(3);
                                        $('#hrOverallComments').prop('required','required')
                                }
                            }