/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.BusinessProduct;
import com.mycompany.gp.domain.PRODUCT_SIZE;
import static com.mycompany.gp.domain.PRODUCT_SIZE.LARGE;
import static com.mycompany.gp.domain.PRODUCT_SIZE.MEDIUM;
import static com.mycompany.gp.domain.PRODUCT_SIZE.SMALL;
import com.mycompany.gp.domain.Product;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
    private Text lblSizes;
    @FXML
    private Text lblPrice;

    Product product;

    ProductsPageController productsPageController;

    BusinessProduct bp = new BusinessProduct();
    @FXML
    private Text txtPrice;
    @FXML
    private Text txtSize;

    /**
     * Getters & Setters
     */
    public void setTxtProductName(String txtProductName) {
        this.txtProductName.setText(txtProductName);
    }

    public Text getTxtProductName() {
        return txtProductName;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public ProductsPageController getProductsPageController() {
        return productsPageController;
    }

    public void setProductsPageController(ProductsPageController productsPageController) {
        this.productsPageController = productsPageController;
    }

    public Text getTxtPrice() {
        return txtPrice;
    }

    public void setTxtPrice(String txtPrice) {
        this.txtPrice.setText(txtPrice);
    }

    public Text getTxtSize() {
        return txtSize;
    }

    public void setTxtSize(String txtSize) {
        this.txtSize.setText(txtSize);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void editProduct(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditProductModal.fxml"));
        Parent modal = loader.load();
        EditProductModalController modalController = loader.getController();
        modalController.setProduct(product);
        modalController.setLblName(product.getName());
        modalController.getTxtName().setText(product.getName());
        modalController.getTxtPrice().setText(String.valueOf(product.getPrice()));
        modalController.setLblPrice(String.valueOf(product.getPrice()));
        modalController.getSelectedSize().setText(setSizeText(product.getPRODUCT_SIZE()));

        Stage stage = new Stage();
        stage.setScene(new Scene(modal));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        if (modalController.isProductEdited()) {
            updateProductUI(product);
        }

    }

    private void updateProductUI(Product product) { 
        txtProductName.setText(product.getName());
        txtPrice.setText(String.valueOf(product.getPrice()));
    }

    private String setSizeText(PRODUCT_SIZE size) {
        switch (size) {
            case SMALL:
                return "CH";
            case MEDIUM:
                return "M";
            case LARGE:
                return "G";
            default:
                return "N/A";
        }

    }

    @FXML
    private void hideProduct(MouseEvent event) throws Exception {
        if (this.hideButton.isSelected()) {
            this.card.setStyle("-fx-background-color: whitesmoke");
            this.txtProductName.setFill(Paint.valueOf("#8D8D8D"));
            this.lblSizes.setFill(Paint.valueOf("#8D8D8D"));
            this.lblPrice.setFill(Paint.valueOf("#8D8D8D"));
            this.hideButton.setStyle("-fx-background-color:  #8EFF8B;");
            this.hideButton.setText("Activar Producto");
            this.hideButton.setTextFill(Paint.valueOf("white"));
            this.imgEye.setImage(new Image(getClass().getResource("/images/eye-fill-1.png").toExternalForm()));
            disableProduct();

        } else {
            this.card.setStyle("-fx-background-color: white");
            this.txtProductName.setFill(Paint.valueOf("black"));
            this.lblSizes.setFill(Paint.valueOf("black"));
            this.lblPrice.setFill(Paint.valueOf("black"));
            this.hideButton.setStyle("-fx-background-color: #ECECEC;");
            this.hideButton.setTextFill(Paint.valueOf("#595858"));
            this.hideButton.setText("Ocultar Producto");
            this.imgEye.setImage(new Image(getClass().getResource("/images/hideButton.png").toExternalForm()));
            enableProduct();
        }

    }

    private void enableProduct() throws Exception {
        bp.enableProduct(product);
        System.out.println("Se activó el producto: " + product.getName());
    }

    private void disableProduct() throws Exception {
        bp.disableProduct(product);
        System.out.println("Se desactivó el producto: " + product.getName());
    }

}
