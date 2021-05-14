package backend.profiling;

import java.util.HashMap;

public interface PointsManagerMBean {
    HashMap<String, Integer> getUsersPointsCount();
    HashMap<String, Integer> getUsersMisses();
    void createANdPublishNotification();
    void increasePointsCounter(String username, boolean hit);
}
