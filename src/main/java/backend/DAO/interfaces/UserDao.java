package backend.DAO.interfaces;

import backend.entity.Point;
import backend.entity.User;

import javax.ejb.Stateless;

public interface UserDao {
    User insert(User user) throws Exception;
    User getByName(String name) throws Exception;
//    User update(String name, Point point);
//    User update(User user);
}
