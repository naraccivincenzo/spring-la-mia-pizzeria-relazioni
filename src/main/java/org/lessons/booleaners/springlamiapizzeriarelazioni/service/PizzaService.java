package org.lessons.booleaners.springlamiapizzeriarelazioni.service;

import org.lessons.booleaners.springlamiapizzeriarelazioni.model.Pizza;
import org.lessons.booleaners.springlamiapizzeriarelazioni.repo.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository repository;

    public List<Pizza> findAll() {
        return repository.findAll();
    }

    public List<Pizza> findByName(String name) {
        return repository.findByNameContainingIgnoreCaseOrderByNameAsc(name);
    }

    public Pizza getById(Integer id) {
        return repository.findById(id).get();
    }

    public void create(Pizza pizza) {
        repository.save(pizza);
    }

    public void update(Pizza pizza) {
        pizza.setUpdatedAt(Instant.now());
        repository.save(pizza);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
