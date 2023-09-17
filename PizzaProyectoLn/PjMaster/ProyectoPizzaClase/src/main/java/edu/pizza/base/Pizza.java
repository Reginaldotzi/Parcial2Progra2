package edu.pizza.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pizza {
    private String name;
    private List<Topping> toppings = new ArrayList<>();
    private String size; // Tamaño de la pizza
    private boolean personalizada; // Indica si la pizza es personalizada o predeterminada

    public Pizza(String name, Topping... toppings) {
        this.name = name;
        for (Topping topping : toppings) {
            this.toppings.add(topping);
        }
        this.personalizada = true; // Por defecto, se considera como una pizza personalizada
    }

    // Constructor para pizzas predeterminadas
    public Pizza(String name, String size, boolean personalizada) {
        this.name = name;
        this.size = size;
        this.personalizada = personalizada; // Indicar si es una pizza personalizada o predeterminada
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void removeTopping(int index) {
        this.toppings.remove(index);
    }

    public List<Topping> getToppings() {
        return Collections.unmodifiableList(new ArrayList<>(toppings));
    }

    public String getName() {
        return name;
    }

    // Método para obtener el tamaño de la pizza
    public String getSize() {
        return size;
    }

    // Método para establecer el tamaño de la pizza
    public void setSize(String tamaño) {
        this.size = tamaño;
    }

    // Método para comprobar si la pizza es personalizada
    public boolean esPersonalizada() {
        return personalizada;
    }

    @Override
    public String toString() {
        return "Pizza{" + "name='" + name + '\'' + ", toppings=" + toppings + '}';
    }

    public void prepare() {
        System.out.println("Preparing..... " + name);
        System.out.println("Adding toppings:");
        for (Topping topping : toppings) {
            System.out.println("- " + topping.getNombre());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("¡La pizza está lista!");
    }

    public double getPrecio() {
        // Precio base de la pizza
        double precioBase = 0;

        // Calcular el precio base según el tamaño de la pizza para pizzas personalizadas
        if (personalizada) {
            switch (size) {
                case "Pequeña":
                    precioBase = 20;
                    break;
                case "Mediana":
                    precioBase = 40;
                    break;
                case "Grande":
                    precioBase = 60;
                    break;
            }
        } else {
            // El precio base de las pizzas predeterminadas no depende del tamaño
            precioBase = getPrecioBasePredeterminado();
        }

        // Calcular el precio total sumando el precio base y el costo de los ingredientes adicionales
        double precioIngredientes = getCostoIngredientes();
        return personalizada ? precioBase + precioIngredientes : precioBase;
    }

    // Método para calcular el costo de los ingredientes adicionales
    private double getCostoIngredientes() {
        double costoIngredientes = 0;
        for (Topping topping : toppings) {
            costoIngredientes += topping.getPrecio();
        }
        return costoIngredientes;
    }

    // Método para obtener el precio base de las pizzas predeterminadas
    private double getPrecioBasePredeterminado() {
        // Definir los precios base de las pizzas predeterminadas (ajusta los valores según tus necesidades)
        switch (name) {
            case "Pizza Hawaiana":
                return 20; // Precio base de la Pizza Hawaiana
            case "Pizza Pepperoni":
                return 30; // Precio base de la Pizza Pepperoni
            case "Pizza Vegetariana":
                return 25; // Precio base de la Pizza Vegetariana
            case "Pizza Margarita":
                return 40; // Precio base de la Pizza Margarita
            default:
                return 0; // Valor predeterminado si el nombre no coincide con ninguna pizza predeterminada
        }
    }
}
