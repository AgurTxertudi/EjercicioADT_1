/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author agurtxertudi
 */
public class DAO {
    private Connection conn;
    private  Properties connectionProps;
    
    public DAO(){
    
    connectionProps = new Properties();
    connectionProps.put("user", "root");
    connectionProps.put("password", "");
    }
    
    public void connectDAO()throws SQLException{
        
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb", connectionProps);
        
    }
    
      public void disconnectDAO()throws SQLException{
        
        conn.close();
        
    }
    
    Customer createCustomer(Customer customer) throws SQLException 
    {

    // the mysql customer insert statement
      String query = " insert into customer (id)" + " values (?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setLong(1, customer.getId());
      
      // execute the preparedstatement
      preparedStmt.execute();
      
      
   
    //throw new UnsupportedOperationException();
    return customer;
    
     }
    
    Customer findCustomer(Long id){
         throw new UnsupportedOperationException();
     }
    
     List<Account> findCustomerAccounts(Long id){
        throw new UnsupportedOperationException();
     }
     
     // En este método devolvería Account y CustomerAccount
     
    CustomerAccount  createAccount (Customer customer, Account account) throws SQLException
    {
        
        // the mysql customerAccount insert statement
        
// the mysql customerAccount insert statement
        
      String query1 = " insert into account (id)" + " values (?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query1);
      preparedStmt.setLong(1, account.getId());
 
      
      // execute the preparedstatement
      preparedStmt.execute();


    // the mysql customerAccount insert statement
        
      String query2 = " insert into customer_account (customers_id, accounts_id)" + " values (?, ?)";

      // create the mysql insert preparedstatement
      preparedStmt = conn.prepareStatement(query2);
      preparedStmt.setLong(1, customer.getId());
      preparedStmt.setLong(2, account.getId());
      
      // execute the preparedstatement
      preparedStmt.execute();
      
      CustomerAccount customerAccount = new CustomerAccount();
      customerAccount.setCustomers_id(customer.getId());
      customerAccount.setAccounts_id(account.getId());
      return customerAccount;
    }
    
    CustomerAccount addClientToAccount (Long customers_id, Long accounts_id){
        throw new UnsupportedOperationException();
    }
    
     Account findAccount(Long id){
         throw new UnsupportedOperationException();
     }
     
     Movement addMovement(Long id){
          throw new UnsupportedOperationException();
     }
     
     List<Movement> findAccountMovements(Long id){
        throw new UnsupportedOperationException();
     }

   
}
