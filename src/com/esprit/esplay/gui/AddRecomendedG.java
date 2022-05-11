/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.esplay.gui;


import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextComponent;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.esplay.entities.Categorie;
import com.esprit.esplay.entities.Game;
import com.esprit.esplay.entities.Recomendedg;
import com.esprit.esplay.services.ServiceCategorie;
import com.esprit.esplay.services.ServiceGame;
import com.esprit.esplay.services.ServiceRecomendedg;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author fadhe
 */
public class AddRecomendedG extends BaseForm{
    
           Form current;

    public AddRecomendedG (Resources res,Form Previous) {
      super ("HomeForm",BoxLayout.y());
      Toolbar tb = new Toolbar (true);
      current = this;
      setToolbar (tb);
      getTitleArea().setUIID("Container");
       getContentPane().setScrollVisible(false);
       
            TextComponent nom= new TextComponent().label("Nom");
            nom.hint("Nom");
        add(nom);
            TextComponent platform= new TextComponent().label("platform");
            platform.hint("platform");
        add(platform);
                Label l2 = new Label("Categorie :");
        add(l2);
        ComboBox<String> combo = new ComboBox<>();
        ArrayList <Categorie> categories = new ArrayList();            
        ServiceCategorie sa =new ServiceCategorie();
                    categories=sa.getAllCategories();
                 for (Categorie tmp : categories) {
                   combo.addItem(tmp.getNom());
                 }
                 add(combo);

            TextComponent url= new TextComponent().label("url");
            url.hint("url");
        add(url);
            TextComponent prix= new TextComponent().label("prix");
            prix.hint("prix");
        add(prix);
                      
       
     
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
          //onclick button event 

        btnAjouter.addActionListener((e) -> {
            
            
            try {
                
                if(url.getText().equals("") || nom.getText().equals("")|| prix.getText().equals("")|| platform.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                   // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    
                    //njibo iduser men session (current user)
                    Recomendedg r = new Recomendedg();

                    r.setNom(nom.getText());
                    r.setCategorie(combo.getSelectedItem());
                    r.setPlatform(platform.getText());
                    r.setUrl(url.getText());
                    r.setPrix(Float.parseFloat(prix.getText()));
                    ServiceRecomendedg.getInstance().addRecomendedg(r);
                    iDialog.dispose();
                    new ListRecomended(res,Previous).show();
                    refreshTheme(); 
                }   
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
        });
}

    
    private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }
 

    
}
