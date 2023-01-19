package com.example.geektrust.backend.factories;

import com.example.geektrust.backend.constants.Constants;
import com.example.geektrust.backend.handlers.BalanceHandler;
import com.example.geektrust.backend.handlers.IRequestHandler;
import com.example.geektrust.backend.handlers.LoanHandler;
import com.example.geektrust.backend.handlers.PaymentHandler;
import com.example.geektrust.backend.repository.IDataStore;
import com.example.geektrust.backend.requests.BalanceRequest;
import com.example.geektrust.backend.requests.LoanRequest;
import com.example.geektrust.backend.requests.PaymentRequest;

public class RequestHandlerFactory
{
    public static IRequestHandler GetRequestHandler(String command, IDataStore dataStore)
    {
        IRequestHandler request = null;
        String[] args = command.split(" ");
        if (args != null)
        {
        	String attribute = args[Constants.ZERO].trim();
        	if ( attribute.equals(Constants.Loan))
        		request = getLoanHandler(args, dataStore);
        	else if (attribute.equals(Constants.Balance))
        		request = getBalanceHandler(args, dataStore);
        	else if ( attribute.equals(Constants.Payment))
        		request = getPaymentHandler(args, dataStore);
        }
        return request;
    }

    private static LoanHandler getLoanHandler(String[] args, IDataStore dataStore)
    {
        String bankName = args[1];
        String borrowerName = args[2];
        String principal = args[3];
        double principalAmount = Double.parseDouble(principal);
        String tenure = args[4];
        int loanTenure = Integer.parseInt(tenure);
        String rateOfInterest = args[5];
        double roi = Double.parseDouble(rateOfInterest);

        LoanRequest loanRequest = new LoanRequest(bankName, borrowerName, principalAmount, loanTenure, roi);
        LoanHandler loanHandler = new LoanHandler(loanRequest, dataStore);
        return loanHandler;
    }

    private static PaymentHandler getPaymentHandler(String[] args, IDataStore dataStore)
    {
    	String bankName = args[1];
        String borrowerName = args[2];
        String amount = args[3];
        double lumpSumAmount = Double.parseDouble(amount);
        String emi = args[4];
        int emiNo = Integer.parseInt(emi);

        PaymentRequest paymentRequest = new PaymentRequest(bankName, borrowerName, lumpSumAmount, emiNo);
        PaymentHandler loanHandler = new PaymentHandler(paymentRequest, dataStore);
        return loanHandler;
    }

    private static BalanceHandler getBalanceHandler(String[] args, IDataStore dataStore)
    {
    	String bankName = args[1];
        String borrowerName = args[2];
        String emi = args[3];
        int emiNo = Integer.parseInt(emi);

        BalanceRequest balanceRequest = new BalanceRequest(bankName, borrowerName, emiNo);
        BalanceHandler loanHandler = new BalanceHandler(balanceRequest, dataStore);
        return loanHandler;
    }
}
