/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.BusinessProduct;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.PRODUCT_SIZE;
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

    private int selectedAmmountOfProduct = 1;

    BusinessProduct businessProduct = new BusinessProduct();

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

    public int getSelectedAmmountOfProduct() {
        return selectedAmmountOfProduct;
    }

    public void setSelectedAmmountOfProduct(int selectedAmmountOfProduct) {
        this.selectedAmmountOfProduct = selectedAmmountOfProduct;
    }

    @FXML
    private void increaseAmount(MouseEvent event) {
        increaseCounter();
    }


    @FXML
    private void dicreaseAmount(MouseEvent event) {
        decreaseCounter();
    }

    /*
        
        Behavior for the + button in the Product Card
    
     */
    private void increaseCounter() {
        selectedAmmountOfProduct++;
        btnDicreaseAmount.setDisable(false);
        txtAmount.setText(selectedAmmountOfProduct + "");
    }
    
    /*
        
        Behavior for the - button in the Product Card
    
     */
    private void decreaseCounter() {
        if (selectedAmmountOfProduct > 1) { // if the counter is greater than 1, decrease the ammount of the txt
            selectedAmmountOfProduct--;
            txtAmount.setText(selectedAmmountOfProduct + "");

            if (selectedAmmountOfProduct == 1) {
                btnDicreaseAmount.setDisable(true); // if the counter reaches 1 disable the decrease button
            }
        }
    }

    /*
        Adds a product to the summary (in the Main Page).
    
        1. Searches for the product with the name.
        2. creates a product order with the addProductDetails() method.
        3. Disables the dicrease ammount of the product (button).
    
     */
    @FXML
    private void addProduct(MouseEvent event) throws IOException {
        Product product = businessProduct.findProductByName(txtProductName.getText());
        ProductOrder po = addProductDetails(product);
        btnDicreaseAmount.setDisable(true);
        mainController.updateSummaryWithNewSelectedProduct(po, selectedAmmountOfProduct);
        selectedAmmountOfProduct = 1;
        txtAmount.setText(selectedAmmountOfProduct + "");
    }
    
    
    
    /*
        Creates a ProductOrder object with the details of the product.
     */
    private ProductOrder addProductDetails(Product product) {
        ProductOrder po = new ProductOrder();
        po.setProduct(product);
        po.setPRODUCT_SIZE(PRODUCT_SIZE.SMALL);
        po.setPrice(product.getPrice());
        return po;
    }

}
