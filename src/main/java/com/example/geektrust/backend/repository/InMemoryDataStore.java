package com.example.geektrust.backend.repository;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.geektrust.backend.models.LoanDetail;
import com.example.geektrust.backend.models.Pair;
import com.example.geektrust.backend.models.Payment;

public class InMemoryDataStore implements IDataStore {

	 private HashMap<Pair, LoanDetail> loanRecords;
	 
	 

     public InMemoryDataStore(HashMap<Pair, LoanDetail> loanRecords) {
		super();
		this.loanRecords = loanRecords;
	}

	public LoanDetail getLoanDetails(String bankName, String borrowerName)

     {
    	 Pair loanRecordKey = getLoanRecordKey(bankName, borrowerName);
    	 LoanDetail existingLoanDetails = getLoanRecord(loanRecordKey);
         return existingLoanDetails;
     }

     public boolean saveLoanDetails(LoanDetail loanDetail)
     {
    	 Pair loanRecordKey = getLoanRecordKey(loanDetail.bankName, loanDetail.borrowerName);
         if ( loanRecords.containsKey(loanRecordKey))
        	 return false;
         else
         {
        	 loanRecords.put(loanRecordKey, loanDetail);
        	 return true;
         }
        			
     }

     private Pair getLoanRecordKey(String bankName, String borrowerName)
     {
    	 Pair loanRecordKey = new Pair(bankName, borrowerName);
         return loanRecordKey;
     }

     public boolean savePayment(String bankName, String borrowerName, Payment payment)
     {
    	 Pair loanRecordKey = getLoanRecordKey(bankName, borrowerName);
    	 LoanDetail existingLoanDetails = getLoanRecord(loanRecordKey);

         if (existingLoanDetails == null)
             return false;

         if (existingLoanDetails.payments == null)
             existingLoanDetails.payments = new ArrayList<Payment>();

         existingLoanDetails.payments.add(payment);
         return true;
     }

     public LoanDetail getLoanRecord(Pair loanRecordKey)
     {
         //LoanDetail existingLoanDetails;
         if (loanRecords.containsKey(loanRecordKey))
         {
             return loanRecords.get(loanRecordKey);
         }
         return null;
     }
}
