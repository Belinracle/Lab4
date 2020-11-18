package backend.services;


import backend.DAO.interfaces.PointDao;
import backend.entity.Point;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class PointService {
    @EJB
    PointDao pointDao;

    public List<Point> getPoints(String username){
        return pointDao.getPoints(username);
    }

    public Point insertPoint(Point point){
        pointDao.insertPoint(point);
        return point;
    }
}
