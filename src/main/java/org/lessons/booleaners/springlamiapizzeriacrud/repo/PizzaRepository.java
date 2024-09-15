package org.lessons.booleaners.springlamiapizzeriacrud.repo;

import org.lessons.booleaners.springlamiapizzeriacrud.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, Integer> {

    List<Pizza> findByNameContainingIgnoreCaseOrderByNameAsc(String name);
}
