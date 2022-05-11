/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.esplay.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
import com.codename1.ui.util.Resources;
import com.esprit.esplay.entities.Recomendedg;
import com.esprit.esplay.services.ServiceRecomendedg;
import java.util.ArrayList;

/**
 *
 * @author msi
 */
public class RechercherRecomendedG extends Form{
        Form current;

        public RechercherRecomendedG(Resources res, Form previous) {
            
        setTitle("Liste des Utilisateurs");
        
          Container co;
                       //search
             Toolbar.setGlobalToolbar(true);
             add(new InfiniteProgress());
             
                Display.getInstance().scheduleBackgroundTask(()-> {
                    // this will take a while...
                    Display.getInstance().callSerially(() -> {
                    removeAll();
                    ArrayList <Recomendedg> games = new ArrayList();
                        ServiceRecomendedg sa =new ServiceRecomendedg();
                    games=sa.getAllRecomendedgs();
                             for (Recomendedg fi : games) {
                            MultiButton m = new MultiButton();
                            m.setTextLine1("Name : "+String.valueOf(fi.getNom()));
                            m.setTextLine2("Cat :"+String.valueOf(fi.getCategorie()));
                            m.setTextLine3("Prix :"+String.valueOf(fi.getPrix()));
                            m.setTextLine4("Platforme :"+String.valueOf(fi.getPlatform()));


                            add(m);
                             }
                     revalidate();
                    });
                });
    getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
            line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        getContentPane().animateLayout(150);
    }
}, 4);

                  getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK   , e-> previous.showBack());
                
            
        }

    
}
