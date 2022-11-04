package hoon.pepper.conti.persistence.entity;

import hoon.pepper.common.code.Depart;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Slf4j
@DynamicInsert
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CONTI")
public class ContiEntity {

	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long contiId;

	@Column(nullable = false)
	private Long categoryId;

	@Column
	@Enumerated(EnumType.STRING)
	private Depart depart;

	@Column
	private LocalDate date;

	@Column(length = 20)
	private String title;

	@Column
	private String password;

	@Column(updatable = false, nullable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@Column
	@LastModifiedDate
	private LocalDateTime updatedAt;
}
