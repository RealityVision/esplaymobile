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
import com.esprit.esplay.entities.Produit2;


import com.esprit.esplay.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author fadhe
 */
public class serviceProduit2 {
    
       
  public ArrayList<Produit2> Produit2;
    
    public static serviceProduit2 instance=null;
    public boolean resultOK;
    private ConnectionRequest req;

    public serviceProduit2() {
         req = new ConnectionRequest();
    }

    public static serviceProduit2 getInstance() {
        if (instance == null) {
            instance = new serviceProduit2();
        }
        return instance;
    }

    public void addProd(Produit2 produit) {
            String url = Statics.BASE_URL + "/api/addprod?nom="+ produit.getNom()
                    +"&description="+produit.getDescription()
                    +"&categorie"+produit.getCategorie()
                     +"&prix"+produit.getPrix();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener ((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        }); 
         NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public ArrayList<Produit2> parseCat(String jsonText){
        try {
            Produit2=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> Produit2ListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)Produit2ListJson.get("root");
            for(Map<String,Object> obj : list){            
                    Produit2 e = new Produit2();
                     try {
                float idp2 = Float.parseFloat(obj.get("idp2").toString());
                      e.setIdp2((int)idp2); } 
                     catch (Exception e1) {
                    System.out.println("errrreuur1");
        }         try {
                      e.setNom(obj.get("nom").toString());
                      } catch (Exception e2) {
                    System.out.println("errrreuur2");
        }
                     
                      try {
                      e.setDescription(obj.get("description").toString());
                      } catch (Exception e4) {
                    System.out.println("errrreuur4");
        }
                      try {
                      e.setCategorie(obj.get("categorie").toString());
                      } catch (Exception e5) {
                    System.out.println("errrreuur5");
        }
                        try {
                      e.setPrix(Float.parseFloat(obj.get("prix").toString()));
                      } catch (Exception e5) {
                    System.out.println("errrreuur5");
        }      
             try {
                Produit2.add(e);
                } catch (Exception e6) {
                    System.out.println("errrreuur6");
        }
            }     
        } catch (IOException ex) {
            
        }
        
        return Produit2;
    }
    
    public ArrayList<Produit2> getAllProduit2(){
         String url = Statics.BASE_URL + "/api/affichProd";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Produit2 = parseCat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Produit2;
    }
    public boolean  Delete(int idp2){
       String url = Statics.BASE_URL + "/api/deleteprod/" +idp2;

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
