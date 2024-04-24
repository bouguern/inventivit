package com.example.demo.sevice;

import com.example.demo.dto.CaseDto;
import com.example.demo.exception.ResourceNotFoundException;

public interface CaseService {

	public CaseDto createCase(CaseDto caseDto);

	public CaseDto getByCaseId(Long caseId) throws ResourceNotFoundException;

	public CaseDto updateCase(Long caseId, CaseDto caseDto) throws ResourceNotFoundException;

	public String deleteCase(Long caseId);

}
