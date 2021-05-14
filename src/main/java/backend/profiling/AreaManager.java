package backend.profiling;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.util.HashMap;


@Singleton
public class AreaManager implements AreaManagerMBean, Serializable {
    HashMap<String, String> usersFigureSquare = null;

    @PostConstruct
    public void init() {
        usersFigureSquare = new HashMap<>();
        try {
            ObjectName objectName = new ObjectName("backend.profiling:type=SquareManager,name=SquareManager");
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

    @Override
    public void setR(String username, Double r) {
        Double square = r * r / 2 + 3.14 * r * r / 4 + r * r / 2 / 2;
        usersFigureSquare.put(username, String.format("%.3f %s", square, "у.е"));
        System.out.println("метод");
    }

    @Override
    public void removeUser(String username) {
        usersFigureSquare.remove(username);
    }
}
