package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//Test for Controller
@WebAppConfiguration

@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", 
						"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardControllerTests {
	
	@Setter(onMethod_ = {@Autowired} )
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc; 
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
	
	@Test //db리스트 보여줌
	public void testList() throws Exception {
		log.info(
				mockMvc.perform(
						MockMvcRequestBuilders.get("/board/list"))
				.andReturn()
				.getModelAndView()
				.getModelMap());
	}
	
	@Test //등록 - 알아서 파라미터 수집해줌
	public void testRegister() throws Exception {
		log.info(
				mockMvc.perform(
						MockMvcRequestBuilders.post("/board/register")
						.param("title", "Test 테스트")
						.param("content", "Content")
						.param("writer", "user10")
						) 
				.andReturn() 
				.getModelAndView()
				.getViewName());
	}
	
	@Test //수정
	public void testModify() throws Exception {
		log.info(
				mockMvc.perform(
						MockMvcRequestBuilders.post("/board/modify")
						.param("bno", "4")
						.param("title", "수정된 테스트 새글 title")
						.param("content", "수정된 테스트 새글 content")
						.param("writer", "user123")
						) 
				.andReturn() 
				.getModelAndView()
				.getViewName());
	}
	@Test //삭제
	public void testRemove() throws Exception {
		log.info(
				mockMvc.perform(
						MockMvcRequestBuilders.post("/board/remove")
						.param("bno", "4")
						) 
				.andReturn() 
				.getModelAndView()
				.getViewName());
	}
}
