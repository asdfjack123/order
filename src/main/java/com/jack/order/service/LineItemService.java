package com.jack.order.service;

import com.jack.order.dao.LineItemRepository;
import com.jack.order.po.LineItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LineItemService {

    @Autowired
    private LineItemRepository lineItemRepository;

    public LineItem save(LineItem lineItem){
        return lineItemRepository.save(lineItem);
    }

}
