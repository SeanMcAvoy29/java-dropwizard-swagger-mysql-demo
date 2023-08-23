package org.kainos.ea.client;

public class ProductDoesNoteExistException extends Throwable {
    @Override
    public String getMessage(){
        return "Product does not Exist in Database";
    }
}
