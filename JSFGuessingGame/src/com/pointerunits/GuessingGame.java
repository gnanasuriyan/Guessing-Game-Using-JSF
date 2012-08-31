package com.pointerunits;

import java.io.IOException;
import java.util.Random;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped

public class GuessingGame {
	private int randomNumber;
	private int userNumber;
	private int minimum;
	private int maximum;
	private int allowableAttempts;
	private int userAttempts;
	private String message;
	public GuessingGame() {
		this.minimum = 0;
		this.maximum =10;
		this.allowableAttempts = 5; 
		this.initializeGame();
	}
	private void initializeGame() {
		Random randomGR = new Random();
		this.randomNumber = new Integer(randomGR.nextInt(this.maximum));
		System.out.println("Random number : " + this.randomNumber);
		this.userAttempts = 0;
		this.userNumber = 0;
		this.message = "";
	}
	public int getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public int getMinimum() {
		return minimum;
	}
	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}
	public int getMaximum() {
		return maximum;
	}
	public void setMaximum(int maximum) {
		this.maximum = maximum;
	}
	public int getAllowableAttempts() {
		return allowableAttempts;
	}
	public void setAllowableAttempts(int allowableAttempts) {
		this.allowableAttempts = allowableAttempts;
	}
	public int getUserAttempts() {
		return userAttempts;
	}
	public void setUserAttempts(int userAttempts) {
		this.userAttempts = userAttempts;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public void playGame() throws IOException {
		this.userAttempts++;
		boolean isRedirect = false;
		if(this.userNumber == this.randomNumber) {
			this.message = "Hey..you got it. Total attempt is " + this.userAttempts ;
			isRedirect = true;
		}
		else if(this.userNumber > this.randomNumber){
			this.message = "Hey I am lesser than your guess";
		}
		else if(this.userNumber < this.randomNumber) {
			this.message = "Hey I am greater than your guess";
		}
		if(this.userAttempts == this.allowableAttempts) {
			this.message = "Hey..you have failed to guess.";
			isRedirect = true;
		}
		if(isRedirect) {
			FacesContext.getCurrentInstance().getExternalContext().redirect("guessingGameResponse.xhtml");
		}
	}
	
	public void resetGame() throws IOException {
		initializeGame();
		FacesContext.getCurrentInstance().getExternalContext().redirect("guessingGame.xhtml");
	}
}
