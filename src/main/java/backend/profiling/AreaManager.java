package backend.profiling;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.HashMap;


@Singleton
@LocalBean
public class AreaManager implements AreaManagerMBean {
    private HashMap<String, String> usersFigureSquare = null;

    @PostConstruct
    public void init() {
        usersFigureSquare = new HashMap<>();
        try {
            ObjectName objectName = new ObjectName("backend.profiling:type=AreaManager,name=AreaManager");
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            server.registerMBean(this, objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("проверка");
    }

    @Override
    public HashMap<String, String> getUsersFigureArea() {
        return usersFigureSquare;
    }

    public void setArea(String username, Double r) {
        Double area = r * r / 2 + 3.14 * r * r / 4 + r * r / 2 / 2;
        usersFigureSquare.put(username, String.format("%.3f %s", area, "у.е"));
        System.out.println("метод");
    }

    public void removeUser(String username) {
        usersFigureSquare.remove(username);
    }
}
