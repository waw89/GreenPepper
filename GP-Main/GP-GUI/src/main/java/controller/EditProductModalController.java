/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.BusinessProduct;
import com.mycompany.gp.domain.Product;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class EditProductModalController implements Initializable {

    @FXML
    private Text lblName;
    @FXML
    private ToggleButton selectedSize;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPrice;
    @FXML
    private Button btnSave;
    @FXML
    private Button btnCancel;

    Product product;

    @FXML
    private Text lblName11;
    @FXML
    private Text lblPrice;

    BusinessProduct bp = new BusinessProduct();
    private boolean productEdited = false;

    /**
     * Getters & Setters
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public Text getLblName() {
        return lblName;
    }

    public void setLblName(String lblName) {
        this.lblName.setText(lblName);
    }

    public ToggleButton getSelectedSize() {
        return selectedSize;
    }

    public void setSelectedSize(ToggleButton selectedSize) {
        this.selectedSize = selectedSize;
    }

    public TextField getTxtName() {
        return txtName;
    }

    public void setTxtName(TextField txtName) {
        this.txtName = txtName;
    }

    public TextField getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(TextField txtPrice) {
        this.txtPrice = txtPrice;
    }

    public Text getLblPrice() {
        return lblPrice;
    }

    public void setLblPrice(String lblPrice) {
        this.lblPrice.setText(lblPrice);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void editProduct(MouseEvent event) throws Exception {
        product.setName(txtName.getText());
        product.setPrice(Integer.parseInt(txtPrice.getText()));
        bp.editProduct(product);
        productEdited = true;
        
        Stage stage = (Stage) btnSave.getScene().getWindow();
        stage.close();

    }

    public boolean isProductEdited() {
        return productEdited;
    }

    @FXML
    private void goBack(MouseEvent event) {
    }

}
