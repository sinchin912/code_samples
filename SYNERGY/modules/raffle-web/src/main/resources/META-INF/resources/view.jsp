<%@ include file="/init.jsp" %>

<portlet:actionURL name="applyTickets" var="applyTicketsUrl"></portlet:actionURL>
<portlet:resourceURL id="getPrizePic" var="getPrizePicUrl"></portlet:resourceURL>
<portlet:resourceURL id="getTicketAvailability" var="getTicketAvailabilityUrl"></portlet:resourceURL>

<div class="container">
    <input type="hidden" id="portletNamespace" value="<portlet:namespace/>"/>
    <input type="hidden" id="getPrizePicResourceUrl" value="${getPrizePicUrl}"/>
    <input type="hidden" id="getTicketAvailabilityResourceUrl" value="${getTicketAvailabilityUrl}"/>
   <div class="card">
      <div class="card-header">
         <div class="row">
             <div class="col pt-1">
                <h5>Raffle Draw ${currentYear}</h5>
             </div>
        </div>
      </div>
      <c:choose>
            <c:when test="${mode == 'off'}">
            <div class="card-body">
               <div class="row">
                  <div class="col pt-1">
                     <h6>Raffle Draw is expected to open near Diwali. Tune back to Synergy once deadline opens.</h6>
                  </div>
               </div>
            </div>
            </c:when>
            <c:otherwise>
            <div class="card-body">

            <c:if test="${buyOpen == 'on'}">
                   <h3 class="pb-3">Buy Tickets</h3>
                    <form method="post" id="raffleTicketForm" action="${applyTicketsUrl}" onsubmit="return validateForm()">
                        <div class="row ">
                            <div class="col-3">
                               <select class="mdb-select md-form form-control form-control-sm" id="numTicket" onchange="validateNum()" name="<portlet:namespace/>numTicket"  >
                                <option value="" disabled selected>Number of Tickets</option>
                                <option value="1">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                                <option value="4">4</option>
                                <option value="5">5</option>
                                <option value="6">6</option>
                                <option value="7">7</option>
                                <option value="8">8</option>
                                <option value="9">9</option>
                                <option value="10">10</option>
                                <option value="11">11</option>
                                <option value="12">12</option>
                                <option value="13">13</option>
                                <option value="14">14</option>
                                <option value="15">15</option>
                                <option value="16">16</option>
                                <option value="17">17</option>
                                <option value="18">18</option>
                                <option value="19">19</option>
                                <option value="20">20</option>
                            </select>
                            </div>
                            <div class="col-9">
                                <h6 class="text-center font-italic text-primary"> You can buy 20 tickets in one go and further make purchase for more tickets from this section again</h6>
                            </div>
                        </div>

                        <table class="table  table-sm  table-bordered table-striped table-hover table-custom mt-3" id="ticketTable">
                            <thead class="thead-dark">
                                <tr>
                                    <th width="30%">Ticket ID</th>
                                    <th width="70%">Availability Status</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr id="row1">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum1" id="ticketNum1" /></td>
                                    <td><span id="message1"></span></td>
                                </tr>
                                <tr id="row2">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum2" id="ticketNum2" /></td>
                                    <td><span id="message2"></span></td>
                                </tr>
                                <tr id="row3">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum3" id="ticketNum3" /></td>
                                    <td><span id="message3"></span></td>
                                </tr>
                                <tr id="row4">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum4" id="ticketNum4" /></td>
                                    <td><span id="message4"></span></td>
                                </tr>
                                <tr id="row5">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum5" id="ticketNum5" /></td>
                                    <td><span id="message5"></span></td>
                                </tr>
                                <tr id="row6">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum6" id="ticketNum6" /></td>
                                    <td><span id="message6"></span></td>
                                </tr>
                                <tr id="row7">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum7" id="ticketNum7" /></td>
                                    <td><span id="message7"></span></td>
                                </tr>
                                <tr id="row8">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum8" id="ticketNum8" /></td>
                                    <td><span id="message8"></span></td>
                                </tr>
                                <tr id="row9">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum9" id="ticketNum9" /></td>
                                    <td><span id="message9"></span></td>
                                </tr>
                                <tr id="row10">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum10" id="ticketNum10" /></td>
                                    <td><span id="message10"></span></td>
                                </tr>
                                <tr id="row11">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum11" id="ticketNum11" /></td>
                                    <td><span id="message11"></span></td>
                                </tr>
                                <tr id="row12">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum12" id="ticketNum12" /></td>
                                    <td><span id="message12"></span></td>
                                </tr>
                                <tr id="row13">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum13" id="ticketNum13" /></td>
                                    <td><span id="message13"></span></td>
                                </tr>
                                <tr id="row14">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum14" id="ticketNum14" /></td>
                                    <td><span id="message14"></span></td>
                                </tr>
                                <tr id="row15">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum15" id="ticketNum15" /></td>
                                    <td><span id="message15"></span></td>
                                </tr>
                                <tr id="row16">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum16" id="ticketNum16" /></td>
                                    <td><span id="message16"></span></td>
                                </tr>
                                <tr id="row17">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum17" id="ticketNum17" /></td>
                                    <td><span id="message17"></span></td>
                                </tr>
                                <tr id="row18">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum18" id="ticketNum18" /></td>
                                    <td><span id="message18"></span></td>
                                </tr>
                                <tr id="row19">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum19" id="ticketNum19" /></td>
                                    <td><span id="message19"></span></td>
                                </tr>
                                <tr id="row20">
                                    <td class="p-0"><input type="number" class="form-control form-control-sm" min="1" max="9999999" step="1" onmouseup="checkAvailability(this)" onkeyup="checkAvailability(this)" onKeyPress="if(this.value.length==7) return false;" name="<portlet:namespace/>ticketNum20" id="ticketNum20" /></td>
                                    <td><span id="message20"></span></td>
                                </tr>
                            </tbody>
                        </table>
                        <div class="pt-2" style="font-size:1.0em;" id="tcDiv">
                                <strong>Terms & Conditions :</strong>
                                <ul>
                                    <li>Draw will be done online on ${timeline.actionDate}.</li>
                                    <li>Presence of winner will be mandatory at online event otherwise we will redraw for next winner</li>
                                    <li>No employee can win more than one gift</li>
                                    <li>Ticket sales will close on <u>${timeline.freezeDate}</u></li>
                                    <li>Cost of 1 ticket is &#8377;${ticketCost} and you can buy as many as you want</li>
                                    <li>No cash payment is to be done, deductions will be made from salary directly</li>
                                    <li>Winners will be required to collect gifts from G9 office. In case you are not in tricity and adjoining areas, you can connect with HR team. We will share schedule of collection post Diwali with winners</li>
                                    <li>Tickets once sold cannot be cancelled</li>
                                    <li>In case of any issues, decision of HR team will be final</li>
                                </ul>
                        </div>
                        <div class="custom-control custom-checkbox mt-3" id="ackDiv">
                            <input type="checkbox" class="custom-control-input" required  oninvalid="this.setCustomValidity('Kindly acknowledge this to proceed')" oninput="this.setCustomValidity('')"
                                 id="terms" /> <label  for="terms"
                                class="custom-control-label pl-2 font-weight-normal">
                                I acknowledge that cost of the tickets (@ &#8377;${ticketCost} per ticket) can be deducted from my next month salary.</label>
                        </div>
                        <div class="row mt-4" id="buttonDiv">
                            <div class="col d-flex justify-content-center">
                                <button class="btn btn-outline-secondary mr-3"  onclick="cancelTicketForm()">Cancel</button>
                                <button class="btn btn-primary" type="submit" id="applyButton" onclick="recheckAvailabilty()">Buy</button>
                            </div>
                        </div>
                    </form>
            </c:if>


            <c:if test="${tickets.size() > 0}" >
            <div class="row pt-5">
                <div class="col">
                    <hr>
                </div>
                <div class="col-5 text-center">
                    <h3>My Tickets (${tickets.size()} Tickets = &#8377;${tickets.size() * ticketCost})</h3>
                </div>
                <div class="col">
                    <hr>
                </div>
            </div>
            <div class="row mt-4">
                <c:forEach items="${tickets}" var="ticket" varStatus="count">
                    <div class="col" style="height:150px;">
                        <div class="border border-dark rounded p-2 m-0 text-center" style="width: 300px;height: 100px;background:rgba(11, 95, 255, .3);">
                            <div class="border border-dark rounded-circle float-left bg-white" style="width:20px;height:20px;"></div>
                            <img style="height:35px;width:35px;" src="<%= themeDisplay.getPathThemeImages() %>/web-logo.png" >
                            <h6>RAFFLE DRAW</h6>
                            <h6><span  class="float-left">&#8377;${ticketCost}</span>Ticket : <span  class="text-danger">${ticket.number}</span></h6>
                        </div>
                        <c:if test="${ticket.prizeDescription != null}">
                            <div>
                                <span class="text-danger">Prize Won : </span><a id="anchor_${ticket.prizePicId}"  href="" data-lightbox="${ticket.prizePicId}">${ticket.prizeDescription}</a>
                            </div>
                        </c:if>
                    </div>
                </c:forEach>
            </div>
            </c:if>
            <div class="row pt-5">
                <div class="col">
                    <hr>
                </div>
                <div class="col-5 text-center">
                    <h3>Attractive prizes !!</h3>
                </div>
                <div class="col">
                    <hr>
                </div>
            </div>

            <div id="rafflePrizes" class="carousel slide" data-ride="carousel" data-ride="carousel" data-interval="false" data-ride="carousel" data-pause="hover">
                <ol class="carousel-indicators">
                    <c:forEach var="i" begin="0" end="${prizes.size()-1}" step="1">
                            <li data-target="#rafflePrizes" data-slide-to="${i}"
                                class="${(i == 0) ? 'active' : ''}"></li>
                    </c:forEach>
                </ol>
              <div class="carousel-inner">
               <c:forEach var="i" begin="0" end="${prizes.size()-1}" step="1">
                    <div class="${(i == 0)? 'carousel-item active' : 'carousel-item'}" >
                        <div class="w-100 text-center" style="height: 550px;">
                            <img style="height:90px;width:90px;margin-top:200px;" class="fn_raffle" src="<%= themeDisplay.getPathThemeImages() %>/spinner.gif" id="raffle_${prizes[i].prizePic}">
                        </div>
                        <div class="carousel-caption d-md-block bg-dark">
                              <h5><c:if test="${prizes[i].winnerName != null}">${prizes[i].winnerName}(${prizes[i].winnerAccount}) won </c:if>${prizes[i].description}</h5>
                        </div>
                    </div>
                </c:forEach>
              </div>
            </div>
            </div>
            </c:otherwise>
      </c:choose>
   </div>
</div>