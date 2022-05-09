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
import com.esprit.esplay.entities.Game;
import com.esprit.esplay.services.ServiceGame;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author fadhe
 */
public class AddGameForm extends BaseForm{
    
           Form current;

    public AddGameForm (Resources res) {
      super ("HomeForm",BoxLayout.y());
      Toolbar tb = new Toolbar (true);
      current = this;
      setToolbar (tb);
      getTitleArea().setUIID("Container");
       setTitle("add a product");
       getContentPane().setScrollVisible(false);
       
    
    TextField title = new TextField("","Add an Item");
    title.setUIID("TextFieldBlack") ;
    addStringValue("Title",title);
    
     TextField description = new TextField("","Add an Item");
    description.setUIID("TextFieldBlack") ;
    addStringValue("description",description);
    
     TextField categorie = new TextField("","Add an Item");
    categorie.setUIID("TextFieldBlack") ;
    addStringValue("categorie",categorie);
    
     TextField size = new TextField("","Add an Item");
    size.setUIID("TextFieldBlack") ;
    addStringValue("prix",size);
    
     
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);
        
          //onclick button event 

        btnAjouter.addActionListener((e) -> {
            
            
            try {
                
                if(title.getText().equals("") || description.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                   // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    
                    //njibo iduser men session (current user)
                    Game r = new Game(String.valueOf(title.getText()),String.valueOf(description.getText()).toString(),0);
                               ///   format.format(new Date()),0);
                    
                   // System.out.println("data  reclamation == "+r);
                                       
                    ServiceGame.getInstance().addGame(r);
                    iDialog.dispose();
                    //new ListReclamationForm(res).show();
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
