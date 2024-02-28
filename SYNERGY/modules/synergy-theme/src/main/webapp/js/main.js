/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

/*
 * This function gets loaded when all the HTML, not including the portlets, is
 * loaded.
 */
AUI().ready(function () {});

/*
 * This function gets loaded after each and every portlet on the page.
 *
 * portletId: the current portlet's id
 * node: the Alloy Node object of the current portlet
 */
Liferay.Portlet.ready(function (_portletId, _node) {});

/*
 * This function gets loaded when everything, including the portlets, is on
 * the page.
 */
Liferay.on('allPortletsReady', function () {
    $('#loadingModal').modal('hide');
    $('.modal-backdrop').remove();
});



function modalConfirm(message, formId, resetUrl, callback) {
    var confirmIndex = true;
    const myArray = message.split(":");
    $('#confirmModalLabel').html(myArray[0]);
    $('#confirmModalDescription').html(myArray[1]);
    $('#alertModal').removeClass('modal');
    $('#confirmModal').modal('show');

    $('#confirm_no').on("click", function() {
        if(confirmIndex) {
            callback(false);
            $('#alertModal').addClass('modal');
            $('#confirmModal').modal('hide');
            confirmIndex = false;
            if(resetUrl !== null){
                window.location.href = resetUrl;
            }
        }
    });

    $('#confirm_yes').on("click", function() {
        if(confirmIndex) {
            callback(true);
            $('#alertModal').addClass('modal');
            $('#confirmModal').modal('hide');
            confirmIndex = false;
            $('#'+formId).submit();
            $('#loadingModal').modal('show');
        }
    });
}

function confirmModal(message, formId, resetUrl){
	modalConfirm(message, formId, resetUrl, function(confirm){
		return confirm;
	});
}

function alertModal(message) {
    const myArray = message.split(":");
    $('#alertModalLabel').html(myArray[0]);
    $('#alertModalDescription').html(myArray[1]);
    $('#confirmModal').removeClass('modal');
    $('#alertModal').modal('show');
}

function dismissAlertModal(){
    $('#confirmModal').addClass('modal');
    $('#alertModal').modal('hide');
}

function siteTour(){
    var enjoyhint_instance = new EnjoyHint({});
    var enjoyhint_script_steps = [
        {
            selector: "#navigation > ul >  li:nth-child(1)",
            event_type: "next",
            description: "Access application's home page here",
            showSkip: true
        },
        {
            selector: "#navigation > ul >  li:nth-child(2)",
            event_type: "next",
            description: "Find your records for various systems here",
            showSkip: true
        },
        {
            selector: "#navigation > ul >  li:nth-child(3)",
            event_type: "next",
            description: "Find all helpful documents and templates here",
            showSkip: true
        },
        {
            selector: "#navigation > ul >  li:nth-child(4)",
            event_type: "next",
            description: "Find links for other Trantor portals here",
            showSkip: true
        },
        {
            selector: "#navigation > ul >  li:nth-child(5)",
            event_type: "next",
            description: "Get birthdays, work-anniversaries, holidays info here",
            showSkip: true
        },
        {
            selector: "#navigation > ul >  li:nth-child(6)",
            event_type: "next",
            description: "Know more about Trantor here",
            showSkip: true
        },
        {
            selector: "#footer > div >  p:nth-child(2) > a:nth-child(1)",
            event_type: "next",
            description: "Let us know your feedback here",
            showSkip: true
        },
        {
            selector: "#trantor_social",
            event_type: "next",
            description: "Access to Trantor's social sharing links",
            showSkip: true
        }
    ];
    enjoyhint_instance.set(enjoyhint_script_steps);

    enjoyhint_instance.run();
}

function convertBase64toBlob(content, contentType) {
   contentType = contentType || '';
   var sliceSize = 512;
   var byteCharacters = window.atob(content); //method which converts base64 to binary
   var byteArrays = [];
   for (var offset = 0; offset < byteCharacters.length; offset += sliceSize) {
      var slice = byteCharacters.slice(offset, offset + sliceSize);
      var byteNumbers = new Array(slice.length);
      for (var i = 0; i < slice.length; i++) {
         byteNumbers[i] = slice.charCodeAt(i);
      }
      var byteArray = new Uint8Array(byteNumbers);
      byteArrays.push(byteArray);
   }
   var blob = new Blob(byteArrays, {
      type: contentType
   }); //statement which creates the blob
   return blob;
}