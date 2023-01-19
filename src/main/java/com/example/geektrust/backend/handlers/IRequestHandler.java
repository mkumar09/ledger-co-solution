package com.example.geektrust.backend.handlers;

import com.example.geektrust.backend.response.BaseResponse;

public interface IRequestHandler {
	
	BaseResponse handleRequests() throws Exception;

}
