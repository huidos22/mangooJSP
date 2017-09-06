package com.huidos.mangooo.model.dto;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JqgridObjectMapper {
	/**
	 * Maps a jQgrid JSON query to a {@link JqgridFilter} instance
	 */
		
		public static JqgridFilter map(String jsonString) {
			
	    	if (jsonString != null) {
	        	ObjectMapper mapper = new ObjectMapper();
	        	
	        	try {
					return mapper.readValue(jsonString, JqgridFilter.class);
	        	} catch (Exception e) {
					throw new RuntimeException (e);
				}
	    	}
	    	
			return null;
		}
}
