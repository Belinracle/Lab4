package backend.DAO.Implementations;

import backend.DAO.interfaces.PointDao;
import backend.entity.Point;
import backend.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.List;

public class PointDaoImpl implements PointDao {

    @PersistenceUnit(name = "postgres")
    public EntityManagerFactory entityManagerFactory;

    @Override
    public List<Point> getPoints(String username) {
        EntityManager em = entityManagerFactory.createEntityManager();
        return (List<Point>)em.createQuery("SELECT all from points where username=?1").setParameter(1,username).getResultList();
    }

    @Transactional
    @Override
    public Point insertPoint(Point point) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.persist(point);
        return point;
    }
}
