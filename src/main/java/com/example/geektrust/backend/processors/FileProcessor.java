package com.example.geektrust.backend.processors;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.geektrust.backend.constants.Constants;
import com.example.geektrust.backend.enums.DataStoreType;
import com.example.geektrust.backend.factories.DataStoreFactory;
import com.example.geektrust.backend.factories.RequestHandlerFactory;
import com.example.geektrust.backend.handlers.BalanceHandler;
import com.example.geektrust.backend.handlers.IRequestHandler;
import com.example.geektrust.backend.repository.IDataStore;
import com.example.geektrust.backend.response.BalanceResponse;
import com.example.geektrust.backend.response.BaseResponse;


public class FileProcessor implements IProcessor {

	private String inputFile;

	public FileProcessor(String inputFile) throws Exception {
		if (inputFile.equals(" ") || inputFile == null) {
			throw new Exception(Constants.FilePathError);
		}

		this.inputFile = inputFile;
	}

	public void process() throws Exception
    {
        List<String> commands = getCommands();
        IDataStore dataStore = DataStoreFactory.getDataStore(DataStoreType.InMemoryStore);
        for(String commandParams : commands)
        {
            IRequestHandler requestHandler = RequestHandlerFactory.GetRequestHandler(commandParams,dataStore);
            if (requestHandler != null)
            {
                BaseResponse response = requestHandler.handleRequests();
                if (requestHandler instanceof BalanceHandler && response.success)
                {
                	BalanceResponse balanceResponse = (BalanceResponse)response;
                    System.out.println(balanceResponse.bankName+" "+balanceResponse.borrowerName+" "+balanceResponse.amountPaid+" "+balanceResponse.remainingEmis);
                }
            }
            else
            {
                System.out.println(Constants.InvalidCommand);
            }
        }
    }

	private List<String> getCommands() {
		List<String> commands = new ArrayList<String>();
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(inputFile));
			String line = reader.readLine();
			while (line != null) {
				commands.add(line);
				line = reader.readLine();
			}
			reader.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		return commands;
	}

}
