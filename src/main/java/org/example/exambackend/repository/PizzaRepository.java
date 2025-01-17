package org.example.exambackend.repository;

import org.example.exambackend.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaRepository extends JpaRepository<Pizza,Long> {
}
