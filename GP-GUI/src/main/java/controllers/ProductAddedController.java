/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.ProductAddedModel;

/**
 *
 * @author waw
 */
public class ProductAddedController implements Initializable{

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.productAddedModel  = new ProductAddedModel(); 
    }

    @FXML
    private void toggleProductList(MouseEvent event) {
    }

    @FXML
    private void deleteProductFromSummary(MouseEvent event) {
    }

    public AnchorPane getAnchorPaneElement() {
        return anchorPaneElement;
    }

    public void setAnchorPaneElement(AnchorPane anchorPaneElement) {
        this.anchorPaneElement = anchorPaneElement;
    }
    
    
    
    
    @FXML
    private AnchorPane ProductSummaryContainer;
    @FXML
    private Text txtSummaryProductName;
    @FXML
    private ImageView toogleImg;
    @FXML
    private ImageView imgTrashGeneral;
    @FXML
    private Text txtProductSummaryPrice;
    @FXML
    private Text txtAmount;
    @FXML
    private Text txtAmount1;
    @FXML
    private Text txtProductSummaryPrice1;
    @FXML
    private VBox productListContainer;

    private ProductAddedModel productAddedModel; 

    private AnchorPane anchorPaneElement; 
}
