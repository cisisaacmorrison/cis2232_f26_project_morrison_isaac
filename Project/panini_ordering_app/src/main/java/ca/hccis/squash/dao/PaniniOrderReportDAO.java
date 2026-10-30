package ca.hccis.squash.dao;

import ca.hccis.squash.jpa.entity.PaniniOrder;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PaniniOrderReportDAO {

    private Connection conn;

    public PaniniOrderReportDAO() {
        ResourceBundle rb = ResourceBundle.getBundle("application");

        String url = rb.getString("spring.datasource.url");
        String username = rb.getString("spring.datasource.username");
        String password = rb.getString("spring.datasource.password");

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<PaniniOrder> selectByDateRange(
            LocalDate startDate, LocalDate endDate) {

        ArrayList<PaniniOrder> orders = new ArrayList<>();

        String sql = "SELECT * FROM panini_order " +
                "WHERE order_date BETWEEN ? AND ? " +
                "ORDER BY order_date";

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    PaniniOrder order = new PaniniOrder();

                    order.setOrderId(rs.getInt("order_id"));
                    order.setOrderDate(rs.getDate("order_date").toLocalDate());
                    order.setSandwichSize(rs.getString("sandwich_size"));
                    order.setBreadType(rs.getString("bread_type"));
                    order.setCustomOrder(rs.getBoolean("custom_order"));
                    order.setSandwichName(rs.getString("sandwich_name"));
                    order.setIngredients(rs.getString("ingredients"));
                    order.setSauce(rs.getString("sauce"));
                    order.setOrderTotal(rs.getBigDecimal("order_total"));

                    orders.add(order);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }
}