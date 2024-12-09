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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class ReportsPageController implements Initializable {

    @FXML
    private Button btnCorteCaja;
    @FXML
    private Button btnVentas;
    @FXML
    private Button btnArticulosVendidos;
    @FXML
    private VBox containerFXML;

    AdminMainPageController adminController;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setAdminController(AdminMainPageController adminController){
        this.adminController = adminController;
    }

    public Button getBtnCorteCaja() {
        return btnCorteCaja;
    }

    public void setBtnCorteCaja(Button btnCorteCaja) {
        this.btnCorteCaja = btnCorteCaja;
    }

    public Button getBtnVentas() {
        return btnVentas;
    }

    public void setBtnVentas(Button btnVentas) {
        this.btnVentas = btnVentas;
    }

    public Button getBtnArticulosVendidos() {
        return btnArticulosVendidos;
    }

    public void setBtnArticulosVendidos(Button btnArticulosVendidos) {
        this.btnArticulosVendidos = btnArticulosVendidos;
    }

    public VBox getContainerFXML() {
        return containerFXML;
    }

    public void setContainerFXML(VBox containerFXML) {
        this.containerFXML = containerFXML;
    }
    
    public void loadSalesPage(){
        
        try{
            
            containerFXML.getChildren().clear();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Sales.fxml"));
            Parent newPage = loader.load();
            
            containerFXML.getChildren().add(newPage);
            
        } catch (IOException ex) {
            Logger.getLogger(ReportsPageController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        
    }

    @FXML
    private void corteOption(MouseEvent event) {
    }

    @FXML
    private void ventasOption(MouseEvent event) {
        loadSalesPage();
    }

    @FXML
    private void articulosOption(MouseEvent event) {
    }
    
}
