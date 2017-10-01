package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import Model.expresiones.ExpresionRelacional;

/**
 * @author gmc_2
 */

public class Controller {
    static private Connection conexion;
    
    // Inicializa la conexion con la base de datos.
    public static void inicializar(String usuario, String pass) throws Exception{
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://localhost;databaseName=RESERVACIONESDB;"
                    + "user=" +
                    usuario + ";password=" +  pass;
            conexion = DriverManager.getConnection(connectionUrl);
            System.out.println("Conexion exitosa"); // Hacer un messagebox
        }catch(Exception e){
            throw e;
        }
    }
    
    // Metodo encargado de crear una expresion algebraica
    public static ResultSet realizarOperacion(String tipo, String tabla, String tabla2,
            String predicado, String tablaResultante) throws Exception{
        
        ExpresionRelacional expresion = 
                Factory.crearExpresion(tipo, tabla, tabla2, predicado, tablaResultante);
        
        Statement stmt = null;
        
        try {
            stmt = conexion.createStatement();
            String query = expresion.obtenerQuery();
            ResultSet res = stmt.executeQuery(query);
            return res;
            
        } catch (Exception e ) {
            throw e;
        }
    }
}
