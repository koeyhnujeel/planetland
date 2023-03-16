package com.myproject.planetland.mapper;

import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.dto.AddPlanetDto;
import com.myproject.planetland.dto.MyPlanetsDto;
import com.myproject.planetland.dto.PlanetDto;
import com.myproject.planetland.dto.PlanetListDto;
import com.myproject.planetland.dto.SellPlanetDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T23:43:16+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class PlanetMapperImpl implements PlanetMapper {

    @Override
    public Planet addPlanetDtoToModel(AddPlanetDto addPlanetDto) {
        if ( addPlanetDto == null ) {
            return null;
        }

        Planet planet = new Planet();

        planet.setPlanetId( addPlanetDto.getPlanetId() );
        planet.setPlanetName( addPlanetDto.getPlanetName() );
        planet.setPopulation( addPlanetDto.getPopulation() );
        planet.setPrice( addPlanetDto.getPrice() );
        planet.setSatellite( addPlanetDto.getSatellite() );
        planet.setImgName( addPlanetDto.getImgName() );
        planet.setImgPath( addPlanetDto.getImgPath() );
        planet.setPlanetStatus( addPlanetDto.getPlanetStatus() );

        return planet;
    }

    @Override
    public PlanetDto modelToDto(Planet planet) {
        if ( planet == null ) {
            return null;
        }

        PlanetDto planetDto = new PlanetDto();

        planetDto.setPlanetId( planet.getPlanetId() );
        planetDto.setPlanetName( planet.getPlanetName() );
        planetDto.setPopulation( planet.getPopulation() );
        planetDto.setSatellite( planet.getSatellite() );
        planetDto.setLastPrice( planet.getLastPrice() );
        planetDto.setPrice( planet.getPrice() );
        planetDto.setImgPath( planet.getImgPath() );
        planetDto.setPlanetStatus( planet.getPlanetStatus() );

        return planetDto;
    }

    @Override
    public AddPlanetDto modelToAddPlanetDto(Planet planet) {
        if ( planet == null ) {
            return null;
        }

        AddPlanetDto addPlanetDto = new AddPlanetDto();

        addPlanetDto.setPlanetId( planet.getPlanetId() );
        addPlanetDto.setPlanetName( planet.getPlanetName() );
        addPlanetDto.setPopulation( planet.getPopulation() );
        addPlanetDto.setSatellite( planet.getSatellite() );
        addPlanetDto.setPrice( planet.getPrice() );
        addPlanetDto.setImgName( planet.getImgName() );
        addPlanetDto.setImgPath( planet.getImgPath() );
        addPlanetDto.setPlanetStatus( planet.getPlanetStatus() );

        return addPlanetDto;
    }

    @Override
    public List<PlanetListDto> modelToDtoList(List<Planet> planets) {
        if ( planets == null ) {
            return null;
        }

        List<PlanetListDto> list = new ArrayList<PlanetListDto>( planets.size() );
        for ( Planet planet : planets ) {
            list.add( planetToPlanetListDto( planet ) );
        }

        return list;
    }

    @Override
    public List<MyPlanetsDto> modelToMyPlanetsDto(List<Planet> planets) {
        if ( planets == null ) {
            return null;
        }

        List<MyPlanetsDto> list = new ArrayList<MyPlanetsDto>( planets.size() );
        for ( Planet planet : planets ) {
            list.add( planetToMyPlanetsDto( planet ) );
        }

        return list;
    }

    @Override
    public List<SellPlanetDto> modelToSellPlanetDto(List<Planet> planets) {
        if ( planets == null ) {
            return null;
        }

        List<SellPlanetDto> list = new ArrayList<SellPlanetDto>( planets.size() );
        for ( Planet planet : planets ) {
            list.add( planetToSellPlanetDto( planet ) );
        }

        return list;
    }

    protected PlanetListDto planetToPlanetListDto(Planet planet) {
        if ( planet == null ) {
            return null;
        }

        PlanetListDto planetListDto = new PlanetListDto();

        planetListDto.setPlanetId( planet.getPlanetId() );
        planetListDto.setPlanetName( planet.getPlanetName() );
        planetListDto.setPlanetStatus( planet.getPlanetStatus() );
        planetListDto.setImgPath( planet.getImgPath() );
        planetListDto.setImgName( planet.getImgName() );

        return planetListDto;
    }

    protected MyPlanetsDto planetToMyPlanetsDto(Planet planet) {
        if ( planet == null ) {
            return null;
        }

        MyPlanetsDto myPlanetsDto = new MyPlanetsDto();

        myPlanetsDto.setPlanetId( planet.getPlanetId() );
        myPlanetsDto.setPlanetName( planet.getPlanetName() );
        myPlanetsDto.setPopulation( planet.getPopulation() );
        myPlanetsDto.setSatellite( planet.getSatellite() );
        myPlanetsDto.setLastPrice( planet.getLastPrice() );
        myPlanetsDto.setPlanetStatus( planet.getPlanetStatus() );

        return myPlanetsDto;
    }

    protected SellPlanetDto planetToSellPlanetDto(Planet planet) {
        if ( planet == null ) {
            return null;
        }

        SellPlanetDto sellPlanetDto = new SellPlanetDto();

        sellPlanetDto.setPlanetId( planet.getPlanetId() );
        sellPlanetDto.setPlanetName( planet.getPlanetName() );
        sellPlanetDto.setPrice( planet.getPrice() );

        return sellPlanetDto;
    }
}
