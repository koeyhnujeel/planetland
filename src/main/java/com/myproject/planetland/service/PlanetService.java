package com.myproject.planetland.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.myproject.planetland.constants.ImgDir;
import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.dto.AddPlanetDto;
import com.myproject.planetland.dto.PlanetListDto;
import com.myproject.planetland.mapper.PlanetMapper;
import com.myproject.planetland.repository.PlanetRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanetService {

	private final PlanetRepository planetRepository;
	private final PlanetMapper mapper;

	public Planet getPlanet(Long planetId) {
		Optional<Planet> res = planetRepository.findById(planetId);
		if (res.isPresent()) {
			return res.get();
		} else {
			throw new EntityNotFoundException(String.format("아이디 %d로 조회되는 행성이 없습니다.", planetId));
		}
	}

	public Planet getPlanet(String planetName) {
		Optional<Planet> res = planetRepository.findByPlanetName(planetName);
		if (res.isPresent()) {
			return res.get();
		} else {
			throw new EntityNotFoundException(String.format("%s는 존재하지 않는 행성입니다.", planetName));
		}
	}

	public List<Planet> getPlanetByUserId(Long userId) {
		List<Planet> res = planetRepository.findByUser_userId(userId);
		if (res.isEmpty()) {
			throw new EntityNotFoundException("소유한 행성이 없습니다.");
		} else {
			return res;
		}
	}

	public List<PlanetListDto> getAllPlanet(String keyword) {
		if(keyword.equals("기본 순")) {
			List<Planet> planetList = planetRepository.findAll();
			List<PlanetListDto> planetListDtos = mapper.ModelToDtoList(planetList);
			return planetListDtos;

		} else if (keyword.equals("높은 가치 순")) {
			List<Planet> allOrderByValueDesc = planetRepository.findAllByOrderByValueDesc();
			List<PlanetListDto> planetListDtosDesc = mapper.ModelToDtoList(allOrderByValueDesc);
			return planetListDtosDesc;

		} else if (keyword.equals("낮은 가치 순")) {
			List<Planet> allOrderByValueAsc = planetRepository.findAllByOrderByValueAsc();
			List<PlanetListDto> planetListDtosAsc = mapper.ModelToDtoList(allOrderByValueAsc);
			return planetListDtosAsc;
		} else {
			throw new IllegalArgumentException("잘못된 정렬입니다.");
		}
	}

	public AddPlanetDto createPlanet(AddPlanetDto addPlanetDto, MultipartFile imgFile) throws IOException {
		Optional<Planet> res = planetRepository.findByPlanetName(addPlanetDto.getPlanetName());
		if (res.isPresent()) {
			throw new IllegalArgumentException("이미 존재하는 행성입니다.");
		} else {
			String oriName = imgFile.getOriginalFilename();
			String myPath = ImgDir.PATH;

			UUID uuid = UUID.randomUUID(); // 파일이름 중복을 피하기 위한 식별자 역할
			String savedFileName = uuid + "_" + oriName;

			imgFile.transferTo(new File(myPath+savedFileName));

			addPlanetDto.setImgName(savedFileName);
			addPlanetDto.setImgPath("/images/"+savedFileName);

			Planet planet = mapper.addPlanetDtoToModel(addPlanetDto);
			planetRepository.save(planet);

			return addPlanetDto;
		}
	}

	public AddPlanetDto updatePlanet(Long planetId, AddPlanetDto addPlanetDto) {
		Optional<Planet> res = planetRepository.findById(planetId);
		if (res.isPresent()) {
			Planet updatePlanet = res.get();
			updatePlanet.setPlanetName(addPlanetDto.getPlanetName());
			updatePlanet.setPopulation(addPlanetDto.getPopulation());
			updatePlanet.setValue(addPlanetDto.getValue());
			updatePlanet.setImgPath(addPlanetDto.getImgPath());
			updatePlanet.setPlanetStatus(addPlanetDto.getPlanetStatus());
			Planet savedPlanet = planetRepository.save(updatePlanet);
			AddPlanetDto updatePlantDto = mapper.ModelToAddPlanetDto(savedPlanet);
			return updatePlantDto;
		} else {
			throw new IllegalArgumentException("잘못된 경로입니다.");
		}
	}
	public void deletePlanet(Long planetId) throws IOException {
		Optional<Planet> res = planetRepository.findById(planetId);
		if (res.isEmpty()) {
			throw new EntityNotFoundException(String.format("아이디 %d로 조회되는 행성이 없습니다.", planetId));
		}

		String myPath = ImgDir.PATH;
		String fileName = res.get().getImgName();
		File file = new File(myPath + fileName);
		if (fileName != null) {
			file.delete();
		}
		planetRepository.deleteById(planetId);
	}
}
