/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import business.BusinessProduct;
import com.mycompany.gp.domain.IndividualProduct;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.PRODUCT_SIZE;
import com.mycompany.gp.domain.PRODUCT_TYPE;
import com.mycompany.gp.domain.Product;
import com.mycompany.gp.domain.ProductOrder;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
public class EditProductAddedController implements Initializable {

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
    private VBox productListContainer;

    private float oldProductPrice = 0;

    private boolean isListVisible = false;
    
    EditOrderProductsController eopController;

    private Image arrowUp = new Image("/images/Arrow-inverted.png");
    private Image arrowDown = new Image("/images/Group 6.png");

    List<EditProductItemController> productItemNodes = new ArrayList<>();

    private Node node;
    @FXML
    private Text txtAmount1;
    @FXML
    private Text txtProductSummaryPrice1;
    
    
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

    public List<EditProductItemController> getProductItemNodes() {
        return productItemNodes;
    }

    public void setProductItemNodes(List<EditProductItemController> productItemNodes) {
        this.productItemNodes = productItemNodes;
    }

    

    @FXML
    private void deleteProductFromSummary(MouseEvent event) {
        Node productNode = imgTrashGeneral.getParent().getParent();
        imgTrashGeneral.setDisable(true);
        eopController.removeProductFromSummary(productNode, productOrder);
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

    public VBox getProductListContainer() {
        return productListContainer;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public EditOrderProductsController getEopController() {
        return eopController;
    }

    public void setEopController(EditOrderProductsController eopController) {
        this.eopController = eopController;
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

    public void addProductToListContainer(float price, String number) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditProductItem.fxml"));
        try {
            AnchorPane productItem = loader.load();
            EditProductItemController itemController = loader.getController();
            itemController.setMainController(mainController);
            itemController.setPaController(this);
            eopController.setEditProductItemController(itemController);
            eopController.setEditProductAddedController(this);
            itemController.setNode(productItem);
            itemController.setProductOrder(productOrder);
            itemController.setNumberOfProduct(number);
            itemController.setTxtIndividualPrice(String.valueOf(price));
            productItem.setUserData(itemController);

            if (productOrder.getProduct().getPRODUCT_SIZE() == PRODUCT_SIZE.STUDENT) {
                disableButtons(itemController);
                productOrder.setPRODUCT_SIZE(PRODUCT_SIZE.STUDENT);
            } else if (productOrder.getProduct().getPRODUCT_SIZE() == PRODUCT_SIZE.UNDEFINED) {
                disableButtons(itemController);
                productOrder.setPRODUCT_SIZE(PRODUCT_SIZE.UNDEFINED);
            } else if (productOrder.getProduct() instanceof IndividualProduct) {
                IndividualProduct individualProduct = (IndividualProduct) productOrder.getProduct();
                if (individualProduct.getType() == PRODUCT_TYPE.DRINK) {
                    itemController.getBtnM().setDisable(true);
                    productOrder.setPRODUCT_SIZE(PRODUCT_SIZE.SMALL);
                }
            }

            productListContainer.getChildren().add(productItem);
            productItemNodes.add(itemController);
        } catch (IOException ex) {
            Logger.getLogger(EditProductAddedController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
     public void addExistentProductToListContainer(float price, String number, String details, PRODUCT_SIZE size) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/EditProductItem.fxml"));
        try {
            AnchorPane productItem = loader.load();
            EditProductItemController itemController = loader.getController();
            itemController.setEopController(eopController);
            itemController.setPaController(this);
            eopController.setEditProductItemController(itemController);
            eopController.setEditProductAddedController(this);
            itemController.setNode(productItem);
            itemController.setProductOrder(productOrder);
            itemController.setNumberOfProduct(number);
            itemController.setTxtIndividualPrice(String.valueOf(price));
            itemController.setTxtDetailProduct(details);
            
            switch (size) {
                case SMALL:
                    itemController.setTxtSize("CH");
                    itemController.setSelectedButtonStyle("CH");
                    break;
                case MEDIUM:
                    itemController.setTxtSize("M");
                    itemController.setSelectedButtonStyle("M");
                    break;
                case LARGE:
                    itemController.setTxtSize("G");
                    itemController.setSelectedButtonStyle("G");
                    break;
                default:
                    break;
            }
            
            productItem.setUserData(itemController);

            if (productOrder.getProduct().getPRODUCT_SIZE() == PRODUCT_SIZE.STUDENT) {
                disableButtons(itemController);
                productOrder.setPRODUCT_SIZE(PRODUCT_SIZE.STUDENT);
            } else if (productOrder.getProduct().getPRODUCT_SIZE() == PRODUCT_SIZE.UNDEFINED) {
                disableButtons(itemController);
                productOrder.setPRODUCT_SIZE(PRODUCT_SIZE.UNDEFINED);
            } else if (productOrder.getProduct() instanceof IndividualProduct) {
                IndividualProduct individualProduct = (IndividualProduct) productOrder.getProduct();
                if (individualProduct.getType() == PRODUCT_TYPE.DRINK) {
                    itemController.getBtnM().setDisable(true);
                    productOrder.setPRODUCT_SIZE(PRODUCT_SIZE.SMALL);
                }
            }

            productListContainer.getChildren().add(productItem);
            productItemNodes.add(itemController);
        } catch (IOException ex) {
            Logger.getLogger(EditProductAddedController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    public void disableButtons(EditProductItemController itemController) {
        itemController.getBtnCH().setDisable(true);
        itemController.getBtnM().setDisable(true);
        itemController.getBtnG().setDisable(true);
    }

    public void deleteProductFromListContainer(Node productNode) {
        // Remueve el nodo del contenedor visual
        productListContainer.getChildren().remove(productNode);
        // Busca y remueve el controlador correspondiente del nodo en la lista de controladores
        EditProductItemController itemControllerToRemove = null;
        for (EditProductItemController itemController : productItemNodes) {
            if (itemController.getNode() == productNode) {
                itemControllerToRemove = itemController;
                break;
            }
        }

        if (itemControllerToRemove != null) {
            productItemNodes.remove(itemControllerToRemove);
        }

        // Actualiza la cantidad total de productos y realiza otras operaciones necesarias
        int newAmount = productListContainer.getChildren().size();
        eopController.removeProductFromProductList(productOrder, ProductSummaryContainer, newAmount);
        updateProductNumbers();
    }

    public void checkIfEmptyAndRemove() {
        if (productListContainer.getChildren().isEmpty()) {
            eopController.removeProductFromSummary(ProductSummaryContainer, productOrder);
        }

    }

    private void updateProductNumbers() {
        int count = 1;
        for (Node node : productListContainer.getChildren()) {
            EditProductItemController itemController = (EditProductItemController) node.getUserData();
            itemController.updateItemNumber("#" + count);
            count++;
        }
    }

    public ProductOrder getProductDetails(EditProductItemController itemController) {
        ProductOrder originalPo = itemController.getProductOrder();

        ProductOrder newPo = new ProductOrder();
        newPo.setProduct(originalPo.getProduct());
        newPo.setPrice(originalPo.getPrice());
        newPo.setOrder(originalPo.getOrder());
        newPo.setDetails(itemController.getTxtDetailProduct());
        newPo.setPRODUCT_SIZE(originalPo.getProduct().getPRODUCT_SIZE());
        itemController.setProductOrder(newPo);

        eopController.removeItemFromPoList(originalPo);
        itemController.setProductOrder(newPo);
        eopController.addItemToPoList(newPo);

        return newPo;
    }

    public ProductOrder updateProductOrder(EditProductItemController itemController, String size) {
        BusinessProduct bp = new BusinessProduct();
        ProductOrder originalPo = itemController.getProductOrder();

        ProductOrder newPo = new ProductOrder();
        newPo.setProduct(originalPo.getProduct());
        newPo.setOrder(originalPo.getOrder());

        Product product = bp.findProductBySize(originalPo.getProduct().getName(), size);

        newPo.setProduct(product);
        newPo.setPrice(product.getPrice());
        newPo.setPRODUCT_SIZE(product.getPRODUCT_SIZE());

        eopController.removeItemFromPoList(originalPo);
        itemController.setProductOrder(newPo);
        eopController.addItemToPoList(newPo);

        return newPo;
    }

    public void updateTotalPrice() {
        float total = 0;
        for (Node node : productListContainer.getChildren()) {
            EditProductItemController itemController = (EditProductItemController) node.getUserData();
            total += Float.parseFloat(itemController.getTxtIndividualPrice());
        }
        txtProductSummaryPrice.setText(String.valueOf(total));
        eopController.updateTotalPrice();

    }

}
