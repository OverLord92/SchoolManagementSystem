package com.school.management.test.util;



import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtil {

	public static byte[] convertObjectToFormUrlEncodedBytes(Object object) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		
		@SuppressWarnings("unchecked")
		Map<String, Object> propertyValues = mapper.convertValue(object, Map.class);
		
		Set<String> propertyNames = propertyValues.keySet();
		Iterator<String> nameIter = propertyNames.iterator();
		
		StringBuilder formUrlEncoded = new StringBuilder();
		
		for(int index = 0; index < propertyNames.size(); index++) {
			String currentKey = nameIter.next();
			Object currentValue = propertyValues.get(currentKey);
			
			formUrlEncoded.append(currentKey);
			formUrlEncoded.append("=");
			formUrlEncoded.append(currentValue);
		
			if(nameIter.hasNext()) {
				formUrlEncoded.append("&");
			}
		}
		
		return formUrlEncoded.toString().getBytes();
	}
	
	public static String createStringOfCertainLength(int length) {
		StringBuilder result = new StringBuilder();
		
		for(int i = 0; i < length; i++) {
			result.append("s");
		}
		
		return result.toString();
		
	}

}
