package backend.entity;

import javax.persistence.*;

@Entity
@Table(name="dolbaebs")
public class User {
    @Id
    private String name;
    private String pass;

    public User(String username,String usrPass){
        name=username;
        pass=usrPass;
    }



    public User() {

    }

    public void setName(String name){this.name=name;}
    public void setPass(String pass){this.pass=pass;}

    public String getName(){return name;}
    public String getPass(){return pass;}
}
