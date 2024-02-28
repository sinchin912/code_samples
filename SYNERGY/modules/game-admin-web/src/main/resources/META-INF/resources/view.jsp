<%@ include file="/init.jsp" %>

<portlet:actionURL name="saveRafflePrize" var="saveRafflePrizeUrl"></portlet:actionURL>
<portlet:actionURL name="saveRaffleTimeline" var="saveRaffleTimelineUrl"></portlet:actionURL>
<portlet:actionURL name="saveSantaTimeline" var="saveSantaTimelineUrl"></portlet:actionURL>
<portlet:actionURL name="confirmWinner" var="confirmWinnerUrl"></portlet:actionURL>
<portlet:resourceURL id="exportRaffle" var="exportRaffleUrl"></portlet:resourceURL>
<portlet:resourceURL id="exportSanta" var="exportSantaUrl"></portlet:resourceURL>
<portlet:resourceURL id="updatePrizePic" var="updatePrizePicUrl"></portlet:resourceURL>
<portlet:resourceURL id="getPrizePic" var="getPrizePicUrl"></portlet:resourceURL>
<portlet:resourceURL id="getWinner" var="getWinnerUrl"></portlet:resourceURL>



<portlet:renderURL var="gameAdminRenderURL" windowState="normal"></portlet:renderURL>

<div class="container">

    <input type="hidden" id="startDate" value="${startDate}"/>
    <input type="hidden" id="endDate" value="${endDate}"/>
    <input type="hidden" id="portletNamespace" value="<portlet:namespace/>"/>
    <input type="hidden" id="updatePrizePicResourceUrl" value="${updatePrizePicUrl}"/>
    <input type="hidden" id="getPrizePicResourceUrl" value="${getPrizePicUrl}"/>
    <input type="hidden" id="getWinnerResourceUrl" value="${getWinnerUrl}"/>
    <c:set var="gameAdminURL" value="${fn:split(gameAdminRenderURL.toString(), '?')}" />

    <ul class="nav nav-tabs nav-justified mb-3" role="tablist">
            <li class="nav-item"><a class="nav-link active" id="first-tab" data-toggle="tab" href="#first" role="tab" aria-controls="first" aria-selected="true">Raffle Draw</a></li>
            <li class="nav-item"><a class="nav-link" id="second-tab" data-toggle="tab" href="#second" role="tab" aria-controls="second" aria-selected="false">Configure Raffle</a></li>
            <li class="nav-item"><a class="nav-link" id="third-tab" data-toggle="tab" href="#third" role="tab" aria-controls="third" aria-selected="false">Configure Secret Santa</a></li>
    </ul>
    <div class="tab-content">

        <!-- RAFFLE DRAW TAB -->
        <div class="tab-pane fade show active" id="first" role="tabpanel" aria-labelledby="first-tab">

            <div id="rafflePrizes" class="carousel slide pt-4" data-ride="carousel"
                data-interval="false" data-ride="carousel" data-pause="hover">
                <ol class="carousel-indicators">
                    <c:forEach var="i" begin="0" end="${raffleDrawPrizes.size()}" step="1">
                        <c:if test="${raffleDrawPrizes[i].view}">
                            <li data-target="#rafflePrizes" data-slide-to="${i}"
                                class="${raffleDrawPrizes[i].action ||((beforeLastDay||(!beforeLastDay && drawCompleted)) && (i == 0)&& !raffleDrawPrizes[i].action) ? 'active' : ''}"></li>
                        </c:if>
                    </c:forEach>
                </ol>
                <div class="carousel-inner">
                    <c:forEach var="i" begin="0" end="${raffleDrawPrizes.size()}" step="1">
                        <c:if test="${raffleDrawPrizes[i].view}">
                            <div class="${raffleDrawPrizes[i].action ||((beforeLastDay||(!beforeLastDay && drawCompleted)) && (i == 0)&& !raffleDrawPrizes[i].action)  ? 'carousel-item active' : 'carousel-item'}">
                                <div style="height: 550px; width: 100%;" class="border border-dark">
                                    <div class="row">
                                        <div class="col-6">
                                            <img style="height: 90px;width: 90px;margin-top: 170px;margin-left: 220px;" class="fn_raffle" src="<%= themeDisplay.getPathThemeImages() %>/spinner.gif" id="raffle_${raffleDrawPrizes[i].prizePic}">
                                        </div>
                                        <c:if test="${!beforeLastDay}">
                                        <div class="col-6">
                                            <c:if test="${raffleDrawPrizes[i].action}">
                                            <button class="btn btn-primary mx-auto d-block mt-5" id="start${i+1}" onclick="getTicketFromServer(this)">Start</button>
                                            </c:if>
                                            <div id="winnerDiv${i+1}" class="${!raffleDrawPrizes[i].action  && (raffleDrawPrizes[i].ticketId != '')  ? 'show' : 'hide'}">
                                            <form method="post" action="${confirmWinnerUrl}">
                                                <input type="hidden" name="<portlet:namespace/>prizeId" value="${i+1}" />
                                                <h2 class="text-center pt-4">Winner</h2>
                                                <h4  id="message${i+1}" class="${raffleDrawPrizes[i].ticketId != ''  ? 'text-center pt-4 text-danger hide' : 'text-center pt-4 text-danger show'}">Happy Diwali !! Employee is already a winner</h4>
                                                <div class="row pt-2">
                                                    <div class="col-3">
                                                        <img id="winner_${raffleDrawPrizes[i].winnerPhoto}" class="fn_winner fn_winnerphoto${i+1}"  src="<%= themeDisplay.getPathThemeImages() %>/spinner.gif"  style="height:150px;" />
                                                    </div>
                                                    <div class="col-9">
                                                        <div class="row">
                                                            <div class="col-4 text-right mt-2">
                                                                <label>Ticket</label>
                                                            </div>
                                                            <div class="col-8">
                                                                <input type="text" class="form-control-plaintext"  readonly id="ticketNumber${i+1}" value="${raffleDrawPrizes[i].ticketNumber}" />
                                                                <input type="hidden" id="ticketId${i+1}" name="<portlet:namespace/>ticketId${i+1}" value="${raffleDrawPrizes[i].ticketId}" />
                                                             </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-4 text-right mt-2">
                                                                <label>ECode</label>
                                                            </div>
                                                            <div class="col-8">
                                                                <input type="text" class="form-control-plaintext" readonly id="ecode${i+1}" value="${raffleDrawPrizes[i].winnerEcode}" />
                                                             </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-4 text-right mt-2">
                                                                <label>Name</label>
                                                            </div>
                                                            <div class="col-8">
                                                                <input type="text" class="form-control-plaintext" readonly id="ename${i+1}" value="${raffleDrawPrizes[i].winnerName}" />
                                                             </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-4 text-right mt-2">
                                                                <label>Account</label>
                                                            </div>
                                                            <div class="col-8">
                                                                 <input type="text" class="form-control-plaintext" readonly id="account${i+1}" value="${raffleDrawPrizes[i].winnerAccount}" />
                                                             </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div  class="${!raffleDrawPrizes[i].action  && (raffleDrawPrizes[i].ticketId != '')  ? 'form-row hide' : 'form-row show'}">
                                                    <div class="form-group col-md-6 mt-2 text-center">
                                                        <button class="btn btn-outline-warning" type="button" onclick="getTicketFromServer(this)" id="reshuffle${i+1}">Re-Shuffle</button>
                                                    </div>
                                                    <div class="form-group col-md-6 mt-2 text-center">
                                                        <button class="btn btn-success" type="submit" id="confirm${i+1}">Confirm</button>
                                                    </div>
                                                </div>
                                            </form>
                                            </div>
                                        </div>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="carousel-caption d-md-block bg-dark">
                                    <h5>${raffleDrawPrizes[i].description}</h5>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>

        </div>

        <!-- RAFFLE SETUP TAB -->
        <div class="tab-pane fade" id="second" role="tabpanel" aria-labelledby="second-tab">
            <div class="card">
                <div class="card-header">
                    <div class="row mt-2">
                        <div class="col-3">
                            <h5>Timeline</h5>
                        </div>
                        <div class="col-9 text-right">
                            <c:if test="${raffleTimeline.edit}">
                                <button type="button" class="btn btn-outline-secondary" id="updateRaffleTimeline" onclick="enableRaffleTimeline()">Update</button>
                                <button type="button" class="btn btn-outline-secondary" id="cancelUpdateRaffleTimeline" onclick="resetRaffleTimeline()" style="display:none;">Cancel</button>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <form  id="raffleTimelineForm" method="post" action="${saveRaffleTimelineUrl}" onsubmit="return confirmRaffleTimeline()">
                    <table class="table table-sm table-striped mt-2" >
                       <thead class="thead-dark">
                          <tr>
                             <th scope="col" width="20%">Date</th>
                             <th scope="col" width="80%">Description</th>
                          </tr>
                       </thead>
                       <tbody>
                          <tr>
                             <td><input type="hidden" id="raffleOpenDateOriginal" value="${raffleTimeline.openDate}"/><input type="text" class="form-control form-control-sm" readonly disabled value="${raffleTimeline.openDate}" id="raffleOpenDate" name="<portlet:namespace/>raffleOpenDate" /></td>
                             <td>Game & Ticket Buying opens</td>
                          </tr>
                          <tr>
                             <td><input type="hidden" id="raffleFreezeDateOriginal" value="${raffleTimeline.freezeDate}"/><input type="text" class="form-control form-control-sm" readonly disabled value="${raffleTimeline.freezeDate}" id="raffleFreezeDate" name="<portlet:namespace/>raffleFreezeDate" /></td>
                             <td>Ticket Buying closes</td>
                          </tr>
                          <tr>
                             <td><input type="hidden" id="raffleActionDateOriginal" value="${raffleTimeline.actionDate}"/><input type="text" class="form-control form-control-sm" readonly disabled value="${raffleTimeline.actionDate}" id="raffleActionDate" name="<portlet:namespace/>raffleActionDate" /></td>
                             <td>Raffle draw start</td>
                          </tr>
                       </tbody>
                    </table>
                    <div class="row mt-2">
                        <div class="col-12 d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary"  style="display:none" id="submitRaffleTimeline" >Submit</button>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
            <div class="card">
                <div class="card-header">
                    <div class="row mt-2">
                        <div class="col-3">
                            <h5>Prize</h5>
                        </div>
                        <div class="col-9 text-right">
                            <c:if test="${raffleTimeline.edit}">
                                <button type="button" class="btn btn-outline-secondary" id="updateRafflePrize" onclick="enableRafflePrize()">Update</button>
                                <a class="btn btn-outline-secondary" id="cancelUpdateRafflePrize" style="display:none;" href="${gameAdminURL[0]}" role="button">Cancel</a>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <form id="saveRafflePrizeForm" method="post" action="${saveRafflePrizeUrl}" onsubmit="return confirmSaveRafflePrizes()">
                    <input type="hidden" id="totalRows" name="<portlet:namespace/>totalRows" value="${prizes.size()}"/>
                    <table class="table table-sm table-striped" >
                       <thead class="thead-dark">
                          <tr>
                             <th scope="col"><button type="button" alt="Add a new prize" class="btn btn-sm btn-outline-success py-0" id="addPrize" style="display:none" onclick="addNewPrize()" ><i class="far fa-plus-square"></i></button></th>
                             <th scope="col">Sequence</th>
                             <th scope="col">Surprise</th>
                             <th scope="col">Name</th>
                             <th scope="col">Image</th>
                             <th scope="col">Update</th>
                          </tr>
                       </thead>
                       <tbody id="prizeTableBody">
                       <c:forEach items="${prizes}" var="prize" varStatus="loop">
                          <tr>
                             <td><c:if test="${loop.count != 1}"><button type="button" alt="Delete this prize" class="btn btn-sm btn-outline-danger py-0 fn_deletePrize" style="display:none" onclick="deletePrize(this)" ><i class="fas fa-trash-alt"></i></button></c:if></td>
                             <td>${loop.count}</td>
                             <td><c:if test="${loop.count != 1}"><input type="checkbox" class="fn_surprise" name="<portlet:namespace/>surprise${loop.count}"  <c:if test="${prize.surprise}">checked</c:if> disabled /></c:if><c:if test="${loop.count == 1}"><input type="hidden" value="false" name="<portlet:namespace/>surprise${loop.count}"/></c:if></td>
                             <td><input type="text" maxlength="70" class="form-control form-control-sm fn_prizeName" required name="<portlet:namespace/>prizeName${loop.count}" value="${prize.description}" readonly /></td>
                             <td class="text-center"><a id="anchor_${prize.prizePic}" href="" data-lightbox="${prize.prizePic}"><img  style="height:25px;" id="${prize.prizePic}" src="<%= themeDisplay.getPathThemeImages() %>/spinner.gif" /></a></td>
                             <td>
                                <c:if test="${raffleTimeline.edit}">
                                    <input type="file" id="${prize.id}" style="display:none;" onchange="prizePicOnChange(this)" />
                                    <label class="btn btn-primary btn-sm fn_fileUpload" for="${prize.id}">Choose File</label>
                                </c:if>
                             </td>
                          </tr>
                       </c:forEach>
                       </tbody>
                    </table>
                    <div class="row mt-2">
                        <div class="col-12 d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary" style="display:none" id="submitRafflePrize" >Submit</button>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
            <div class="card">
                <div class="card-header">
                    <div class="row mt-2">
                        <div class="col-3">
                            <h5>Report</h5>
                        </div>
                        <div class="col-9 text-right">
                            <a class="btn btn-outline-secondary" href="<%= exportRaffleUrl %>">Download</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="tab-pane fade" id="third" role="tabpanel" aria-labelledby="third-tab">
            <div class="card">
                <div class="card-header">
                    <div class="row mt-2">
                        <div class="col-3">
                            <h5>Timeline <i class="fa fa-question-circle" aria-hidden="true" title="Dates can be updated unless Secret-Santa are assigned."></i></h5>
                        </div>
                        <div class="col-9 text-right">
                            <c:if test="${santaTimeline.edit}">
                                <button type="button" class="btn btn-outline-secondary" id="updateSantaTimeline" onclick="enableSantaTimeline()">Update</button>
                                <button type="button" class="btn btn-outline-secondary" id="cancelUpdateSantaTimeline" onclick="resetSantaTimeline()" style="display:none;">Cancel</button>
                            </c:if>
                        </div>
                    </div>
                </div>
                <div class="card-body">
                    <form id="santaTimelineForm" method="post" action="${saveSantaTimelineUrl}" onsubmit="return confirmSantaTimeline()">
                    <table class="table table-sm table-striped mt-2" >
                       <thead class="thead-dark">
                          <tr>
                             <th scope="col" width="20%">Start Date</th>
                             <th scope="col" width="80%">Description</th>
                          </tr>
                       </thead>
                       <tbody>
                          <tr>
                             <td><input type="hidden" id="santaOpenDateOriginal" value="${santaTimeline.openDate}" /> <input type="text" class="form-control form-control-sm" readonly disabled value="${santaTimeline.openDate}" id="santaOpenDate" name="<portlet:namespace/>santaOpenDate" /></td>
                             <td>Game & Registration opens</td>
                          </tr>
                          <tr>
                             <td><input type="hidden" id="santaFreezeDateOriginal" value="${santaTimeline.freezeDate}" /> <input type="text" class="form-control form-control-sm" readonly disabled value="${santaTimeline.freezeDate}" id="santaFreezeDate" name="<portlet:namespace/>santaFreezeDate" /></td>
                             <td>Secret Santa are assigned by system. Gift to be dispatched by another 10 days.</td>
                          </tr>
                          <tr>
                             <td><input type="hidden" id="santaActionDateOriginal" value="${santaTimeline.actionDate}" /> <input type="text" class="form-control form-control-sm" readonly disabled value="${santaTimeline.actionDate}" id="santaActionDate" name="<portlet:namespace/>santaActionDate" /></td>
                             <td>Guessing game begins</td>
                          </tr>
                          <tr>
                             <td><input type="hidden" id="santaCloseDateOriginal" value="${santaTimeline.closeDate}" /> <input type="text" class="form-control form-control-sm" readonly disabled value="${santaTimeline.closeDate}" id="santaCloseDate" name="<portlet:namespace/>santaCloseDate" /></td>
                             <td>Guessing game stops. Thanks email stops in next 10 days and game closes in another next 10 days</td>
                          </tr>
                       </tbody>
                    </table>
                    <div class="row mt-2">
                        <div class="col-12 d-flex justify-content-center">
                            <button type="submit" class="btn btn-primary" style="display:none" id="submitSantaTimeline" >Submit</button>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
            <div class="card">
                <div class="card-header">
                    <div class="row mt-2">
                        <div class="col-3">
                            <h5>Report</h5>
                        </div>
                        <div class="col-9 text-right">
                            <a class="btn btn-outline-secondary" href="<%= exportSantaUrl %>">Download</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>


    </div>

    <div class="modal fade" id="shufflingModal" tabindex="-1" role="dialog" aria-labelledby="shufflingModalLabel" aria-hidden="true" data-backdrop="static" data-keyboard="false">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-body text-center p-0 border-0">
    		<div class="row">
    			<div class="col text-center">
                    <img style="height:200px;width:200px;" src="<%=request.getContextPath()%>/images/processingData.gif">
                    <h2 class="pt-4">Shuffling... Shuffling...</h2>
    		    </div>
    		 </div>
          </div>
        </div>
      </div>
    </div>

</div>