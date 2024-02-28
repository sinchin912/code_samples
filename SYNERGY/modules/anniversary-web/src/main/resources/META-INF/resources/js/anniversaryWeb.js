$(document).ready(function() {
    var photoKey = $('#portletNamespace').val()+'photoId';
    var url = $('#getPhotoResourceUrl').val();
    $.each($('.fn_image'), function() {
      var image = $(this);
      var photoId = image.attr('id');
      var postData = {
        [photoKey] : photoId
      }
      AUI().use('aui-io-request',function(A) {
        A.io.request(url,{
            method : 'post',
            data : postData,
            on : {
              success : function() {
                var imageData = JSON.parse(this.get('responseData'));
                 image.attr('src', 'data:image/png;base64,'+imageData.data);
                 image.parent().attr('href', 'data:image/png;base64,'+imageData.data);
              }
            }
        });
      });
    });
});
