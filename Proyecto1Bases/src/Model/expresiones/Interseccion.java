package Model.expresiones;

import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * @author gmc_2
 */
public class Interseccion extends ExpresionRelacional{
    private ExpresionRelacional relacion2;
    
    public Interseccion(String predicado, ExpresionRelacional relacion,
            ExpresionRelacional relacion2, String tabla){
        this.relacion = relacion;
        this.relacion2 = relacion2;
        this.predicado = predicado;
        this.tablaResultante = tabla;
    }
    
    @Override
    public String procesarPredicado() throws Exception{
        try{
            validarPredicado();
            return "";
        }catch(Exception e){
            throw e;
        }
    }
    
    @Override
    public String obtenerQuery() throws Exception{
        try{
            if(tablaResultante.equals(""))
                return "SELECT * FROM " +  relacion.obtenerQuery() 
                        + " INTERSECT SELECT * FROM " + relacion2.obtenerQuery()
                            +  procesarPredicado();
            else
                return "SELECT * INTO " + tablaResultante  
                        + " FROM " +  relacion.obtenerQuery() 
                        + " INTERSECT SELECT * FROM " + relacion2.obtenerQuery()
                            +  procesarPredicado();
            /*
            if(relacion instanceof Relacion && relacion2 instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT * FROM " +  relacion.obtenerQuery() 
                            + "UNION " + relacion.obtenerQuery();
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM " +  relacion.obtenerQuery() 
                            + "UNION " + relacion.obtenerQuery(); 
            
            if(relacion instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT * FROM " + relacion.obtenerQuery() +
                            " CROSS JOIN (" + relacion2.obtenerQuery() + ")";
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM " + relacion.obtenerQuery() +
                            " CROSS JOIN (" + relacion2.obtenerQuery() + ")";
            
            if(relacion2 instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT * FROM (" + relacion.obtenerQuery() + 
                            ") CROSS JOIN " + relacion2.obtenerQuery();
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM (" + relacion.obtenerQuery() + 
                            ") CROSS JOIN " + relacion2.obtenerQuery();
            
            else
                if(tablaResultante.equals(""))
                    return "SELECT * FROM (" + relacion.obtenerQuery() + 
                            ") CROSS JOIN (" + relacion2.obtenerQuery() + ")";
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM (" + relacion.obtenerQuery() + 
                            ") CROSS JOIN (" + relacion2.obtenerQuery() + ")";*/
        }
        catch(Exception e){
            throw e;
        }
    }
    
    @Override
    public void validarPredicado() throws Exception{
        if(!predicado.equals("") && predicado != null){
            throw new Exception("La interseccion no tiene predicado");
        }
    }
    
    @Override
    public String obtenerExpresion() throws Exception{
        try{
            return relacion.obtenerExpresion() + " ∩ " + relacion2.obtenerExpresion();
        }
        catch(Exception e){
            throw e;
        }
    }
}

