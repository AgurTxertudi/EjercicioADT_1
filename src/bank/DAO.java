/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

import exception.MyException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author agurtxertudi
 */
public interface DAO {

    
    Movement addMovement(Movement movement) throws Exception;
   
    CustomerAccount createAccount(Customer customer, Account account) throws Exception;

    Customer createCustomer(Customer customer) throws Exception;
    
    Account findAccount(Long id) throws Exception;

    Collection<Movement> findAccountMovements(Long id) throws Exception;

    Customer findCustomer(Long id) throws Exception;

    List<Account> findCustomerAccounts(Long id);
    
}
