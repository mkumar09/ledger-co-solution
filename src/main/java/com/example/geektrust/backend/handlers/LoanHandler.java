package com.example.geektrust.backend.handlers;

import com.example.geektrust.backend.constants.Constants;
import com.example.geektrust.backend.models.LoanDetail;
import com.example.geektrust.backend.repository.IDataStore;
import com.example.geektrust.backend.requests.LoanRequest;
import com.example.geektrust.backend.response.BaseResponse;
import com.example.geektrust.backend.translator.RequestToModelTranslator;

public class LoanHandler implements IRequestHandler
{
    private LoanRequest loanRequest;
    private  IDataStore dataStore;

    public LoanHandler(LoanRequest loanRequest, IDataStore dataStore)
    {
        this.loanRequest = loanRequest;
        this.dataStore = dataStore;
    }

    public BaseResponse handleRequests() throws Exception
    {
    	LoanDetail existingLoanDetails = dataStore.getLoanDetails(loanRequest.bankName, loanRequest.borrowerName);
        if (existingLoanDetails != null)
        {
            throw new Exception(Constants.LoanRecordExists);
        }

        LoanDetail loanDetail = RequestToModelTranslator.toLoanDetailModel(loanRequest);
        boolean isSaved = dataStore.saveLoanDetails(loanDetail);
        BaseResponse baseResponse= new BaseResponse();
        baseResponse.success = isSaved;
        
        return baseResponse;
        
    }
}