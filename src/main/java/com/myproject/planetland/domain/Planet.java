package com.myproject.planetland.domain;

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

import com.myproject.planetland.constants.PlanetStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@Table(name = "planet", schema = "planet_land")
public class Planet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "planet_id",unique = true, nullable = false)
	private Long planetId;

	@Column(name = "planet_name")
	private String planetName;

	private int population;

	private int value;

	@Enumerated(EnumType.STRING)
	private PlanetStatus planetStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
}
