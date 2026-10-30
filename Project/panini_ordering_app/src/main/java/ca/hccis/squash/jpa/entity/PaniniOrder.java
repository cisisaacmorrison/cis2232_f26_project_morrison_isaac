package ca.hccis.squash.jpa.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import ca.hccis.squash.jpa.entity.PaniniOrder;

@Entity
@Table(name = "panini_order")
public class PaniniOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "sandwich_size")
    private String sandwichSize;

    @Column(name = "bread_type")
    private String breadType;

    @Column(name = "custom_order")
    private Boolean customOrder;

    @Column(name = "sandwich_name")
    private String sandwichName;

    @Column(name = "ingredients")
    private String ingredients;

    @Column(name = "sauce")
    private String sauce;

    @Column(name = "order_total")
    private BigDecimal orderTotal;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getSandwichSize() {
        return sandwichSize;
    }

    public void setSandwichSize(String sandwichSize) {
        this.sandwichSize = sandwichSize;
    }

    public String getBreadType() {
        return breadType;
    }

    public void setBreadType(String breadType) {
        this.breadType = breadType;
    }

    public Boolean getCustomOrder() {
        return customOrder;
    }

    public void setCustomOrder(Boolean customOrder) {
        this.customOrder = customOrder;
    }

    public String getSandwichName() {
        return sandwichName;
    }

    public void setSandwichName(String sandwichName) {
        this.sandwichName = sandwichName;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getSauce() {
        return sauce;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId +
                "\nOrder Date: " + orderDate +
                "\nSandwich: " + sandwichName +
                "\nSize: " + sandwichSize +
                "\nBread: " + breadType +
                "\nCustom Order: " + customOrder +
                "\nIngredients: " + ingredients +
                "\nSauce: " + sauce +
                "\nTotal: $" + orderTotal +
                "\n----------------------------";
    }
}