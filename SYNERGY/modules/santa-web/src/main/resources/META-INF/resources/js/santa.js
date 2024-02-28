function confirmRegistration() {
  event.preventDefault();
  confirmModal('Secret Santa:Are you sure you want to register for Secret Santa game ?','registrationForm',null);
}

function confirmAllocation() {
  event.preventDefault();
  confirmModal('Secret Santa:Are you sure that you have sent gift to the assigned employee ?','allocationForm',null);
}

function confirmGame() {
  event.preventDefault();
  confirmModal('Secret Santa:Are you sure you want to submit the details ?','gameForm',null);
}

function confirmSendEmailForm() {
  event.preventDefault();
  confirmModal('Secret Santa:A system generated email will be sent to the Secret Santa, Are you sure you want to send ?','sendMailForm',null);
}

$('#uploadPic').on('change', function() {
	var fileName = $(this).val().replace('C:\\fakepath\\', "");
	$(this).next('.custom-file-label').html(fileName);
});