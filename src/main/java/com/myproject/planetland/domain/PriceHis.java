package com.myproject.planetland.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
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

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "price_his", schema = "planet_land")
@Getter @Setter @ToString
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class PriceHis {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "priceHis_id", unique = true, nullable = false)
	private Long priceHisId;

	private int price;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@CreatedDate
	private LocalDate date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "planet_id")
	private Planet planet;
}
