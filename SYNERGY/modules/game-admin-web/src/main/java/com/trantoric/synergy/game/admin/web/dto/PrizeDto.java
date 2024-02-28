package com.trantoric.synergy.game.admin.web.dto;

public class PrizeDto {

    private long id;
    private int sequence;
    private String description;
    private String winnerEcode;
    private String winnerName;
    private String winnerAccount;
    private String winnerPhoto;
    private long ticketId;
    private String ticketNumber;
    private boolean surprise;
    private String prizePic;
    private boolean action;
    private boolean view;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWinnerEcode() {
        return winnerEcode;
    }

    public void setWinnerEcode(String winnerEcode) {
        this.winnerEcode = winnerEcode;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }

    public String getWinnerAccount() {
        return winnerAccount;
    }

    public void setWinnerAccount(String winnerAccount) {
        this.winnerAccount = winnerAccount;
    }

    public String getWinnerPhoto() {
        return winnerPhoto;
    }

    public void setWinnerPhoto(String winnerPhoto) {
        this.winnerPhoto = winnerPhoto;
    }

    public long getTicketId() {
        return ticketId;
    }

    public void setTicketId(long ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public boolean isSurprise() {
        return surprise;
    }

    public void setSurprise(boolean surprise) {
        this.surprise = surprise;
    }

    public String getPrizePic() {
        return prizePic;
    }

    public void setPrizePic(String prizePic) {
        this.prizePic = prizePic;
    }

    public boolean isAction() {
        return action;
    }

    public void setAction(boolean action) {
        this.action = action;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }
}
