import backend.profiling.PointsManager;
import org.junit.Test;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class PointManagerTest {
    @Test
    public void createTest() throws Exception {
        PointsManager manager = new PointsManager();
        ObjectName objectName = new ObjectName("backend.profiling:type=PointsManager,name=PointsManager");
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        server.registerMBean(manager, objectName);
    }
}
