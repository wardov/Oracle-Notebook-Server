package com.notebook.oracle.version2;

import javax.servlet.http.HttpSession;

import com.notebook.oracle.version2.Input;

public interface BackEndService {
	
	String createFileCode(Input input, HttpSession httpSession);
	
	String createPythonProcess(String fileName);
	
}
