package com.eswaraj.core.service;

import java.io.InputStream;

import com.eswaraj.core.exceptions.ApplicationException;

public interface FileService {

	void saveFile(String fileDir,String fileName, InputStream inputStream) throws ApplicationException;
}
