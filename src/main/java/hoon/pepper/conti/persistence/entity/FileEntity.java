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
@Table(name = "FILE")
public class FileEntity {

	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileId;

	@Column(length = 100)
	private String filePath;

	@Column(length = 100)
	private String fileAccessKey;

	@Column(nullable = false, length = 100)
	private String fileName;

	@Column(nullable = false, length = 100)
	private String orgFileName;

	@Column(nullable = false, length = 10)
	private String fileType;

	@Column
	private Long fileSize;

	@Column(updatable = false, nullable = false)
	@CreatedDate
	private LocalDateTime createdAt;
}
