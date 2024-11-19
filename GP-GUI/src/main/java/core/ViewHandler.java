/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import controllers.MainPageController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Luis Enrique Contreras Peraza 
 * @author Jesús Raúl Luna Bringas
 * @author Brayan D. García Picos 
 */
public class ViewHandler {

    private ModelFactory modelFactory;
    private Stage stage;
    private Scene mainPageScene;
    MainPageController mainPageController;
    
    public ViewHandler(ModelFactory modelFactory) {
        this.modelFactory = modelFactory;
        stage = new Stage();
    }

    /*
        This method calls the openMainView, with the stage that was defined in the constructor.
        After the Stage is set, this method shows it.
     */
    public void start() {
        openMainView(stage);
        stage.show();
    }

    public void openMainView(Stage stage) {

        if (mainPageScene == null) {
            try {

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/fxml/MainPage.fxml"));
                Parent root = loader.load();
                mainPageController = loader.getController();
                mainPageController.init(this, modelFactory.getMainPageModel());
                mainPageScene = new Scene(root);

            } catch (Exception e) {
                System.out.println("There was an error");
                e.printStackTrace();
            }

        }
        stage.setScene(mainPageScene);
        stage.setTitle("Green Pepper");
    }
    
    
    
    
    
  
    
    
    
}
