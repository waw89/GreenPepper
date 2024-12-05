/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.mycompany.gp.domain.IndividualProduct;
import com.mycompany.gp.domain.PRODUCT_SIZE;
import com.mycompany.gp.domain.PRODUCT_TYPE;
import com.mycompany.gp.domain.Product;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class AddProductPageController implements Initializable {

    @FXML
    private ToggleButton btnExtra;
    @FXML
    private ToggleButton btnFood;
    @FXML
    private ToggleButton btnDrink;
    @FXML
    private TextField txtProductName;
    @FXML
    private ToggleButton btnCH;
    @FXML
    private ToggleButton btnM;
    @FXML
    private ToggleButton btnG;
    @FXML
    private TextField priceCH;
    @FXML
    private TextField priceM;
    @FXML
    private TextField priceG;
    @FXML
    private Button btnAddProduct;
    
    IndividualProduct newProduct = new IndividualProduct();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void selectFood(MouseEvent event) {
        setSelectedTypeButtonStyle(btnFood);
        newProduct.setType(PRODUCT_TYPE.FOOD);
        
    }

    @FXML
    private void selectDrink(MouseEvent event) {
        setSelectedTypeButtonStyle(btnDrink);
        newProduct.setType(PRODUCT_TYPE.DRINK);
    }

    @FXML
    private void selectExtra(MouseEvent event) {
        setSelectedTypeButtonStyle(btnExtra);
        newProduct.setType(PRODUCT_TYPE.EXTRA);
    }

    @FXML
    private void btnCHSelected(MouseEvent event) {
        if(btnCH.isSelected()){
            setSelectedSizeButtonStyle(btnCH);
            newProduct.setPRODUCT_SIZE(PRODUCT_SIZE.SMALL);
        }else if(!btnCH.isSelected()){
            setUnselectedSizeButtonStyle(btnCH);
        }
    }

    @FXML
    private void btnMSelected(MouseEvent event) {
         if(btnM.isSelected()){
            setSelectedSizeButtonStyle(btnM);
        }else if(!btnM.isSelected()){
            setUnselectedSizeButtonStyle(btnM);
        }
    }

    @FXML
    private void btnGSelected(MouseEvent event) {
         if(btnG.isSelected()){
            setSelectedSizeButtonStyle(btnG);
        }else if(!btnG.isSelected()){
            setUnselectedSizeButtonStyle(btnG);
        }
    }

    @FXML
    private void addProduct(MouseEvent event) {
        
    }
    
     private void setSelectedTypeButtonStyle(ToggleButton selectedButton) {

        List<ToggleButton> buttons = List.of(btnFood, btnDrink, btnExtra);

        for (ToggleButton button : buttons) {
            if (button.equals(selectedButton)) {
                button.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 50; -fx-border-radius: 50;");
            } else {
                button.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-background-radius: 50; -fx-border-radius: 50; -fx-border-color: black;");
            }
        }
    }
     
    private void setSelectedSizeButtonStyle(ToggleButton toggleButton){
        toggleButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 10; -fx-border-radius: 10;");
    } 
    
    private void setUnselectedSizeButtonStyle(ToggleButton toggleButton){
        toggleButton.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: black;");
    }
    
}
