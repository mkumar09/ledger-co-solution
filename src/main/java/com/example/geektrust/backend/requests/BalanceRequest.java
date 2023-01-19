package com.example.geektrust.backend.requests;

public class BalanceRequest extends BaseRequest {
	
	public BalanceRequest(String bankName, String borrowerName, int emi)
    {
        this.bankName = bankName;
        this.borrowerName = borrowerName;
        this.emi = emi;
    }

    public String bankName;
    public String borrowerName;
    public int emi;

    public boolean validate()
    {
        // Write Validation Rules here
        // Or can use Validation Framework For validating requests
        return true;
    }

}
