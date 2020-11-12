package backend.DAO.interfaces;

import backend.entity.Point;

import java.util.List;

public interface PointDao {
    public List<Point> getPoints(String username);
    public Point insertPoint(Point point);
}
