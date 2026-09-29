package ca.hccis.panini.controllers;

import ca.hccis.panini.jpa.entity.PaniniOrder;
import ca.hccis.panini.repositories.PaniniOrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Controller
@RequestMapping("/panini")
public class PaniniOrderController {

    private final PaniniOrderRepository repository;

    public PaniniOrderController(PaniniOrderRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("orders", repository.findAll());
        return "panini/list";
    }

    @GetMapping("/add")
    public String add(Model model) {
        PaniniOrder order = new PaniniOrder();
        order.setOrderDate(LocalDate.now());
        order.setCustomOrder(false);
        order.setOrderTotal(BigDecimal.ZERO);
        model.addAttribute("order", order);
        return "panini/add";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute PaniniOrder order) {
        repository.save(order);
        return "redirect:/panini";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return "redirect:/panini";
    }
}
