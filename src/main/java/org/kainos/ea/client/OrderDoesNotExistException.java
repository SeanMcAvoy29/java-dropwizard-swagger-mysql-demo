package org.kainos.ea.client;

public class OrderDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "Order does not Exist in Database";
    }
}
