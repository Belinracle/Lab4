package backend.DAO.interfaces;

import backend.entity.Point;

import java.util.List;

public interface PointDao {
    List<Point> getPoints(String username);
    Point insertPoint(Point point);
}