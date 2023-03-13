package com.myproject.planetland.mapper;

import com.myproject.planetland.domain.PriceHis;
import com.myproject.planetland.dto.PriceHisDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-13T23:23:15+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.17 (Azul Systems, Inc.)"
)
@Component
public class PriceHisMapperImpl implements PriceHisMapper {

    @Override
    public List<PriceHisDto> modelToDto(List<PriceHis> priceHis) {
        if ( priceHis == null ) {
            return null;
        }

        List<PriceHisDto> list = new ArrayList<PriceHisDto>( priceHis.size() );
        for ( PriceHis priceHis1 : priceHis ) {
            list.add( priceHisToPriceHisDto( priceHis1 ) );
        }

        return list;
    }

    protected PriceHisDto priceHisToPriceHisDto(PriceHis priceHis) {
        if ( priceHis == null ) {
            return null;
        }

        PriceHisDto priceHisDto = new PriceHisDto();

        priceHisDto.setPrice( priceHis.getPrice() );
        priceHisDto.setDate( priceHis.getDate() );

        return priceHisDto;
    }
}
