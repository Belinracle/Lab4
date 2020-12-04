package backend.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="dolbaebs")
@NamedQueries(
        {
                @NamedQuery(name=User.GET_BY_NAME,query = User.GET_BY_NAME_QUERY)
        }
)
public class User {

    public static final String GET_BY_NAME = "GET_BY_NAME";
    static final String GET_BY_NAME_QUERY = "SELECT dolbaeb from User dolbaeb where dolbaeb.name = :name";
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
