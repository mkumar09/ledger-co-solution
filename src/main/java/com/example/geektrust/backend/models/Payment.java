package com.example.geektrust.backend.models;

public class Payment {
	
	public int emiNumber; 
    public double amount;
	public int getEmiNumber() {
		return emiNumber;
	}
	public void setEmiNumber(int emiNumber) {
		this.emiNumber = emiNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public Payment(int emiNumber, double amount) {
		super();
		this.emiNumber = emiNumber;
		this.amount = amount;
	}
    
    
    

}
