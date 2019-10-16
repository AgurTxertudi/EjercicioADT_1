package bank;


import java.sql.*;
import java.util.*;
 /**
 * Esta clase implementa un pool de conexiones que se almacena el un objeto pila
 * (java.util.Stack).
 */
public class ConexionPoolBasico{ 

    protected Stack pool;
    protected String connectionURL;
    protected String userName;
    protected String password;
    /**
     * Construye un pool de conexiones almacenando los datos pasados como parámetros
     * para usarlos al crear las conexiones.
     * @param aConnectionURL URL de la BD a conectarse.
     * @param aUserName Nombre de usuario de la BD para crear la conexión.
     * @param aPassword Contraseña de usuario de la BD para crear la conexión.
     */
    public ConexionPoolBasico(String aConnectionURL, String aUserName, String aPassword){
		connectionURL = aConnectionURL;
		userName = aUserName;
		password = aPassword;
		pool = new Stack();
    } 
    /** Obtiene una conexión del pool de conexiones
     * @return Un objeto java.sql.Connection para la conexión.
     * @throws SQLException si existe error al obtener la conexión.
     */
    public synchronized Connection extraerConexion()   throws SQLException{ 
	// Si el pool no esta vacio, tomar una conexion  
	   if(!pool.empty()) { 
			return (Connection) pool.pop();
           } 
           else { 
			// Entonces generar una conexion nueva
			return DriverManager.getConnection(connectionURL, userName, password);
	   } 
    }
    /** Devuelve una conexión al pool de conexiones.
     * @param conn La conexión a liberar.
     */
    public synchronized void liberarConexion(Connection conn)throws SQLException{ 
            //!!!!TIENE SENTIDO CERRAR LA CONEXION ANTES DE APILARLA EN EL POOL?????
            //conn.close();
	    pool.push(conn);
    } 
}
 

