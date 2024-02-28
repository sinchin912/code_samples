<%@ include file="/init.jsp" %>
<portlet:resourceURL id="getVideoBase64" var="getVideoBase64Url"></portlet:resourceURL>
<input type="hidden" id="getVideoBase64ResourceUrl" value="${getVideoBase64Url}"/>
<button class="btn btn-outline-secondary" onblur="loadVideoAjax()">Load Video</button>
<br>
<video id="video" width="450" controls="">
<source id="source" src="" type=""></source>
</video>

<script>
function convertBasetoBlob(content, contentType) {
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

function loadVideoAjax(){
               var url = $('#getVideoBase64ResourceUrl').val();
               AUI().use('aui-io-request', function (A) {
                  A.io.request(url, {
                     method: 'post',
                     on: {
                        success: function () {
                            var employeeData = JSON.parse(this.get('responseData'));
                            if (employeeData.data != "" && employeeData.data != null) {
                                var video = document.getElementById('video');
                                var source = document.getElementById('source');

                                // var source = document.createElement('source');
                                // source.setAttribute('src', 'http://techslides.com/demos/sample-videos/small.mp4');
                                // source.setAttribute('type', 'video/webm');
                                // video.appendChild(source);
                                // video.play();
                                // video.pause();

                                source.setAttribute('src',  window.URL.createObjectURL(convertBasetoBlob(employeeData.data,"video/mp4")));
                                source.setAttribute('type', 'video/mp4');

                                video.load();
                                //video.play();
                            }
                        }
                     }
                  });
               });
}



</script>