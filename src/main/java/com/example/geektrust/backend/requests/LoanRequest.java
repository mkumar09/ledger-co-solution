package com.example.geektrust.backend.requests;

public class LoanRequest extends BaseRequest {
	
	public String bankName; 
	public String borrowerName;
	public double principalAmount;
	public int loanTenure; 
	public double rateOfInterest;
	
	    
	    public LoanRequest(String bankName, String borrowerName, double principalAmount, int loanTenure, double rateOfInterest)
	    {
	    	this.bankName = bankName;
	    	this.borrowerName = borrowerName;
	    	this.principalAmount = principalAmount;
	    	this.loanTenure = loanTenure;
	    	this.rateOfInterest = rateOfInterest;
	    }

   
    public boolean validate()
    {
        // Write Validation Rules here
        // Or can use Validation Framework For validating requests
        return true;
    }

}
