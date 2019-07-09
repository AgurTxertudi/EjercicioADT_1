/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author agurtxertudi
 */
public class Application {
    
    public static void main(String[] args) throws SQLException{
        
        //create Customer
        
        Customer customer1 = new Customer();
        customer1.setId((Long) 1111L);
        customer1.setCity("Long Island");
        customer1.setEmail("john.wick@gmail.com");
        customer1.setFirstName("John");
        customer1.setLastName("Wick");
        customer1.setMiddleInitial("Keanu");
        customer1.setPhone((Long) 11111111L);
        customer1.setState("New York");
        customer1.setStreet("Long Island 1");
        customer1.setZip((Integer) 11111);
        
        Customer customer2 = new Customer();
        customer2.setId((Long) 2222L);
        customer2.setCity("Los Angeles");
        customer2.setEmail("black.mamba@gmail.com");
        customer2.setFirstName("Beatrix");
        customer2.setLastName("Kiddo");
        customer2.setMiddleInitial("Uma");
        customer2.setPhone((Long) 22222222L);
        customer2.setState("California");
        customer2.setStreet("Los Angeles 1");
        customer2.setZip((Integer) 22222);
        
        Customer customer3 = new Customer();
        customer3.setId((Long) 3333L);
        customer3.setCity("Tokio");
        customer3.setEmail("jun.kim.com");
        customer3.setFirstName("Jun");
        customer3.setLastName("Kim");
        customer3.setMiddleInitial("Eok Seon");
        customer3.setPhone((Long) 33333333L);
        customer3.setState("Tokio");
        customer3.setStreet("Tokio 1");
        customer3.setZip((Integer) 33333);
        
        
        //create Account
        
        Account account1 = new Account();
        account1.setBalance((Double) 45000.0);
        account1.setBeginBalance((Double) 23000.0);
        Timestamp timeStamp1_account = new Timestamp(System.currentTimeMillis());
        account1.setBeginBalanceTimestamp(timeStamp1_account);
        account1.setCreditLine((Double) 3000.0);
        account1.setDescription("Premium - Golden account");
        account1.setId((Long) 01L);
        account1.setType((Integer) 1);
        
        Account account2 = new Account();
        account2.setBalance((Double) 15000.0);
        account2.setBeginBalance((Double) 20000.0);
        Timestamp timeStamp2_account = new Timestamp(System.currentTimeMillis());
        account2.setBeginBalanceTimestamp(timeStamp2_account);
        account2.setCreditLine((Double) 2000.0);
        account2.setDescription("Super Premium - Diamond account");
        account2.setId((Long) 02L);
        account2.setType((Integer) 1);
        
        Account account3 = new Account();
        account3.setBalance((Double) 5000.0);
        account3.setBeginBalance((Double) 1000.0);
        Timestamp timeStamp3_account = new Timestamp(System.currentTimeMillis());
        account3.setBeginBalanceTimestamp(timeStamp3_account);
        account3.setCreditLine((Double) 1000.0);
        account3.setDescription("Plain - Steel account");
        account3.setId((Long) 03L);
        account3.setType((Integer) 2);
        
        //create Movement
        
        Movement movement1 = new Movement();
        movement1.setAccount_id(01L);
        movement1.setAmount(1000.0);
        movement1.setBalance(300.0);
        movement1.setDescription("Movement 1");
        Timestamp timeStamp1_movement = new Timestamp(System.currentTimeMillis());
        movement1.setTimeStamp(timeStamp1_movement);
        
       
        Movement movement2 = new Movement();
        movement1.setAccount_id(01L);
        movement1.setAmount(1100.0);
        movement1.setBalance(200.0);
        movement1.setDescription("Movement 2");
        Timestamp timeStamp2_movement = new Timestamp(System.currentTimeMillis());
        movement1.setTimeStamp(timeStamp2_movement);
        
        Movement movement3 = new Movement();
        movement1.setAccount_id(03L);
        movement1.setAmount(700.0);
        movement1.setBalance(100.0);
        movement1.setDescription("Movement 3");
        Timestamp timeStamp3_movement = new Timestamp(System.currentTimeMillis());
        movement1.setTimeStamp(timeStamp3_movement); 
        
        
        //create CustomerAccount
        
        CustomerAccount customerAccount1 = new CustomerAccount();
        customerAccount1.setAccounts_id((Long) 01L);
        customerAccount1.setCustomers_id((Long) 1111L);
        
        CustomerAccount customerAccount2 = new CustomerAccount();
        customerAccount1.setAccounts_id((Long) 02L);
        customerAccount1.setCustomers_id((Long) 1111L);
        
        CustomerAccount customerAccount3 = new CustomerAccount();
        customerAccount1.setAccounts_id((Long) 03L);
        customerAccount1.setCustomers_id((Long) 3333L);
        
        //create DAO
        
        DAO dao = new DAO();
        
        //DUDAS: ¿y lo que devuelve?
        
        customer1 = dao.createCustomer(customer1);
        //dao.createCustomer(customer2);
        //dao.createCustomer(customer3);
        //dao.findCustomer(3333L);
        //dao.createAccount(customer3);
        //dao.findCustomerAccounts(3333L);
        
        
        
        
        
    }
    
}
