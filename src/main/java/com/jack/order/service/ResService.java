package com.jack.order.service;

import com.jack.order.dao.MenuRepository;
import com.jack.order.dao.ResRepository;
import com.jack.order.po.Menu;
import com.jack.order.po.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResService {

    @Autowired
    private ResRepository resRepository;

    public List<Restaurant> getAll(){
        return resRepository.findAll();
    }

    public Page<Restaurant> findAllByPage(Pageable pageable){
        //pageable改用controoler傳參的
        return resRepository.findAll(pageable);
    }

    public Restaurant save(Restaurant re){
        return resRepository.save(re);
    }

    public Restaurant findById(Long id){
        return resRepository.findById(id).orElse(null);
    }

}
