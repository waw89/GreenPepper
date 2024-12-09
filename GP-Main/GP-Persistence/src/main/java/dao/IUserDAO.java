/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import com.mycompany.gp.domain.User;
import java.util.List;

/**
 *
 * @author Raul
 */
public interface IUserDAO {
    public User create(User user);
    public List<User> fillUsersList(List<User> users);
    public User findUser(Long id);
}
