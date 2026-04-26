package com.mono.utils;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResourceLoader {
	
	private static final Logger LOG = LoggerFactory.getLogger(ResourceLoader.class);
	
	public static InputStream getInputStream(String path) throws Exception  {
		LOG.info("Reading resource from path: {}", path);
		InputStream stream = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
		if (Objects.isNull(stream))
			stream = Files.newInputStream(Path.of(path));
		return stream;
	}

}
