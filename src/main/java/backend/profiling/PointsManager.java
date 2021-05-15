package backend.profiling;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.management.MBeanServer;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;
import java.util.HashMap;

@Singleton
@LocalBean
public class PointsManager extends NotificationBroadcasterSupport implements PointsManagerMBean {
    private HashMap<String, Long> usersPointsCount = null;
    private HashMap<String, Long> usersMisses = null;
    private long allPointsSinceStartup = 0;
    private long allMissesSinceStartup = 0;
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
    public long getAllPointsSinceStartup() {
        return allPointsSinceStartup;
    }

    @Override
    public long getAllMissesSinceStartup() {
        return allMissesSinceStartup;
    }

    public void initUserInMBean(String username, long total, long misses) {
        usersPointsCount.putIfAbsent(username, total);
        usersMisses.putIfAbsent(username, misses);
    }

    public void increasePointsCounter(String username, boolean hit) {
        usersPointsCount.put(username, usersPointsCount.get(username) + 1);
        allPointsSinceStartup++;
        if (!hit) {
            usersMisses.put(username, usersMisses.get(username) + 1);
            allMissesSinceStartup++;
        }
    }

    public void notifyAboutInvisiblePoint() {
        Notification n =
                new Notification("PointManagerNotification",
                        this,
                        notificationCounter++,
                        System.currentTimeMillis(),
                        "Point is not visible");
        sendNotification(n);
    }

    public void removeUser(String username) {
        usersPointsCount.remove(username);
        usersMisses.remove(username);
    }
}
