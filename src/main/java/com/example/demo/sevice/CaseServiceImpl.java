package com.example.demo.sevice;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CaseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.CaseMapper;
import com.example.demo.repository.CaseRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CaseServiceImpl implements CaseService {

	private final CaseRepository caseRepository;

	@Override
	public CaseDto createCase(CaseDto caseDto) {

		log.info("Creating a new Case -> {}", caseDto);

		return CaseMapper.toCaseDto(caseRepository.save(CaseMapper.toCaseEntity(caseDto)));
	}

	@Override
	public CaseDto getByCaseId(Long caseId) throws ResourceNotFoundException {

		log.info("Finding the case with caseID : {}", caseId);
		return CaseMapper.toCaseDto(caseRepository.findById(caseId)
				.orElseThrow(() -> new ResourceNotFoundException("Case with id " + caseId + " not found")));
	}

	@Override
	public CaseDto updateCase(Long caseId, CaseDto caseDto) throws ResourceNotFoundException {

		CaseDto updatedCase = CaseMapper.toCaseDto(caseRepository.findById(caseId)
				.orElseThrow(() -> new ResourceNotFoundException("Case with id " + caseId + " not found")));
		updatedCase.setDescription(caseDto.getDescription());
		updatedCase.setTitle(caseDto.getTitle());
		log.info("Updating the case with caseID : {}", caseId);
		return CaseMapper.toCaseDto(caseRepository.save(CaseMapper.toCaseEntity(updatedCase)));

	}

	@Override
	public String deleteCase(Long caseId) {

		caseRepository.findById(caseId).ifPresent(caseRepository::delete);
		log.info("Case with ID {} is deleted Successfully", caseId);
		return "Case deleted Successfully";
	}

}
