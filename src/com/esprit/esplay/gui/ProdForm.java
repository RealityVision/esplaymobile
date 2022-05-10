/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.esplay.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author fadhe
 */
public class ProdForm extends Form{
      Form current;
          private Resources theme;

    public ProdForm() {
                        theme = UIManager.initFirstTheme("/theme");

        current = this; 
        setTitle("ESPLAY");
        setLayout(BoxLayout.y());

        add(new Label("Choisissez une option"));
        Button btnAddTask = new Button("Product List");
        Button btnAddTask1 = new Button("Product Statistics");
        Button btnAddTask2 = new Button("Add a Product");
        
        btnAddTask.addActionListener(e -> new ListProduit2(current).show());
        btnAddTask2.addActionListener(e -> new AddGameForm(theme).show());
        addAll(btnAddTask,btnAddTask1,btnAddTask2);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,e->current.showBack());

    }

    
}
