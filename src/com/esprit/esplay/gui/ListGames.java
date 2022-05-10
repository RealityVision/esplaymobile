/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.esplay.gui;


import com.codename1.components.SpanLabel;
import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.esplay.entities.Game;
import com.esprit.esplay.services.ServiceGame;
import java.util.ArrayList;

/**
 *
 * @author fadhe
 */
public class ListGames extends Form{
    
         public  ListGames(Form previous) {

      
       setTitle("GAME LIST");
         
   
 
        
      
        ServiceGame es = new ServiceGame();
        ArrayList<Game> list = es.getAllGames();

        {
       
            for (Game r : list) {
                Container c3 = new Container(BoxLayout.y());
               
                 SpanLabel cat= new SpanLabel("Game name :" + r.getTitle());
                 SpanLabel cat1= new SpanLabel("Description :" + r.getDescription());
                 SpanLabel cat2= new SpanLabel("Category :" + r.getCategorie());
                 SpanLabel cat3= new SpanLabel("Size :" + r.getSize()); 
                        c3.add(cat);
                        c3.add(cat1);
                        c3.add(cat2);
                        c3.add(cat3);
                         Button Delete =new Button("Delete");
         c3.add(Delete);
            Delete.getAllStyles().setBgColor(0xF36B08);
            Delete.addActionListener(e -> {
               Dialog alert = new Dialog("Attention");
                SpanLabel message = new SpanLabel("Are You Sure?\n!!!!!!!!!!!");
                alert.add(message);
                Button ok = new Button("Confirmer");
                Button cancel = new Button(new Command("Annuler"));
                ok.addActionListener(new ActionListener() {
                  
                    public void actionPerformed(ActionEvent evt) {
                       es.Delete(r.getIdGame());
                     
                        alert.dispose();
                        refreshTheme();
                    }
                    
                }
                
                
                );

                alert.add(cancel);
                alert.add(ok);
                alert.showDialog();
                
                new ListGames(previous).show();
            });       
           System.out.println("");
              
  add(c3);
          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK
                , e-> previous.showBack());
                
            }
          
        }
     
    }

}
