package backend.services;

import backend.entity.Point;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.Arrays;

@Stateless
public class PointBuilder {
    @EJB
    PointChecker pointChecker;

    public Point createPoint(String x, String y, String r, String username)throws  Exception{
        Double cx;
        Double cy;
        Double cr;
        try{
            cx = Double.parseDouble(x);
            cy = Double.parseDouble(y);
            cr = Double.parseDouble(r);
        }catch(NumberFormatException ex1){
            throw new Exception("Введенное вами значение не может быть приведено к типу Double");
        }catch (NullPointerException ex2){
            throw new Exception("Вы не ввели одно из необхбодимых данных (x,y,r)");
        }
        pointChecker.checkDoubleValueInInterval("Y",cy,-5D,3D);
        pointChecker.checkDoubleValueInMassive("X",cx, Arrays.asList(-4D,-3D,-2D,-1D,0D,1D,2D,3D,4D));
        pointChecker.checkDoubleValueInMassive("R",cr, Arrays.asList(1D,2D,3D,4D));
        return new Point(cx,cy,cr,username);
    }
}