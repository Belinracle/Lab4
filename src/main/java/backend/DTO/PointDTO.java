package backend.DTO;

import backend.controllers.PointsController;

public class PointDTO {
    private String x;
    private String y;
    private String r;
    private String username;
    private Boolean hit;

    public PointDTO(String x, String y, String r, String username, Boolean hit){
        this.hit=hit;
        this.r=r;
        this.x=x;
        this.username=username;
        this.y=y;
    }
    public PointDTO(){
    }

    public Boolean getHit() { return hit; }

    public void setHit(Boolean hit) { this.hit = hit; }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getR() {
        return r;
    }

    public void setR(String r) {
        this.r = r;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
