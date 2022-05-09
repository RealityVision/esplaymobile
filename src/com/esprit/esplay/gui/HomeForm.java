/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.esplay.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

    Form current;
    public HomeForm() {
        current = this; 
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choisissez une option"));
        Button btnAddTask = new Button("Games Liste");
        Button btnAddTask1 = new Button("Store Statistics");
        Button btnAddTask2 = new Button("Add a Product");
        
        btnAddTask.addActionListener(e -> new ListGames(current).show());
       //  btnAddTask2.addActionListener(e -> new AddProdForm(current).show());
        addAll(btnAddTask,btnAddTask1);

    }

}
