/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import com.mycompany.gp.domain.DinerOrder;
import java.util.List;

/**
 *
 * @author Raul
 */
public interface IDinerOrderDAO {
    public DinerOrder create(DinerOrder dinerOrder);
    public List<DinerOrder> findDinerOrderEntities();
}
