package edu.pizza.especialidades;


import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;

public class PizzaHawaiana extends Pizza {
    public PizzaHawaiana() {
        super("Hawaiian Pizza", new Topping("Ham", 10), new Topping("Pineapple", 8));
    }
}