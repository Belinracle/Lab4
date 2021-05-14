package backend.profiling;

import java.util.HashMap;

public interface PointsManagerMBean {
    HashMap<String, Long> getUsersPointsCount();

    HashMap<String, Long> getUsersMisses();

    void notifyAboutInvisiblePoint();

    void removeUser(String username);

    void increasePointsCounter(String username, boolean hit);

    void initUserInMBean(String user, long total, long misses);
}
