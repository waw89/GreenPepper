/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import model.ProductItemModel;

/**
 *
 * @author waw
 */
public class ProductItemController implements Initializable {

    @FXML
    private AnchorPane productListItem;
    @FXML
    private Text numberOfProduct;
    @FXML
    private Text txtSize;
    @FXML
    private Button btnCH;
    @FXML
    private Button btnM;
    @FXML
    private Button btnG;
    @FXML
    private ImageView imgTrashIndividual;
    @FXML
    private Text txtIndividualPrice;
    @FXML
    private TextArea txtDetailProduct;
    @FXML
    private Text txtIndividualPrice1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.productItemModel = productItemModel; 
    }
    
    
    ProductItemModel productItemModel;

    @FXML
    private void chSizeClicked(MouseEvent event) {
    }

    @FXML
    private void mSizeClicked(MouseEvent event) {
    }

    @FXML
    private void gSizeClicked(MouseEvent event) {
    }

    @FXML
    private void deleteIndividualProduct(MouseEvent event) {
    }
    

    
    
}
