package edu.pizza.especialidades;


import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaPeperoni extends Pizza {
    public PizzaPeperoni() {
        super("Pepperoni Pizza", new Topping("Pepperoni", 12), new Topping("Cheese", 8));
    }
}