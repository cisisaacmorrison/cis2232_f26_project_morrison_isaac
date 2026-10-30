package ca.hccis.squash.controllers;

import ca.hccis.squash.bo.PaniniOrderReportBO;
import ca.hccis.squash.jpa.entity.PaniniOrder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

@Controller
public class PaniniReportController {

    @GetMapping("/panini-report")
    public String showReportForm() {
        return "report/paniniReport";
    }

    @PostMapping("/panini-report")
    public String processReport(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            Model model) {

        PaniniOrderReportBO bo = new PaniniOrderReportBO();

        ArrayList<PaniniOrder> orders =
                bo.processSelectByDateRange(startDate, endDate);

        model.addAttribute("orders", orders);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);

        HashMap<String, Integer> sandwichSales = new HashMap<>();

        for (PaniniOrder order : orders) {
            String sandwichName = order.getSandwichName();

            if (sandwichSales.containsKey(sandwichName)) {
                sandwichSales.put(sandwichName, sandwichSales.get(sandwichName) + 1);
            } else {
                sandwichSales.put(sandwichName, 1);
            }
        }

        model.addAttribute("sandwichSales", sandwichSales);

        ArrayList<String> sandwichNames = new ArrayList<>();
        ArrayList<Integer> sandwichQuantities = new ArrayList<>();

        for (String name : sandwichSales.keySet()) {
            sandwichNames.add(name);
            sandwichQuantities.add(sandwichSales.get(name));
        }

        model.addAttribute("sandwichNames", sandwichNames);
        model.addAttribute("sandwichQuantities", sandwichQuantities);

        int totalOrders = orders.size();
        int totalSandwiches = orders.size();

        BigDecimal totalSales = BigDecimal.ZERO;

        for (PaniniOrder order : orders) {
            if (order.getOrderTotal() != null) {
                totalSales = totalSales.add(order.getOrderTotal());
            }
        }

        model.addAttribute("totalOrders", totalOrders);
        model.addAttribute("totalSandwiches", totalSandwiches);
        model.addAttribute("totalSales", totalSales);

        return "report/paniniReportResults";


    }
}