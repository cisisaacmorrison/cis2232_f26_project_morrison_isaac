package ca.hccis.squash.bo;

import ca.hccis.squash.dao.PaniniOrderReportDAO;
import ca.hccis.squash.jpa.entity.PaniniOrder;
import ca.hccis.squash.util.CisUtilityFile;

import java.time.LocalDate;
import java.util.ArrayList;

public class PaniniOrderReportBO {

    public ArrayList<PaniniOrder> processSelectByDateRange(
            LocalDate startDate, LocalDate endDate) {

        PaniniOrderReportDAO dao = new PaniniOrderReportDAO();

        ArrayList<PaniniOrder> orders =
                dao.selectByDateRange(startDate, endDate);

        CisUtilityFile.writeReportToFile("PaniniSalesReport", orders);

        return orders;
    }
}