package com.trantorinc.synergy.raffle.web.service;

import com.trantorinc.synergy.game.core.model.GameTimeline;
import com.trantorinc.synergy.raffle.web.dto.PrizeDto;
import com.trantorinc.synergy.raffle.web.dto.TicketDto;
import com.trantorinc.synergy.raffle.web.dto.TimelineDto;

import javax.portlet.ActionRequest;
import java.util.List;

public interface RaffleService {
    List<PrizeDto> getPrizes();
    List<TicketDto> getTickets(String ecode);
    String getPrizePic(String photoId);
    TimelineDto getTimeline(GameTimeline raffleTimeline);
    String getTicketAvailability(int ticketId);
    void saveTickets(String loggedUserEmail, ActionRequest request, GameTimeline raffleTimeline);
}
