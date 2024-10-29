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
    private Button btnE;
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
        // TODO
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

    public Text getTxtIndividualPrice() {
        return txtIndividualPrice;
    }

    public void setTxtIndividualPrice(String txtIndividualPrice) {
        this.txtIndividualPrice.setText(txtIndividualPrice);
    }

    public TextArea getTxtDetailProduct() {
        return txtDetailProduct;
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

    public void setPaController(ProductAddedController paController) {
        this.paController = paController;
    }

    @FXML
    private void chSizeClicked(MouseEvent event) {
        setSelectedButtonStyle(btnCH);
    }

    @FXML
    private void mSizeClicked(MouseEvent event) {
        setSelectedButtonStyle(btnM);
    }

    @FXML
    private void gSizeClicked(MouseEvent event) {
        setSelectedButtonStyle(btnG);
    }

    @FXML
    private void eSizeClicked(MouseEvent event) {
        setSelectedButtonStyle(btnE);
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

        List<Button> buttons = List.of(btnCH, btnM, btnG, btnE);

        for (Button button : buttons) {
            if (button.equals(selectedButton)) {

                button.setStyle("-fx-background-color: black; -fx-text-fill: white;");
            } else {

                button.setStyle("-fx-background-color:  #D9D9D9; -fx-text-fill: black;");
            }
        }
    }
}
