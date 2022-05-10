/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.esplay.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.esplay.entities.Chat;
import com.esprit.esplay.services.ServiceMessage;
import java.util.ArrayList;

/**
 *
 * @author fadhe
 */
public class AfficheChat extends Form {

    public AfficheChat(Form previous) {

        setTitle("Liste des événements");

        ServiceMessage es = new ServiceMessage();
        ArrayList<Chat> list = es.getAllProduit2();

        {

            for (Chat r : list) {
                Container c3 = new Container(BoxLayout.y());

                SpanLabel cat = new SpanLabel("Username :" + r.getUsername());
                SpanLabel cat1 = new SpanLabel("message :" + r.getNom());

                c3.add(cat);
                c3.add(cat1);
                add(c3);
            }

            getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,
                     e -> previous.showBack());

        }

    }

}
