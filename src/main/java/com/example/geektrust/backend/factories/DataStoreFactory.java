package com.example.geektrust.backend.factories;

import java.util.HashMap;

import com.example.geektrust.backend.enums.DataStoreType;
import com.example.geektrust.backend.models.LoanDetail;
import com.example.geektrust.backend.models.Pair;
import com.example.geektrust.backend.repository.IDataStore;
import com.example.geektrust.backend.repository.InMemoryDataStore;

public class DataStoreFactory {

	 public static IDataStore getDataStore(DataStoreType dataStoreType)
     {   
		 IDataStore dataStore;
         switch(dataStoreType)
         {
         case InMemoryStore : dataStore = new InMemoryDataStore(new HashMap<Pair,LoanDetail>());
         					 break;
         					 
         default : dataStore = new InMemoryDataStore(new HashMap<Pair,LoanDetail>());
 		 		  break;
         };
         
         return dataStore;
     }
}
