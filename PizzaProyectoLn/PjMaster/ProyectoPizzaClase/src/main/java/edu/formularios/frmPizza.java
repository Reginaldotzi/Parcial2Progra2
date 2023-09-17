package edu.formularios;

import edu.pizza.base.Pizza;
import edu.pizza.base.Topping;
import edu.pizza.especialidades.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class frmPizza {
    private JPanel jPanelPrincipal;
    private JComboBox<Topping> comboBoxToppings;
    private JTextField txtPizza;
    private JButton btnAddIngrediente;
    private JLabel lblTotal;
    private JList<Topping> lista1;
    private JButton btbPrepararPizza;
    private JRadioButton radioPequeña;
    private JRadioButton radioMediana;
    private JRadioButton radioGrande;
    private JComboBox<String> TiposPizza;
    private JLabel TipoPizza;
    private List<Topping> ingredientes = new ArrayList<>();
    private double total = 0;
    private DefaultListModel<Topping> modeloLista = new DefaultListModel<>();
    private Pizza pizzaSeleccionada;

    public JPanel getjPanelPrincipal() {
        return jPanelPrincipal;
    }

    public frmPizza() {
        cargarToppings();
        configurarComboBoxTiposPizza();
        configurarRadioButtonsTamaño();
        configurarBotonAgregarIngrediente();
        configurarBotonPrepararPizza();
    }

    // Configurar JComboBox de tipos de pizza
    private void configurarComboBoxTiposPizza() {
        // Crear el modelo de ComboBox con las opciones de pizza
        DefaultComboBoxModel<String> pizzaModel = new DefaultComboBoxModel<>();
        String[] tiposPizza = {"Pizza Hawaiana", "Pizza Pepperoni", "Pizza Vegetariana", "Pizza Margarita", "¡Yo la armo!"};
        for (String tipo : tiposPizza) {
            pizzaModel.addElement(tipo);
        }
        TiposPizza.setModel(pizzaModel);

        // Configurar el ActionListener para el JComboBox
        TiposPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedPizza = (String) TiposPizza.getSelectedItem();

                if (selectedPizza.equals("¡Yo la armo!")) {
                    // Permitir que el usuario cree su propia pizza
                    pizzaSeleccionada = null;
                    txtPizza.setEnabled(true); // Habilitar la entrada de texto para el nombre
                } else {
                    // Seleccionar una pizza predeterminada
                    pizzaSeleccionada = obtenerPizzaPredeterminada(selectedPizza);
                    txtPizza.setEnabled(false); // Deshabilitar la entrada de texto para el nombre
                }
            }
        });
    }

    // Obtener la instancia de Pizza según el tipo seleccionado
    private Pizza obtenerPizzaPredeterminada(String selectedPizza) {
        switch (selectedPizza) {
            case "Pizza Hawaiana":
                return new PizzaHawaiana();
            case "Pizza Pepperoni":
                return new PizzaPeperoni();
            case "Pizza Vegetariana":
                return new PizzaVegetariana();
            case "Pizza Margarita":
                return new PizzaMargarita();
            default:
                return null;
        }
    }

    // Configurar radio buttons para los tamaños
    private void configurarRadioButtonsTamaño() {
        ButtonGroup tamañoGroup = new ButtonGroup();

        JRadioButton[] radioButtons = {
                radioPequeña,
                radioMediana,
                radioGrande
        };

        for (JRadioButton radioButton : radioButtons) {
            tamañoGroup.add(radioButton);
        }
    }

    // Configurar botón para agregar ingrediente
    private void configurarBotonAgregarIngrediente() {
        btnAddIngrediente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pizzaSeleccionada != null || txtPizza.isEnabled()) {
                    Topping ingrediente = (Topping) comboBoxToppings.getSelectedItem();
                    modeloLista.addElement(ingrediente);
                    lista1.setModel(modeloLista);
                    total += ingrediente.getPrecio();
                    lblTotal.setText(String.valueOf(total));
                } else {
                    JOptionPane.showMessageDialog(null, "Primero selecciona una pizza predeterminada o elige '¡Yo la armo!'.");
                }
            }
        });
    }

    // Configurar botón para preparar la pizza
    private void configurarBotonPrepararPizza() {
        btbPrepararPizza.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (pizzaSeleccionada != null || txtPizza.isEnabled()) {
                    Pizza pizza;
                    if (txtPizza.isEnabled()) {
                        // El usuario está creando su propia pizza
                        if (!txtPizza.getText().isEmpty()) {
                            pizza = new Pizza(txtPizza.getText());
                        } else {
                            JOptionPane.showMessageDialog(null, "Ingresa un nombre para la pizza.");
                            return; // Salir del método si no se ingresa un nombre
                        }
                    } else {
                        // Seleccionar una pizza predeterminada
                        pizza = pizzaSeleccionada;
                    }

                    for (int i = 0; i < modeloLista.getSize(); i++) {
                        Topping topi = modeloLista.getElementAt(i);
                        pizza.addTopping(topi);
                    }

                    String tamañoSeleccionado = "";
                    if (radioPequeña.isSelected()) {
                        tamañoSeleccionado = "Pequeña";
                        total += 40;
                    } else if (radioMediana.isSelected()) {
                        tamañoSeleccionado = "Mediana";
                        total += 60;
                    } else if (radioGrande.isSelected()) {
                        tamañoSeleccionado = "Grande";
                        total += 90;
                    }

                    pizza.setSize(tamañoSeleccionado);

                    double precioAdicional = pizza.getPrecio();
                    double precioTotal = total + precioAdicional;

                    System.out.println("Pizza: " + pizza.getName());
                    System.out.println("Tamaño: " + tamañoSeleccionado);
                    System.out.println("Ingredientes: " + pizza.getToppings());
                    System.out.println("Precio Total: Q" + precioTotal);
                } else {
                    JOptionPane.showMessageDialog(null, "Primero selecciona una pizza predeterminada o elige '¡Yo la armo!'.");
                }
            }
        });
    }


    // Cargar toppings
    private void cargarToppings() {
        ingredientes.add(new Topping("Jamón", 1));
        ingredientes.add(new Topping("Tomate", 1.80));
        ingredientes.add(new Topping("Cebolla", 1));
        ingredientes.add(new Topping("Tacuazin", 4.80));
        ingredientes.add(new Topping("Salchica", 3));
        ingredientes.add(new Topping("Anchoas", 6));

        DefaultComboBoxModel<Topping> model = new DefaultComboBoxModel<>(ingredientes.toArray(new Topping[0]));
        comboBoxToppings.setModel(model);

        // Agregar un MouseListener para eliminar ingredientes haciendo doble clic
        comboBoxToppings.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Verificar si fue un doble clic
                    Object selectedItem = comboBoxToppings.getSelectedItem();
                    if (selectedItem instanceof Topping) {
                        Topping selectedTopping = (Topping) selectedItem;
                        total -= selectedTopping.getPrecio(); // Restar el precio del ingrediente eliminado al total
                        modeloLista.removeElement(selectedTopping); // Eliminar el ingrediente de la lista del modelo
                        lblTotal.setText(String.valueOf(total)); // Actualizar el total en la etiqueta
                    }
                }
            }
        });
    }
}
