package com.example.geektrust.backend.translator;

import java.time.LocalDateTime;

import com.example.geektrust.backend.models.LoanDetail;
import com.example.geektrust.backend.models.Payment;
import com.example.geektrust.backend.requests.LoanRequest;
import com.example.geektrust.backend.requests.PaymentRequest;

public class RequestToModelTranslator {

	 public static LoanDetail toLoanDetailModel(LoanRequest loanRequest)
     {
         if (loanRequest == null)
             return null;
         
         return new LoanDetail(loanRequest.bankName,loanRequest.borrowerName,loanRequest.principalAmount,loanRequest.loanTenure,loanRequest.rateOfInterest,LocalDateTime.now());
        
     }
	 

     public static Payment toPaymentModel(PaymentRequest paymentRequest)
     {
         if (paymentRequest == null)
             return null;
         
         return new Payment(paymentRequest.emi,paymentRequest.lumpsumAmount);
     }
}
