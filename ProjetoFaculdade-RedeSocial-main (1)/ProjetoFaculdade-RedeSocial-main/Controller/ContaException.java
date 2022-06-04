package Controller;
public class ContaException extends RuntimeException {

    public ContaException(){
        super();
    }

    public ContaException(String message){
        super(message);
    }
}