package business;


import com.mycompany.gp.domain.User;
import dao.IUserDAO;
import dao.UserDAO;
import java.util.ArrayList;
import java.util.List;
import util.Util;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Raul
 */
public class UserBusiness {
    IUserDAO udao = new UserDAO();
    Util util = new Util();
    ArrayList<User> users;
    
    
    public List<User> chargeUsers(){
        this.users = (ArrayList<User>) util.createUsers(); 
        return udao.fillUsersList(users);
    }
    
    public User findUser(Long id){
      return udao.findUser(id);      
    }
    
}
