/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.BusinessProduct;
import com.mycompany.gp.domain.IndividualProduct;
import com.mycompany.gp.domain.PRODUCT_SIZE;
import com.mycompany.gp.domain.PRODUCT_TYPE;
import com.mycompany.gp.domain.Product;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Raul
 */
public class AddProductPageController implements Initializable {

    @FXML
    private ToggleButton btnExtra;
    @FXML
    private ToggleButton btnFood;
    @FXML
    private ToggleButton btnDrink;
    @FXML
    private TextField txtProductName;
    @FXML
    private ToggleButton btnCH;
    @FXML
    private ToggleButton btnM;
    @FXML
    private ToggleButton btnG;
    @FXML
    private TextField priceCH;
    @FXML
    private TextField priceM;
    @FXML
    private TextField priceG;
    @FXML
    private Button btnAddProduct;
    @FXML
    private VBox priceContainer;
    @FXML
    private TextField priceGeneral;
    @FXML
    private HBox containerCH;
    @FXML
    private HBox containerM;
    @FXML
    private HBox containerG;
    @FXML
    private ImageView btnBack;
    
    
    IndividualProduct newProduct = new IndividualProduct();
    List<IndividualProduct> productsPerPrice = new ArrayList<>();
    BusinessProduct productBusiness = new BusinessProduct();
    AdminMainPageController adminMainPage;
    

    public void setAdminMainPage(AdminMainPageController adminMainPage) {
        this.adminMainPage = adminMainPage;
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hideContainer(containerCH);
        hideContainer(containerM);
        hideContainer(containerG);
    }

    @FXML
    private void selectFood(MouseEvent event) {
        setSelectedTypeButtonStyle(btnFood);
        newProduct.setType(PRODUCT_TYPE.FOOD);

    }

    @FXML
    private void selectDrink(MouseEvent event) {
        setSelectedTypeButtonStyle(btnDrink);
        newProduct.setType(PRODUCT_TYPE.DRINK);
    }

    @FXML
    private void selectExtra(MouseEvent event) {
        setSelectedTypeButtonStyle(btnExtra);
        newProduct.setType(PRODUCT_TYPE.EXTRA);
    }

    @FXML
    private void btnCHSelected(MouseEvent event) {
        hideContainer(priceGeneral);
        if (btnCH.isSelected()) {
            setSelectedSizeButtonStyle(btnCH);
            showContainer(containerCH);

        } else if (!btnCH.isSelected()) {
            setUnselectedSizeButtonStyle(btnCH);
            hideContainer(containerCH);
            verifyPriceContainer();
        }
    }

    @FXML
    private void btnMSelected(MouseEvent event) {
        hideContainer(priceGeneral);
        if (btnM.isSelected()) {
            setSelectedSizeButtonStyle(btnM);
            showContainer(containerM);
        } else if (!btnM.isSelected()) {
            setUnselectedSizeButtonStyle(btnM);
            hideContainer(containerM);
            verifyPriceContainer();
        }
    }

    @FXML
    private void btnGSelected(MouseEvent event) {
        hideContainer(priceGeneral);
        if (btnG.isSelected()) {
            setSelectedSizeButtonStyle(btnG);
            showContainer(containerG);
        } else if (!btnG.isSelected()) {
            setUnselectedSizeButtonStyle(btnG);
            hideContainer(containerG);
            verifyPriceContainer();
        }
    }

    @FXML
    private void addProduct(MouseEvent event) {
        if (validateProductType()) {
            if (validateEmptyNameField()) {
                if (!validatePriceFields()) {
                    if (priceGeneral.isVisible()) {
                        createProduct();    
                        showConfirmation();
                        adminMainPage.loadPage("ProductsPage");
                        
                    } else {
                        createManyProducts();
                        showConfirmation();
                        adminMainPage.loadPage("ProductsPage");
                    }
                } else {
                    showErrorAlert("Debe especificar el precio de su producto.");
                }
            } else {
                showErrorAlert("Debe especificar un nombre para su producto.");
            }
        } else {
            showErrorAlert("Debe seleccionar un tipo de producto.");
        }

    }

    private IndividualProduct createProduct() {
        newProduct.setName(txtProductName.getText());
        newProduct.setState(true);
        newProduct.setPrice(Integer.parseInt(priceGeneral.getText()));
        newProduct.setActiveOffer(null);
        newProduct.setStateProduct(true);
        newProduct.setPRODUCT_SIZE(PRODUCT_SIZE.UNDEFINED);
        productBusiness.createSingleProduct(newProduct);
        return newProduct;
    }

    private List<IndividualProduct> createManyProducts() {
        if (containerCH.isVisible()) {
            IndividualProduct smallProduct = new IndividualProduct();
            smallProduct.setName(txtProductName.getText());
            smallProduct.setState(true);
            smallProduct.setPrice(Integer.parseInt(priceCH.getText()));
            smallProduct.setActiveOffer(null);
            smallProduct.setStateProduct(true);
            smallProduct.setPRODUCT_SIZE(PRODUCT_SIZE.SMALL);
            smallProduct.setType(newProduct.getType());
            productsPerPrice.add(smallProduct);
        }
        if (containerM.isVisible()) {
            IndividualProduct mediumProduct = new IndividualProduct();
            mediumProduct.setName(txtProductName.getText());
            mediumProduct.setState(true);
            mediumProduct.setPrice(Integer.parseInt(priceM.getText()));
            mediumProduct.setActiveOffer(null);
            mediumProduct.setStateProduct(true);
            mediumProduct.setPRODUCT_SIZE(PRODUCT_SIZE.MEDIUM);
            mediumProduct.setType(newProduct.getType());
            productsPerPrice.add(mediumProduct);
        }
        if (containerG.isVisible()) {
            IndividualProduct largeProduct = new IndividualProduct();
            largeProduct.setName(txtProductName.getText());
            largeProduct.setState(true);
            largeProduct.setPrice(Integer.parseInt(priceG.getText()));
            largeProduct.setActiveOffer(null);
            largeProduct.setStateProduct(true);
            largeProduct.setPRODUCT_SIZE(PRODUCT_SIZE.LARGE);
            largeProduct.setType(newProduct.getType());
            productsPerPrice.add(largeProduct);
        }
        productBusiness.createManyProducts(productsPerPrice);
        return productsPerPrice;
    }

    private void setSelectedTypeButtonStyle(ToggleButton selectedButton) {

        List<ToggleButton> buttons = List.of(btnFood, btnDrink, btnExtra);

        for (ToggleButton button : buttons) {
            if (button.equals(selectedButton)) {
                button.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 50; -fx-border-radius: 50;");
            } else {
                button.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-background-radius: 50; -fx-border-radius: 50; -fx-border-color: black;");
            }
        }
    }

    private void setSelectedSizeButtonStyle(ToggleButton toggleButton) {
        toggleButton.setStyle("-fx-background-color: black; -fx-text-fill: white; -fx-background-radius: 10; -fx-border-radius: 10;");
    }

    private void setUnselectedSizeButtonStyle(ToggleButton toggleButton) {
        toggleButton.setStyle("-fx-background-color: transparent; -fx-text-fill: black; -fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: black;");
    }

    private void showContainer(Node container) {
        container.setVisible(true);
        container.setManaged(true);
    }

    private void hideContainer(Node container) {
        container.setVisible(false);
        container.setManaged(false);
    }

    private void verifyPriceContainer() {
        if (!containerCH.isVisible() && !containerM.isVisible() && !containerG.isVisible()) {
            showContainer(priceGeneral);
        }
    }

    private boolean validateProductType() {
        if (!btnFood.isSelected() && !btnDrink.isSelected() && !btnExtra.isSelected()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validateEmptyNameField() {
        if (txtProductName.getText().isBlank() || txtProductName.getText().isEmpty()) {
            txtProductName.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
            Tooltip tooltip = new Tooltip("Este campo es obligatorio.");
            txtProductName.setTooltip(tooltip);
            return false;
        } else {
            priceCH.setStyle("");
            txtProductName.setTooltip(null);
            return true;
        }

    }

    private boolean validatePriceFields() {
        if (priceGeneral.isVisible()) {
            if (priceGeneral.getText().isBlank() || priceGeneral.getText().isEmpty()) {
                priceGeneral.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                Tooltip tooltip = new Tooltip("Este campo es obligatorio.");
                priceGeneral.setTooltip(tooltip);
                return true;
            } else {
                priceGeneral.setStyle("");
                priceGeneral.setTooltip(null);
                return false;
            }
        } else {
            return validateIndividualPrices();
        }
    }

    private boolean validateIndividualPrices() {
        boolean hasEmptyFields = false;
        if (containerCH.isVisible()) {
            if (priceCH.getText().isBlank() || priceCH.getText().isEmpty()) {
                priceCH.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                Tooltip tooltip = new Tooltip("Este campo es obligatorio.");
                priceCH.setTooltip(tooltip);
                hasEmptyFields = true;
            } else {
                priceCH.setStyle("");
                priceCH.setTooltip(null);
            }
        } else if (containerM.isVisible()) {
            if (priceM.getText().isBlank() || priceM.getText().isEmpty()) {
                priceM.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                Tooltip tooltip = new Tooltip("Este campo es obligatorio.");
                priceM.setTooltip(tooltip);
                hasEmptyFields = true;
            } else {
                priceM.setStyle("");
                priceM.setTooltip(null);
            }
        } else if (containerG.isVisible()) {
            if (priceG.getText().isBlank() || priceG.getText().isEmpty()) {
                priceG.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
                Tooltip tooltip = new Tooltip("Este campo es obligatorio.");
                priceG.setTooltip(tooltip);
                hasEmptyFields = true;
            } else {
                priceG.setStyle("");
                priceG.setTooltip(null);
            }
        }
        return hasEmptyFields;
    }

    private void showConfirmation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Producto creada");
        alert.setContentText("Se ha creado el producto correctamente");
        alert.showAndWait();
    }
    private void showErrorAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error al crear producto");
        alert.setContentText("No se puede crear el producto. " + message);
        alert.showAndWait();
    }

    @FXML
    private void goBack(MouseEvent event) {
        adminMainPage.loadPage("ProductsPage");
    }

}
