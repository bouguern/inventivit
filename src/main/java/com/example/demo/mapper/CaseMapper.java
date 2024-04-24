package com.example.demo.mapper;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.demo.dto.CaseDto;
import com.example.demo.entity.Case;

@Component
public class CaseMapper {

	private CaseMapper() {
	}

	public static CaseDto toCaseDto(Optional<Case> caseEntity) {

		if (!caseEntity.isPresent())
			return null;

		return CaseDto.builder().caseId(caseEntity.get().getCaseId()).creationDate(caseEntity.get().getCreationDate())
				.description(caseEntity.get().getDescription()).title(caseEntity.get().getTitle())
				.lastUpdateDate(caseEntity.get().getLastUpdateDate()).build();
	}

	public static CaseDto toCaseDto(Case caseEntity) {

		if (caseEntity == null)
			return null;

		return CaseDto.builder().caseId(caseEntity.getCaseId()).creationDate(caseEntity.getCreationDate())
				.description(caseEntity.getDescription()).title(caseEntity.getTitle())
				.lastUpdateDate(caseEntity.getLastUpdateDate()).build();
	}

	public static Case toCaseEntity(CaseDto caseDto) {

		if (caseDto == null)
			return null;

		return Case.builder().caseId(caseDto.getCaseId()).creationDate(caseDto.getCreationDate())
				.description(caseDto.getDescription()).title(caseDto.getTitle())
				.lastUpdateDate(caseDto.getLastUpdateDate()).build();
	}

}
