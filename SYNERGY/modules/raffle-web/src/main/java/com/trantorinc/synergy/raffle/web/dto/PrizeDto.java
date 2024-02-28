package com.trantorinc.synergy.raffle.web.dto;

public class PrizeDto {

    private long id;
    private int sequence;
    private String description;
    private String winnerName;
    private String winnerAccount;
    private boolean surprise;
    private String prizePic;

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

}
