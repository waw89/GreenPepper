/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import model.ProductAddedModel;
import model.ProductCardModel;

/**
 *
 * @author waw
 */
public class ProductAddedController implements Initializable {

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.productAddedModel = new ProductAddedModel();

    }

    @FXML
    private void toggleProductList(MouseEvent event) {
    }

    @FXML
    private void deleteProductFromSummary(MouseEvent event) {
    }

    public Text getTxtSummaryProductName() {
        return txtSummaryProductName;
    }

    public Text getTxtProductSummaryPrice() {
        return txtProductSummaryPrice;
    }

    public Text getTxtAmount() {
        return txtAmount;
    }

    public void setTxtSummaryProductName(Text txtSummaryProductName) {
        this.txtSummaryProductName = txtSummaryProductName;
    }

    public void setTxtProductSummaryPrice(Text txtProductSummaryPrice) {
        this.txtProductSummaryPrice = txtProductSummaryPrice;
    }

    public void setTxtAmount(Text txtAmount) {
        this.txtAmount = txtAmount;
    }

    public ProductAddedModel getProductAddedModel() {
        return productAddedModel;
    }

    public AnchorPane getAnchorPaneElement() {
        return anchorPaneElement;
    }

    public void setAnchorPaneElement(AnchorPane anchorPaneElement) {
        this.anchorPaneElement = anchorPaneElement;
    }

    public ProductAddedController addProductItemToProductAdded(ProductAddedController productAddedController) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/fxml/ProductItem.fxml"));
        AnchorPane productItemAnchorPaneElement = loader.load();
        ProductItemController productItemController = loader.getController();
        productListContainer.getChildren().add(productItemAnchorPaneElement);

        return productAddedController;
    }

    public void customControllerWithProductCardData(ProductCardModel productCardModel) {
        this.txtAmount.setText(String.valueOf(productCardModel.getAmmount()));
        this.txtSummaryProductName.setText(productCardModel.getAmmount().get());
        this.txtProductSummaryPrice.setText(productCardModel.getAccumulatedPrice().get());
        this.productAddedModel.customModelWithSameData(productCardModel);
    }

    public void setupBinding() {
        this.txtSummaryProductName.textProperty().bindBidirectional(this.productAddedModel.getTxtSummaryProductName());
        this.txtAmount.textProperty().bindBidirectional(this.productAddedModel.getTxtAmount());
        this.txtProductSummaryPrice.textProperty().bindBidirectional(this.productAddedModel.getTxtProductSummaryPrice());
    }

}
