package util;

import com.mycompany.gp.domain.Admin;
import com.mycompany.gp.domain.Employee;
import com.mycompany.gp.domain.IndividualProduct;
import com.mycompany.gp.domain.PRODUCT_SIZE;
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
        IndividualProduct prod1 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Ensalada Boneless", 119, true, null, PRODUCT_SIZE.SMALL);
        IndividualProduct prod2 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Ensalada Boneless", 149, true, null, PRODUCT_SIZE.MEDIUM);
        IndividualProduct prod3 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Ensalada Boneless", 199, true, null, PRODUCT_SIZE.LARGE);
        IndividualProduct prod4 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Ensalada de Pollo", 119, true, null, PRODUCT_SIZE.SMALL);
        IndividualProduct prod5 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Ensalada de Pollo", 149, true, null, PRODUCT_SIZE.MEDIUM);
        IndividualProduct prod6 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Ensalada de Pollo", 199, true, null, PRODUCT_SIZE.LARGE);
        IndividualProduct prod7 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Ensalada de Atún", 159, true, null, PRODUCT_SIZE.SMALL);
        IndividualProduct prod8 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Ensalada de Atún", 179, true, null, PRODUCT_SIZE.MEDIUM);
        IndividualProduct prod9 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Ensalada de Atún", 239, true, null, PRODUCT_SIZE.LARGE);
        
        IndividualProduct prod10 = new IndividualProduct(PRODUCT_TYPE.EXTRA, "Panini", 60, true, null, PRODUCT_SIZE.UNDEFINED);
        IndividualProduct prod11 = new IndividualProduct(PRODUCT_TYPE.EXTRA, "Boneless", 90, true, null, PRODUCT_SIZE.UNDEFINED);
        IndividualProduct prod12 = new IndividualProduct(PRODUCT_TYPE.EXTRA, "Tenders", 80, true, null, PRODUCT_SIZE.UNDEFINED);
        
        IndividualProduct prod13 = new IndividualProduct(PRODUCT_TYPE.DRINK, "Limonada", 15, true, null, PRODUCT_SIZE.SMALL);
        IndividualProduct prod14 = new IndividualProduct(PRODUCT_TYPE.DRINK, "Limonada", 25, true, null, PRODUCT_SIZE.LARGE);
        IndividualProduct prod15 = new IndividualProduct(PRODUCT_TYPE.DRINK, "Agua de Piña Apio", 20, true, null, PRODUCT_SIZE.SMALL);
        IndividualProduct prod16 = new IndividualProduct(PRODUCT_TYPE.DRINK, "Agua de Piña Apio", 30, true, null, PRODUCT_SIZE.LARGE);
        IndividualProduct prod17 = new IndividualProduct(PRODUCT_TYPE.DRINK, "Agua de Pepino Limón", 20, true, null, PRODUCT_SIZE.SMALL);
        IndividualProduct prod18 = new IndividualProduct(PRODUCT_TYPE.DRINK, "Agua de Pepino Limón", 30, true, null, PRODUCT_SIZE.LARGE);
        
        IndividualProduct prod19 = new IndividualProduct(PRODUCT_TYPE.FOOD, "Ensalada Estudiante", 75, true, null, PRODUCT_SIZE.STUDENT);
        
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
            newProdructs.add(prod10);
            newProdructs.add(prod11);
            newProdructs.add(prod12);
            newProdructs.add(prod13);
            newProdructs.add(prod14);
            newProdructs.add(prod15);
            newProdructs.add(prod16);
            newProdructs.add(prod17);
            newProdructs.add(prod18);
            newProdructs.add(prod19);
        }
        
        return newProdructs;
    }
}
