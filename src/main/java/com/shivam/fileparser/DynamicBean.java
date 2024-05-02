package com.shivam.fileparser;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

public class DynamicBean {
	
	protected Map<String,Object> pojoFields = new HashMap<String,Object>();

	public Object get(String name) {
		return pojoFields.get(name);
	}

	// "any getter" needed for serialization    
	@JsonAnyGetter
	public Map<String,Object> any() {
		return pojoFields;
	}

	@JsonAnySetter
	public void set(String name, Object value) {
		pojoFields.put(name, value);
	}

	@Override
	public String toString() {
		return "DynamicBean [pojoFields=" + pojoFields + "]";
	}
	
	
}
