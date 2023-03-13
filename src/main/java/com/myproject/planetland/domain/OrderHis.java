package com.myproject.planetland.domain;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.myproject.planetland.constants.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "transaction_his", schema = "planet_land")
public class OrderHis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transactionHis_id", unique = true, nullable = false)
	private Long transactionHisId;

	@Enumerated(EnumType.STRING)
	private Type type;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@CreatedDate
	private LocalDateTime date;

	private String planetName;
	private int price;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
}
