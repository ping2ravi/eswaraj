package com.eswaraj.core.service.impl;

import java.io.InputStream;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.eswaraj.core.exceptions.ApplicationException;
import com.eswaraj.core.service.FileService;

@Component
@Profile("prod")
public class NetworkFileServiceImpl implements FileService {

	@Override
	public void saveFile(String fileDir, String fileName, InputStream inputStream) throws ApplicationException {

	}

}
