package backend.profiling;

import java.util.HashMap;

public interface AreaManagerMBean {
    HashMap<String, String> getUsersFigureArea();
    void setR(String username, Double r);
    void removeUser(String username);
}
