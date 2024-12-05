/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class AdminProductCardController implements Initializable {

    @FXML
    private Text txtProductName;
    @FXML
    private Button editButton;
    @FXML
    private ToggleButton hideButton;
    @FXML
    private AnchorPane card;
    @FXML
    private ImageView imgEye;
    @FXML
    private ToggleButton activateProduct;

    /**
     * Getters & Setters
     */
    public void setTxtProductName(String txtProductName) {
        this.txtProductName.setText(txtProductName);
    }

    public Text getTxtProductName() {
        return txtProductName;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void editProduct(MouseEvent event) {
    }

    @FXML
    private void hideProduct(MouseEvent event) {
        if (this.hideButton.isSelected()) {
            this.card.setDisable(true);
            this.hideButton.setStyle("-fx-background-color:  #8EFF8B; -fx-border-radius: 10");
            this.hideButton.setText("Activar Producto");
            this.hideButton.setDisable(false);
            this.imgEye.setImage(new Image(getClass().getResource("/images/eye-fill-1.png").toExternalForm()));
        } else {
            this.card.setDisable(false);
            this.hideButton.setStyle("-fx-background-color:  #ECECEC; -fx-border-radius: 10");
            this.hideButton.setText("Ocultar Producto");
            this.imgEye.setImage(new Image(getClass().getResource("/images/hideButton.png").toExternalForm()));

        }

    }

}
