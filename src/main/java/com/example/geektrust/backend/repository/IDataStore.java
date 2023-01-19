package com.example.geektrust.backend.repository;

import com.example.geektrust.backend.models.LoanDetail;
import com.example.geektrust.backend.models.Payment;

public interface IDataStore {
	
	boolean saveLoanDetails(LoanDetail loanDetail);

    LoanDetail getLoanDetails(String bankName, String borrowerName);

    boolean savePayment(String bankName, String borrowerName, Payment payment);

}
