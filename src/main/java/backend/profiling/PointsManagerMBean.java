package backend.profiling;

import java.util.HashMap;

public interface PointsManagerMBean {
    HashMap<String, Long> getUsersPointsCount();

    HashMap<String, Long> getUsersMisses();

}
