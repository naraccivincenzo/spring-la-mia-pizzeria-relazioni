package org.lessons.booleaners.springlamiapizzeriarelazioni.service;

import org.lessons.booleaners.springlamiapizzeriarelazioni.model.Discount;
import org.lessons.booleaners.springlamiapizzeriarelazioni.repo.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountService {

    @Autowired
    private DiscountRepository repository;

    public List<Discount> findAll() {
        return repository.findAll();
    }

    public List<Discount> findByName(String name) {
        return repository.findByDiscountNameContainingIgnoreCaseOrderByDiscountNameAsc(name);
    }

    public Discount getById(Integer id) {
        return repository.findById(id).get();
    }

    public void create(Discount discount) {
        repository.save(discount);
    }

    public void update(Discount discount) {
        repository.save(discount);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
