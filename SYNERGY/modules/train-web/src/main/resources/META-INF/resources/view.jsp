<%@ include file="/init.jsp" %>

<div class="d-flex justify-content-center">
    <video width="950" id="video">
      <source src="/webdav/intranet/document_library/Training%20Videos/Ride_It.mp4" type="video/mp4">
    </video>
</div>
 <div id="video-controls" class="d-flex justify-content-around mt-3">
    <button type="button" class="btn btn-dark m-2" style="display:inline-block; width:130px;" id="play-pause">Play</button>
    <button type="button" class="btn btn-dark m-2" style="display:inline-block;" id="replay">Replay</button>
    <label for="volume-bar" class="btn btn-dark m-2" id="volume-label">Volume
        <input type="range" style="display:inline-block" id="volume-bar" min="0" max="1" step="0.1" class="" value="1"/>
    </label>
  </div>

<div class="d-flex justify-content-center my-5">
    <video width="950" controls>
      <source src="/webdav/intranet/document_library/Training%20Videos/Arjit_Singh.mp4" type="video/mp4">
    </video>
</div>
<div>
   <table class="table table-sm table-bordered table-responsive-md table-striped ">
    <thead class="thead-dark">
        <tr>
          <th width="10%">Question 1 :</th>
          <th width="90%">What will happen next ?</th>
        </tr>
    </thead>
    <tbody>
        <tr>
          <td>Option 1 :</td>
          <td>I break my legs</td>
        </tr>
        <tr>
          <td>Option 2 :</td>
          <td>Music stops</td>
        </tr>
        <tr>
          <td style="background-color: #ccffcc;">Option 3 :</td>
          <td style="background-color: #ccffcc;">Everyone dances with me</td>
        </tr>
		<tr>
          <td>Option 4 :</td>
          <td>Replay the music</td>
        </tr>
	</tbody>
    </table>
   <table class="table table-sm table-bordered table-responsive-md table-striped ">
    <thead class="thead-dark">
        <tr>
          <th width="10%">Question 2 :</th>
          <th width="90%">What will happen next ?</th>
        </tr>
    </thead>
    <tbody>
        <tr>
          <td>Option 1 :</td>
          <td>I break my legs</td>
        </tr>
        <tr>
          <td>Option 2 :</td>
          <td>Music stops</td>
        </tr>
        <tr>
          <td style="background-color: #ccffcc;">Option 3 :</td>
          <td style="background-color: #ccffcc;">Everyone dances with me</td>
        </tr>
		<tr>
          <td>Option 4 :</td>
          <td>Replay the music</td>
        </tr>
	</tbody>
    </table>
   <table class="table table-sm table-bordered table-responsive-md table-striped ">
    <thead class="thead-dark">
        <tr>
          <th width="10%">Question 3 :</th>
          <th width="90%">What will happen next ?</th>
        </tr>
    </thead>
    <tbody>
        <tr>
          <td>Option 1 :</td>
          <td>I break my legs</td>
        </tr>
        <tr>
          <td>Option 2 :</td>
          <td>Music stops</td>
        </tr>
        <tr>
          <td style="background-color: #ccffcc;">Option 3 :</td>
          <td style="background-color: #ccffcc;">Everyone dances with me</td>
        </tr>
		<tr>
          <td>Option 4 :</td>
          <td>Replay the music</td>
        </tr>
	</tbody>
    </table>
</div>

<div class="d-flex justify-content-center my-5">
    <video width="950" controls>
      <source src="/webdav/intranet/document_library/Training%20Videos/Induction.mp4" type="video/mp4">
    </video>
</div>
<div>
   <table class="table table-sm table-bordered table-responsive-md table-striped ">
    <thead class="thead-dark">
        <tr>
          <th width="10%">Question 1 :</th>
          <th width="90%">What will happen next ?</th>
        </tr>
    </thead>
    <tbody>
        <tr>
          <td>Option 1 :</td>
          <td>I break my legs</td>
        </tr>
        <tr>
          <td>Option 2 :</td>
          <td>Music stops</td>
        </tr>
        <tr>
          <td style="background-color: #ccffcc;">Option 3 :</td>
          <td style="background-color: #ccffcc;">Everyone dances with me</td>
        </tr>
		<tr>
          <td>Option 4 :</td>
          <td>Replay the music</td>
        </tr>
	</tbody>
    </table>
   <table class="table table-sm table-bordered table-responsive-md table-striped ">
    <thead class="thead-dark">
        <tr>
          <th width="10%">Question 2 :</th>
          <th width="90%">What will happen next ?</th>
        </tr>
    </thead>
    <tbody>
        <tr>
          <td>Option 1 :</td>
          <td>I break my legs</td>
        </tr>
        <tr>
          <td>Option 2 :</td>
          <td>Music stops</td>
        </tr>
        <tr>
          <td style="background-color: #ccffcc;">Option 3 :</td>
          <td style="background-color: #ccffcc;">Everyone dances with me</td>
        </tr>
		<tr>
          <td>Option 4 :</td>
          <td>Replay the music</td>
        </tr>
	</tbody>
    </table>
   <table class="table table-sm table-bordered table-responsive-md table-striped ">
    <thead class="thead-dark">
        <tr>
          <th width="10%">Question 3 :</th>
          <th width="90%">What will happen next ?</th>
        </tr>
    </thead>
    <tbody>
        <tr>
          <td>Option 1 :</td>
          <td>I break my legs</td>
        </tr>
        <tr>
          <td>Option 2 :</td>
          <td>Music stops</td>
        </tr>
        <tr>
          <td style="background-color: #ccffcc;">Option 3 :</td>
          <td style="background-color: #ccffcc;">Everyone dances with me</td>
        </tr>
		<tr>
          <td>Option 4 :</td>
          <td>Replay the music</td>
        </tr>
	</tbody>
    </table>
</div>

<script>
window.onload = function() {
  var video = document.getElementById("video");
  var playButton = document.getElementById("play-pause");
  var replay = document.getElementById("replay");
  var volumeBar = document.getElementById("volume-bar");
  var volumeButton = document.getElementById("volume-label");

    playButton.addEventListener("click", function() {
      if (video.paused == true) {
        video.play();
        playButton.innerHTML = "Pause";
      } else {
        video.pause();
        playButton.innerHTML = "Play";
      }
    });


    volumeBar.addEventListener("change", function() {
      video.volume = volumeBar.value;
    });

    replay.addEventListener("click", function() {
            playButton.style.display="inline-block";
            volumeButton.style.display="block";
            video.currentTime = 0;
            video.play();
    });

    video.addEventListener('ended',function(){
            playButton.style.display="none";
            volumeButton.style.display="none";
    });
}
</script>
