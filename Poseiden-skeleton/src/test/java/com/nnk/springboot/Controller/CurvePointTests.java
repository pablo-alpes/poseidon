package com.nnk.springboot.Controller;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc // https://stackoverflow.com/questions/73511395/intellij-could-not-autowire-no-beans-of-mockmvc-type-found-but-test-is-ok
public class CurvePointTests {

	@Autowired
	private CurvePointRepository curvePointRepository;
	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("GET - ADD - Controller status Ok & returns Expected")
	public void GivenList_WhenGET_ReplyOK_AndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/curvePoint/add";
		//ACT
		this.mockMvc
				.perform(get(url))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("GET - UPDATE - by Id")
	public void GivenId_WhenGETUpdatebyId_ReplyOKAndReturnsExpectedURL() throws Exception {
		//TODO -- Add a case with id in the SQL Records
		//ARRANGE
		String url = "/curvePoint/update/203"; // "id = NN"
		//ACT
		this.mockMvc
				.perform(get(url)
						.param("id", String.valueOf(203)))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("GET - Get List")
	public void GivenId_WhenGETList_ReplyOKAndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/curvePoint/list";
		//ACT
		this.mockMvc
				.perform(get(url))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("DELETE - Delete user by Id")
	public void GivenId_WhenDELETEbyId_ReplyOKAndReturnsExpectedURL() throws Exception {
		//TODO-Add the record to be added & eliminated
		//ARRANGE
		String url = "/curvePoint/delete/203";
		int id = 203;
		//ACT
		this.mockMvc
				.perform(get(url)
						.param("id", String.valueOf(id)))
				.andExpect(status().is3xxRedirection());
	}

	//	@WithMockUser(username = "user", roles = {"ADMIN"})
	// by default 'user'
	@Test
	@DisplayName("POST - POSTS Id 1 and checks return and record in the DB")
	public void GivenList_WhenPOST_ReplyOK_AndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/curvePoint/update/1";
		CurvePoint curvePoint = new CurvePoint();

		curvePoint.setId(1);
		curvePoint.setCurveId(3);
		curvePoint.setAsofdate(Instant.now());
		curvePoint.setTerm(1.0d);
		curvePoint.setValueNumber(2.0d);
		curvePoint.setCreationdate(Instant.now());

		//ACT
		MvcResult mvcResult = this.mockMvc
				.perform(post(url)
						.param("term", "1.0d")
						.param("valueNumber", "2.0d")
						.param("id", "1")
				)
				.andExpect(status().is3xxRedirection())
				.andReturn();

		curvePointRepository.save(curvePoint);

		//assert
		assertEquals(mvcResult.getModelAndView().getViewName(), "redirect:/curvePoint/list");
		assertNotNull(curvePointRepository.findById(curvePoint.getId()));
	}

	@Test
	@DisplayName("POST - VALIDATION - Redirects towards the appropriate link")
	public void GivenUpdate_WhenValidateRequest_ReplyOK_AndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/curvePoint/validate";
		CurvePoint curvePoint = new CurvePoint();

		curvePoint.setId(1);
		curvePoint.setCurveId(3);
		curvePoint.setAsofdate(Instant.now());
		curvePoint.setTerm(1.0d);
		curvePoint.setValueNumber(2.0d);
		curvePoint.setCreationdate(Instant.now());

		//ACT
		MvcResult mvcResult = this.mockMvc
				.perform(post(url)
						.param("term", "1.0d")
						.param("valueNumber", "2.0d")
						.param("id", "1")
				)
				.andExpect(status().is3xxRedirection())
				.andReturn();

		//assert
		assertEquals(mvcResult.getModelAndView().getViewName(), "redirect:/curvePoint/list");

	}



}
