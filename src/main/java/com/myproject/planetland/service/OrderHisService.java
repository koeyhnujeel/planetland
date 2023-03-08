package com.myproject.planetland.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.myproject.planetland.auth.CustomUserDetails;
import com.myproject.planetland.domain.OrderHis;
import com.myproject.planetland.mapper.TransactionHisMapper;
import com.myproject.planetland.repository.OrderHisRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderHisService {

	private final OrderHisRepository hisRepository;
	private final TransactionHisMapper mapper;

	public Page<OrderHis> getHistory(CustomUserDetails user, Pageable pageable) {
		String userName = user.getUsername();
		Page<OrderHis> list = hisRepository.findByUser_UserNameOrderByDateAsc(userName, pageable);
		// Page<OrderHisDto> orderHisDtos = mapper.modelToDto(list);

		return list;
	}
}
