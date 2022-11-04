package hoon.pepper.conti.persistence.entity;

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
@EntityListeners(AuditingEntityListener.class)
@Table(name = "CATEGORY")
public class CategoryEntity {

	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;

	@Column(length = 20)
	private String categoryName;

	@Column(updatable = false, nullable = false)
	@CreatedDate
	private LocalDateTime createdAt;
}
