/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import business.BusinessProduct;
import core.ModelFactory;
import core.ViewHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Raul
 */
public class MainFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ModelFactory mf = new ModelFactory(); 
        ViewHandler vh = new ViewHandler(mf); //Create a model factory instance and pass it as argument to the view handler.
        vh.start(); // tell the view handler to show the first visual.

    }

}
