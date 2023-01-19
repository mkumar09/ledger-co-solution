package com.example.geektrust.backend.requests;

public class PaymentRequest extends BaseRequest {
	
	public PaymentRequest(String bankName, String borrowerName, double lumpsumAmount, int emi)
    {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.lumpsumAmount = lumpsumAmount;
        this.emi = emi;
    }

    public String bankName;
    public String borrowerName;
    public double lumpsumAmount; 
    public int emi; 

    public boolean validate()
    {
        // Write Validation Rules here
        // Or can use Validation Framework For validating requests
        return true;
    }

}
