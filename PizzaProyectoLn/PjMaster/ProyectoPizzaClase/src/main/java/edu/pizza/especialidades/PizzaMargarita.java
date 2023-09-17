package edu.pizza.especialidades;


import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaMargarita extends Pizza {
    public PizzaMargarita() {
        super("Margherita Pizza", new Topping("Tomato", 9), new Topping("Mozzarella", 8), new Topping("Basil", 5));
    }
}