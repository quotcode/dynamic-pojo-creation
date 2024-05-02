package com.shivam.fileparser;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class FIleParserApplication {
	

	public static void main(String[] args) throws IOException {
		Logger logger = LogManager.getLogger(FIleParserApplication.class) ;
		SpringApplication.run(FIleParserApplication.class, args);

		// config values for POJO object to be generated
		Map<String, Object> configMap = new HashMap<>();
		configMap.put("custId", "java.lang.Integer");
		configMap.put("custName", "java.lang.String");
		configMap.put("custCity", "java.lang.String");
		configMap.put("custPinCode", "java.lang.Integer");
		configMap.put("empId", "java.lang.Integer");
		configMap.put("empName", "java.lang.String");
		configMap.put("empSalary", "java.lang.Double");
		
		File folder = new File("src/main/resources/lib/");
		File[] listOfFiles = folder.listFiles();

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		    	new DynamicMappingLogic().mapConfigAndSetPOJOFields(new DynamicBean(),configMap,file.toString());
		        
		    }
		}
	}
}

