package com.example.geektrust.backend.models;

import java.time.LocalDateTime;
import java.util.List;

import com.example.geektrust.backend.constants.Constants;

public class LoanDetail
{
    public String bankName;
    public String borrowerName ;
    public double principalAmount;
    public int loanTenure; 
    public double rateOfInterest; 

    public List<Payment> payments;

    public LocalDateTime createDate;
    
    

    public LoanDetail(String bankName, String borrowerName, double principalAmount, int loanTenure,
			double rateOfInterest, LocalDateTime createDate) {
		super();
		this.bankName = bankName;
		this.borrowerName = borrowerName;
		this.principalAmount = principalAmount;
		this.loanTenure = loanTenure;
		this.rateOfInterest = rateOfInterest;
		this.createDate = createDate;
	}

	public double totalAmountToBeRepaid()
    {
        if (this.loanTenure > Constants.ZERO)
            return this.principalAmount + ((this.principalAmount * this.loanTenure * this.rateOfInterest) / Constants.Percentage);
        else
            return 0;
    }

    public double emiAmount()
    {
        double totalAmountToBeRepaid = totalAmountToBeRepaid();
        if (totalAmountToBeRepaid > Constants.ZERO)
            return Math.ceil(totalAmountToBeRepaid / (this.loanTenure * Constants.MonthsInYear));
        else
            return 0;
    }

    public double lumpSumPaidTillEmiNumber(int emiNumber)
    {
        if (this.payments != null && this.payments.size() > Constants.ZERO)
        {
            return this.payments.stream().filter(x -> x.emiNumber <= emiNumber).mapToDouble(x -> x.amount).sum();
        }
        return 0;
    }
}
