package backend.services.point;

import javax.ejb.Stateless;

@Stateless
public class HitCalculator {
    public Boolean calculate(Double x, Double y, Double r) {
//        Проверка на вхождение в 3 четверть в этом случает там круг радиуса r/2 включая границы
        if (x <= 0 && y <= 0 && x * x + y * y <= (r / 2) * (r / 2)) return true;
//        проверка на 1 четверть
        if (x >= 0 && y >= 0 && y <= (-1) * x / 2 + r / 2) return true;
//        проверка на 4 четвреть
        return x >= 0 && y <= 0 && x < r && y > -r / 2;
    }
}
