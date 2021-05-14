package backend.profiling;

import backend.entity.Point;

import java.util.HashMap;
import java.util.List;

public interface PointsManagerMBean {
    HashMap<String, Integer> getUsersPointsCount();

    HashMap<String, Integer> getUsersMisses();

    void method(String username, List<Point> points);

    void createANdPublishNotification();

    void removeUser(String username);

    void increasePointsCounter(String username, boolean hit);
}
