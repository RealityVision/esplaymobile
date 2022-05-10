/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.esplay.services;

import com.codename1.io.NetworkManager;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;

import com.codename1.ui.List;
import com.codename1.ui.events.ActionListener;
import com.esprit.esplay.entities.Chat;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
/**
 *
 * @author fadhe
 */
public class ServiceMessage {
    
    
    public static ServiceMessage instance = null;
    private ConnectionRequest req;
    public boolean resultOK;
     public ServiceMessage() {
        req = new ConnectionRequest();
    }

    public static ServiceMessage getInstance() {
        if (instance == null) {
            instance = new ServiceMessage();
        }
        return instance;
    }
    
     public boolean addmessage(String message) {
        
        String url =  "http://127.0.0.1:8000/chat/chatmobile/?message=" + message ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    public ArrayList<Chat> getAllProduit2(){
         ArrayList<Chat> result = new ArrayList<>();
         String url ="http://127.0.0.1:8000/chat/chatmobile/affiche" ;
        req.setUrl(url);
       // req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               // Produit2 = parseCat(new String(req.getResponseData()));
                //req.removeResponseListener(this);
                 
                
                try {
                    JSONParser jsonp ;
                jsonp = new JSONParser();
                    Map<String,Object> mapProduit2s = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                
                       java.util.List<Map<String,Object>> list = (java.util.List<Map<String,Object>>)mapProduit2s.get("root");
                    for( Map<String, Object> obj : list ) {
                        Chat p = new Chat();
                         String username = obj.get("username").toString();
                         if (obj.get("message")!=null){
                         String nom = obj.get("message").toString();
                         p.setNom(nom);
                        }
                       
                        
                        p.setUsername(username);
                        
                        result.add(p);
                       
            }
        }       
                catch(Exception ex) {
                    
                    ex.printStackTrace();
               }
            
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return result;
    }
    
}
