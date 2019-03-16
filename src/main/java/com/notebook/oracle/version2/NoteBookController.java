package com.notebook.oracle.version2;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.notebook.oracle.version2.Input;
import com.notebook.oracle.version2.Output;
import com.notebook.oracle.version2.BackEndService;

@Controller
public class NoteBookController {

	@Autowired
	private BackEndService backEndService;

	@RequestMapping("/execute")
	@ResponseBody

	public Output Interprete(@RequestBody Input input, HttpSession httpSession) {

		Output output = new Output();

		
		String fileName = backEndService.createFileCode(input, httpSession);

		
		String outString = backEndService.createPythonProcess(fileName);

		output.setResult(outString);

		return output;

	}

}