package hoon.pepper.conti.persistence.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Slf4j
@DynamicInsert
@NoArgsConstructor
@Table(name = "SONG")
@EntityListeners(AuditingEntityListener.class)
public class SongEntity {

	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long songId;

	@Column(nullable = false)
	private Long contiId;

	@Column(nullable = false)
	private Integer songOrder;

	@Column(length = 20)
	private String title;

	@Column(length = 256)
	private String link;

	@Column(updatable = false, nullable = false)
	@CreatedDate
	private LocalDateTime createdAt;

	@Column
	@LastModifiedDate
	private LocalDateTime updatedAt;
}
