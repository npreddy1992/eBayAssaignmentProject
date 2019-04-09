package com.Application.Pages.eBay;

public interface LoginImpl{
	public boolean logIn_Into_App(String usernameText, String passwordText);
	public boolean invalidLogin(String invalidUsernameText, String invalidPasswordText);
}
