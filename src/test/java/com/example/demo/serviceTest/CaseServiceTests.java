package com.example.demo.serviceTest;

import static org.mockito.ArgumentMatchers.any;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.dto.CaseDto;
import com.example.demo.entity.Case;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CaseRepository;
import com.example.demo.sevice.CaseService;
import com.example.demo.sevice.CaseServiceImpl;

import static org.mockito.Mockito.mock;

@TestInstance(Lifecycle.PER_CLASS)
@ActiveProfiles("test")
@Tag("UnitTest")
@DisplayName("Case Service Unit Tests")
class CaseServiceTests {

	private CaseRepository caseRepository;
	private CaseService caseService;

	@BeforeAll
	public void init() {
		caseRepository = mock(CaseRepository.class);
		caseService = new CaseServiceImpl(caseRepository);
	}

	@Test
	@DisplayName("Given Case id, when get Case, then Case is retrieved")
	void givenCaseId_whenGetCase_ThenCaseRetrieved() throws ResourceNotFoundException {

		// given
		Long existingCaseId = 1L;
		Case case1 = Case.builder().caseId(1L).description("description").title("title")
				.creationDate(LocalDateTime.of(2024, 8, 27, 10, 15)).lastUpdateDate(null).build();
		when(caseRepository.findById(existingCaseId)).thenReturn(Optional.of(case1));

		// when
		CaseDto caseDto1 = caseService.getByCaseId(existingCaseId);

		// then
		assertNotNull(case1);
		assertNotNull(case1.getCaseId());
		assertEquals(caseDto1.getCaseId(), case1.getCaseId());
	}

	@Test
	@DisplayName("Given Case id, when get non existing Case, then exception is thrown")
	void givenCaseId_whenGetNonExistingCase_ThenExceptionThrown() {

		// given
		Long nonExistingCaseId = 404L;
		String errorMsg = "Case with id " + nonExistingCaseId + " not found";

		// when
		ResourceNotFoundException throwException = assertThrows(ResourceNotFoundException.class,
				() -> caseService.getByCaseId(nonExistingCaseId));

		// then
		assertEquals(errorMsg, throwException.getMessage());
	}

	@Test
	@DisplayName("Given Case data, when create new Case, then Case id is returned")
	void givenCaseData_whenCreateCase_ThenCaseIdReturned() {

		// given
		CaseDto caseDto1 = CaseDto.builder().caseId(1L).description("description").title("title")
				.creationDate(LocalDateTime.of(2024, 8, 27, 10, 15)).lastUpdateDate(null).build();
		Case case1 = Case.builder().caseId(1L).description("description").title("title")
				.creationDate(LocalDateTime.of(2024, 8, 27, 10, 15)).lastUpdateDate(null).build();

		// when
		when(caseRepository.save(any(Case.class))).thenReturn(case1);
		CaseDto savedCase = caseService.createCase(caseDto1);

		// then
		assertNotNull(savedCase);
		assertEquals(case1.getCaseId(), savedCase.getCaseId());
	}

	@Test
	@DisplayName("Given Case id, when delete Case, then Case is retrieved")
	void givenCaseId_whenDeleteCase_ThenCaseRetrieved() {

		// given
		Long existingCaseId = 1L;
		Case case1 = Case.builder().caseId(1L).description("description").title("title")
				.creationDate(LocalDateTime.of(2024, 8, 27, 10, 15)).lastUpdateDate(null).build();
		when(caseRepository.findById(existingCaseId)).thenReturn(Optional.of(case1));

		// when
		String deletedMessage = caseService.deleteCase(existingCaseId);

		// then
		assertNotNull(deletedMessage);
		assertEquals("Case deleted Successfully", deletedMessage);
	}

}
