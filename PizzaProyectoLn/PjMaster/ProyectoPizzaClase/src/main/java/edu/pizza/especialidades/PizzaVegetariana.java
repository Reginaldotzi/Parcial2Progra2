package edu.pizza.especialidades;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaVegetariana extends Pizza {
    public PizzaVegetariana() {
        super("Vegetarian Pizza", new Topping("Mushrooms", 10), new Topping("Bell Peppers", 7), new Topping("Onions", 6));
    }
}