package backend.entity;

import javax.persistence.*;

@Entity
@Table(name = "points")
@NamedQueries(
        {
                @NamedQuery(name= Point.GET_BY_USERNAME,query = Point.GET_BY_USERNAME_QUERY),
                @NamedQuery(name= Point.DELETE_BY_USERNAME,query = Point.DELETE_BY_USERNAME_QUERY)
        }
)
public class Point {

    public static final String GET_BY_USERNAME = "GET_BY_USERNAME";
    static final String GET_BY_USERNAME_QUERY = "SELECT point from Point point where point.user = :user";

    public static final String DELETE_BY_USERNAME = "DELETE_BY_USERNAME";
    static final String DELETE_BY_USERNAME_QUERY = "DELETE from Point point where point.user = :user";
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne()
    private User user;

    private Double x;
    private Double y;
    private Double r;
    private Boolean hit;

    public Point(Double x, Double y, Double r, Boolean hit){
        this.x = x;
        this.y = y;
        this.r = r;
        this.hit=hit;
    }
    public Point(){}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public Double getX() { return x; }
    public void setX(Double x) { this.x = x; }
    public Double getY() { return y; }
    public void setY(Double y) { this.y = y; }
    public Double getR() { return r; }
    public void setR(Double r) { this.r = r; }
    public Boolean getHit() { return hit; }
    public void setHit(Boolean hit) { this.hit = hit; }

}