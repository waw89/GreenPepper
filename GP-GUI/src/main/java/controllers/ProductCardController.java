/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.mycompany.gp.domain.IndividualProduct;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.ProductCardModel;

/**
 *
 * @author waw
 */
public class ProductCardController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.productCardModel = new ProductCardModel();

    }

    @FXML
    private void increaseAmount(MouseEvent event) {

        this.productCardModel.increaseAmmount();
        this.btnDecreaseAmount.setDisable(false);
    }

    @FXML
    private void decreaseAmmountActionPerformed(MouseEvent event) {
        this.productCardModel.decreaseAmmountActionPerformed();
    }

    @FXML
    private void addProduct(MouseEvent event) {

    }

    /*
        Set the anchor element for the product card
     */
    public void setProductCardAnchorPaneElement(AnchorPane productCardAnchorPaneElement) {
        this.productCardAnchorPaneElement = productCardAnchorPaneElement;
    }

    /*
        Get the anchor element of the product card
     */
    public AnchorPane getProductCardAnchorPaneElement() {
        return productCardAnchorPaneElement;
    }

    public void customControllerWithProductData(IndividualProduct foodProduct) {
        // initialize the values of the variables for each product 
        this.txtProductName.setText(foodProduct.getName());
        this.txtPrice.setText(String.valueOf(foodProduct.getPrice()));
        this.txtAmount.setText("1");
        this.productCardModel.customControllerWithProductData(foodProduct); // initialize the model variables with the same values the view has.
        
    }

    /*
        Perform the configuration of binding, so the variables update their values in a bidirectional way.
     */
    public void setupBinding() {
        this.txtProductName.textProperty().bindBidirectional(this.productCardModel.getProductName());
        this.txtPrice.textProperty().bind(this.productCardModel.getAccumulatedPrice());
        this.txtAmount.textProperty().bindBidirectional(this.productCardModel.getAmmount());

    }

    /*
        FXML variables
     */
    @FXML
    private Text txtProductName;

    @FXML
    private Text txtPrice;

    @FXML
    private Button btnIncreaseAmount;

    @FXML
    private Button btnDecreaseAmount; // Fixed typo

    @FXML
    private Text txtAmount;

    @FXML
    private Button btnAddProduct;

    /*
        Class variables
     */
    private AnchorPane productCardAnchorPaneElement;

    private ProductCardModel productCardModel;

}
