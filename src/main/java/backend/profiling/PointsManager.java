package backend.profiling;

import backend.entity.Point;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.management.MBeanServer;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;
import java.io.Serializable;
import java.lang.management.ManagementFactory;
import java.util.HashMap;

@Singleton
public class PointsManager extends NotificationBroadcasterSupport implements PointsManagerMBean, Serializable {
    private HashMap<String, Long> usersPointsCount = null;
    private HashMap<String, Long> usersMisses = null;
    private int notificationCounter = 1;

    @PostConstruct
    public void init() {
        usersPointsCount = new HashMap<>();
        usersMisses = new HashMap<>();
        try {
            ObjectName objectName = new ObjectName("backend.profiling:type=PointsManager,name=PointsManager");
            MBeanServer server = ManagementFactory.getPlatformMBeanServer();
            server.registerMBean(this, objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public HashMap<String, Long> getUsersPointsCount() {
        return usersPointsCount;
    }

    @Override
    public HashMap<String, Long> getUsersMisses() {
        return usersMisses;
    }

    @Override
    public void initUserInMBean(String username, long total, long misses) {
        usersPointsCount.putIfAbsent(username, total);
        usersMisses.putIfAbsent(username, misses);
    }


    @Override
    public void increasePointsCounter(String username, boolean hit) {
        usersPointsCount.put(username, usersPointsCount.get(username) + 1);
        if (!hit) {
            usersMisses.put(username, usersMisses.get(username) + 1);
        }
    }

    @Override
    public void notifyAboutInvisiblePoint() {
        Notification n =
                new Notification("PointManagerNotification",
                        this,
                        notificationCounter++,
                        System.currentTimeMillis(),
                        "Point is not visible");
        sendNotification(n);
    }

    @Override
    public void removeUser(String username) {
        usersPointsCount.remove(username);
        usersMisses.remove(username);
    }
}
