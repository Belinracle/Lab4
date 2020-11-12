package backend.DAO.Implementations;

import backend.DAO.exceptions.UserIsAlreadyExist;
import backend.DAO.interfaces.UserDao;
import backend.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.*;
import javax.transaction.Transactional;


@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserDaoImpl implements UserDao {

    private final Logger log = LogManager.getLogger(UserDaoImpl.class);

    @PersistenceUnit(name = "postgres")
    public EntityManagerFactory entityManagerFactory;


    @Transactional
    @Override
    public User insert(User user) {
        try {
            EntityManager em = entityManagerFactory.createEntityManager();
            em.persist(user);
            log.info(String.format("Пользователь %s успешно зарегистрирован",user.getName()));
        }catch(Exception e ){
//                System.out.println(e.getMessage()+"       "+e.getCause());
                log.warn(String.format("кто то попытался зарегистрироваться, но пользователь %s уже существует", user.getName()));
                throw new UserIsAlreadyExist(String.format("Пользователь %s уже существует", user.getName()));
        }
        return user;
    }

    @Override
    public User getByName(String name) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return (User)em.createQuery("SELECT user from dolbaebs where name=?1").setParameter(1,name).getSingleResult();
    }
}
