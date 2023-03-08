package com.myproject.planetland.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
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

import com.myproject.planetland.constants.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@Table(name = "transaction_his", schema = "planet_land")
public class OrderHis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transactionHis_id", unique = true, nullable = false)
	private Long transactionHisId;

	@Enumerated(EnumType.STRING)
	private Type type;

	@CreationTimestamp
	private Date date;

	private String planetName;
	private int value;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
}
