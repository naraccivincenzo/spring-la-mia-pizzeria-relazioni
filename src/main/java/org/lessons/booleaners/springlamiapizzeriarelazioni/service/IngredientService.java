package org.lessons.booleaners.springlamiapizzeriarelazioni.service;

import org.lessons.booleaners.springlamiapizzeriarelazioni.model.Ingredient;
import org.lessons.booleaners.springlamiapizzeriarelazioni.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository repository;

    public List<Ingredient> findAll() {
        return repository.findAll();
    }

    public List<Ingredient> findByName(String name) {
        return repository.findByNameContainingIgnoreCaseOrderByNameAsc(name);
    }

    public Ingredient getById(Integer id) {
        return repository.findById(id).get();
    }

    public void create(Ingredient ingredient) {
        repository.save(ingredient);
    }

    public void update(Ingredient ingredient) {
        repository.save(ingredient);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}
