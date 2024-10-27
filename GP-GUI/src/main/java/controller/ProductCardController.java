/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.BusinessProduct;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.Product;
import com.mycompany.gp.domain.ProductOrder;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class ProductCardController implements Initializable {

    @FXML
    private Text txtProductName;
    @FXML
    private Text txtPrice;
    @FXML
    private ComboBox<?> cmbSize;
    @FXML
    private Button btnIncreaseAmount;
    @FXML
    private Text txtAmount;
    @FXML
    private Button btnAddProduct;
    @FXML
    private Button btnDicreaseAmount;
    
    private int counter = 1;

    
    BusinessProduct bp = new BusinessProduct();
    MainPageController mainController;
    
     public void setMainController(MainPageController mainController) {
        this.mainController = mainController;
    }
    
    /**
     * Initializes the controller class.
     */
    private Timeline timelineIncrease;
    private Timeline timelineDecrease;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        btnDicreaseAmount.setDisable(true);

        
        timelineIncrease = new Timeline(new KeyFrame(Duration.millis(200), (ActionEvent event) -> {
            increaseCounter();
        }));
        timelineIncrease.setCycleCount(Timeline.INDEFINITE);

        
        timelineDecrease = new Timeline(new KeyFrame(Duration.millis(200), (ActionEvent event) -> {
            decreaseCounter();
        }));
        timelineDecrease.setCycleCount(Timeline.INDEFINITE);

        
        btnIncreaseAmount.setOnMousePressed(event -> {
            timelineIncrease.play(); 
        });

        btnIncreaseAmount.setOnMouseReleased(event -> {
            timelineIncrease.stop();  
        });

        
        btnDicreaseAmount.setOnMousePressed(event -> {
            timelineDecrease.play();  
        });

        btnDicreaseAmount.setOnMouseReleased(event -> {
            timelineDecrease.stop();  
        });
        
       
        btnDicreaseAmount.setOnMouseClicked(event -> {
            if (event.isStillSincePress()) {  
                decreaseCounter();
            }
        });
    }


    public Text getTxtProductName() {
        return txtProductName;
    }

    public void setTxtProductName(String txtProductName) {
        this.txtProductName.setText(txtProductName);
    }

    public Text getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(String txtPrice) {
        this.txtPrice.setText(txtPrice);
    }

    public Text getTxtAmount() {
        return txtAmount;
    }

    public void setTxtAmount(Text txtAmount) {
        this.txtAmount = txtAmount;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    
     @FXML
    private void increaseAmount(MouseEvent event) {
        increaseCounter();
    }

    private void increaseCounter() {
        counter++;
        btnDicreaseAmount.setDisable(false);
        txtAmount.setText(counter + "");
    }
    

    @FXML
    private void dicreaseAmount(MouseEvent event) {
        decreaseCounter();
    }

    
    private void decreaseCounter() {
        if (counter > 1) {
            counter--;
            txtAmount.setText(counter + "");

            if (counter == 1) {
                btnDicreaseAmount.setDisable(true); 
            }
        }
    }

    @FXML
    private void addProduct(MouseEvent event) throws IOException {
        Product product = bp.findProductByName(txtProductName.getText());
        ProductOrder po = addProductDetails(product);
        counter = 1;
        btnDicreaseAmount.setDisable(true);
        txtAmount.setText(counter+"");
        mainController.updateSummary(po);
    }
    
    private ProductOrder addProductDetails(Product product){
         ProductOrder po = new ProductOrder();
         po.setProduct(product);
         po.setPrice(product.getPrice());
         po.setAmount(counter);
        return po;
    }

   
    
}
