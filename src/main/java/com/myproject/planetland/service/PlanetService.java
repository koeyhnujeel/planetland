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
import com.myproject.planetland.constants.PlanetStatus;
import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.domain.User;
import com.myproject.planetland.dto.AddPlanetDto;
import com.myproject.planetland.dto.PlanetListDto;
import com.myproject.planetland.mapper.PlanetMapper;
import com.myproject.planetland.repository.PlanetRepository;
import com.myproject.planetland.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanetService {

	private final PlanetRepository planetRepository;
	private final UserRepository userRepository;
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
		if (keyword.equals("기본 순")) {
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
			String savedFileName = getFileName(imgFile);

			addPlanetDto.setImgName(savedFileName);
			addPlanetDto.setImgPath("/images/" + savedFileName);

			Planet planet = mapper.addPlanetDtoToModel(addPlanetDto);
			Planet savedPlanet = planetRepository.save(planet);

			return mapper.ModelToAddPlanetDto(savedPlanet);
		}
	}

	public AddPlanetDto updatePlanet(Long planetId, AddPlanetDto addPlanetDto, MultipartFile imgFile) throws IOException {
		Optional<Planet> res = planetRepository.findById(planetId);
		if (res.isPresent()) {
			Planet updatePlanet = res.get();
			updatePlanet.setPlanetName(addPlanetDto.getPlanetName());
			updatePlanet.setPopulation(addPlanetDto.getPopulation());
			updatePlanet.setValue(addPlanetDto.getValue());
			updatePlanet.setPlanetStatus(addPlanetDto.getPlanetStatus());
			if (imgFile != null) {
				if (updatePlanet.getImgPath() != null) {
					String myPath = ImgDir.PATH;
					File file1 = new File(myPath, updatePlanet.getImgName());
					file1.delete();
				}
				String savedFileName = getFileName(imgFile);
				updatePlanet.setImgName(savedFileName);
				updatePlanet.setImgPath("/images/" + savedFileName);
			}

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

	public void buyPlanet(String userName, Long planetId) {
		Optional<User> resUser = userRepository.findByUserName(userName);
		Optional<Planet> resPlanet = planetRepository.findById(planetId);
		if (resUser.isEmpty() || resPlanet.isEmpty()) {
			throw new IllegalArgumentException("잘못된 경로입니다.");
		}

		User user = resUser.get();
		Planet planet = resPlanet.get();

		if (planet.getUser() != null) {
			User res = planet.getUser();
			List<Planet> planets = res.getPlanets();
			for (int i = 0; i < planets.size(); i++) {
				if (planets.get(i).getPlanetName().equals(planet.getPlanetName())) {
					planets.remove(i);
					res.setPlanets(planets);
					userRepository.save(res);
				}
			}
		}
		planet.setUser(user);
		planet.setPlanetStatus(PlanetStatus.SOLD_OUT);
		user.setAsset(user.getAsset() - planet.getValue());
		user.getPlanets().add(planet);
		userRepository.save(user);
		planetRepository.save(planet);
	}

	public void sellPlanet(Long planetId) {
		Optional<Planet> res = planetRepository.findById(planetId);
		if (res.isEmpty()) {
			throw new IllegalArgumentException("잘못된 경로입니다.");
		}

		Planet planet = res.get();
		planet.setPlanetStatus(PlanetStatus.ON_SALE);
		planetRepository.save(planet);
	}
	public void cancelSellPlanet(Long planetId) {
		Optional<Planet> res = planetRepository.findById(planetId);
		if (res.isEmpty()) {
			throw new IllegalArgumentException("잘못된 경로입니다.");
		}

		Planet planet = res.get();
		planet.setPlanetStatus(PlanetStatus.SOLD_OUT);
		planetRepository.save(planet);
	}

	private static String getFileName(MultipartFile imgFile) throws IOException {
		String oriName = imgFile.getOriginalFilename();
		String myPath = ImgDir.PATH;

		UUID uuid = UUID.randomUUID(); // 파일이름 중복을 피하기 위한 식별자 역할
		String savedFileName = uuid + "_" + oriName;

		imgFile.transferTo(new File(myPath + savedFileName));
		return savedFileName;
	}
}
