package backend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="dolbaebs")
public class User {
    @Id
    private String name;
    private String pass;

    @OneToMany(mappedBy = "username")
    private List<Point> points;

    public User() { }
    public User(String username,String usrPass){
        name=username;
        pass=usrPass;
    }

    public void setName(String name){this.name=name;}
    public void setPass(String pass){this.pass=pass;}
    public void setPoints(List<Point> points) { this.points = points; }

    public List<Point> getPoints() { return points; }
    public String getName(){return name;}
    public String getPass(){return pass;}
}
