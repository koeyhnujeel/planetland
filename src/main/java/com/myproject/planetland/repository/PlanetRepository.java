package com.myproject.planetland.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.planetland.domain.Planet;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {

	List<Planet> findByUser_userId(Long userId);
}
