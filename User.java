/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author MED KHALIL
 */
public class User {

    
    
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String Mot_de_passe;
    private String adresse_mail;
    private String role;
    private String image ;
    private int archeive ;
    public User() {
    }
    
    
  public User( int id,String nom, String prenom, String adresse, String Mot_de_passe, String adresse_mail, String role) {
        
        this.id= id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.Mot_de_passe = Mot_de_passe;
        this.adresse_mail = adresse_mail;
        this.role = role;
    }
  
    public User( String nom, String prenom, String adresse, String Mot_de_passe, String adresse_mail, String role) {
        
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.Mot_de_passe = Mot_de_passe;
        this.adresse_mail = adresse_mail;
        this.role = role;
    }

    public User(String nom, String prenom, String adresse, String Mot_de_passe, String adresse_mail) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public User(String nom, String prenom, String adresse, String mail) {
        this.nom=nom ;
        this.prenom=prenom ;
        this.adresse=adresse;
        this.adresse_mail=mail;

    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

 
   

   

   

   
    
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMot_de_passe() {
        return Mot_de_passe;
    }

    public void setMot_de_passe(String Mot_de_passe) {
        this.Mot_de_passe = Mot_de_passe;
    }

    public String getAdresse_mail() {
        return adresse_mail;
    }

    public void setAdresse_mail(String adresse_mail) {
        this.adresse_mail = adresse_mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", Mot_de_passe=" + Mot_de_passe + ", adresse_mail=" + adresse_mail + ", role=" + role + ", image=" + image + ", archeive=" + archeive + '}';
    }


    public int getArcheive() {
        return archeive;
    }

    public void setArcheive(int archeive) {
        this.archeive = archeive;
    }

    

    
    
    
}
