package util;

import com.mycompany.gp.domain.Employee;
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
        User user1 = new Employee("rully", "1234", "Raúl Luna");
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
}
