/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import business.BusinessProduct;
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
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainPage.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setMaximized(false); // Cambiar a True cuando el sistema sea responsivo
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        chargeProducts();
        launch(args);
    }
    
    
     public static void chargeProducts() {
        BusinessProduct bp = new BusinessProduct();
        bp.chargerProducts();
    }

}
