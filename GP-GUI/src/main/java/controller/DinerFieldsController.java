/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ximenacastro
 */
public class DinerFieldsController implements Initializable {

    @FXML
    private TextField txtTableName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    public TextField getTxtTableName() {
        return txtTableName;
    }

    public void setTxtTableName(TextField txtTableName) {
        this.txtTableName = txtTableName;
    }
    
    
    
}
