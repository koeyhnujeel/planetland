package com.myproject.planetland.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import com.myproject.planetland.domain.OrderHis;
import com.myproject.planetland.dto.OrderHisDto;

@Mapper(componentModel = "spring")
public interface TransactionHisMapper {

	// List<OrderHisDto> modelToDto(List<OrderHis> orderHis);

	// Page<OrderHisDto> modelToDto(Page<OrderHis> orderHis);
}
