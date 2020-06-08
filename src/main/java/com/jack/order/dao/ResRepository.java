package com.jack.order.dao;

import com.jack.order.po.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResRepository extends JpaRepository<Restaurant,Long> {


}
