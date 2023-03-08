package entites;

import java.sql.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class Post {
     private int id_post;
    private String description;
    private Date date_p;
    private String image ;
    private int id_user ;

    public Post() {
    }

    public Post(int id_post, String description, Date date_p) {
        this.id_post = id_post;
        this.description = description;
        this.date_p = date_p;
       
    }

    public Post(String description, Date date_p) {
        this.description = description;
        this.date_p = date_p;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    
    
    public int getid_post() {
        return id_post;
    }

    public void setid_post(int id_post) {
        this.id_post= id_post;
    }

    public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }

    public Date getdate_p() {
        return date_p;
    }

    public void setdate_p(Date date) {
        this.date_p = date;
    }

    public boolean contains() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString() {
        return "Post{" + "id_post=" + id_post + ", description=" + description + ", date_p=" + date_p + ", image=" + image + ", id_user=" + id_user + '}';
    }
    
    
    
}
