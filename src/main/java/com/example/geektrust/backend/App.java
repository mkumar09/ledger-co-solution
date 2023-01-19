package com.example.geektrust.backend;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.example.geektrust.backend.constants.Constants;
import com.example.geektrust.backend.enums.ProcessorType;
import com.example.geektrust.backend.exceptions.ProvideInputException;
import com.example.geektrust.backend.factories.ProcessorFactory;
import com.example.geektrust.backend.processors.IProcessor;

public class App {
	public static void main(String[] args) {
		 try
         {
             if (args.length == 0)
             {
                throw new ProvideInputException(Constants.InvalidInputFile);
             }
             else
             {
            	 List<String> commandLineArgs = new LinkedList<>(Arrays.asList(args));
            	 String inputFile = commandLineArgs.get(Constants.ZERO);
                 ProcessCommands(inputFile);
             }
         }
         catch (Exception ex)
         {
             System.out.println(ex.getMessage());
         }
	}
	
	private static void ProcessCommands(String inputFile) throws Exception
    {
        IProcessor processor = ProcessorFactory.CreateProcessor(ProcessorType.FileProcessor, inputFile );
        processor.process();
    }
    }
