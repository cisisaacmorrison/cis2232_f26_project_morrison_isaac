package ca.hccis.panini.jpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "panini_order")
public class PaniniOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    @NotNull
    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @NotNull
    @Size(max = 15)
    @Column(name = "sandwich_size", nullable = false, length = 15)
    private String sandwichSize;

    @NotNull
    @Size(max = 20)
    @Column(name = "bread_type", nullable = false, length = 20)
    private String breadType;

    @NotNull
    @Column(name = "custom_order", nullable = false)
    private Boolean customOrder = false;

    @Size(max = 100)
    @Column(name = "sandwich_name", length = 100)
    private String sandwichName;

    @Size(max = 255)
    @Column(name = "ingredients", length = 255)
    private String ingredients;

    @Size(max = 50)
    @Column(name = "sauce", length = 50)
    private String sauce;

    @NotNull
    @Column(name = "order_total", nullable = false, precision = 8, scale = 2)
    private BigDecimal orderTotal = BigDecimal.ZERO;

    public PaniniOrder() {
    }

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
        return "PaniniOrder{" +
                "orderId=" + orderId +
                ", orderDate=" + orderDate +
                ", sandwichSize='" + sandwichSize + '\'' +
                ", breadType='" + breadType + '\'' +
                ", customOrder=" + customOrder +
                ", sandwichName='" + sandwichName + '\'' +
                ", ingredients='" + ingredients + '\'' +
                ", sauce='" + sauce + '\'' +
                ", orderTotal=" + orderTotal +
                '}';
    }
}
