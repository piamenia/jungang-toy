package hoon.pepper.conti.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Slf4j
@DynamicInsert
@NoArgsConstructor
@Builder
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "SHEET")
public class SheetEntity {

	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sheetId;

	@Column(nullable = false)
	private Long songId;

	@Column(nullable = false)
	private Integer sheetOrder;

	@Column(nullable = false)
	private Long fileId;

	@Column(updatable = false, nullable = false)
	@CreatedDate
	private LocalDateTime createdAt;
}
