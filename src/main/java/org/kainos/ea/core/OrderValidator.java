package org.kainos.ea.core;

import org.kainos.ea.cli.Customer;
import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.db.CustomerDao;
import org.kainos.ea.db.OrderDao;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;

public class OrderValidator {

    private CustomerDao customerDao = new CustomerDao();
    private OrderDao orderDao = new OrderDao();
    public String isValidOrder(OrderRequest request) throws SQLException {
        Customer customer = customerDao.getCustomerById(request.getCustomerId());

        if(customer == null){
            return "Customer ID does not Exist!";
        }
        if(request.getOrderDate().before(Date.from(Instant.now().minus(Duration.ofDays(365))))){
            return "Oder is Older Than a Year!";
        }
        return null;
    }
}
