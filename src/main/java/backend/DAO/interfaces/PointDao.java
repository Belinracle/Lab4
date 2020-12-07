package backend.DAO.interfaces;

import backend.entity.Point;
import backend.entity.User;

import java.util.List;

public interface PointDao {
    List<Point> getPoints(User user);
    Point insertPoint(Point point);
    void deletePoints(User user);
}