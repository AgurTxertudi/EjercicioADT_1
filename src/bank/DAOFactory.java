/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank;

/**
 *
 * @author agurtxertudi
 */
public class DAOFactory {
    static ConexionPoolBasico pool= new ConexionPoolBasico("jdbc:mysql://localhost:3306/bankdb","root","");
    
   public static synchronized DAO getDAO (){   
   return new DAOImplementation(pool, "config.config");
   }
    
}
