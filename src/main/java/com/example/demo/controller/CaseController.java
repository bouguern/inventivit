package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.CaseDto;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.sevice.CaseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/cases")
@RestController
@RequiredArgsConstructor
@Slf4j
public class CaseController {

	private final CaseService caseService;

	@Operation(summary = "Creates a new Case")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Created the Case"),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content) })
	@PostMapping
	public ResponseEntity<CaseDto> createCase(@RequestBody CaseDto caseDto) {
		log.info("Creating a new Case -> {}", caseDto);
		CaseDto savedCase = caseService.createCase(caseDto);
		log.info("Case created successfully -> {}", savedCase);
		return new ResponseEntity<>(savedCase, HttpStatus.CREATED);
	}

	@Operation(summary = "Get one Case by caseId")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the Case", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CaseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Case not found", content = @Content) })
	@GetMapping("/{caseId}")
	public ResponseEntity<CaseDto> getByCaseId(@PathVariable("caseId") Long caseId) throws ResourceNotFoundException {
		log.info("Searching the case with caseID -> {}", caseId);
		CaseDto returnedCase = caseService.getByCaseId(caseId);
		log.info("The case with caseID : {} is found", caseId);
		return new ResponseEntity<>(returnedCase, HttpStatus.OK);

	}

	@Operation(summary = "Update one Case by caseId")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Updated the Case", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = CaseDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied", content = @Content),
			@ApiResponse(responseCode = "404", description = "Case not found", content = @Content) })
	@PutMapping("/{caseId}")
	public ResponseEntity<CaseDto> updateCase(@PathVariable("caseId") Long caseId, @RequestBody CaseDto caseDto)
			throws ResourceNotFoundException {
		log.info("Searching the case with caseID -> {}", caseId);
		CaseDto returnedCase = caseService.updateCase(caseId, caseDto);
		log.info("The case with caseID : {} is updated", caseId);
		return ResponseEntity.ok().body(returnedCase);
	}

	@Operation(summary = "Delete one Case by caseId")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Deleted the Case", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = CaseDto.class)) }) })
	@DeleteMapping("/{caseId}")
	public ResponseEntity<String> deleteCase(@PathVariable("caseId") Long caseId) throws ResourceNotFoundException {

		log.info("Delete the case with caseID -> {}", caseId);
		return new ResponseEntity<>(caseService.deleteCase(caseId), HttpStatus.OK);

	}
}
