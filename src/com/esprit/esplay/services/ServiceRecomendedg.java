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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.esprit.esplay.entities.Recomendedg;
import com.esprit.esplay.utils.Statics;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 *
 * @author fadhe
 */
public class ServiceRecomendedg {
    
      public ArrayList<Recomendedg> games;

    public static ServiceRecomendedg instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
    
    public ServiceRecomendedg() {
         req = new ConnectionRequest();
    }
    
    public static ServiceRecomendedg getInstance() {
        if (instance == null) {
            instance = new ServiceRecomendedg();
        }
        return instance;
    }

    public void addRecomendedg(Recomendedg games) {
            String url = Statics.BASE_URL + "/recomendedg/mobile/new?nom="+ games.getNom()
                    +"&url="+games.getUrl()
                    +"&platforme="+games.getPlatform()
                    +"&category="+games.getCategorie()
                     +"&prix="+games.getPrix();
            System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener ((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        }); 
         NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public void EditRecomendedg(Recomendedg games) {
            String url = Statics.BASE_URL + "/recomendedg/mobile/editrecg?id="+games.getIdGame()+"&nom="+ games.getNom()
                    +"&url="+games.getUrl()
                    +"&platforme="+games.getPlatform()
                    +"&category="+games.getCategorie()
                     +"&prix="+games.getPrix();
            System.out.println(url);
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener ((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        }); 
         NetworkManager.getInstance().addToQueueAndWait(req);
    }

        public ArrayList<Recomendedg> parseCat(String jsonText){
                try {

                    System.out.println(jsonText);
            games=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Recomendedg a = new Recomendedg();
                                
                float id = Float.parseFloat(obj.get("id").toString());
                a.setIdGame((int) id);
                a.setUrl(obj.get("url").toString());
                float nbr = Float.parseFloat(obj.get("prix").toString());
                a.setPrix(nbr);
                a.setPlatform(obj.get("platform").toString());
                String test=obj.get("category").toString();
                test=test.substring((test).indexOf("categoryName=")+13 ,(test).indexOf(", __"));
                a.setCategorie(test);
                a.setNom(obj.get("nom").toString());
                
                games.add(a);


            }
        } catch (IOException ex) {
            
        }
        return games;
    }


    public ArrayList<Recomendedg> getAllRecomendedgs(){
         String url = Statics.BASE_URL + "/recomendedg/mobile/aff";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                games = parseCat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return games;
    }

    public boolean  Delete(int idg){
       String url = Statics.BASE_URL + "/recomendedg/mobile/del?id=" +idg;

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
