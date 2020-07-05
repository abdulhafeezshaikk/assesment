package com.marqueta.app.coreapi.model;

public class CreateUserResponse {
	
	private String token;
	private String account_holder_group_token;
	private Boolean uses_parent_account;
	private String parent_token;
	
	public String getParent_token() {
		return parent_token;
	}
	public void setParent_token(String parent_token) {
		this.parent_token = parent_token;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAccount_holder_group_token() {
		return account_holder_group_token;
	}
	public void setAccount_holder_group_token(String account_holder_group_token) {
		this.account_holder_group_token = account_holder_group_token;
	}
	public Boolean getUses_parent_account() {
		return uses_parent_account;
	}
	public void setUses_parent_account(Boolean uses_parent_account) {
		this.uses_parent_account = uses_parent_account;
	}
}
