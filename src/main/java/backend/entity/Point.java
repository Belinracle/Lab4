package backend.entity;

import javax.persistence.*;
@Entity
@Table(name = "points")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String username;

    private Double x;
    private Double y;
    private Double r;

    public Point(Double x, Double y, Double r, String username){
        this.username = username ;
        this.x = x;
        this.y = y;
        this.r = r;
    }
    public Point(){}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String user) { this.username = user; }
    public Double getX() { return x; }
    public void setX(Double x) { this.x = x; }
    public Double getY() { return y; }
    public void setY(Double y) { this.y = y; }
    public Double getR() { return r; }
    public void setR(Double r) { this.r = r; }

}
