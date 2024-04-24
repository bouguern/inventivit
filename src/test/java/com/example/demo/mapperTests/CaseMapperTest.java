package com.example.demo.mapperTests;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.dto.CaseDto;
import com.example.demo.entity.Case;
import com.example.demo.mapper.CaseMapper;

@ActiveProfiles("test")
@Tag("UnitTest")
@DisplayName("Case Mapper Unit Tests")
class CaseMapperTest {

	@Test
	@DisplayName("When convert Case entity to Case dto, then correct")
	void whenConvertCaseEntityToCaseDto_thenCorrect() {

		// given
		Case case1 = Case.builder()
				.caseId(1L)
				.title("case mapper title")
				.description("case mapper description")
				.build();

		// when

		CaseDto caseDto = CaseMapper.toCaseDto(case1);

		// then
		assertEquals(case1.getCaseId(), caseDto.getCaseId());
		assertEquals(case1.getTitle(), caseDto.getTitle());
		assertEquals(case1.getDescription(), caseDto.getDescription());
	}

	@Test
	@DisplayName("When convert Case dto to Case entity, then correct")
	void whenConvertCaseDtoToCaseEntity_thenCorrect() {

		// given
		CaseDto caseDto = CaseDto.builder()
				.caseId(1L)
				.title("case mapper title")
				.description("case mapper description")
				.build();

		// when
		Case case1 = CaseMapper.toCaseEntity(caseDto);

		// then
		assertEquals(caseDto.getCaseId(), case1.getCaseId());
		assertEquals(caseDto.getTitle(), case1.getTitle());
		assertEquals(caseDto.getDescription(), case1.getDescription());
	}

}
