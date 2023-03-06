package com.myproject.planetland.mapper;

import com.myproject.planetland.domain.Planet;
import com.myproject.planetland.dto.AddPlanetDto;
import com.myproject.planetland.dto.PlanetDto;
import com.myproject.planetland.dto.PlanetListDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-06T00:38:03+0900",
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
        planet.setValue( addPlanetDto.getValue() );
        planet.setImgName( addPlanetDto.getImgName() );
        planet.setImgPath( addPlanetDto.getImgPath() );
        planet.setPlanetStatus( addPlanetDto.getPlanetStatus() );

        return planet;
    }

    @Override
    public PlanetDto ModelToDto(Planet planet) {
        if ( planet == null ) {
            return null;
        }

        PlanetDto planetDto = new PlanetDto();

        planetDto.setPlanetId( planet.getPlanetId() );
        planetDto.setPlanetName( planet.getPlanetName() );
        planetDto.setPopulation( planet.getPopulation() );
        planetDto.setValue( planet.getValue() );
        planetDto.setImgPath( planet.getImgPath() );
        planetDto.setPlanetStatus( planet.getPlanetStatus() );

        return planetDto;
    }

    @Override
    public AddPlanetDto ModelToAddPlanetDto(Planet planet) {
        if ( planet == null ) {
            return null;
        }

        AddPlanetDto addPlanetDto = new AddPlanetDto();

        addPlanetDto.setPlanetId( planet.getPlanetId() );
        addPlanetDto.setPlanetName( planet.getPlanetName() );
        addPlanetDto.setPopulation( planet.getPopulation() );
        addPlanetDto.setValue( planet.getValue() );
        addPlanetDto.setImgName( planet.getImgName() );
        addPlanetDto.setImgPath( planet.getImgPath() );
        addPlanetDto.setPlanetStatus( planet.getPlanetStatus() );

        return addPlanetDto;
    }

    @Override
    public List<PlanetListDto> ModelToDtoList(List<Planet> planets) {
        if ( planets == null ) {
            return null;
        }

        List<PlanetListDto> list = new ArrayList<PlanetListDto>( planets.size() );
        for ( Planet planet : planets ) {
            list.add( planetToPlanetListDto( planet ) );
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
}
