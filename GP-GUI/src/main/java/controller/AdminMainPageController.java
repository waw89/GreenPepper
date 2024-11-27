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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class AdminMainPageController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private ImageView employeesImg;
    @FXML
    private ImageView productsImg;
    @FXML
    private ImageView promoImg;
    @FXML
    private ImageView reportsImg;
    @FXML
    private AnchorPane ap;
    @FXML
    private Button btnProducts;
    @FXML
    private Button btnEmployees;
    @FXML
    private Button btnPromos;
    @FXML
    private Button btnReports;
    @FXML
    private Button btnLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPage("ProductsPage");
    }    

    @FXML
    private void products(MouseEvent event) {
        loadPage("ProductsPage");
    }

    @FXML
    private void employees(MouseEvent event) {
    }

    @FXML
    private void promos(MouseEvent event) {
    }

    @FXML
    private void reports(MouseEvent event) {
        loadPage("ReportsPage");
    }

    @FXML
    private void logout(MouseEvent event) {
    }

    @FXML
    private void productsImgClick(MouseEvent event) {
        products(event);
    }
    
    @FXML
    private void employeesImgClick(MouseEvent event) {
        employees(event);
    }
    
    @FXML
    private void promosImgClick(MouseEvent event) {
        promos(event);
    }

    @FXML
    private void reportsImgClick(MouseEvent event) {
        reports(event);
    }

    @FXML
    private void logoutImgClick(MouseEvent event) {
        logout(event);
    }
   

       @FXML
    public void loadPage(String namePage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/" + namePage + ".fxml"));
            Parent root = loader.load();
            if (namePage.equals("ProductsPage")) {
                ProductsPageController controller = loader.getController();
                controller.setAdminMainController(this);
            }
            bp.setCenter(root);  // Establece el contenido en el centro del BorderPane
        } catch (IOException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    
}
