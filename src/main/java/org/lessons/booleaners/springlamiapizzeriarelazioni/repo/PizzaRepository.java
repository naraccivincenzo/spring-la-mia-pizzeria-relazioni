package org.lessons.booleaners.springlamiapizzeriarelazioni.repo;

import org.lessons.booleaners.springlamiapizzeriarelazioni.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    List<Pizza> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
}
