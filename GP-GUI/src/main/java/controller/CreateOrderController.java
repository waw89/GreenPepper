/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.ProductOrder;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class CreateOrderController implements Initializable {


   private Order order;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    public void setOrder(Order order) {
        this.order = order;
    }
    
    

}
