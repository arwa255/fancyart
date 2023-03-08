/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import java.sql.Date;




public class Commentaire {
    private int id_commentaire;
    private String contenu;
    private Date date;
    private int idpost;
    private int iduser;


    public Commentaire(int id_commentaire, String contenu, Date date, int idpost, int iduser ) {
        this.id_commentaire = id_commentaire;
        this.contenu = contenu;
        this.date = date;
        this.idpost = idpost;
        this.iduser=iduser;
            }
    
    
    public Commentaire(String contenu, Date date, int idpost) {
        this.contenu = contenu;
        this.date = date;
        this.idpost = idpost;
    }

    public int getId_commentaire() {
        return id_commentaire;
    }

    public void setId_commentaire(int id_commentaire) {
        this.id_commentaire = id_commentaire;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public int getIdpost() {
        return idpost;
    }

    public void setIdpost(int idpost) {
        this.idpost = idpost;
    }

    @Override
    public String toString() {
        return "\n Commentaire" + id_commentaire + ", contenu=" + contenu + ", date=" + date + ", idpost=" + idpost + '}';
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    
   
    
}
