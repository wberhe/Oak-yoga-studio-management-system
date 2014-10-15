
import com.oak_yoga_studio.dao.UserDAO;
import com.oak_yoga_studio.dao.impl.UserDAOImpl;
import com.oak_yoga_studio.domain.Customer;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Senai
 */
public class Test {
    public static void main(String[] args){
//        Customer s=new Customer();
//        s.setEmail("asd@asd.edd");
//        s.setFirstName("Senai");
//        s.setLastName("Fitwi");
//        s.setDateOfBirth(new Date());
        UserDAO u=new UserDAOImpl();
//        u.addUser(s);
        if(u.getUser(1) instanceof Customer) 
            System.out.println("It is customer");
    }
            
}
