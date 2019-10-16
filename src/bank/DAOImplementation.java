/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;


import exception.DAOException;
import exception.MyException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Logger;

/**
 *
 * @author agurtxertudi
 */
public class DAOImplementation implements DAO{
    private     Connection conn;
    private     Properties connectionProps;
    private     ConexionPoolBasico   poolBD;
    private     ResourceBundle  configFile;
    private     String          driverBD;
    private     String          urlBD;
    private     String          userBD;
    private     String          passwordBD;
    
    protected   Logger          logger;
    protected   Connection      conexionBD;
    
    /**
     * Inicializa las propiedades del DAO
     * @param poolBD El pool de conexiones a utilizar.
     * @param configFile El archivo de configuración donde están los datos necesarios
     * para las conexiones a la BD.
     */
    public DAOImplementation(ConexionPoolBasico poolBD,String configFile){

        this.poolBD=poolBD;
        this.configFile=ResourceBundle.getBundle(configFile);
        this.driverBD= this.configFile.getString("Driver");
        this.urlBD= this.configFile.getString("Conn");
        this.userBD= this.configFile.getString("DBUser");
        this.passwordBD= this.configFile.getString("DBPass");
        //obtenemos un logger
        this.logger=Logger.getLogger("");
        
    }
    
    
     /**
     * Obtiene conexión con BD.
     * @throws DAOException Si existe cualquier error al obtener la conexión.
     */
    protected void conectar() throws Exception{
        logger.info("Obteniendo conexión con la BD.");
        try{
            //Si hay pool lo usamos
           if(poolBD!=null){
               this.conexionBD=poolBD.extraerConexion();
           }
           //Si no creamos una nueva conexión mediante DriverManager
           else{
               Class.forName(this.driverBD);
               this.conexionBD = 
                    DriverManager.getConnection(urlBD,userBD,passwordBD);
           }
           //por defecto ponemos el autocommit de la conexión a true
           this.conexionBD.setAutoCommit(true);
        }catch(SQLException e){
            logger.severe("Error al crear Conexión con BD."+
                    "No se puede obtener conexión:"+e.getMessage());
            throw new DAOException("Error al crear Conexión con BD."+
                           "No se puede obtener conexión:"+e.getMessage());
            
        }catch(ClassNotFoundException e){
            logger.severe("Error al crear Conexión con BD:"+
                           "No se puede cargar la clase del Driver.");
            throw new DAOException("Error al crear Conexión con BD:"+
                           "No se puede cargar la clase del Driver.");
        }

    }
    /**
     * Libera conexión con BD. 
     * @throws DAOException Si existe cualquier error al liberar la conexión.
     */
    protected void desconectar() throws DAOException{
       logger.info("Liberando conexión con la BD.");
       try{
            //Si hay pool liberamos la conexión hacia el pool
           if(poolBD!=null){
               poolBD.liberarConexion(conexionBD);
               this.conexionBD=null;
           }
           //Si no cerramos la conexión creada mediante el DriverManager
           else{
               this.conexionBD.close();
               this.conexionBD=null;
           }
        }catch(SQLException e){
            logger.severe("Error al liberar Conexión con BD:\n"+
                           "SQLERROR="+e.getMessage());
            throw new DAOException("Error al liberar Conexión con BD:\n"+
                           "SQLERROR="+e.getMessage());
            
        }
        
    }
    

    @Override
    public Customer createCustomer(Customer customer) throws Exception 
    {
      conectar();
    // the mysql customer insert statement
      String query = " insert into customer (id, city, email, firstName, lastName, middleInitial, phone, state, street, zip )" + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
      try{
          
      //connectDAO();
      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = this.conexionBD.prepareStatement(query);
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
      }catch(SQLException e){
            throw new MyException("Error en CREACIÓN de customer"
                   +e.getMessage());
        }finally {
          desconectar();
      
    //throw new UnsupportedOperationException();
    return customer;  
     }
      
    }
    /**
     *
     * @param id
     * @return
     * @throws MyException
     * @throws DAOException
     */
    @Override
    public Customer findCustomer(Long id) throws Exception{

        Customer customer = null;
        PreparedStatement preparedStmt = null;
        ResultSet resulSet =null;
        //obtenemos conexión
        this.conectar();
        try{
            //preparamos sentencia
            String query1 = "SELECT * FROM customer "+
                                                     "WHERE customer.id ='"+id+"' ";
             preparedStmt = this.conexionBD.prepareStatement(query1);
               
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
                throw new MyException("Error en SELECT de account por id:"
                        +e.getMessage());
        }finally{
            try{        
        
                //cerramos todos los objetos relacionados con la operación y devolvemos
                //la conexión al pool
                resulSet.close();
                preparedStmt.close();
            }catch(SQLException e){
                throw new MyException("Error en SELECT de account por id:"
                        +e.getMessage());
            }
        }
        desconectar();
        //devolvemos el cliente
        return customer;
        
    }

    
    
    @Override
     public List<Account> findCustomerAccounts(Long id){
        throw new UnsupportedOperationException();
     }
     
     // Hecho con Javier y funcionando el 3 de Agosto -- En este método devolvería Account y CustomerAccount
     
    @Override
    public CustomerAccount  createAccount (Customer customer, Account account) throws Exception
    {
        this.conectar();
      try{  
        // the mysql Account insert statement
        
      String query1 = " insert into account (id, balance, beginBalance, beginBalanceTimeStamp, creditLine, description, type)" + " values (?, ?, ?, ?, ?, ?, ?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = this.conexionBD.prepareStatement(query1);
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
      
      
       }catch(SQLException e){
            throw new MyException("Error en SELECT de account por id:"
                    +e.getMessage());

        }   
      CustomerAccount customerAccount = new CustomerAccount();
      customerAccount.setCustomers_id(customer.getId());
      customerAccount.setAccounts_id(account.getId());
      
      desconectar();
      
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
    
    
    @Override
    public Account findAccount(Long id)throws Exception{

        Account account = null;
        PreparedStatement preparedStmt = null;
        ResultSet resulSet =null;
        //obtenemos conexión
        //this.conectar();
        conectar();
        try{
            //preparamos sentencia
            String query1 = "SELECT * FROM account "+
                                                     "WHERE account.id ='"+id+"' ";
             preparedStmt = this.conexionBD.prepareStatement(query1);
               
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
            throw new MyException("Error en SELECT de account por id:"
                    +e.getMessage());
        }finally{
            try{        
        
                //cerramos todos los objetos relacionados con la operación y devolvemos
                //la conexión al pool
                resulSet.close();
                preparedStmt.close();
            }catch(SQLException e){
                throw new MyException("Error en SELECT de account por id:"
                        +e.getMessage());
            }
        }
       
        desconectar();
        //devolvemos la cuenta
        return account;
        
    }

   
    
    
    /* Account findAccount(Long id){
         throw new UnsupportedOperationException();
     } */
     
    
    // Lo he cambiado para pasarle el Movement entero en lugar de sólo el id. Como en CreateCustomer 
    @Override
     public Movement addMovement(Movement movement)throws Exception
    {
      conectar();
    // the mysql customer insert statement
      String query = " insert movement (id, amount, balance, description, timeStamp, account_id )" + " values (?, ?, ?, ?, ?, ?)";

      // create the mysql insert preparedstatement
      PreparedStatement preparedStmt = this.conexionBD.prepareStatement(query);
      preparedStmt.setLong(1, movement.getId());
      preparedStmt.setDouble(2, movement.getAmount());
      preparedStmt.setDouble(3, movement.getBalance());
      preparedStmt.setString(4, movement.getDescription());
      preparedStmt.setTimestamp(5, movement.getTimeStamp());
      preparedStmt.setLong(6, movement.getAccount_id());

      
      // execute the preparedstatement
      preparedStmt.execute();
   
     desconectar();
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
    @Override
     public Collection<Movement> findAccountMovements(Long id) throws Exception{
        //Objeto en el que retornaremos los movimientoss
        Vector movementVector = new Vector();
        Movement movement = null;
        PreparedStatement preparedStmt = null;
        ResultSet resulSet = null;
        
        conectar();
        
        try{
            //preparamos sentencia
            String query1 = "SELECT * FROM movement "+
                                                     "WHERE account_id ='"+id+"' ";
             preparedStmt = this.conexionBD.prepareStatement(query1);
               
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
                //Show data
                movement.setAccount_id(new Long(resulSet.getLong("movement.account_id")));
               
                System.out.println("Movement");
                System.out.println("--------");
                System.out.println("id: " + resulSet.getLong("movement.id"));
                System.out.println("amount: " + resulSet.getDouble("movement.amount"));
                System.out.println("balance: " + resulSet.getDouble("movement.balance"));
                System.out.println("description " + resulSet.getString("movement.description"));
                //System.out.println("timeStamp timeStamp: " + resulSet.getTimestamp("movement.timeStamp"));
                System.out.println("account id: " + resulSet.getLong("movement.account_id"));
                
                movementVector.add(movement); 
            }
                
              
                
      
        }finally{
            try{        
                //cerramos todos los objetos relacionados con la operación y devolvemos
                //la conexión al pool
                resulSet.close();
                preparedStmt.close();
            }catch(SQLException e){
                throw new MyException("Error en SELECT de account por id:"
                        +e.getMessage());
            }
        }
        
        desconectar();
        //devolvemos el vector de movimientos
        return movementVector;
        
    }
     
     
     
   
}
