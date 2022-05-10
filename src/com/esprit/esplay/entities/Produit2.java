/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.esplay.entities;

import java.util.Objects;

/**
 *
 * @author fadhe
 */
public class Produit2 {
      private int idp2; 
    private String nom; 
    private String description; 
    private String categorie; 
    private float  prix; 

    public Produit2() {
    }

    
    
    public Produit2(int idp2) {
        this.idp2 = idp2;
    }

    public Produit2(int idp2, String nom, String description, String categorie, float prix) {
        this.idp2 = idp2;
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.prix = prix;
    }

    public Produit2(String nom, String description, String categorie, float prix) {
        this.nom = nom;
        this.description = description;
        this.categorie = categorie;
        this.prix = prix;
    }

    public Produit2(String nom, String description, float prix) {
        this.nom = nom;
        this.description = description;
        
        this.prix = prix; //To change body of generated methods, choose Tools | Templates.
    }

  

    public int getIdp2() {
        return idp2;
    }

    public void setIdp2(int idp2) {
        this.idp2 = idp2;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "serviceArticle{" + "idp2=" + idp2 + ", nom=" + nom + ", description=" + description + ", categorie=" + categorie + ", prix=" + prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produit2 other = (Produit2) obj;
        if (this.idp2 != other.idp2) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.categorie, other.categorie)) {
            return false;
        }
        return true;
    }

   
    
}
