package com.myproject.planetland.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.myproject.planetland.domain.PriceHis;
import com.myproject.planetland.dto.PriceHisDto;

@Mapper(componentModel = "spring")
public interface PriceHisMapper {

	List<PriceHisDto> modelToDto(List<PriceHis> priceHis);
}
