/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.esplay.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.esplay.entities.Produit2;
import com.esprit.esplay.services.serviceProduit2;

/**
 *
 * @author fadhe
 */
public class AddProdForm extends Form {

    Form current;

    public AddProdForm(Resources res) {
        super("HomeForm", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current = this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("add a product");
        getContentPane().setScrollVisible(false);

        TextField nom = new TextField("", "Add an Item");
       // nom.setUIID("TextFieldBlack");
        addStringValue("nom", nom);

        TextField description = new TextField("", "Add an Item");
       // description.setUIID("TextFieldBlack");
        addStringValue("description", description);

        TextField categorie = new TextField("", "Add an Item");
        //categorie.setUIID("TextFieldBlack");
        addStringValue("categorie", categorie);

        TextField prix = new TextField("", "Add an Item");
        //prix.setUIID("TextFieldBlack");
        addStringValue("prix", prix);

        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);

        //onclick button event 
        btnAjouter.addActionListener((e) -> {

            try {

                if (nom.getText().equals("") || description.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données", "", "Annuler", "OK");
                } else {
                    InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data

                    final Dialog iDialog = ip.showInfiniteBlocking();

                    // SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    //njibo iduser men session (current user)
                    Produit2 r = new Produit2(String.valueOf(nom.getText()
                    ).toString(),
                            String.valueOf(description.getText()).toString(),
                            ///   format.format(new Date()),
                            0);

                    // System.out.println("data  reclamation == "+r);
                    serviceProduit2.getInstance().addProd(r);
                    iDialog.dispose();
                    //new ListReclamationForm(res).show();
                    refreshTheme();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void addStringValue(String s, Component v) {

        add(BorderLayout.west(new Label(s, "PaddedLabel"))
                .add(BorderLayout.CENTER, v));
        //add(createLineSeparator(0xeeeeee));
    }

}
