package com.example.demo.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "This Entity holds case (dossier) information.")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "cases")
public class Case {

	@Schema(description = "This ID of the case")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "caseId")
	private Long caseId;

	@Schema(description = "The creation date of the object.")
	@CreatedDate
	@Column(name = "creationDate", nullable = false, updatable = false)
	private LocalDateTime creationDate;

	@Schema(description = "The last update date of the object.")
	@LastModifiedDate
	@Column(name = "lastUpdateDate", insertable = false)
	private LocalDateTime lastUpdateDate;

	@Schema(description = "This title of the case")
	@Column(name = "title", length = 255)
	private String title;

	@Schema(description = "This description of the case")
	@Column(name = "description", length = 2056)
	private String description;

}
