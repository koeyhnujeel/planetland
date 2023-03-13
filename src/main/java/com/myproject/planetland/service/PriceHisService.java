package com.myproject.planetland.service;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Service;

import com.myproject.planetland.domain.PriceHis;
import com.myproject.planetland.dto.PriceHisDto;
import com.myproject.planetland.mapper.PriceHisMapper;
import com.myproject.planetland.repository.PriceHisRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PriceHisService {

	private final PriceHisRepository priceHisRepository;
	private final PriceHisMapper mapper;

	public List<PriceHisDto> getPriceHisList(Long planetId) {
		List<PriceHis> res = priceHisRepository.findByPlanet_PlanetId(planetId);
		List<PriceHis> res2 = new ArrayList<>();

		if (res.size() >= 8) {
			for (int i = res.size() - 7; i < res.size(); i++) {
				res2.add(res.get(i));
			}
			return mapper.modelToDto(res2);
		} else {
			return mapper.modelToDto(res);
		}
	}
}
