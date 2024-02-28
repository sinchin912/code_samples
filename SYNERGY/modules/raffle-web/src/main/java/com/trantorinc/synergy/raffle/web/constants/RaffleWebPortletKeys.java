package com.trantorinc.synergy.raffle.web.constants;

/**
 * @author sachin.goyal
 */
public class RaffleWebPortletKeys {

	public static final String RAFFLEWEB =
		"com_trantorinc_synergy_raffle_web_RaffleWebPortlet";

	public static final String SUBJECT_RAFFLE_TICKET = "Synergy - Raffle Draw Tickets" ;
	public static final String BODY_RAFFLE_TICKET = "Dear {0},<br><br>Thank You for buying {1} raffle ticket'/'s on Synergy.<br>" +
			"Your total tickets purchased till now are : {2}" +
			"Note : " +
			"<ul><li>Please refer to this email for ticket Ids when Draw is being declared</li>" +
			"<li>Total amount of <b> INR {3} </b> will be deducted from your next month salary</li></ul>" +
			"<br>Regards,<br>Synergy Admin";

	public static final String FORMAT_7_NUMBER = "%07d";
	public static final int TICKET_COST = 50;

}