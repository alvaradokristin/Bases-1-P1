package Model.expresiones;

import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * @author gmc_2
 */
public class Join extends ExpresionRelacional{
    private ExpresionRelacional relacion2;
    
    public Join(String predicado, ExpresionRelacional relacion,
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
            return " ON " + predicado;
        }catch(Exception e){
            throw e;
        }
    }
    
    @Override
    public String obtenerQuery() throws Exception{
        try{
            if(relacion instanceof Relacion && relacion2 instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT * FROM " +  relacion.obtenerQuery() 
                            + " JOIN " + relacion2.obtenerQuery()
                            + procesarPredicado();
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM " +  relacion.obtenerQuery() 
                            + " JOIN " + relacion2.obtenerQuery()
                            + procesarPredicado();
            
            if(relacion instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT * FROM " + relacion.obtenerQuery() +
                            " JOIN (" + relacion2.obtenerQuery() + ")"
                            + procesarPredicado();
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM " + relacion.obtenerQuery() +
                            " JOIN (" + relacion2.obtenerQuery() + ")" 
                            + procesarPredicado();
            
            if(relacion2 instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT * FROM (" + relacion.obtenerQuery() + 
                            ") JOIN " + relacion2.obtenerQuery()
                            + procesarPredicado();
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM (" + relacion.obtenerQuery() + 
                            ") JOIN " + relacion2.obtenerQuery()
                            + procesarPredicado();
            
            else
                if(tablaResultante.equals(""))
                    return "SELECT * FROM (" + relacion.obtenerQuery() + 
                            ") JOIN (" + relacion2.obtenerQuery() + ")"
                            + procesarPredicado();
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM (" + relacion.obtenerQuery() + 
                            ") JOIN (" + relacion2.obtenerQuery() + ")"
                            + procesarPredicado();
        }
        catch(Exception e){
            throw e;
        }
    }
    
    @Override
    public void validarPredicado() throws Exception{
        try{
            revisarInyeccionSQL(predicado);
            if(!predicado.matches("([a-zA-Z0-9_.]+ (=|<|>|>=|<=|¡=){1} [a-zA-Z0-9_.]+)+"
                    + "( (AND|OR|and|or) [a-zA-Z0-9_.]+ (=|<|>|>=|<=|¡=){1} [a-zA-Z0-9_.]+)*"))

                throw new Exception("El predicado debe ser de la forma: \n" +
                "Atributo1 > | < | = | != | >= | <=  Atributo2 \n\n" +
                "Ademas pueden concatenerse predicados de la forma: \n" +
                "Predicado1 AND|OR Predicado2");
        }catch(Exception e){
            throw e;
        }
    }
    
    
    @Override
    public String obtenerExpresion() throws Exception{
        try{
            return relacion.obtenerExpresion() + " ⨝" + predicado + " " + relacion2.obtenerExpresion();
        }
        catch(Exception e){
            throw e;
        }
    }
}

