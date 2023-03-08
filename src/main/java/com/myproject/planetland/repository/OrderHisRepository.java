package com.myproject.planetland.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myproject.planetland.domain.OrderHis;

@Repository
public interface OrderHisRepository extends JpaRepository<OrderHis, Long> {
	List<OrderHis> findAllByUser_UserName(String userName);

	Page<OrderHis> findByUser_UserNameOrderByDateAsc(String userName, Pageable pageable);
}

