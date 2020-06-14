package com.jack.order.service;

import com.jack.order.autil.Strs;
import com.jack.order.dao.MenuRepository;
import com.jack.order.po.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    public List<Menu> findByResId(Long id) {
        return menuRepository.findByRestaurantId(id);
    }

    public Menu findById(Long id){
        return menuRepository.findById(id).orElse(null);
    }

    public List<Menu> findByResIdAndType(Long id, String type) {
        if (Strs.isEmpty(type)) {
            return menuRepository.findByRestaurantId(id);
        }
        else{
            return menuRepository.findAll(new Specification<Menu>() {
                @Override
                public Predicate toPredicate(Root<Menu> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(criteriaBuilder.equal(root.<String>get("type"),type));
                    predicates.add(criteriaBuilder.equal(root.<Long>get("restaurant").get("id"),id));
                    criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                    return null;
                }
            });
        }
    }

}
