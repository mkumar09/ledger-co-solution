package com.example.geektrust.backend.factories;

import com.example.geektrust.backend.enums.ProcessorType;
import com.example.geektrust.backend.processors.FileProcessor;
import com.example.geektrust.backend.processors.IProcessor;

public class ProcessorFactory {

	public static IProcessor CreateProcessor(ProcessorType processorType, String inputFile) throws Exception
    {
       IProcessor processor;
        switch(processorType)
        {
        // Add more Processor Types when and if needed if input data source changes
        case FileProcessor : processor = new FileProcessor(inputFile);
        					 break;
        					 
        default : processor = new FileProcessor(inputFile);
		 		  break;
        };
        
        return processor;
    }
	
}
 