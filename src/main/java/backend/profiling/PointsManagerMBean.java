package backend.profiling;

import javax.management.MalformedObjectNameException;
import java.util.HashMap;

public interface PointsManagerMBean {
    HashMap<String, Integer> getUsersPointsCount();
    HashMap<String, Integer> getUsersMisses();
    void increasePointsCounter(String username,boolean hit);
    void createANdPublishNotification();
}
