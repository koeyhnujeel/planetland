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
import com.myproject.planetland.constants.Type;
import com.myproject.planetland.domain.OrderHis;
import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.domain.PriceHis;
import com.myproject.planetland.domain.User;
import com.myproject.planetland.dto.AddPlanetDto;
import com.myproject.planetland.dto.MyPlanetsDto;
import com.myproject.planetland.dto.PlanetDto;
import com.myproject.planetland.dto.PlanetListDto;
import com.myproject.planetland.dto.SellPlanetDto;
import com.myproject.planetland.dto.UpgradePlanetDto;
import com.myproject.planetland.mapper.PlanetMapper;
import com.myproject.planetland.repository.PlanetRepository;
import com.myproject.planetland.repository.OrderHisRepository;
import com.myproject.planetland.repository.PriceHisRepository;
import com.myproject.planetland.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlanetService {

	private final PlanetRepository planetRepository;
	private final UserRepository userRepository;
	private final OrderHisRepository hisRepository;
	private final PriceHisRepository priceHisRepository;
	private final PlanetMapper mapper;

	public AddPlanetDto getEditPlanet(Long planetId) {
		Optional<Planet> res = planetRepository.findById(planetId);
		if (res.isPresent()) {
			Planet planet = res.get();
			AddPlanetDto addPlanetDto = mapper.modelToAddPlanetDto(planet);
			return addPlanetDto;
		}else {
			throw new EntityNotFoundException(String.format("아이디 %d로 조회되는 행성이 없습니다.", planetId));
		}
	}

	public PlanetDto getPlanetDetail(Long planetId) {
		Optional<Planet> res = planetRepository.findById(planetId);
		if (res.isPresent()) {
			Planet planet = res.get();
			PlanetDto planetDto = mapper.modelToDto(planet);
			if (planet.getUser() != null) {
				planetDto.setOwner(planet.getUser().getUserName());
			}
			return planetDto;
		} else {
			throw new EntityNotFoundException(String.format("아이디 %d로 조회되는 행성이 없습니다.", planetId));
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

	public List<MyPlanetsDto> getMyPlanets(Long userId) {
		List<Planet> res = planetRepository.findByUser_userId(userId);
		return mapper.modelToMyPlanetsDto(res);
	}

	public List<SellPlanetDto> getSellPlanet(Long userId) {
		List<Planet> res = planetRepository.findByUser_UserIdAndPlanetStatus(userId, PlanetStatus.ON_SALE);
		return mapper.modelToSellPlanetDto(res);
	}

	public List<PlanetListDto> getAllPlanet(String keyword) {
		if (keyword.equals("기본 순")) {
			List<Planet> planetList = planetRepository.findAll();
			List<PlanetListDto> planetListDtos = mapper.modelToDtoList(planetList);
			return planetListDtos;

		} else if (keyword.equals("높은 가치 순")) {
			List<Planet> allOrderByValueDesc = planetRepository.findAllByOrderByPriceDesc();
			List<PlanetListDto> planetListDtosDesc = mapper.modelToDtoList(allOrderByValueDesc);
			return planetListDtosDesc;

		} else if (keyword.equals("낮은 가치 순")) {
			List<Planet> allOrderByValueAsc = planetRepository.findAllByOrderByPriceAsc();
			List<PlanetListDto> planetListDtosAsc = mapper.modelToDtoList(allOrderByValueAsc);
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
			if(!imgFile.isEmpty()) {
				String savedFileName = getFileName(imgFile);
				addPlanetDto.setImgName(savedFileName);
				addPlanetDto.setImgPath("/images/" + savedFileName);
			}
			Planet planet = mapper.addPlanetDtoToModel(addPlanetDto);
			Planet savedPlanet = planetRepository.save(planet);

			return mapper.modelToAddPlanetDto(savedPlanet);
		}
	}

	public AddPlanetDto updatePlanet(Long planetId, AddPlanetDto addPlanetDto, MultipartFile imgFile) throws IOException {
		Optional<Planet> res = planetRepository.findById(planetId);
		if (res.isPresent()) {
			Planet updatePlanet = res.get();
			updatePlanet.setPlanetName(addPlanetDto.getPlanetName());
			updatePlanet.setPopulation(addPlanetDto.getPopulation());
			updatePlanet.setSatellite(addPlanetDto.getSatellite());
			updatePlanet.setPrice(addPlanetDto.getPrice());
			updatePlanet.setPlanetStatus(addPlanetDto.getPlanetStatus());
			if (!imgFile.isEmpty()) {
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
			AddPlanetDto updatePlantDto = mapper.modelToAddPlanetDto(savedPlanet);
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

		User buyer = resUser.get();
		Planet planet = resPlanet.get();

		if (planet.getUser() != null) {
			User seller = planet.getUser();
			List<Planet> planets = seller.getPlanets();
			for (int i = 0; i < planets.size(); i++) {
				if (planets.get(i).getPlanetName().equals(planet.getPlanetName())) {
					planets.remove(i);
					seller.setPlanets(planets);
					seller.setAsset(seller.getAsset() + planet.getPrice());
					OrderHis his = createOrderHis(seller, planet);
					his.setType(Type.SELL);
					hisRepository.save(his);
					userRepository.save(seller);
				}
			}
		}

		planet.setUser(buyer);
		planet.setLastPrice(planet.getPrice());
		planet.setPlanetStatus(PlanetStatus.SOLD_OUT);
		buyer.setAsset(buyer.getAsset() - planet.getPrice());
		buyer.getPlanets().add(planet);
		OrderHis his = createOrderHis(buyer, planet);
		PriceHis priceHis = createPriceHis(planet);
		his.setType(Type.BUY);
		hisRepository.save(his);
		userRepository.save(buyer);
		planetRepository.save(planet);
		priceHisRepository.save(priceHis);
	}

	public void sellPlanet(Long planetId, int price) {
		Optional<Planet> res = planetRepository.findById(planetId);
		if (res.isEmpty()) {
			throw new IllegalArgumentException("잘못된 경로입니다.");
		}

		Planet planet = res.get();
		planet.setPrice(price);
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

	public void upgradePlanet(Long planetId, UpgradePlanetDto upgradePlanetDto) {
		Optional<Planet> res = planetRepository.findById(planetId);
		if (res.isEmpty()) {
			throw new IllegalArgumentException("잘못된 경로입니다.");
		}
		int population = upgradePlanetDto.getPopulation();
		int satellite = upgradePlanetDto.getSatellite();
		int totalPrice = (population * 3) + (satellite * 450);
		User user = res.get().getUser();
		int userAsset = user.getAsset();
		if (totalPrice > userAsset) {
			throw new IllegalArgumentException("잔고가 부족합니다.");
		} else {
			Planet planet = res.get();
			planet.setPopulation(planet.getPopulation() + population);
			planet.setSatellite(planet.getSatellite() + satellite);
			planetRepository.save(planet);

			user.setAsset(userAsset - totalPrice);
			userRepository.save(user);
		}
	}

	private static String getFileName(MultipartFile imgFile) throws IOException {
		String oriName = imgFile.getOriginalFilename();
		String myPath = ImgDir.PATH;

		UUID uuid = UUID.randomUUID(); // 파일이름 중복을 피하기 위한 식별자 역할
		String savedFileName = uuid + "_" + oriName;

		imgFile.transferTo(new File(myPath + savedFileName));
		return savedFileName;
	}

	private static OrderHis createOrderHis(User user, Planet planet) {
		OrderHis his = new OrderHis();
		his.setPlanetName(planet.getPlanetName());
		his.setPrice(planet.getPrice());
		his.setUser(user);
		user.getOrderHis().add(his);
		return his;
	}

	private static PriceHis createPriceHis(Planet planet) {
		PriceHis priceHis = new PriceHis();
		priceHis.setPrice(planet.getPrice());
		priceHis.setPlanet(planet);
		planet.getPriceHis().add(priceHis);
		return priceHis;
	}
}
