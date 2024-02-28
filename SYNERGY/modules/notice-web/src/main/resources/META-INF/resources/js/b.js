//Resignation.jsp
function confirmResignFormSubmission(actionPerformed) {
   event.preventDefault();
   if (actionPerformed == '1') {
      confirmModal('Are you sure you want to submit the resign form to your manager and HR ?', 'resignForm', null);

} else if (actionPerformed == '3') {
   confirmModal('Are you sure you want to submit the details ?', 'resignForm', null);
} else {
   confirmModal('Exit:Are you sure you want to withdraw your resignation ?', 'resignForm', null);
}
}

function validEmail() {
   var email = $("#emailId").val();
   var regex = /^([a-zA-Z0-9_\.\-\+])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
   if (!regex.test(email) && email != '' && email != null) {
      alertModal("Exit:Personal email address is not valid.");
      $("#emailId").val("");
      return false;
   } else if (email == '' && email == null) {
      return false;
   } else if (email.includes("@trantorinc.com") || email.includes("@chd.trantorinc.com")) {
      alertModal("Exit:personal email not should be trantor email");
      $("#emailId").val("");
      return false;
   } else {
      return true;
   }
}
// Questionnaire.jsp
function confirmResignFormSubmission() {
   event.preventDefault();
   confirmModal('Exit:Are you sure you want to submit ?', 'resignForm', null);
}
$('#workAgain').change(function () {
   if ($('#workAgain').val() == '2') {
      $('#notWorkAgain').prop("required", true);
   } else {
      $('#notWorkAgain').prop("required", false);
   }
});
$('#recommendTrantor').change(function () {
   if ($('#recommendTrantor').val() == '2') {
      $('#notRecommendTrantor').prop("required", true);
   } else {
      $('#notRecommendTrantor').prop("required", false);
   }
});