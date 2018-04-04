/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.papenhagen.tolatemobile.view;

import com.gluonhq.charm.glisten.mvc.View;
import java.io.IOException;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author jay
 */
public class MainView {

    private final String name;

    public MainView(String name) {
        this.name = name;
    }

    public View getView() {
        try {
            View view = FXMLLoader.load(MainView.class.getResource("main.fxml"));
            view.setName(name);
            return view;
        } catch (IOException e) {
            System.out.println("IOException: " + e);
            return new View(name);
        }
    }
}
