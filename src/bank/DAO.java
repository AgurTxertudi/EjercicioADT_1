/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

/**
 *
 * @author agurtxertudi
 */
public class DAO {
    private Connection conn;
    private  Properties connectionProps;
    
    public DAO(){
    
    connectionProps = new Properties();
    connectionProps.put("user", "rootc");
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
      String query = " insert into customer (id, city, email, firstName, lastName, middleInitial, phone, state, street, zip )" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setLong(1, customer.getId());
      preparedStmt.setString(2, customer.getCity());
      preparedStmt.setString(3, customer.getEmail());
      preparedStmt.setString(4, customer.getFirstName());
      preparedStmt.setString(5, customer.getLastName());
      preparedStmt.setString(6, customer.getMiddleInitial());
      preparedStmt.setLong(7, customer.getPhone());
      preparedStmt.setString(8, customer.getStreet());
      preparedStmt.setString(9, customer.getCity());
      preparedStmt.setLong(10, customer.getZip());
      
      // execute the preparedstatement
      preparedStmt.execute();
   
    //throw new UnsupportedOperationException();
    return customer;
    
     }
    
    
    
        Customer findCustomer(Long id)throws SQLException{

        Customer customer = null;
        PreparedStatement preparedStmt = null;
        ResultSet resulSet =null;
        //obtenemos conexión
        //this.conectar();
        try{
            //preparamos sentencia
            String query1 = "SELECT * FROM customer "+
                                                     "WHERE customer.id ='"+id+"' ";
             preparedStmt = conn.prepareStatement(query1);
               
            //ejecutamos la sentencia
            resulSet = preparedStmt.executeQuery();
            //procesamos el resultado
            
            while(resulSet.next()){
                customer=new Customer();
                customer.setId(new Long(resulSet.getLong("id")));
                customer.setCity(new String(resulSet.getString("city")));
                customer.setEmail(new String(resulSet.getString("email")));
                customer.setFirstName(new String(resulSet.getString("firstName")));
                customer.setLastName(new String(resulSet.getString("lastName")));
                customer.setMiddleInitial(new String(resulSet.getString("middleInitial")));
                customer.setPhone(new Long(resulSet.getLong("phone")));
                customer.setState(new String(resulSet.getString("state")));
                customer.setStreet(new String(resulSet.getString("street")));
                customer.setZip(new Integer(resulSet.getInt("zip")));
      
                //Mostrar datos 
                System.out.println("Customer");
                System.out.println("--------");
                
                System.out.println("id: " + resulSet.getLong("id"));
                System.out.println("city: " + resulSet.getString("city"));
                System.out.println("email: " + resulSet.getString("email"));
                System.out.println("first name: " + resulSet.getString("firstName"));
                System.out.println("last name: " + resulSet.getString("lastName"));
                System.out.println("middle initial: " + resulSet.getString("middleInitial"));
                System.out.println("phone: " + resulSet.getLong("phone"));
                System.out.println("state: " + resulSet.getString("state"));
                System.out.println("street: " + resulSet.getString("street"));
                System.out.println("zip " + resulSet.getInt("zip"));
             
                
       
            }
        }catch(SQLException e){
            throw new SQLException("Error en SELECT de account por id:"
                    +e.getMessage());
        }finally{
            try{        
        
                //cerramos todos los objetos relacionados con la operación y devolvemos
                //la conexión al pool
                resulSet.close();
                preparedStmt.close();
            }catch(SQLException e){
                throw new SQLException("Error en SELECT de account por id:"
                        +e.getMessage());
            }
        }
       
        //devolvemos el cliente
        return customer;
        
    }

    
    
     List<Account> findCustomerAccounts(Long id){
        throw new UnsupportedOperationException();
     }
     
     // Hecho con Javier y funcionando el 3 de Agosto -- En este método devolvería Account y CustomerAccount
     
    CustomerAccount  createAccount (Customer customer, Account account) throws SQLException
    {
        
        // the mysql Account insert statement
        
      String query1 = " insert into account (id, balance, beginBalance, beginBalanceTimeStamp, creditLine, description, type)" + " values (?, ?, ?, ?, ?, ?, ?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query1);
      preparedStmt.setLong(1, account.getId());
      preparedStmt.setDouble(2, account.getBalance());
      preparedStmt.setDouble(3, account.getBeginBalance());
      preparedStmt.setTimestamp(4, account.getBeginBalanceTimestamp());
      preparedStmt.setDouble(5, account.getCreditLine());
      preparedStmt.setString(6, account.getDescription());
      preparedStmt.setInt(7, account.getType());
 
      
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
     
     /*
     
     Account  createAccount (Account account) throws SQLException
    {
        
        // the mysql customerAccount insert statement
        
      String query = " insert into account (id, balance, beginBalance, beginBalanceTimeStamp, creditLine, description, type)" + " values (?, ?, ?, ?, ?, ?, ?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setLong(1, account.getId());
      preparedStmt.setDouble(2, account.getBalance());
      preparedStmt.setDouble(3, account.getBeginBalance());
      preparedStmt.setTimestamp(4, account.getBeginBalanceTimestamp());
      preparedStmt.setDouble(5, account.getCreditLine());
      preparedStmt.setString(6, account.getDescription());
       preparedStmt.setInt(7, account.getType());
 
      
      // execute the preparedstatement
      preparedStmt.execute();
     
      return account;
    }
    
    /*CustomerAccount addClientToAccount (Customer customer, Account account) throws SQLException {
        
      String query = " insert into customer_account (customers_id, accounts_id)" + " values (?, ?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setLong(1, account.getId());
      preparedStmt.setLong(2, customer.getId());
 
      
      // execute the preparedstatement
      preparedStmt.execute();
      
      CustomerAccount customerAccount = new CustomerAccount();
      customerAccount.setCustomers_id(customer.getId());
      customerAccount.setAccounts_id(account.getId());
      return customerAccount;
    }*/
    
    
    Account findAccount(Long id)throws SQLException{

        Account account = null;
        PreparedStatement preparedStmt = null;
        ResultSet resulSet =null;
        //obtenemos conexión
        //this.conectar();
        try{
            //preparamos sentencia
            String query1 = "SELECT * FROM account "+
                                                     "WHERE account.id ='"+id+"' ";
             preparedStmt = conn.prepareStatement(query1);
               
            //ejecutamos la sentencia
            resulSet = preparedStmt.executeQuery();
            //procesamos el resultado
            
            while(resulSet.next()){
                account=new Account();
                account.setId(new Long(resulSet.getLong("id")));
                account.setBalance(new Double(resulSet.getDouble("balance")));
                account.setBeginBalance(new Double(resulSet.getDouble("beginBalance")));
               
                //TimeStamp no sale
                //account.setBeginBalanceTimestamp(new Timestamp(resulSet.getTimestamp("beginBalanceTimeStamp")));
                
                account.setCreditLine(new Double(resulSet.getDouble("creditLine")));
                account.setDescription(new String(resulSet.getString("description")));
                account.setType(new Integer(resulSet.getInt("type")));
                //Mostrar datos 
                System.out.println("Account");
                System.out.println("-------");
                System.out.println("id: " + resulSet.getLong("id"));
                System.out.println("balance: " + resulSet.getDouble("balance"));
                System.out.println("begin balance: " + resulSet.getDouble("beginBalance"));
                System.out.println("begin balance timeStamp: " + resulSet.getTimestamp("beginBalanceTimeStamp"));
                System.out.println("credit line " + resulSet.getDouble("creditLine"));
                System.out.println("description " + resulSet.getString("description"));
                System.out.println("type " + resulSet.getInt("type"));
       
            }
        }catch(SQLException e){
            throw new SQLException("Error en SELECT de account por id:"
                    +e.getMessage());
        }finally{
            try{        
        
                //cerramos todos los objetos relacionados con la operación y devolvemos
                //la conexión al pool
                resulSet.close();
                preparedStmt.close();
            }catch(SQLException e){
                throw new SQLException("Error en SELECT de account por id:"
                        +e.getMessage());
            }
        }
       
        //devolvemos la cuenta
        return account;
        
    }

   
    
    
    /* Account findAccount(Long id){
         throw new UnsupportedOperationException();
     } */
     
    
    // Lo he cambiado para pasarle el Movement entero en lugar de sólo el id. Como en CreateCustomer 
     Movement addMovement(Movement movement)throws SQLException 
    {

    // the mysql customer insert statement
      String query = " insert movement (id, amount, balance, description, timeStamp, account_id )" + " values (?, ?, ?, ?, ?, ?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = conn.prepareStatement(query);
      preparedStmt.setLong(1, movement.getId());
      preparedStmt.setDouble(2, movement.getAmount());
      preparedStmt.setDouble(3, movement.getBalance());
      preparedStmt.setString(4, movement.getDescription());
      preparedStmt.setTimestamp(5, movement.getTimeStamp());
      preparedStmt.setLong(6, movement.getAccount_id());

      
      // execute the preparedstatement
      preparedStmt.execute();
   
    //throw new UnsupportedOperationException();
    return movement;
    
     }
     
     /*List<Movement> findAccountMovements(Long id){
        throw new UnsupportedOperationException();
     }*/

     
         /**
     * Busca un medicos por su número de colegiado. 
     * @param numColegiado El número de colegiado por el que buscar.
     * @return Colección de médicos con ese número de colegiado.
     * @throws DAOException Si ocurre cualquier error de acceso a BD.
     */
     Collection<Movement> findAccountMovements(Long id) throws SQLException{
        //Objeto en el que retornaremos los movimientoss
        Vector movementVector = new Vector();
        Movement movement = null;
        PreparedStatement preparedStmt = null;
        ResultSet resulSet = null;
        
        
        try{
            //preparamos sentencia
            String query1 = "SELECT * FROM account, movement "+
                                                     "WHERE account.id ='"+id+"' "+
                                                     "AND movement.account_id=account.id ";
             preparedStmt = conn.prepareStatement(query1);
               
            //ejecutamos la sentencia
            resulSet = preparedStmt.executeQuery();
            //procesamos el resultado
       
     
            //procesamos el resultado
            while(resulSet.next()){
                movement =new Movement();
                movement.setId(new Long(resulSet.getLong("movement.id")));
                movement.setAmount(new Double(resulSet.getDouble("movement.amount")));
                movement.setBalance(new Double(resulSet.getDouble("movement.balance")));
                movement.setDescription(new String(resulSet.getString("movement.description")));
               //no sale
                // movement.setTimeStamp(new Timestamp(resulSet.getTimestamp("movement.timeStamp")));
                movement.setAccount_id(new Long(resulSet.getLong("movement.account_id")));
            }
                System.out.println("Movement");
                System.out.println("--------");
                System.out.println("id: " + resulSet.getLong("movement.id"));
                System.out.println("amount: " + resulSet.getDouble("movement.amount"));
                System.out.println("balance: " + resulSet.getDouble("movement.balance"));
                System.out.println("description " + resulSet.getString("movement.description"));
                System.out.println("timeStamp timeStamp: " + resulSet.getTimestamp("movement.timeStamp"));
                System.out.println("account id: " + resulSet.getLong("movement.account_id"));
               
                
      
        }finally{
            try{        
                //cerramos todos los objetos relacionados con la operación y devolvemos
                //la conexión al pool
                resulSet.close();
                preparedStmt.close();
            }catch(SQLException e){
                throw new SQLException("Error en SELECT de account por id:"
                        +e.getMessage());
            }
        }
        //devolvemos el medico
        return movementVector;
        
    }
     
     
     
   
}
