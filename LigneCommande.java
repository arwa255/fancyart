/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author Administrateur
 */
public class LigneCommande {
    
    private int  id_ligne_commande;
    private int id_commande;
    
    private int quantité ; 
    private float prix_unitaire;

    public LigneCommande() {
    }

    public LigneCommande(int id_ligne_commande, int id_commande, int quantité, float prix_unitaire) {
        this.id_ligne_commande = id_ligne_commande;
        this.id_commande = id_commande;
        this.quantité = quantité;
        this.prix_unitaire = prix_unitaire;
    }

    public LigneCommande(int id_commande, int quantité, float prix_unitaire) {
        this.id_commande = id_commande;
        this.quantité = quantité;
        this.prix_unitaire = prix_unitaire;
    }

    public int getId_ligne_commande() {
        return id_ligne_commande;
    }

    public void setId_ligne_commande(int id_ligne_commande) {
        this.id_ligne_commande = id_ligne_commande;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public float getPrix_unitaire() {
        return prix_unitaire;
    }

    public void setPrix_unitaire(float prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }
    
    
    
    
    
}
