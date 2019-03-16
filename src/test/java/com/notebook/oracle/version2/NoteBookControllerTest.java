package com.notebook.oracle.version2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import javax.servlet.http.HttpSession;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notebook.oracle.version2.Input;
import com.notebook.oracle.version2.Output;
import com.notebook.oracle.version2.BackEndService;

@RunWith(SpringRunner.class)
@WebMvcTest(NoteBookController.class)

public class NoteBookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BackEndService backEndService;

	@Before
	public void setUp() throws JsonProcessingException {

		Output expected = new Output();
		expected.setResult("2");

		String intoJson = this.mapJson(expected);

		Input input = new Input();
		input.setCode("print(1+1)");

		HttpSession session = new MockHttpSession();

		String fileName = "code" + session.getId() + ".py";
		when(backEndService.createFileCode(input, session)).thenReturn(fileName);
		when(backEndService.createPythonProcess(fileName)).thenReturn(intoJson);

	}

	@Test
	public void testInterprete() throws Exception {

		String URI = "/execute";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String outPutJson = response.getContentAsString();

		assertThat(outPutJson).isEqualTo("");

	}

	private String mapJson(Object object) throws JsonProcessingException {
		ObjectMapper om = new ObjectMapper();
		return om.writeValueAsString(object);
	}

}
