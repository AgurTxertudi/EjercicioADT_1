/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import java.util.List;

/**
 *
 * @author agurtxertudi
 */
public class DAO {
    
    Customer createCustomer(Customer customer){
        throw new UnsupportedOperationException();
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
