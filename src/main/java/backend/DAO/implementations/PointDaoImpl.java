package backend.DAO.implementations;
import backend.DAO.interfaces.PointDao;
import backend.entity.Point;
import backend.entity.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class PointDaoImpl implements PointDao {

    @PersistenceUnit(name = "postgres")
    public EntityManagerFactory entityManagerFactory;

    @Override
    public List<Point> getPoints(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return em.createNamedQuery(Point.GET_BY_USERNAME,Point.class).setParameter("user",user).getResultList();
    }

    @Transactional
    @Override
    public Point insertPoint(Point point) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.persist(point);
        return point;
    }

    @Override
    @Transactional
    public void deletePoints(User user) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.createNamedQuery(Point.DELETE_BY_USERNAME).setParameter("user",user).executeUpdate();
    }
}