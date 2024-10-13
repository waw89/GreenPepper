/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.gp.gui;

import business.BusinessProduct;
import business.UserBusiness;
import com.mycompany.gp.domain.IndividualProduct;
import com.mycompany.gp.domain.PRODUCT_TYPE;
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
            System.out.println("2. Editar Pedido");
            System.out.println("3. Cancelar Pedido");
            System.out.println("4. Ver Pedidos Activos");
            System.out.println("5. Ver Historial de Pedidos");
            System.out.println("6. Salir");
            System.out.println("---------------------------");

            System.out.print("Elija una opción: ");
            option = tec.nextInt();

            switch (option) {
                case 1:
                    addOrder();
                    break;
                case 2:
                    editOrder();
                    break;
                case 3:
                    cancelOrder();
                    break;
                case 4:
                    viewActiveOrders();
                    break;
                case 5:
                    viewOrderHistory();
                    break;
                case 6:
                    System.out.println("Saliendo... ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción Invalida. Intente nuevamente: ");
            }
        } while (option != 6);

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
                    showProducts();
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
    }

    private static void addPickUpOrder() {
        BusinessProduct bp = new BusinessProduct();
        ArrayList<IndividualProduct> products = (ArrayList<IndividualProduct>) bp.findProducts();
        
        

    }

    private static void showProducts() {
        int i = 0;
        BusinessProduct bp = new BusinessProduct();
        ArrayList<IndividualProduct> products = (ArrayList<IndividualProduct>) bp.findProducts();

        System.out.println("---------------------------");
        System.out.println("Lista de productos:");
        for (IndividualProduct product : products) {
            i++;
            System.out.println(i + "." + product.getName());
        }
        System.out.println("---------------------------");
    }

    private static void addProduct() {
        Scanner tec = new Scanner(System.in);
        BusinessProduct bp = new BusinessProduct();
        String name;
        PRODUCT_TYPE type = null;
        int price, selectType;
        boolean state = false;

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
