package backend.profiling;

import java.util.HashMap;

public interface SquareManagerMBean {
    HashMap<String, String> getUsersFigureSquare();
    void setR(String username, Double r);
}
