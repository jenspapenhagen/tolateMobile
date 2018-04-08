/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.papenhagen.tolatemobile.views;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author jay
 */
public class PrimaryView {

     private final String name;

    public PrimaryView(String name) {
        this.name = name;
    }
    
    public View getView() {
        try {
            View view = FXMLLoader.load(PrimaryView.class.getResource("primary.fxml"));
            view.setName(name);
            return view;
        } catch (IOException ex) {
            System.out.println("PrimaryView - IOException: " + ex);
            return new View(name);
        }
}
}
