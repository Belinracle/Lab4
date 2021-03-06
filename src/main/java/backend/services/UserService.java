package backend.services;

import backend.DAO.interfaces.UserDao;
import backend.entity.Point;
import backend.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class UserService {
    private final Logger log = LogManager.getLogger(UserService.class);

    @EJB
    UserDao userDao;

    public User registerUser(User user) throws Exception {
        try {
            userDao.insert(user);
        } catch (Exception e) {
            log.warn("Произошла ошибка в БД");
            throw new Exception("Пользователь с таким именем уже существует");
        }
        return user;
    }

    public User findUserByName(String username) throws Exception {
        return userDao.getByName(username);
    }

    public void authenticate(String name, String password) throws Exception {
        User user;
        try {
            user = findUserByName(name);
        } catch (Exception e) {
            throw new Exception(String.format("Пользователь с именем %s отсутствует в базе", name));
        }
        if (!user.getPass().equals(password)) throw new Exception("Пароли не совпадают");
    }

//    public void updateUser(String name, Point point){
//        userDao.update(name,point);
//    }
}