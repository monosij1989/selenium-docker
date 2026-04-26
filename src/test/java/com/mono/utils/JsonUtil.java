package com.mono.utils;

import java.io.InputStream;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {
	private static final Logger LOG = LoggerFactory.getLogger(JsonUtil.class);
	private static final ObjectMapper mapper = new ObjectMapper();
	
	public static <T> T getTestData(String path, Class<T> type) {
		try(InputStream stream = ResourceLoader.getInputStream(path)) {
			return mapper.readValue(stream, type);
		} catch (Exception e) {
			LOG.error("Unable to read test data from path {}", path, e);
		}
		return null;
	}
	

}
