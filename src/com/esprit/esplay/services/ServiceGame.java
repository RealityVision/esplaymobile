/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.esplay.services;



import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.esplay.entities.Game;
import com.esprit.esplay.utils.Statics;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author fadhe
 */
public class ServiceGame {
    
      public ArrayList<Game> game;

    public static ServiceGame instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
    
    public ServiceGame() {
         req = new ConnectionRequest();
    }
    
    public static ServiceGame getInstance() {
        if (instance == null) {
            instance = new ServiceGame();
        }
        return instance;
    }

    public void addGame(Game game) {
            String url = Statics.BASE_URL + "/api/addprod?title="+ game.getTitle()
                    +"&description="+game.getDescription()
                    +"&category"+game.getCategorie()
                     +"&size"+game.getSize();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener ((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        }); 
         NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public ArrayList<Game> parseCat(String jsonText){
        try {
            game=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> GameListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)GameListJson.get("root");
            for(Map<String,Object> obj : list){            
                    Game e = new Game();
                     try {
                float idg = Float.parseFloat(obj.get("idGame").toString());
                      e.setIdGame((int)idg); } 
                     catch (Exception e1) {
                    System.out.println("errrreuur1");
        }         try {
                      e.setTitle(obj.get("Title").toString());
                      } catch (Exception e2) {
                    System.out.println("errrreuur2");
        }
                     
                      try {
                      e.setDescription(obj.get("description").toString());
                      } catch (Exception e4) {
                    System.out.println("errrreuur4");
        }
                      try {
                      e.setCategorie(obj.get("category").toString());
                      } catch (Exception e5) {
                    System.out.println("errrreuur5");
        }
                        try {
                      e.setSize(Float.parseFloat(obj.get("size").toString()));
                      } catch (Exception e5) {
                    System.out.println("errrreuur5");
        }      
             try {
                game.add(e);
                } catch (Exception e6) {
                    System.out.println("errrreuur6");
        }
            }     
        } catch (IOException ex) {
            
        }
        
        return game;
    }

    public ArrayList<Game> getAllGames(){
         String url = Statics.BASE_URL + "/Gapi/affichGame";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                game = parseCat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return game;
    }

    public boolean  Delete(int idg){
       String url = Statics.BASE_URL + "/Gapi/deleteGame/" +idg;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
      
      
      }

    
}
