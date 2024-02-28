package com.trantoric.synergy.game.admin.web.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author sachin.goyal
 */
public class GameAdminWebPortletKeys {

	public static final String GAMEADMINWEB =
		"com_trantoric_synergy_game_admin_web_GameAdminWebPortlet";

	public static final String SUBJECT_WINNER_TICKET = "Congratulations on winning a prize in Trantor''s Diwali Raffle Draw {0}" ;

	public static final String RAFFLE_WINNER_MAIL_BODY = "Dear {0},<br><br>Congratulations !!<br><br> You have won" +
			" <b> {1}</b>  in the Trantor''s Diwali Raffle Draw {2}<br><br>You are required to collect gift from " +
			"the G9 office. HR will share the schedule of collection post Diwali. In case you are not in the city" +
			" and adjoining areas," +
			" you can connect with the HR team by writing to hr@trantorinc.com" +

			"<br><br>Regards,<br>Synergy Admin";

	public static final List<String> HEADERS_RAFFLE_EXPORT= Arrays.asList("Ecode","Name","Total tickets","Total amount","Prize");
	public static final List<String> HEADERS_SANTA_EXPORT= Arrays.asList("Ecode","Name","Mobile","Secret Santa","Guessed Correct","Gift Sent","Thankyou Email Sent");

}