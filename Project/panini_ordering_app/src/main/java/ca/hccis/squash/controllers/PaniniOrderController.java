package ca.hccis.squash.controllers;

import ca.hccis.squash.jpa.entity.PaniniOrder;
import ca.hccis.squash.repositories.PaniniOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class PaniniOrderController {

    private final PaniniOrderRepository paniniOrderRepository;

    @Autowired
    public PaniniOrderController(PaniniOrderRepository paniniOrderRepository) {
        this.paniniOrderRepository = paniniOrderRepository;
    }

    @GetMapping("/panini-orders")
    public String listOrders(Model model) {
        List<PaniniOrder> orders = paniniOrderRepository.findAll();
        model.addAttribute("orders", orders);
        return "panini/list";
    }

    @GetMapping("/panini-orders/add")
    public String showAddOrderForm(Model model) {
        model.addAttribute("order", new PaniniOrder());
        model.addAttribute("isEdit", false);
        return "panini/add";
    }

    @PostMapping("/panini-orders/add")
    public String addOrder(@ModelAttribute("order") PaniniOrder order) {
        saveOrder(order);
        return "redirect:/panini-orders";
    }

    @GetMapping("/panini-orders/edit/{id}")
    public String showEditOrderForm(@PathVariable Integer id, Model model) {

        PaniniOrder order = paniniOrderRepository.findById(id).orElse(null);

        if (order == null) {
            return "redirect:/panini-orders";
        }

        if (order.getIngredients() != null && !order.getIngredients().isBlank()) {
            order.setSelectedIngredients(
                    new ArrayList<>(Arrays.asList(order.getIngredients().split(",\\s*")))
            );
        } else {
            order.setSelectedIngredients(new ArrayList<>());
        }

        if (order.getSauce() != null && !order.getSauce().isBlank()) {
            order.setSelectedSauces(
                    new ArrayList<>(Arrays.asList(order.getSauce().split(",\\s*")))
            );
        } else {
            order.setSelectedSauces(new ArrayList<>());
        }

        model.addAttribute("order", order);
        model.addAttribute("isEdit", true);

        return "panini/add";
    }

    @PostMapping("/panini-orders/edit/{id}")
    public String updateOrder(
            @PathVariable Integer id,
            @ModelAttribute("order") PaniniOrder order) {

        if (!paniniOrderRepository.existsById(id)) {
            return "redirect:/panini-orders";
        }

        order.setOrderId(id);
        saveOrder(order);

        return "redirect:/panini-orders";
    }

    @PostMapping("/panini-orders/delete/{id}")
    public String deleteOrder(@PathVariable Integer id) {
        paniniOrderRepository.deleteById(id);
        return "redirect:/panini-orders";
    }

    private void saveOrder(PaniniOrder order) {

        if (order.getOrderDate() == null) {
            order.setOrderDate(LocalDate.now());
        }

        boolean custom = "Custom Panini".equals(order.getSandwichName());
        order.setCustomOrder(custom);

        List<String> toppings;
        List<String> sauces;

        if (custom) {
            toppings = order.getSelectedIngredients() == null
                    ? new ArrayList<>()
                    : order.getSelectedIngredients();

            sauces = order.getSelectedSauces() == null
                    ? new ArrayList<>()
                    : order.getSelectedSauces();

        } else {
            toppings = getPresetToppings(order.getSandwichName());
            sauces = getPresetSauces(order.getSandwichName());
        }

        order.setIngredients(String.join(", ", toppings));
        order.setSauce(String.join(", ", sauces));

        order.setOrderTotal(calculateOrderTotal(order, custom, toppings, sauces));

        paniniOrderRepository.save(order);
    }

    private List<String> getPresetToppings(String sandwichName) {

        return switch (sandwichName) {
            case "Classic Italian" ->
                    List.of("Salami", "Pepperoni", "Ham", "Provolone");

            case "Caprese" ->
                    List.of("Tomato", "Mozzarella", "Basil");

            case "Meatball" ->
                    List.of("Meatball", "Tomato", "Mozzarella");

            case "Chicken Pesto" ->
                    List.of("Chicken", "Provolone");

            case "Vegetariano" ->
                    List.of("Tomato", "Peppers", "Mozzarella");

            default -> new ArrayList<>();
        };
    }

    private List<String> getPresetSauces(String sandwichName) {

        return switch (sandwichName) {
            case "Chicken Pesto", "Vegetariano" ->
                    List.of("Pesto");

            default -> new ArrayList<>();
        };
    }

    private BigDecimal calculateOrderTotal(
            PaniniOrder order,
            boolean custom,
            List<String> toppings,
            List<String> sauces) {

        BigDecimal total;

        if ("Lunch".equals(order.getSandwichSize())) {
            total = new BigDecimal("10.00");
        } else {
            total = new BigDecimal("15.00");
        }

        if ("Focaccia".equals(order.getBreadType())) {
            total = total.add(new BigDecimal("2.00"));
        }

        if (custom) {

            List<String> allSelections = new ArrayList<>();
            allSelections.addAll(toppings);
            allSelections.addAll(sauces);

            for (int i = 4; i < allSelections.size(); i++) {
                total = total.add(getExtraIngredientPrice(allSelections.get(i)));
            }
        }

        total = total.multiply(new BigDecimal("1.15"));

        return total.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal getExtraIngredientPrice(String ingredient) {

        if (List.of(
                "Salami",
                "Pepperoni",
                "Ham",
                "Meatball",
                "Chicken",
                "Mozzarella",
                "Provolone"
        ).contains(ingredient)) {
            return new BigDecimal("2.00");
        }

        return new BigDecimal("1.50");
    }
}
