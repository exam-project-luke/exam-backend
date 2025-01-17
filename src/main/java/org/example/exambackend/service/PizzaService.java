package org.example.exambackend.service;

import org.example.exambackend.entity.Pizza;
import org.example.exambackend.repository.PizzaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    public PizzaService(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    public void initializePizzas() {
        if (pizzaRepository.count() < 5) {
            System.out.println("Initializing default pizzas...");

            Pizza pizza1 = new Pizza();
            pizza1.setTitle("Robot Margherita");
            pizza1.setPrice(60);

            Pizza pizza2 = new Pizza();
            pizza2.setTitle("Pepperoni Drone");
            pizza2.setPrice(70);

            Pizza pizza3 = new Pizza();
            pizza3.setTitle("Hawaiian Hover");
            pizza3.setPrice(80);

            Pizza pizza4 = new Pizza();
            pizza4.setTitle("Vesterbro Veggie");
            pizza4.setPrice(65);

            Pizza pizza5 = new Pizza();
            pizza5.setTitle("Christianshavn Cheese");
            pizza5.setPrice(90);

            pizzaRepository.saveAll(List.of(pizza1, pizza2, pizza3, pizza4, pizza5));
        } else {
            System.out.println("Pizzas already initialized.");
        }
    }
}
