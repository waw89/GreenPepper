/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.mycompany.gp.domain.ProductOrder;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class ProductAddedController implements Initializable {

    @FXML
    private Text txtSummaryProductName;
    private Text txtProductSummarySize;
    @FXML
    private Text txtProductSummaryPrice;
    private ImageView imgTrash;

    private MainPageController mainController;
    private ProductOrder productOrder;
    @FXML
    private AnchorPane ProductSummaryContainer;
    @FXML
    private ImageView toogleImg;
    @FXML
    private ImageView imgTrashGeneral;
    @FXML
    private Text txtAmount;
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
    private Button btnE;
    @FXML
    private ImageView imgTrashIndividual;
    @FXML
    private Text txtIndividualPrice;
    @FXML
    private TextArea txtDetailProduct;
    @FXML
    private VBox productListContainer;
    @FXML
    private AnchorPane productListItem;

    private boolean isListVisible = false;

    private Image arrowUp = new Image("/images/Arrow-inverted.png");
    private Image arrowDown = new Image("/images/Group 6.png");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productListContainer.setVisible(false);
        productListContainer.setManaged(false);
    }

    public ProductOrder getProductOrder() {
        return productOrder;
    }

    public void setProductOrder(ProductOrder productOrder) {
        this.productOrder = productOrder;
    }

    public void setMainController(MainPageController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void deleteProductFromSummary(MouseEvent event) {
        Node productNode = imgTrashGeneral.getParent().getParent();
        imgTrashGeneral.setDisable(true);
        mainController.removeProductFromSummary(productNode, productOrder);
    }

    public Text getTxtSummaryProductName() {
        return txtSummaryProductName;
    }

    public void setTxtSummaryProductName(String txtSummaryProductName) {
        this.txtSummaryProductName.setText(txtSummaryProductName);
    }

    public Text getTxtProductSummarySize() {
        return txtProductSummarySize;
    }

    public void setTxtProductSummarySize(String txtProductSummarySize) {
        this.txtProductSummarySize.setText(txtProductSummarySize);
    }

    public String getTxtProductSummaryPrice() {
        return txtProductSummaryPrice.getText();
    }

    public void setTxtProductSummaryPrice(String txtProductSummaryPrice) {
        this.txtProductSummaryPrice.setText(txtProductSummaryPrice);
    }

    public String getTxtAmount() {
        return txtAmount.getText();
    }

    public void setTxtAmount(String txtAmount) {
        this.txtAmount.setText(txtAmount);
    }

    public ImageView getToogleImg() {
        return toogleImg;
    }

    public void setToogleImg(ImageView toogleImg) {
        this.toogleImg = toogleImg;
    }

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
    private void eSizeClicked(MouseEvent event) {
    }

    @FXML
    private void toggleProductList(MouseEvent event) {
        isListVisible = !isListVisible;
        productListContainer.setVisible(isListVisible);
        productListContainer.setManaged(isListVisible);

        if (isListVisible) {
            this.toogleImg.setImage(arrowUp);
        } else {
            this.toogleImg.setImage(arrowDown);
        }
    }

    public void addProductToListContainer(String price, String number) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ProductItem.fxml"));
        try {
            AnchorPane productItem = loader.load();
            ProductItemController itemController = loader.getController();
            itemController.setMainController(mainController);
            itemController.setPaController(this);
            itemController.setProductOrder(productOrder);
            itemController.setNumberOfProduct(number);
            itemController.setTxtIndividualPrice(price);
            productItem.setUserData(itemController);
            productListContainer.getChildren().add(productItem);

        } catch (IOException ex) {
            Logger.getLogger(ProductAddedController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteProductFromListContainer(Node productNode) {
        productListContainer.getChildren().remove(productNode);
        mainController.removeProductFromProductList(productOrder);
        updateProductNumbers();
    }

    public void checkIfEmptyAndRemove() {
        if (productListContainer.getChildren().isEmpty()) {
            mainController.removeProductFromSummary(ProductSummaryContainer, productOrder);
        }

    }

    private void updateProductNumbers() {
        int count = 1;
        for (Node node : productListContainer.getChildren()) {
            ProductItemController itemController = (ProductItemController) node.getUserData();
            itemController.updateItemNumber("#" + count);
            count++;
        }
    }
}
