package org.kainos.ea.api;

import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.ProductValidator;
import org.kainos.ea.db.ProductDao;

import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

public class ProductService {

    private ProductDao productDao = new ProductDao();
    private ProductValidator productValidator = new ProductValidator();
    public List<Product> getAllProducts() throws FailedToGetProductsException {

        //double totalPriceOfProduct = 0;
        /** Working With Collections
//        for(Product product : productList){
//            totalPriceOfProduct += product.getPrice();
//        }
        Iterator<Product> productIterator = productList.iterator();

//        while(productIterator.hasNext()){
//            Product product = productIterator.next();
//            totalPriceOfProduct += product.getPrice();
//        }

//        do{
//            Product product = productIterator.next();
//            totalPriceOfProduct += product.getPrice();
//        }while(productIterator.hasNext());

        // use a stream to print out the total price
        //totalPriceOfProduct = productList.stream().mapToDouble(product -> product.getPrice()).sum();


        //use a for each loop to print out the total price of all of the products in your database that cost less than £100
        //second Step: Update to Print Total cost products <100 and total of >100.
        double totalPriceofCheapProduct = 0;
        double totalPriceOfExpensiveProducts = 0;
//        for(Product product : productList){
//            if(product.getPrice() < 100){
//                totalPriceofCheapProduct += product.getPrice();
//            }else{
//                totalPriceOfExpensiveProducts += product.getPrice();
//            }
//        }
        System.out.println("Total Price of all Product = £"+totalPriceOfProduct);
        System.out.println("Total Price of all Cheap Products (<100) = £"+totalPriceofCheapProduct);
        System.out.println("Total Price of all Expensive Products (>100) = £"+totalPriceOfExpensiveProducts);

        for(Product product : productList){
            switch (product.getName()){
                case("Jordan 1s"):
                    System.out.println("This is the Jordan 1s price: £"+product.getPrice());
                    break;
                case("Air jordan 4s"):
                    System.out.println("This is the Jordan 4s price: £"+product.getPrice());
                    break;
                default:
                    System.out.println("This is the other items price: £"+product.getPrice());

            }
        }

        //9. Create a list of integers with at least one duplicated value and print out the list
        List<Integer> intList = Arrays.asList(1,2,2,4,5);
        //intList.stream().forEach(System.out::println);

        // 10. Create a set of integers and assign it the values from the list created above and print out the set.
        // Notice the difference between the values in the list and the values in the set.
        // A set only can be used to store unique values.
        Set<Integer> intSet = new HashSet<>(intList);
        intSet.stream().forEach(System.out::println);

        // 11 - 16 -- Sort
//        Collections.sort(productList);
//        productList.stream().forEach(System.out::println);

        //17. print min price only
        System.out.println("Min Product Price: "+Collections.min(productList));

        //18. print max price only
        System.out.println("Max Product Price: "+Collections.max(productList));

        // 19. only print products with a price higher than £10
        System.out.println("== Products Over 10 ==");
        productList.stream().filter(product -> product.getPrice() >10).forEach(System.out::println);

        // 20. create a new list with products with a price lower than £10. Print out this new list
        System.out.println("== Products Less 10 ==");
        List<Product> cheapProducts =
                productList.stream().filter(product -> product.getPrice() <10).collect(Collectors.toList());

        cheapProducts.forEach(System.out::println);

         */


        try {
            List<Product> productList = productDao.getAllProducts();
            return productList;

        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetProductsException();
        }
    }

    public Product getProductsById(int Id) throws FailedToGetProductsException, ProductDoesNoteExistException {
        try{

            Product product = productDao.getProductsById(Id);

            if (product == null){
                throw new ProductDoesNoteExistException();
            }

            return product;
        }catch(SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToGetProductsException();
        }
    }

    public int createProduct(ProductRequest product) throws FailedToCreateProductException, InvalidProductException {
        try{
            String validation = productValidator.isValidProduct(product);

            if(validation != null){
                throw new InvalidProductException(validation);

            }
            int id = productDao.createProduct(product);

            if(id == -1){
                throw new FailedToCreateProductException();
            }

            return id;
        }catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToCreateProductException();
        }

    }

    public void updateProduct(int id, ProductRequest product) throws InvalidProductException, ProductDoesNoteExistException, FailedToUpdateProductException {
        try{
            String validation = productValidator.isValidProduct(product);

            if(validation != null){
                throw new InvalidProductException(validation);
            }

            Product productToUpdate = productDao.getProductsById(id);

            if(productToUpdate == null)
                throw new ProductDoesNoteExistException();

            productDao.updateProduct(id,product);
        }catch (SQLException e){
            System.err.println(e.getMessage());

            throw new FailedToUpdateProductException();
        }
    }

    public void deleteProduct(int id) throws ProductDoesNoteExistException, FailedToDeleteProductException{
        try {
            Product productToDelete = productDao.getProductsById(id);

            if(productToDelete == null){
                throw new ProductDoesNoteExistException();
            }

            productDao.deleteProduct(id);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToDeleteProductException();
        }
    }

}
