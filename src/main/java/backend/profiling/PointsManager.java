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
import java.util.List;


@Singleton
public class PointsManager extends NotificationBroadcasterSupport implements PointsManagerMBean, Serializable {
    HashMap<String, Integer> usersPointsCount = null;
    HashMap<String, Integer> usersMisses = null;
    int notificationCounter = 1;

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
    public HashMap<String, Integer> getUsersPointsCount() {
        return usersPointsCount;
    }

    @Override
    public HashMap<String, Integer> getUsersMisses() {
        return usersMisses;
    }

    @Override
    public void method(String username, List<Point> points) {
        for (int i = 0; i < points.size(); i++) {
            increasePointsCounter(username, points.get(i).getHit());
        }
    }


    @Override
    public void increasePointsCounter(String username, boolean hit) {
        if (!usersPointsCount.containsKey(username)) {
            usersPointsCount.put(username, 0);
        }
        usersPointsCount.put(username, usersPointsCount.get(username) + 1);
        if (!hit) {
            if (!usersMisses.containsKey(username)) {
                usersMisses.put(username, 0);
            }
            usersMisses.put(username, usersMisses.get(username) + 1);
        }
    }

    @Override
    public void createANdPublishNotification() {
        Notification n =
                new Notification("PointManagerNotification",
                        this,
                        notificationCounter++,
                        System.currentTimeMillis());
        sendNotification(n);
    }

    @Override
    public void removeUser(String username) {
        usersPointsCount.remove(username);
        usersMisses.remove(username);
    }
}
