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
import com.esprit.esplay.entities.Categorie;
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
public class ServiceCategorie {
    
      public ArrayList<Categorie> cats;

    public static ServiceCategorie instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    
    
    public ServiceCategorie() {
         req = new ConnectionRequest();
    }
    
    public static ServiceCategorie getInstance() {
        if (instance == null) {
            instance = new ServiceCategorie();
        }
        return instance;
    }



        public ArrayList<Categorie> parseCat(String jsonText){
                try {

                    System.out.println(jsonText);
            cats=new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String,Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String,Object>> list = (List<Map<String,Object>>)tasksListJson.get("root");
            for(Map<String,Object> obj : list){
                Categorie a = new Categorie();
                                
                float id = Float.parseFloat(obj.get("categoryId").toString());
                a.setId((int) id);
                a.setNom(obj.get("categoryName").toString());
                
                cats.add(a);


            }
        } catch (IOException ex) {
            
        }
        return cats;
    }


    public ArrayList<Categorie> getAllCategories(){
         String url = Statics.BASE_URL + "/category/mobile/aff";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                cats = parseCat(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return cats;
    }

    
}
