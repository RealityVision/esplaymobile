/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.esplay.entities;

/**
 *
 * @author fadhe
 */
public class Chat {
    
    String username;

    public Chat(String username, String nom) {
        this.username = username;
        this.nom = nom;
    }

    public Chat(String username) {
        this.username = username;
    }

    public Chat() {
     
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
   String  nom ;
    
        
    
}
