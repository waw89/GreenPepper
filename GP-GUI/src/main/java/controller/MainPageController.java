/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class MainPageController implements Initializable {

    @FXML
    private Button btnInicio;
    @FXML
    private Button btnActiveOrders;
    @FXML
    private Button btnOrdersHistory;
    @FXML
    private Button btnLogOut;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnFood;
    @FXML
    private Button btnDrink;
    @FXML
    private Button btnExtra;
    @FXML
    private TextField txtSearchProduct;
    @FXML
    private Button btnSearch;
    @FXML
    private BorderPane bp;
    @FXML
    private ImageView homeImg;
    @FXML
    private ImageView orderImg;
    @FXML
    private ImageView historyImg;
    @FXML
    private ImageView logOutImg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void home(MouseEvent event) {
        bp.setCenter(ap);
    }

    @FXML
    private void activeOrders(MouseEvent event) {
        loadPage("ActiveOrders");
    }

    @FXML
    private void ordersHistory(MouseEvent event) {
        loadPage("OrdersHistory");
    }

    @FXML
    private void logOut(MouseEvent event) {
    }
    
    private void loadPage(String namePage){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/fxml/" + namePage + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        bp.setCenter(root);
    }

    @FXML
    private void save(MouseEvent event) {
        loadPage("CreateOrder");
    }

    @FXML
    private void homeImgClick(MouseEvent event) {
        home(event);
    }

    @FXML
    private void ordersImgClick(MouseEvent event) {
        activeOrders(event);
    }

    @FXML
    private void historyImgClick(MouseEvent event) {
        ordersHistory(event);
    }

    @FXML
    private void logOutImgClick(MouseEvent event) {
    }
}
