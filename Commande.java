/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Administrateur
 */
public class Commande {
    private int id_com;
    private int id_produit;
    private float prix;
    private float montant;
    private Date date_com;
    private int id_user ;

    public Commande() {
    }

    public Commande(int id_com, int id_produit, float prix, float montant, Date date_com) {
        this.id_com = id_com;
        this.id_produit = id_produit;
        this.prix = prix;
        this.montant = montant;
        this.date_com = date_com;
    }

    public Commande(int id_produit, float prix, float montant, Date date_com) {
        this.id_produit = id_produit;
        this.prix = prix;
        this.montant = montant;
        this.date_com = date_com;
    }

    public int getId_com() {
        return id_com;
    }

    public void setId_com(int id_com) {
        this.id_com = id_com;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Date getDate_com() {
        return date_com;
    }

    public void setDate_com(Date date_com) {
        this.date_com = date_com;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    
    
    
    
}
