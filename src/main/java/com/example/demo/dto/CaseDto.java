package com.example.demo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Schema(description = "This Class holds case (dossier) information.")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CaseDto {

	@Schema(description = "This ID of the case")
	@JsonIgnore
	private Long caseId;

	@Schema(description = "The creation date of the object.")
	@JsonIgnore // but if we need it, we have to remove this annotation. For example: when we want to give the creation date to the user
	private LocalDateTime creationDate;

	@Schema(description = "The last update date of the object.")
	@JsonIgnore // but if we need it, we have to remove this annotation. For example: when we want to give the last updated date to the user
	private LocalDateTime lastUpdateDate;

	@Schema(description = "This title of the case")
	private String title;

	@Schema(description = "This description of the case")
	private String description;

}
