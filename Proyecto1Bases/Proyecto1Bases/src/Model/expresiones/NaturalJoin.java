package Model.expresiones;

import java.util.ArrayList;
import controller.Controller;

/**
 * @author gmc_2
 */

public class NaturalJoin extends ExpresionRelacional{
    private ExpresionRelacional relacion2;
    
    public NaturalJoin(String predicado, ExpresionRelacional relacion,
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
            return formarPredicado();
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
                            + " JOIN " + relacion.obtenerQuery()
                            + procesarPredicado();
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM " +  relacion.obtenerQuery() 
                            + " JOIN " + relacion.obtenerQuery()
                            + procesarPredicado();
            
            if(relacion instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT * FROM " + relacion.obtenerQuery() +
                            " JOIN (" + relacion2.obtenerQuery() + 
                            ") AS " + relacion2.getNombre() + 
                            procesarPredicado();
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM " + relacion.obtenerQuery() +
                            " JOIN (" + relacion2.obtenerQuery() + 
                            ") AS " + relacion2.getNombre() + 
                            procesarPredicado();
            
            if(relacion2 instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT * FROM (" + relacion.obtenerQuery() + 
                            ") AS " + relacion.getNombre() + 
                            " JOIN " + relacion2.obtenerQuery()
                            + procesarPredicado();
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM (" + relacion.obtenerQuery() + 
                            ") AS " + relacion.getNombre() + 
                            " JOIN " + relacion2.obtenerQuery()
                            + procesarPredicado();
            
            else
                if(tablaResultante.equals(""))
                    return "SELECT * FROM (" + relacion.obtenerQuery() + 
                            ") AS " + relacion.getNombre() + 
                            " JOIN (" + relacion2.obtenerQuery() + 
                            ") AS " + relacion2.getNombre()
                            + procesarPredicado();
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM (" + relacion.obtenerQuery() + 
                            ") AS " + relacion.getNombre() + 
                            " JOIN (" + relacion2.obtenerQuery() + 
                            ") AS " + relacion2.getNombre()
                            + procesarPredicado();
        }
        catch(Exception e){
            throw e;
        }
    }
    
    @Override
    public void validarPredicado() throws Exception{
        if(predicado != null || !predicado.equals("")){
            throw new Exception("Un natural join no puede tener predicado");
        }
    }
    
    private String formarPredicado() throws Exception{
        
        String iguales = null;
        
        if(relacion instanceof Relacion && relacion2 instanceof Relacion){
            try{
                iguales = columnasIguales(
                    Controller.obtenerColumnas(relacion.obtenerQuery()),
                    Controller.obtenerColumnas(relacion2.obtenerQuery()));
            }catch(Exception e){
                throw e;
            }
        }
        
        else if(relacion instanceof Relacion){
            try{
                iguales = columnasIguales(
                    Controller.obtenerColumnas(relacion.obtenerQuery()),
                    Controller.obtenerColumnasQuery(Controller.realizarOperacionAux(relacion2)));
            }
            catch(Exception e){
                throw e;
            }
        }
        
        else if(relacion2 instanceof Relacion){
            try{
                iguales = columnasIguales(
                    Controller.obtenerColumnasQuery(Controller.realizarOperacionAux(relacion)),
                    Controller.obtenerColumnas(relacion2.obtenerQuery()));
            }
            catch(Exception e){
                throw e;
            }
        }
        else{
            try{
                iguales = columnasIguales(
                    Controller.obtenerColumnasQuery(Controller.realizarOperacionAux(relacion)),
                    Controller.obtenerColumnasQuery(Controller.realizarOperacionAux(relacion2)));
            }
            catch(Exception e){
                throw e;
            }
        }
        
        if(iguales != null)
            return "ON " + relacion.getNombre() + "." + iguales + " = " 
                    + relacion2.getNombre() + "." + iguales;
        else
            return null;
    }
    
    private String columnasIguales(ArrayList<String> lista1, ArrayList<String> lista2){
        for(String s1: lista1)
            for(String s2: lista2)
                if(s1.equals(s2))
                    return s1;
        return null;
    }
}

