package com.shivam.fileparser;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DynamicMappingLogic {
	
	public DynamicBean mapConfigAndSetPOJOFields(DynamicBean dynamicBean, Map<String, Object> configurationMap, String filePath) throws StreamReadException, DatabindException, IOException {
		Logger logger = LogManager.getLogger(DynamicMappingLogic.class) ;
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> rawMap = mapper.readValue(new File(filePath), new TypeReference<Map<String,Object>>(){});
		
		
		for(Entry<String, Object> item: rawMap.entrySet()) {
	//		logger.info(item.getKey() +  "...."+ item.getValue() + "... Type: " + item.getValue().getClass() + "..."+configMap.get(item.getKey()));
			if(item.getValue().getClass().getName() == configurationMap.get(item.getKey())) {
	//			logger.info(item.getKey() +  "...."+ item.getValue());
				dynamicBean.set(item.getKey(), item.getValue());
			}
		}
		
		logger.info("\n...Displaying the POJO fields of bean:");
		System.out.println(dynamicBean.toString());
		for(Entry<String,Object> ele: dynamicBean.any().entrySet()) {
			logger.info(ele.getValue());
		}
		
		return dynamicBean;
	}
}