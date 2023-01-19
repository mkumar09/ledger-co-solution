package com.example.geektrust.backend.handlers;

import com.example.geektrust.backend.constants.Constants;
import com.example.geektrust.backend.models.LoanDetail;
import com.example.geektrust.backend.models.Payment;
import com.example.geektrust.backend.repository.IDataStore;
import com.example.geektrust.backend.requests.PaymentRequest;
import com.example.geektrust.backend.response.BaseResponse;
import com.example.geektrust.backend.translator.RequestToModelTranslator;

public class PaymentHandler implements IRequestHandler{
	
	private  PaymentRequest paymentRequest;
    private  IDataStore dataStore;

    public PaymentHandler(PaymentRequest paymentRequest, IDataStore dataStore)
    {
        this.paymentRequest = paymentRequest;
        this.dataStore = dataStore;
    }

    public BaseResponse handleRequests() throws Exception
    {
        LoanDetail existingLoanRecord = dataStore.getLoanDetails(paymentRequest.bankName, paymentRequest.borrowerName);
        if (existingLoanRecord == null)
            throw new Exception(Constants.LoanRecordNotFound);
        int totalValidEmis = existingLoanRecord.loanTenure * 12;
        if (paymentRequest.emi > totalValidEmis)
            throw new Exception(Constants.InvalidEmi);
        Payment payment = RequestToModelTranslator.toPaymentModel(paymentRequest);
        boolean isSaved = dataStore.savePayment(paymentRequest.bankName, paymentRequest.borrowerName, payment);
        BaseResponse baseResponse= new BaseResponse();
        baseResponse.success = isSaved;
        
        return baseResponse;
    }

}
