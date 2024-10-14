/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.gp.gui;

import business.BusinessProduct;
import business.OrderBusiness;
import business.UserBusiness;
import com.mycompany.gp.domain.DinerOrder;
import com.mycompany.gp.domain.Employee;
import com.mycompany.gp.domain.ORDER_STATE;
import com.mycompany.gp.domain.Order;
import com.mycompany.gp.domain.PRODUCT_TYPE;
import com.mycompany.gp.domain.PickUpOrder;
import com.mycompany.gp.domain.Product;
import com.mycompany.gp.domain.ProductOrder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Raul
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        chargeUsers();
        chargeProducts();
        deployMenu();
    }

    public static void deployMenu() {
        Scanner tec = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("---------- Green Pepper ----------");
            System.out.println("Menú Principal");
            System.out.println("1. Agregar Pedido");
            System.out.println("2. Ver Pedidos Activos");
            System.out.println("3. Ver Historial de Pedidos");
            System.out.println("4. Salir");
            System.out.println("---------------------------");

            System.out.print("Elija una opción: ");
            option = tec.nextInt();

            switch (option) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    viewActiveOrders();
                    break;
                case 3:
                    viewOrderHistory();
                    break;
                case 4:
                    System.out.println("Saliendo... ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción Invalida. Intente nuevamente: ");
            }
        } while (option != 4);

        tec.close();
    }

    public static void addOrder() {
        Scanner tec = new Scanner(System.in);
        int option = 0;

        do {
            System.out.println("---------------------------");
            System.out.println("Agregar Orden");
            System.out.println("1. A Domicilio");
            System.out.println("2. En Comedor");
            System.out.println("3. Para Recoger");
            System.out.println("4. Volver al menú principal");
            System.out.println("---------------------------");

            System.out.print("Elija una opción: ");
            option = tec.nextInt();

            switch (option) {
                case 1:
                    showProducts();
                    addDeliveryOrder();
                    break;
                case 2:
                    addDinerOrder();
                    break;
                case 3:
                    showProducts();
                    addPickUpOrder();
                    break;
                case 4:
                    deployMenu();
                    break;
                default:
                    System.out.println("Opción Invalida. Intente nuevamente: ");
            }
        } while (option != 4);

        tec.close();
    }

    public static void editOrder() {
        System.out.println("Edit Order functionality goes here.");
    }

    public static void cancelOrder() {
        System.out.println("Cancel Order functionality goes here.");
    }

    public static void viewActiveOrders() {
        System.out.println("Viewing active orders...");
    }

    public static void viewOrderHistory() {
        System.out.println("Viewing orders history...");
    }

    public static void chargeUsers() {
        UserBusiness bu = new UserBusiness();
        bu.chargeUsers();
    }

    public static void chargeProducts() {
        BusinessProduct bp = new BusinessProduct();
        bp.chargerProducts();
    }

    private static void addDeliveryOrder() {
    }

    private static void addDinerOrder() {
        Scanner tec = new Scanner(System.in);
        int option;
        String orderName;
        char addOption;
        OrderBusiness ob = new OrderBusiness();
        List<Product> prods = ob.getAllProducts();
        List<ProductOrder> SelectedProducts = new ArrayList<>();
        Product selProd;
        DinerOrder dOr = new DinerOrder();
        UserBusiness us = new UserBusiness();
        
        System.out.println("Ingresa el número de mesa:");
        orderName = tec.nextLine();
        
        
        do{
            showProducts();
            
            System.out.println("Seleccione el número del producto que desea agregar:");
            option = tec.nextInt();
            
            if(option > 0 && option <= prods.size()){
                
                for(int i =0; i < prods.size(); i++){
                    if(i == (option - 1)){
                        selProd = prods.get(option-1);
                        
                        ProductOrder pOrder = addProductDetails(selProd, dOr);
                        
                        SelectedProducts.add(pOrder);
                        
                        if(pOrder.getAmount() == 1){
                            System.out.println("Se agregó correctamente el producto: " + selProd.getName());
                        } else if (pOrder.getAmount() > 1){
                            System.out.println("Se agregó "+pOrder.getAmount()+ " veces el producto: "+selProd.getName());
                        }
                    }
                }       
            }else if(option < 0 && option >= prods.size()){
                        System.out.println("Opción inválida. Por favor, seleccione un número de producto válido.");
                    }
            
            System.out.println("¿Desea agregar otro producto? (y/n)");
            addOption = tec.next().toLowerCase().charAt(0);
                     
        } while (addOption == 'y');
        
        dOr.setProducts(SelectedProducts);
        tec.nextLine();
        
        System.out.println("Ingrese detalles generales de la orden (opcional): ");
        String details = tec.nextLine();
        dOr.setDetails(details);
        
        dOr.setCreationDate(LocalDateTime.now());
        
        dOr.setORDER_STATE(ORDER_STATE.ACTIVE);
        
        dOr.setCashier((Employee) us.findUser(2L));
        
        dOr.setOrderName(orderName);
        
        DinerOrder orderCreated = ob.createDinerOrder(dOr);
        
        System.out.println("Orden Creada!");
        
        generateDinerOrderNote(orderCreated);
        
    }

    private static void addPickUpOrder() {
        Scanner tec = new Scanner(System.in);
        OrderBusiness ob = new OrderBusiness();
        List<Product> products = ob.getAllProducts();
        List<Product> selectedProducts = new ArrayList<>();
        PickUpOrder order = new PickUpOrder();
        ProductOrder po = new ProductOrder();
        List<ProductOrder> poList = new ArrayList<>();
        UserBusiness ub = new UserBusiness();
        char addOption;

        do {
            
            for (int i = 0; i < products.size(); i++) {
                System.out.println((i + 1) + ". " + products.get(i).getName());
            }
            System.out.println("Seleccione el número del producto que desea agregar: ");
            int option = tec.nextInt();

            if (option > 0 && option <= products.size()) {
                Product selectedProduct = products.get(option - 1);
                po = addProductDetails(selectedProduct, order);
                poList.add(po);
                if (po.getAmount() == 1) {
                    selectedProducts.add(selectedProduct);
                    System.out.println("Se agregó correctamente el producto: " + selectedProduct.getName());
                } else if (po.getAmount() > 1) {
                    for (int i = 0; i < po.getAmount(); i++) {
                        selectedProducts.add(selectedProduct);
                    }
                    System.out.println("Se agregó " + po.getAmount() + " veces " + "el producto: " + selectedProduct.getName());

                }

            } else {
                System.out.println("Opción inválida. Por favor, seleccione un número de producto válido.");
            }

            System.out.println("¿Desea agregar otro producto? (y/n)");
            addOption = tec.next().toLowerCase().charAt(0);

        } while (addOption == 'y');
        
        order.setProducts(poList);
        tec.nextLine();
        System.out.println("Ingrese detalles generales de la orden (opcional): ");
        String details = tec.nextLine();
        order.setDetails(details);
        
        System.out.println("Ingrese el nombre del cliente: ");
        String name = tec.nextLine();
        order.setCustomerName(name);
        
        System.out.println("Ingrese el telefono del cliente: ");
        String phone = tec.nextLine();
        order.setCustomerPhone(phone);
        
        order.setCreationDate(LocalDateTime.now());
        
        order.setORDER_STATE(ORDER_STATE.ACTIVE);
        
        order.setCashier((Employee) ub.findUser(2L));
        
        PickUpOrder orderCreated = ob.createPickUpOrder(order);
        
        System.out.println("Orden creada!");
        
        generatePickUpOrderNote(orderCreated);
        
    }

    private static void showProducts() {
        int i = 0;
        OrderBusiness ob = new OrderBusiness();
        List<Product> products = ob.getAllProducts();
        System.out.println("---------------------------");
        System.out.println("Lista de productos:");
        for (Product product : products) {
            i++;
            System.out.println(i + "." + product.getName());
        }
        System.out.println("---------------------------");
    }

    private static ProductOrder addProductDetails(Product selectedProduct, Order order) {
        Scanner tec = new Scanner(System.in);
        ProductOrder po = new ProductOrder();
        int amount = 0;
        po.setOrder(order);
        po.setProduct(selectedProduct);
        po.setPrice(selectedProduct.getPrice());
        System.out.println("Producto seleccionado: " + selectedProduct.getName());
        System.out.println("Ingrese la cantidad del producto: ");
        amount = tec.nextInt();
        po.setAmount(amount);
        return po;
    }
    
    
    private static void generatePickUpOrderNote(PickUpOrder order) {
    int i = 0; 
    Scanner tec = new Scanner(System.in);
    List<ProductOrder> products = order.getProducts();
    System.out.println("----------------------");
    System.out.println("ORDEN PARA RECOGER");
    System.out.println("Creada por: " + order.getCashier().getName());
    System.out.println("Orden No. " + order.getOrderNumber());
    
    System.out.println(String.format("%-10s %-20s %-10s", "Cantidad", "Nombre", "Precio"));
    
    for (ProductOrder product : products) {
        double productTotalPrice = product.getAmount() * product.getPrice();         
        i++;
        
        System.out.println(String.format("%-10d %-20s $%-10.2f",  product.getAmount(), product.getProduct().getName(), productTotalPrice));
    }
    
    System.out.println("----------------------");
    System.out.println("Detalles: " + order.getDetails());
    System.out.println(String.format("Total: $%.2f", order.getPrice()));
    System.out.println("----------------------");
    System.out.println("Datos del cliente");
    System.out.println("Nombre: " + order.getCustomerName());
    System.out.println("Telefono: " + order.getCustomerPhone());
    System.out.println("Presiona ENTER para volver al menú principal");
    tec.nextLine();
    
    }
    
    private static void generateDinerOrderNote(DinerOrder order){
        int i = 0; 
        Scanner tec = new Scanner(System.in);
        List<ProductOrder> products = order.getProducts();
        System.out.println("----------------------");
        System.out.println("ORDEN PARA COMEDOR");
        System.out.println("Creada por: " + order.getCashier().getName());
        System.out.println("Orden No. " + order.getOrderNumber());
        System.out.println("Nombre: " + order.getOrderName());

        System.out.println(String.format("%-10s %-20s %-10s", "Cantidad", "Nombre", "Precio"));

        for (ProductOrder product : products) {
            double productTotalPrice = product.getAmount() * product.getPrice();         
            i++;

            System.out.println(String.format("%-10d %-20s $%-10.2f",  product.getAmount(), product.getProduct().getName(), productTotalPrice));
        }

        System.out.println("----------------------");
        System.out.println("Detalles: " + order.getDetails());
        System.out.println(String.format("Total: $%.2f", order.getPrice()));
        System.out.println("----------------------");
        System.out.println("Presiona ENTER para volver al menú principal");
        tec.nextLine();
    }


    private static void addProduct() {
        Scanner tec = new Scanner(System.in);
        BusinessProduct bp = new BusinessProduct();
        String name;
        PRODUCT_TYPE type = null;
        int price, selectType;
        boolean state = true;

        System.out.println("Add new Product");
        System.out.println("-----------------");
        System.out.println("Product Type");
        System.out.println("-----------------");
        System.out.println("1.- FOOD");
        System.out.println("2.- DRINK");
        System.out.println("3.- EXTRA");
        System.out.println("Enter the number");
        selectType = tec.nextInt();

        switch (selectType) {
            case 1:
                type = type.FOOD;
                break;
            case 2:
                type = type.DRINK;
                break;
            case 3:
                type = type.EXTRA;
                break;
            default:
                break;
        }

        tec.nextLine();
        System.out.println("-----------------");
        System.out.println("Name: ");
        name = tec.nextLine();

        System.out.println("Price:");
        price = tec.nextInt();

        bp.createProduct(type, name, price, state);
    }
    
       
}
