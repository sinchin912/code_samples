package com.hbg.rp.search.model;

import com.hbg.rp.model.FactSheetQuoteReview;

import java.io.Serializable;
import java.util.List;

/**
 * @author ravi.kumar
 */
public class CatalogDTO implements Serializable{
	
	private static final long serialVersionUID = -611495172876596160L;
	
	private String productKey;
	private String workKey;
	private String isbn10;
	private String title;
	private String byLine;
	private String pubStatusDesc;
	private String unitHeight;
	private String unitWidth;
	private String unitDepth;
	private String unitWeight;
	private String availabilityDate;
	private String usaNetPrice;
	private String canadaNetPrice;
	private String nbrOfPages;
	private String onSaleDate;
	private String authorBio;
	private String bookDescription;
	private String publisherDesc;
	private String factSheetKey;
	private String productCode;
	private String productDesc;
	private String defaultUseableBalance;
	private String hotTitleFlag;
	private String pubStatusCode;
	private String onixSynopsis;
	private String hideFromOnix;
	private List<FactSheetQuoteReview> quotes;
	private String isbn;
	private String workISBN;
	private String teamSize;
	private String imprint;
    private String usPrice;
    private String canPrice;
    private String numberOfPages;
    private String isTentative;
    
	public String getProductKey() {
		return productKey;
	}
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}
	public String getWorkKey() {
		return workKey;
	}
	public void setWorkKey(String workKey) {
		this.workKey = workKey;
	}
	public String getIsbn10() {
		return isbn10;
	}
	public void setIsbn10(String isbn10) {
		this.isbn10 = isbn10;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getByLine() {
		return byLine;
	}
	public void setByLine(String byLine) {
		this.byLine = byLine;
	}
	public String getPubStatusDesc() {
		return pubStatusDesc;
	}
	public void setPubStatusDesc(String pubStatusDesc) {
		this.pubStatusDesc = pubStatusDesc;
	}
	public String getUnitHeight() {
		return unitHeight;
	}
	public void setUnitHeight(String unitHeight) {
		this.unitHeight = unitHeight;
	}
	public String getUnitWidth() {
		return unitWidth;
	}
	public void setUnitWidth(String unitWidth) {
		this.unitWidth = unitWidth;
	}
	public String getUnitDepth() {
		return unitDepth;
	}
	public void setUnitDepth(String unitDepth) {
		this.unitDepth = unitDepth;
	}
	public String getUnitWeight() {
		return unitWeight;
	}
	public void setUnitWeight(String unitWeight) {
		this.unitWeight = unitWeight;
	}
	public String getAvailabilityDate() {
		return availabilityDate;
	}
	public void setAvailabilityDate(String availabilityDate) {
		this.availabilityDate = availabilityDate;
	}
	public String getUsaNetPrice() {
		return usaNetPrice;
	}
	public void setUsaNetPrice(String usaNetPrice) {
		this.usaNetPrice = usaNetPrice;
	}
	public String getCanadaNetPrice() {
		return canadaNetPrice;
	}
	public void setCanadaNetPrice(String canadaNetPrice) {
		this.canadaNetPrice = canadaNetPrice;
	}
	public String getNbrOfPages() {
		return nbrOfPages;
	}
	public void setNbrOfPages(String nbrOfPages) {
		this.nbrOfPages = nbrOfPages;
	}
	public String getOnSaleDate() {
		return onSaleDate;
	}
	public void setOnSaleDate(String onSaleDate) {
		this.onSaleDate = onSaleDate;
	}
	public String getAuthorBio() {
		return authorBio;
	}
	public void setAuthorBio(String authorBio) {
		this.authorBio = authorBio;
	}
	public String getBookDescription() {
		return bookDescription;
	}
	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}
	public String getPublisherDesc() {
		return publisherDesc;
	}
	public void setPublisherDesc(String publisherDesc) {
		this.publisherDesc = publisherDesc;
	}
	public String getFactSheetKey() {
		return factSheetKey;
	}
	public void setFactSheetKey(String factSheetKey) {
		this.factSheetKey = factSheetKey;
	}
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getDefaultUseableBalance() {
		return defaultUseableBalance;
	}
	public void setDefaultUseableBalance(String defaultUseableBalance) {
		this.defaultUseableBalance = defaultUseableBalance;
	}
	public String getHotTitleFlag() {
		return hotTitleFlag;
	}
	public void setHotTitleFlag(String hotTitleFlag) {
		this.hotTitleFlag = hotTitleFlag;
	}
	public String getPubStatusCode() {
		return pubStatusCode;
	}
	public void setPubStatusCode(String pubStatusCode) {
		this.pubStatusCode = pubStatusCode;
	}
	public String getOnixSynopsis() {
		return onixSynopsis;
	}
	public void setOnixSynopsis(String onixSynopsis) {
		this.onixSynopsis = onixSynopsis;
	}
	public String getHideFromOnix() {
		return hideFromOnix;
	}
	public void setHideFromOnix(String hideFromOnix) {
		this.hideFromOnix = hideFromOnix;
	}
	public List<FactSheetQuoteReview> getQuotes() {
		return quotes;
	}
	public void setQuotes(List<FactSheetQuoteReview> quotes) {
		this.quotes = quotes;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getWorkISBN() {
		return workISBN;
	}
	public void setWorkISBN(String workISBN) {
		this.workISBN = workISBN;
	}

	public String getTeamSize() {
		return teamSize;
	}
	public void setTeamSize(String teamSize) {
		this.teamSize = teamSize;
	}
	public String getImprint() {
		return imprint;
	}
	public void setImprint(String imprint) {
		this.imprint = imprint;
	}
	public String getUsPrice() {
		return usPrice;
	}
	public void setUsPrice(String usPrice) {
		this.usPrice = usPrice;
	}
	public String getCanPrice() {
		return canPrice;
	}
	public void setCanPrice(String canPrice) {
		this.canPrice = canPrice;
	}
	public String getNumberOfPages() {
		return numberOfPages;
	}
	public void setNumberOfPages(String numberOfPages) {
		this.numberOfPages = numberOfPages;
	}
	public String getIsTentative() {
		return isTentative;
	}
	public void setIsTentative(String isTentative) {
		this.isTentative = isTentative;
	}
    
}
