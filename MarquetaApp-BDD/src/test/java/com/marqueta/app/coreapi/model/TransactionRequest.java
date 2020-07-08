package com.marqueta.app.coreapi.model;

public class TransactionRequest {
	private String amount;
	private String mid;
	private String card_token;
	
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getCard_token() {
		return card_token;
	}
	public void setCard_token(String card_token) {
		this.card_token = card_token;
	}
}
