package com.example.demo.controllerTest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import java.time.LocalDateTime;
import static org.mockito.Mockito.when;
import com.example.demo.dto.CaseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.sevice.CaseService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Tag("IntegrationTest")
@DisplayName("Case Controller Integration Tests")
class CaseControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private CaseService caseService;

	@Test
	@DisplayName("Get Case, should return expected Case")
	void getCaseShouldReturn200() throws Exception {

		// given
		Long caseId = 0L;
		given(this.caseService.getByCaseId(caseId)).willReturn(new CaseDto());

		// when-then
		this.mockMvc.perform(get("/cases/" + caseId).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());

	}

	@Test
	@DisplayName("Get non existing Case, should return 404")
	void getNotExistingCaseShouldReturn404() throws Exception {

		// given
		Long caseId = 404L;
		given(this.caseService.getByCaseId(caseId))
				.willThrow(new ResourceNotFoundException("Case with id " + caseId + " not found"));

		// when-then
		this.mockMvc.perform(get("/cases/" + caseId)).andExpect(status().isNotFound());
	}

	@Test
	@DisplayName("Create Case, should return 201")
	void createCaseShouldReturn201() throws Exception {

		// given
		CaseDto defaultCaseDto = CaseDto.builder().caseId(0L).build();
		CaseDto caseDto = CaseDto.builder().caseId(1L).creationDate(LocalDateTime.of(2024, 8, 27, 10, 15))
				.lastUpdateDate(null).title("title").description("description").build();
		String json = objectMapper.writeValueAsString(caseDto);
		when(this.caseService.createCase(caseDto)).thenReturn(defaultCaseDto);

		// when-then
		this.mockMvc.perform(post("/cases/").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isCreated());
	}

	@Test
	@DisplayName("Delete Case, should return expected message 'Case deleted Successfully'")
	void deleteCaseShouldReturn204() throws Exception {

		// given
		Long caseId = 0L;
		when(this.caseService.deleteCase(caseId)).thenReturn("Case deleted Successfully");

		// when-then
		this.mockMvc.perform(delete("/cases/" + caseId)).andExpect(status().isOk());
	}

}
