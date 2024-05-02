package com.shivam.fileparser;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ImplementationOne {
	public static void main(String[] args) throws IOException {
		Logger logger = LogManager.getLogger(FIleParserApplication.class) ;
		
		ObjectMapper mapper = new ObjectMapper();

		Map<String, Object> rawMap = mapper.readValue(new File("src/main/resources/lib/customer.json"), new TypeReference<Map<String,Object>>(){});
		// POJO result
		Map<String, Object> pojoResult = new HashMap<>();

		// config values for POJO object to be generated
		Map<String, String> configMap = new HashMap<>();
		configMap.put("custId", "java.lang.Integer");
		configMap.put("custName", "java.lang.String");
		configMap.put("custCity", "java.lang.String");
		configMap.put("custPinCode", "java.lang.Integer");


		logger.info("ConfigMap: "+configMap.toString());
		logger.info("RawMap: "+rawMap.toString());

		// type mapping and parsing logic
		for (Map.Entry<String, String> entry : configMap.entrySet()) {
			String configKey = entry.getKey();    // ex:  custId
			String configType = entry.getValue();
			configType = configType.substring(configType.lastIndexOf(".")+1,configType.length()); // ex:  java.lang.Integer
			logger.info(configType);
			Object value = rawMap.get(configKey);  // ex: 1001
			
			if ((value instanceof Integer) && ("Integer".equals(configMap)) ) {
				pojoResult.put(configKey, Integer.parseInt(value.toString()));
			} else if ("java.lang.String".equals(configType)) {
				pojoResult.put(configKey, value.toString());
			}

		}


		System.out.println(pojoResult);
	}
}
