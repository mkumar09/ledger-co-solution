package com.example.geektrust.backend.handlers;

import com.example.geektrust.backend.constants.Constants;
import com.example.geektrust.backend.models.LoanDetail;
import com.example.geektrust.backend.repository.IDataStore;
import com.example.geektrust.backend.requests.BalanceRequest;
import com.example.geektrust.backend.response.BalanceResponse;
import com.example.geektrust.backend.response.BaseResponse;

public class BalanceHandler implements IRequestHandler {
	
	 private  BalanceRequest balanceRequest;
     private  IDataStore dataStore;

     public BalanceHandler(BalanceRequest balanceRequest, IDataStore dataStore)
     {
         this.balanceRequest = balanceRequest;
         this.dataStore = dataStore;
     }

     public BaseResponse handleRequests() throws Exception
     {
         LoanDetail existingLoanRecord = dataStore.getLoanDetails(balanceRequest.bankName, balanceRequest.borrowerName);
         if (existingLoanRecord == null)
             throw new Exception(Constants.LoanRecordNotFound);

         double emiAmount = existingLoanRecord.emiAmount();
         double totalAmountByEmi = balanceRequest.emi * emiAmount;
         double totalLumpSumPaid = existingLoanRecord.lumpSumPaidTillEmiNumber(balanceRequest.emi);
         
        double totalAmountPaidTillEmi = totalAmountByEmi + totalLumpSumPaid;
        if ( totalAmountPaidTillEmi > existingLoanRecord.totalAmountToBeRepaid() )
        	totalAmountPaidTillEmi = existingLoanRecord.totalAmountToBeRepaid();

         double amountPending = existingLoanRecord.totalAmountToBeRepaid() - totalAmountPaidTillEmi;
         double remainingEmis = Math.ceil(amountPending / emiAmount);

         BalanceResponse balanceResponse = new BalanceResponse();
         balanceResponse.amountPaid = (int) totalAmountPaidTillEmi;
         balanceResponse.bankName = existingLoanRecord.bankName;
         balanceResponse.borrowerName = existingLoanRecord.borrowerName;
         balanceResponse.remainingEmis = remainingEmis > 0 ? (int)remainingEmis : 0;
         balanceResponse.success = true;
         
         return balanceResponse;
     }

}
