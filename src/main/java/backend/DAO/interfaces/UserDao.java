package backend.DAO.interfaces;

import backend.entity.User;

import javax.ejb.Stateless;

public interface UserDao {
     User insert(User user);
     User getByName(String name);
//     User update(User user);
}
