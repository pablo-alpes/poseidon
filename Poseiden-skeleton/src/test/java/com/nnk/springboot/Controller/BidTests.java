package com.nnk.springboot.Controller;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc // https://stackoverflow.com/questions/73511395/intellij-could-not-autowire-no-beans-of-mockmvc-type-found-but-test-is-ok
public class BidTests {
	@Autowired
	private BidListRepository bidListRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("GET - ADD - Controller status Ok & returns Expected")
	public void GivenList_WhenGET_ReplyOK_AndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/bidList/add";
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
		String url = "/bidList/update/203"; // "id = NN"
		//ACT
		this.mockMvc
				.perform(get(url)
						.param("bidListId", String.valueOf(203)))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("GET - Get List")
	public void GivenId_WhenGETList_ReplyOKAndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/bidList/list";
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
		String url = "/bidList/delete/203";
		int id = 203;
		//ACT
		this.mockMvc
				.perform(get(url)
						.param("bidListId", String.valueOf(id)))
				.andExpect(status().is3xxRedirection());
	}

	//	@WithMockUser(username = "user", roles = {"ADMIN"})
	// by default 'user'
	@Test
	@DisplayName("POST - POSTS Id 1 and checks return and record in the DB")
	public void GivenList_WhenPOST_ReplyOK_AndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/bidList/update/1";
		BidList bidList = new BidList();

		bidList.setId(1);
		bidList.setCreationdate(Instant.now());

		//ACT
		MvcResult mvcResult = this.mockMvc
				.perform(post(url)
						.param("bidListId", "1")
				)
				.andExpect(status().is3xxRedirection())
				.andReturn();

		bidListRepository.save(bidList);

		//assert
		//à corriger : ava.lang.AssertionError: Range for response status value 200
		//Expected :REDIRECTION
		//Actual   :SUCCESSFUL
		assertEquals(mvcResult.getModelAndView().getViewName(), "redirect:/bidList/list");
		assertNotNull(bidListRepository.findById(bidList.getId()));
	}

	@Test
	@DisplayName("POST - VALIDATION - Redirects towards the appropriate link")
	public void GivenUpdate_WhenValidateRequest_ReplyOK_AndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/bidList/validate";
		BidList bidList = new BidList();

		bidList.setId(1);
		bidList.setCreationdate(Instant.now());

		//ACT
		MvcResult mvcResult = this.mockMvc
				.perform(post(url)
						.param("id", "1")
						.param("account", "UserAccount")
						.param("type", "Obligation")
						.param("bidQuantity", "1")
				)
				.andExpect(status().is3xxRedirection())
				.andReturn();

		//assert
		assertEquals(mvcResult.getModelAndView().getViewName(), "redirect:/bidList/list");

	}



}
