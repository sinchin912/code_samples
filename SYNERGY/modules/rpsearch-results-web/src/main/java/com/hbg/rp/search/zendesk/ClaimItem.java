package com.hbg.rp.search.zendesk;

import java.io.Serializable;

public class ClaimItem implements Serializable {

    private static final long serialVersionUID = 2579116846385771814L;

    private String code; // ISBN/EAN
    private String shortTitle;
    private String quantityShipped;
    private String reship;
    private String quantityClaimed;
    private String wrongIsbn;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getShortTitle() {
        return shortTitle;
    }

    public void setShortTitle(String shortTitle) {
        this.shortTitle = shortTitle;
    }

    public String getQuantityShipped() {
        return quantityShipped;
    }

    public void setQuantityShipped(String quantityShipped) {
        this.quantityShipped = quantityShipped;
    }

    public String getReship() {
        return reship;
    }

    public void setReship(String reship) {
        this.reship = reship;
    }

    public String getQuantityClaimed() {
        return quantityClaimed;
    }

    public void setQuantityClaimed(String quantityClaimed) {
        this.quantityClaimed = quantityClaimed;
    }

	public String getWrongIsbn() {
		return wrongIsbn;
	}

	public void setWrongIsbn(String wrongIsbn) {
		this.wrongIsbn = wrongIsbn;
	}
}
