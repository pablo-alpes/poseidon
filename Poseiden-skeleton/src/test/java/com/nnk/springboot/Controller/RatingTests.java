package com.nnk.springboot.Controller;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
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
public class RatingTests {
	@Autowired
	private RatingRepository ratingRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("GET - ADD - Controller status Ok & returns Expected")
	public void givenListWhenGETReplyOKAndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/rating/add";
		//ACT
		this.mockMvc
				.perform(get(url))
				.andExpect(status().isOk());
	}

	@Test
	@WithUserDetails("test")
	@DisplayName("GET - UPDATE - by Id")
	public void givenIdWhenGETUpdatebyIdReplyOKAndReturnsExpectedURL() throws Exception {
		//TODO -- Add test SQL of the record 203
		//ARRANGE
		String url = "/rating/update/203"; // "id = NN"
		//ACT
		this.mockMvc
				.perform(get(url)
						.param("id", String.valueOf(203)))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("GET - Get List")
	public void givenIdWhenGETListReplyOKAndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/rating/list";
		//ACT
		this.mockMvc
				.perform(get(url))
				.andExpect(status().isOk());
	}

	@Test
	@DisplayName("DELETE - Delete user by Id")
	public void givenIdWhenDELETEbyIdReplyOKAndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/rating/delete/204";
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
	public void givenListWhenPOSTReplyOKAndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/rating/update/1";
		Rating rating = new Rating("Moodys Rating", "S&PRating", "Fitch Rating", 10);
		rating.setId(1);

		//ACT
		MvcResult mvcResult = this.mockMvc
				.perform(post(url)
						.param("id", "1")
						.param("moodysRating", "Moodys Rating")
						.param("sandPRating", "S&PRating")
			  			.param("fitchRating", "1")
						.param("orderNumber", "10")
				)
				.andExpect(status().is3xxRedirection())
				.andReturn();

		ratingRepository.save(rating);

		//assert
		assertEquals(Objects.requireNonNull(mvcResult.getModelAndView()).getViewName(), "redirect:/rating/list");
		assertNotNull(ratingRepository.findById(rating.getId()));
	}

	@Test
	@DisplayName("POST - VALIDATION - Redirects towards the appropriate link once validates")
	public void givenUpdateWhenValidateRequestReplyOKAndReturnsExpectedURL() throws Exception {
		//ARRANGE
		String url = "/rating/validate";

		Rating rating = new Rating("Moodys Rating", "S&PRating", "Fitch Rating", 10);
		rating.setId(1);

		//ACT
		MvcResult mvcResult = this.mockMvc
				.perform(post(url)
						.param("id", "1")
						.param("moodysRating", "Moodys Rating")
						.param("sandPRating", "S&PRating")
						.param("fitchRating", "1")
						.param("orderNumber", "10")
				)
				.andExpect(status().is3xxRedirection())
				.andReturn();

		//assert
		assertEquals(Objects.requireNonNull(mvcResult.getModelAndView()).getViewName(), "redirect:/rating/list");

	}



}