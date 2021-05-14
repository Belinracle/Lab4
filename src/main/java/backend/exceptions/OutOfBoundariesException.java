package backend.exceptions;

public class OutOfBoundariesException extends RuntimeException{
    public OutOfBoundariesException(String message){
        super(message);
    }
}
