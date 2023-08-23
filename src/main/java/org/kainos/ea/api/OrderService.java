package org.kainos.ea.api;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.OrderValidator;
import org.kainos.ea.db.OrderDao;

import java.sql.SQLException;
import java.util.*;


public class OrderService {
    private OrderDao orderDao = new OrderDao();
    private OrderValidator orderValidator = new OrderValidator();

    public List<Order> getAllOrders() throws FailedToGetProductsException {
        List<Order> orderList = null;
        try {
            orderList = orderDao.getAllOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        /** Working With Collection
        //21. Update your `OrderService` and `Order` classes to print out the
        // `OrderID`, `CustomerID` and `OrderDate` of all orders
        orderList.stream().forEach(System.out::println);


        //22. Update your `OrderService` and `Order` classes to print out order the list by order date descending
        System.out.println("== 22. Date descending Order ==");
        Collections.sort(orderList, Collections.reverseOrder());
        orderList.stream().forEach(System.out::println);

        //23. Update your `OrderService` to only show orders from the last week
        orderList.stream().filter(order -> order.getOrderDate().before(Date.from(Instant.now().minus(Duration.ofDays(7))))).forEach(System.out::println);


        //24 . only show orders from customer with `CustomerID` 1
        System.out.println("== Order for customerID 1 ==");
        for(Order order : orderList){
            if(order.getCustomerId() == 1){
                System.out.println(order);
            }
        }

       //25. Show most recent Order
        System.out.println("Most Recent Order: "+Collections.max(orderList));

        //26. Oldest Order
        System.out.println("Oldest Order: "+Collections.min(orderList));

        // 27. Update your `OrderService` to show the total count of all orders
        System.out.println("Total Orders: "+orderList.size());

        // 28 - Update your `OrderService` to show the customer ID with the most orders
//        List<Integer> custID = orderList.stream().map(order -> order.getCustomerId()).collect(Collectors.toList());
//        custID.stream()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                .entrySet()
//                .stream()
//                .max(Map.Entry.comparingByValue())
//                .ifPresent(System.out::println);
        //Easier Way
        Map<Integer, Long> countOrderMap = orderList.stream().collect(Collectors.groupingBy(Order::getCustomerId, Collectors.counting()));

        System.out.println("Customer with most orders: "    + Collections.max(countOrderMap.entrySet(), Map.Entry.comparingByValue()).getKey());

        // 29 - Update your `OrderService` to show the customer ID with the least orders
        System.out.println("Customer with least orders: "    + Collections.min(countOrderMap.entrySet(), Map.Entry.comparingByValue()).getKey());
        */



        return orderList;
    }
    public Order getOrdersById(int Id) throws FailedToGetOrdersException, OrderDoesNotExistException {
        try{
            Order order = orderDao.getOrdersById(Id);

            if (order == null){
                throw new OrderDoesNotExistException();
            }

            return order;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToGetOrdersException();
        }

    }
    public int createOrder(OrderRequest order) throws FailedToCreateOrderException, InvalidOrderException {
        try{
            String validation = orderValidator.isValidOrder(order);

            if(validation != null){
                throw new InvalidOrderException(validation);

            }
            int id = orderDao.createOrder(order);

            if(id == -1){
                throw new FailedToCreateOrderException();
            }

            return id;
        }catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToCreateOrderException();
        }

    }

    public void updateOrder(int id, OrderRequest order) throws InvalidOrderException, OrderDoesNotExistException, FailedToUpdateOrderException {
        try{
            String validation = orderValidator.isValidOrder(order);

            if(validation != null){
                throw new InvalidOrderException(validation);
            }

            Order orderToUpdate = orderDao.getOrdersById(id);

            if(orderToUpdate == null)
                throw new OrderDoesNotExistException();

            orderDao.updateOrder(id,order);
        }catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToUpdateOrderException();
        }
    }

    public void deleteOrder(int id) throws OrderDoesNotExistException, FailedToDeleteOrderException{
        try {
            Order orderToDelete = orderDao.getOrdersById(id);

            if(orderToDelete == null){
                throw new OrderDoesNotExistException();
            }

            orderDao.deleteOrder(id);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToDeleteOrderException();
        }
    }





}
