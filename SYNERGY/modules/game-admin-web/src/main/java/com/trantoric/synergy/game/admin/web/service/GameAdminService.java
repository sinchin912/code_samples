package com.trantoric.synergy.game.admin.web.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.trantoric.synergy.game.admin.web.dto.PrizeDto;
import com.trantoric.synergy.game.admin.web.dto.TimelineDto;
import com.trantorinc.synergy.game.core.model.GameTimeline;
import com.trantorinc.synergy.game.core.model.Prize;

import javax.portlet.ActionRequest;
import javax.portlet.ResourceResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public interface GameAdminService {

    TimelineDto getRaffleTimeline(GameTimeline raffle);
    TimelineDto getSantaTimelines(GameTimeline santa);
    List<PrizeDto> getPrizes(List<Prize> prizes);
    String getPrizePic(String photoId);
    void saveRaffleTimeline(String openDate, String freezeDate, String actionDate);
    void saveSantaTimeline(String openDate, String freezeDate, String actionDate, String closeDate);
    String updatePrizePic(long prizeId, File uploadedPic);
    void saveRafflePrize(ActionRequest actionRequest) throws PortalException;
    List<PrizeDto> getRaffleDrawPrizes(List<Prize> prizes);
    void confirmWinner(long prizeId, long ticketId, int currentYear);
    String getWinner();
    void exportRaffle(ResourceResponse response) throws IOException;
    void exportSanta(ResourceResponse response) throws IOException;
}
