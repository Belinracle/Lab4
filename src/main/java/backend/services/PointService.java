package backend.services;

import backend.DAO.interfaces.PointDao;
import backend.entity.Point;
import backend.entity.User;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PointService {
    @EJB
    PointDao pointDao;

    public List<Point> getPoints(User user) {
        return pointDao.getPoints(user);
    }

    public void deletePoints(User user){
        pointDao.deletePoints(user);
    }

    public Point insertPoint(Point point) {
        pointDao.insertPoint(point);
        return point;
    }
}