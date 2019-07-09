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
    
    Customer createCustomer(Customer customer) throws SQLException 
    {
    Connection conn = null;
    Properties connectionProps = new Properties();
    connectionProps.put("user", "root");
    connectionProps.put("password", "");
    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankdb", connectionProps);
   
    // the mysql insert statement
      String query = " insert into customer (id)" + " values (?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setLong(1, customer.getId());
      

      // execute the preparedstatement
      preparedStmt.execute();
      
      conn.close();
   
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
     
    CustomerAccount  createAccount (Customer customer){
        throw new UnsupportedOperationException();
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
