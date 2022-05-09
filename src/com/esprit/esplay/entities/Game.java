
package com.esprit.esplay.entities;

import java.util.Objects;

/**
 *
 * @author slim
 */
public class Game {
      private int idGame; 

    public Game(int idGame, String title) {
        this.idGame = idGame;
        this.title = title;
    }
    private String title; 
    private String description; 
    private String categorie; 

    public Game(int idGame) {
        this.idGame = idGame;
    }

    public Game(int idGame, String title, String description) {
        this.idGame = idGame;
        this.title = title;
        this.description = description;
    }

    public Game(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public Game(String title, String description, String categorie, float size) {
        this.title = title;
        this.description = description;
        this.categorie = categorie;
        this.size = size;
    }

    public Game(String title, String description, float size) {
        this.title = title;
        this.description = description;
        this.size = size;
    }

    
    
    
    public Game(int idGame, String title, String description, String categorie, float size) {
        this.idGame = idGame;
        this.title = title;
        this.description = description;
        this.categorie = categorie;
        this.size = size;
    }
    private float  size; 

    public Game() {
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public Game(int idGame, String title, String categorie, float size) {
        this.idGame = idGame;
        this.title = title;
        this.categorie = categorie;
        this.size = size;
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
        final Game other = (Game) obj;
        if (this.idGame != other.idGame) {
            return false;
        }
        if (Float.floatToIntBits(this.size) != Float.floatToIntBits(other.size)) {
            return false;
        }
        if (!Objects.equals(this.title, other.title)) {
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

    @Override
    public String toString() {
        return "Game{" + "idGame=" + idGame + ", title=" + title + ", description=" + description + ", categorie=" + categorie + ", size=" + size + '}';
    }

    

   
    
}
