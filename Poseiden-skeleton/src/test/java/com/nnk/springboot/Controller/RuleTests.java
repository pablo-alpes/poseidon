package com.nnk.springboot.Controller;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc // https://stackoverflow.com/questions/73511395/intellij-could-not-autowire-no-beans-of-mockmvc-type-found-but-test-is-ok
@WithMockUser(username = "test")
public class RuleTests {
	@Autowired
	private RuleNameRepository ruleNameRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("GET - ADD - Controller status Ok & returns Expected")
	public void GivenList_WhenGET_ReplyOK_AndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/ruleName/add";
		//ACT
		this.mockMvc
				.perform(get(url))
				.andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("test")
	@DisplayName("GET - UPDATE - by Id")
	public void GivenId_WhenGETUpdatebyId_ReplyOKAndReturnsExpectedURL() throws Exception {
		//TODO -- Add test SQL of the record 203
		//ARRANGE
		String url = "/ruleName/update/203"; // "id = NN"
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
		String url = "/ruleName/list";
		//ACT
		this.mockMvc
				.perform(get(url))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("DELETE - Delete user by Id")
	public void GivenId_WhenDELETEbyId_ReplyOKAndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/ruleName/delete/204";
		int id = 204;
		//ACT
		this.mockMvc
				.perform(get(url)
						.param("id", String.valueOf(id)))
				.andExpect(status().is3xxRedirection());
	}


	// by default 'user'
	@Test
	@WithUserDetails("test")
	@DisplayName("POST - POSTS Id 1 and checks return and record in the DB")
	public void GivenList_WhenPOST_ReplyOK_AndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/ruleName/update/1";
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		rule.setId(1);

		//ACT
		MvcResult mvcResult = this.mockMvc
	         			.perform(post(url)
								.param("name", "Rule Name")
								.param("description", "desc")
								.param("json", "Json")
								.param("template", "Template")
								.param("sql", "SQL")
								.param("sqlPart", "SQLpart")
				)
				.andExpect(status().is3xxRedirection())
				.andReturn();

		ruleNameRepository.save(rule);

		//assert
		assertEquals(Objects.requireNonNull(mvcResult.getModelAndView()).getViewName(), "redirect:/ruleName/list");
		assertNotNull(ruleNameRepository.findById(rule.getId()));
	}

	@Test
	@DisplayName("POST - VALIDATION - Redirects towards the appropriate link once validates")
	public void GivenUpdate_WhenValidateRequest_ReplyOK_AndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/ruleName/validate";

		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
		rule.setId(1);

		//ACT
		MvcResult mvcResult = this.mockMvc
				.perform(post(url)
						.param("name", "Rule Name")
						.param("description", "desc")
						.param("json", "Json")
						.param("template", "Template")
						.param("sql", "SQL")
						.param("sqlPart", "SQLpart")
				)
				.andExpect(status().is3xxRedirection())
				.andReturn();

		//assert
		assertEquals(Objects.requireNonNull(mvcResult.getModelAndView()).getViewName(), "redirect:/ruleName/list");

	}



}