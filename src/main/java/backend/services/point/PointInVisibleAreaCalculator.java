package backend.services.point;

public class PointInVisibleAreaCalculator {
    public boolean isVisible(Double x, Double y, Double r) {
        return Math.abs(x / r) <= 1.25 && Math.abs(y / r) <= 1.25;
    }
}
