package backend.DAO.exceptions;

public class UserIsAlreadyExist extends RuntimeException{
    public UserIsAlreadyExist(String mes){
        super(mes);
    }
}
