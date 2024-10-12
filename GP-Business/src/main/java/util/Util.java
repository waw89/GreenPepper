package util;

import com.mycompany.gp.domain.Admin;
import com.mycompany.gp.domain.Employee;
import com.mycompany.gp.domain.IndividualProduct;
import com.mycompany.gp.domain.PRODUCT_TYPE;
import com.mycompany.gp.domain.Product;
import com.mycompany.gp.domain.User;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Raul
 */
public class Util {

    public List<User> createUsers() {
        User user1 = new Admin("rully", "1234", "Raúl Luna");
        User user2 = new Employee("waw", "1234", "Luis Contreras");
        User user3 = new Employee("picosB", "1234", "Brayan Picos");

        ArrayList<User> newUsers = new ArrayList<>();

        if (newUsers.isEmpty()) {
            newUsers.add(user1);
            newUsers.add(user2);
            newUsers.add(user3);
        }
        return newUsers;
    }
    
    public List<IndividualProduct> createProducts(){
        IndividualProduct prod1 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Ensalada Boneless", 149, true, null);
        IndividualProduct prod2 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Ensalada Pollo", 149, true, null);
        IndividualProduct prod3 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Ensalada Atún", 149, true, null);
        IndividualProduct prod4 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Panini", 60, true, null);
        IndividualProduct prod5 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Boneless", 90, true, null);
        IndividualProduct prod6 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Tenders", 80, true, null);
        IndividualProduct prod7 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Limonada Grande", 35, true, null);
        IndividualProduct prod8 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Limonada Chica", 25, true, null);
        IndividualProduct prod9 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Agua de Pepino Grande", 35, true, null);
        
        ArrayList<IndividualProduct> newProdructs = new ArrayList<>();
        
        if(newProdructs.isEmpty()){
            newProdructs.add(prod1);
            newProdructs.add(prod2);
            newProdructs.add(prod3);
            newProdructs.add(prod4);
            newProdructs.add(prod5);
            newProdructs.add(prod6);
            newProdructs.add(prod7);
            newProdructs.add(prod8);
            newProdructs.add(prod9);            
            
        }
        
        return newProdructs;
    }
}
