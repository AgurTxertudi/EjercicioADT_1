/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.sql.Timestamp;

/**
 *
 * @author agurtxertudi
 */
public class Application {
    
    public static void main(String[] args){
        
        //create Customer
        
        Customer customer1 = new Customer();
        customer1.id = 1111L;
        customer1.city = "Long Island";
        customer1.email = "john.wick@gmail.com";
        customer1.firstName = "John";
        customer1.lastName = "Wick";
        customer1.middleInitial = "Keanu";
        customer1.phone = 11111111L;
        customer1.state = "New York";
        customer1.street = "Long Island 1";
        customer1.zip = 11111;
        
        Customer customer2 = new Customer();
        customer2.id = 2222L;
        customer2.city = "Los Angeles";
        customer2.email = "black.mamba@gmail.com";
        customer2.firstName = "Beatrix";
        customer2.lastName = "Kiddo";
        customer2.middleInitial = "Uma";
        customer2.phone = 22222222L;
        customer2.state = "California";
        customer2.street = "Los Angeles 1";
        customer2.zip = 22222;
        
        Customer customer3 = new Customer();
        customer3.id = 3333L;
        customer3.city = "Tokio";
        customer3.email = "jun.kim.com";
        customer3.firstName = "Jun";
        customer3.lastName = "Kim";
        customer3.middleInitial = "Eok Seon";
        customer3.phone = 33333333L;
        customer3.state = "Tokio";
        customer3.street = "Tokio 1";
        customer3.zip = 33333;
        
        
        //create Account
        
        Account account1 = new Account();
        account1.balance = 45000.0;
        account1.beginBalance = 23000.0;
        Timestamp timeStamp1_account = new Timestamp(System.currentTimeMillis());
        account1.beginBalanceTimestamp = timeStamp1_account;
        account1.creditLine = 3000.0;
        account1.description = "Premium - Golden account";
        account1.id = 01L;
        account1.type = 1;
        
        Account account2 = new Account();
        account2.balance = 15000.0;
        account2.beginBalance = 20000.0;
        Timestamp timeStamp2_account = new Timestamp(System.currentTimeMillis());
        account2.beginBalanceTimestamp = timeStamp2_account;
        account2.creditLine = 2000.0;
        account2.description = "Super Premium - Diamond account";
        account2.id = 02L;
        account2.type = 1;
        
        Account account3 = new Account();
        account3.balance = 5000.0;
        account3.beginBalance = 1000.0;
        Timestamp timeStamp3_account = new Timestamp(System.currentTimeMillis());
        account3.beginBalanceTimestamp = timeStamp3_account;
        account3.creditLine = 1000.0;
        account3.description = "Plain - Steel account";
        account3.id = 03L;
        account3.type = 2;
        
        //create Movement
        
        Movement movement1 = new Movement();
        movement1.account_id = 01L;
        movement1.amount = 1000.0;
        movement1.balance = 300.0;
        movement1.description = "Movement 1";
        Timestamp timeStamp1_movement = new Timestamp(System.currentTimeMillis());
        movement1.timeStamp = timeStamp1_movement;
        
        Movement movement2 = new Movement();
        movement1.account_id = 01L;
        movement1.amount = 1100.0;
        movement1.balance = 200.0;
        movement1.description = "Movement 2";
        Timestamp timeStamp2_movement = new Timestamp(System.currentTimeMillis());
        movement1.timeStamp = timeStamp2_movement;
        
        Movement movement3 = new Movement();
        movement1.account_id = 03L;
        movement1.amount = 700.0;
        movement1.balance = 100.0;
        movement1.description = "Movement 3";
        Timestamp timeStamp3_movement = new Timestamp(System.currentTimeMillis());
        movement1.timeStamp = timeStamp3_movement;
        
        //create CustomerAccount
        
        CustomerAccount customerAccount1 = new CustomerAccount();
        customerAccount1.accounts_id =01L;
        customerAccount1.customers_id = 1111L;
        
        CustomerAccount customerAccount2 = new CustomerAccount();
        customerAccount1.accounts_id =02L;
        customerAccount1.customers_id = 1111L;
        
        CustomerAccount customerAccount3 = new CustomerAccount();
        customerAccount1.accounts_id =03L;
        customerAccount1.customers_id = 3333L;
        
        //create DAO
        
        DAO dao = new DAO();
        
        //DUDAS: ¿y lo que devuelve?
        
        dao.createCustomer(customer1);
        dao.createCustomer(customer2);
        dao.createCustomer(customer3);
        dao.findCustomer(3333L);
        dao.createAccount(customer3);
        dao.findCustomerAccounts(3333L);
        
        
        
        
        
    }
    
}
