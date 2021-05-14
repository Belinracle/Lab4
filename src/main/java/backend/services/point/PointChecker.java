package backend.services.point;

import javax.ejb.Stateless;
import java.util.List;
import backend.exceptions.OutOfBoundariesException;

@Stateless
public class PointChecker {
    public void checkDoubleValueInInterval(String valueName, Double value, Double min, Double max)throws Exception{
        if(value<min||value>max) throw new OutOfBoundariesException(String.format("Значение %s не входит в границы: [%f;%f]", valueName,min,max));
    }

    public void checkDoubleValueInMassive(String valueName, Double value, List<Double> massive)throws Exception{
        if(!massive.contains(value)) throw new OutOfBoundariesException(String.format("Значение %s не является элементом массива %s",valueName,massToString(massive)));
    }
    private String massToString(List<Double> list){
        StringBuilder sb =  new StringBuilder();
        sb.append("[");
        for(Double i : list){
            sb.append(String.format("'%f',",i));
        }
        sb.append("]");
        return sb.toString();
    }
}