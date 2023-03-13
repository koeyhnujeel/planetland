package com.myproject.planetland.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.planetland.domain.PriceHis;

@Repository
public interface PriceHisRepository extends JpaRepository<PriceHis, Long> {

	List<PriceHis> findByPlanet_PlanetId(Long planetId);

}
