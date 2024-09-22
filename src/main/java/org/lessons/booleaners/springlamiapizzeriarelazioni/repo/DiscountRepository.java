package org.lessons.booleaners.springlamiapizzeriarelazioni.repo;

import org.lessons.booleaners.springlamiapizzeriarelazioni.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Integer> {

    List<Discount> findByDiscountNameContainingIgnoreCaseOrderByDiscountNameAsc(String name);

}
