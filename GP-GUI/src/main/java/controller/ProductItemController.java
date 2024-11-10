/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import com.mycompany.gp.domain.ProductOrder;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Raul
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

    private MainPageController mainController;

    private ProductOrder productOrder;

    private ProductAddedController paController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public Text getNumberOfProduct() {
        return numberOfProduct;
    }

    public void setNumberOfProduct(String numberOfProduct) {
        this.numberOfProduct.setText(numberOfProduct);
    }

    public Text getTxtSize() {
        return txtSize;
    }

    public void setTxtSize(String txtSize) {
        this.txtSize.setText(txtSize);
    }

    public String getTxtIndividualPrice() {
        return txtIndividualPrice.getText();
    }

    public void setTxtIndividualPrice(String txtIndividualPrice) {
        this.txtIndividualPrice.setText(txtIndividualPrice);
    }

    public String getTxtDetailProduct() {
        return txtDetailProduct.getText();
    }

    public void setTxtDetailProduct(String txtDetailProduct) {
        this.txtDetailProduct.setText(txtDetailProduct);
    }

    public MainPageController getMainController() {
        return mainController;
    }

    public void setMainController(MainPageController mainController) {
        this.mainController = mainController;
    }

    public void setProductOrder(ProductOrder productOrder) {
        this.productOrder = productOrder;
    }

    public ProductOrder getProductOrder() {
        return productOrder;
    }

    public void setPaController(ProductAddedController paController) {
        this.paController = paController;
    }

    public Button getBtnCH() {
        return btnCH;
    }

    public Button getBtnM() {
        return btnM;
    }

    public Button getBtnG() {
        return btnG;
    }

    @FXML
    private void chSizeClicked(MouseEvent event) {
        Node productNode = btnCH.getParent();
        setSelectedButtonStyle(btnCH);
        ProductOrder productOrder = paController.updateProductOrder(this, "CH");
        String newPrice = String.valueOf(productOrder.getPrice());
        setSelectedSize("CH", newPrice);
        paController.updateTotalPrice();
    }

    @FXML
    private void mSizeClicked(MouseEvent event) {
        Node productNode = btnM.getParent();
        setSelectedButtonStyle(btnM);
        ProductOrder productOrder = paController.updateProductOrder(this, "M");
        String price = String.valueOf(productOrder.getPrice());
        setSelectedSize("M", price);
        paController.updateTotalPrice();
    }

    @FXML
    private void gSizeClicked(MouseEvent event) {
        Node productNode = btnG.getParent();
        setSelectedButtonStyle(btnG);
        ProductOrder productOrder = paController.updateProductOrder(this, "G");
        String price = String.valueOf(productOrder.getPrice());
        setSelectedSize("G", price);
        paController.updateTotalPrice();
    }

    @FXML
    private void deleteIndividualProduct(MouseEvent event) {
        Node productNode = imgTrashIndividual.getParent();
        paController.deleteProductFromListContainer(productNode);
        paController.checkIfEmptyAndRemove();
    }

    public void updateItemNumber(String newNumber) {
        numberOfProduct.setText(newNumber);
    }

    private void setSelectedButtonStyle(Button selectedButton) {

        List<Button> buttons = List.of(btnCH, btnM, btnG);

        for (Button button : buttons) {
            if (button.equals(selectedButton)) {

                button.setStyle("-fx-background-color: black; -fx-text-fill: white;");
            } else {

                button.setStyle("-fx-background-color:  #D9D9D9; -fx-text-fill: black;");
            }
        }
    }

    public ProductOrder getProductDetails(ProductItemController piController) {

        return paController.getProductDetails(piController);
    }

    public void setSelectedSize(String size, String price) {

        switch (size) {
            case "CH":
                this.txtSize.setText(size);
                this.txtIndividualPrice.setText(price);
                break;
            case "M":
                this.txtSize.setText(size);
                this.txtIndividualPrice.setText(price);
                break;
            case "G":
                this.txtSize.setText(size);
                this.txtIndividualPrice.setText(price);
                break;
            default:
                break;
        }
    }

}
