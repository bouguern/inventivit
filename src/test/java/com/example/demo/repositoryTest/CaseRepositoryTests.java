package com.example.demo.repositoryTest;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.entity.Case;
import com.example.demo.repository.CaseRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Tag("IntegrationTest")
@DisplayName("Case Repository Integration Tests")
class CaseRepositoryTests {

	@Autowired
	private CaseRepository caseRepository;

	private Case savedEntity1;
	private Case savedEntity2;
	private long initialCount;

	@BeforeEach
	public void init() {

		this.savedEntity1 = Case.builder().description("description1").title("title1").build();
		this.savedEntity2 = Case.builder().description("description2").title("title2").build();

		this.caseRepository.saveAll(Arrays.asList(savedEntity1, savedEntity2));
		this.initialCount = this.caseRepository.count();
	}

	@AfterEach
	public void teardown() {
		this.caseRepository.deleteAll(Arrays.asList(this.savedEntity1, this.savedEntity2));
	}

	@Test
	@DisplayName("When deleteByCaseId from repository, then deleting should be successful")
	void whenDeleteByIdFromRepository_thenDeletingShouldBeSuccessful() {
		this.caseRepository.deleteById(savedEntity1.getCaseId());

		assertEquals(initialCount - 1, this.caseRepository.count());
	}

	@Test
	@DisplayName("When creating a new Case repository, then returning it")
	void whenCreateCase_thenCreatingShouldBeSuccessful() {

		Case savedEntity = Case.builder().description("description from repository").title("title from repository")
				.build();

		assertEquals(savedEntity, this.caseRepository.save(savedEntity));
		assertEquals(initialCount + 1, this.caseRepository.count());

	}

}
