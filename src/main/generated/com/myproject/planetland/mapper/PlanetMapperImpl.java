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
    date = "2023-03-03T17:39:51+0900",
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

        planet.setPlanetName( addPlanetDto.getPlanetName() );
        planet.setPopulation( addPlanetDto.getPopulation() );
        planet.setValue( addPlanetDto.getValue() );
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
        planetDto.setPlanetStatus( planet.getPlanetStatus() );

        return planetDto;
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

        planetListDto.setPlanetName( planet.getPlanetName() );

        return planetListDto;
    }
}
