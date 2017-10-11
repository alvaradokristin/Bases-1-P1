package Model.expresiones;

import java.util.ArrayList;
import controller.Controller;
import java.awt.Graphics;
import javax.swing.JComponent;

/**
 * @author gmc_2
 */

public class NaturalJoin extends ExpresionRelacional{
    private ExpresionRelacional relacion2;
    private ArrayList<String> columnasTabla1;
    private ArrayList<String> columnasTabla2;
    
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
            //Inicializa las columnas
            this.obtenerColumnas();
            
            if(relacion instanceof Relacion && relacion2 instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT " + this.formarColumnas() 
                            + " FROM " +  relacion.obtenerQuery() 
                            + " JOIN " + relacion2.obtenerQuery()
                            + procesarPredicado();
                else
                    return "SELECT " + this.formarColumnas() 
                            + " INTO " + tablaResultante  
                            + " FROM " +  relacion.obtenerQuery() 
                            + " JOIN " + relacion2.obtenerQuery()
                            + procesarPredicado();
            
            if(relacion instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT " + this.formarColumnas() + 
                            " FROM " + relacion.obtenerQuery() +
                            " JOIN (" + relacion2.obtenerQuery() + 
                            ") AS " + relacion2.getNombre() + 
                            procesarPredicado();
                else
                    return "SELECT " + this.formarColumnas() + 
                            " INTO " + tablaResultante  
                            + " FROM " + relacion.obtenerQuery() +
                            " JOIN (" + relacion2.obtenerQuery() + 
                            ") AS " + relacion2.getNombre() + 
                            procesarPredicado();
            
            if(relacion2 instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT " + this.formarColumnas() + 
                            " FROM (" + relacion.obtenerQuery() + 
                            ") AS " + relacion.getNombre() + 
                            " JOIN " + relacion2.obtenerQuery()
                            + procesarPredicado();
                else
                    return "SELECT " + this.formarColumnas() 
                            + " INTO " + tablaResultante  
                            + " FROM (" + relacion.obtenerQuery()
                            + ") AS " + relacion.getNombre()
                            + " JOIN " + relacion2.obtenerQuery()
                            + procesarPredicado();
            
            else
                if(tablaResultante.equals(""))
                    return "SELECT " + this.formarColumnas() + 
                            " FROM (" + relacion.obtenerQuery() + 
                            ") AS " + relacion.getNombre() + 
                            " JOIN (" + relacion2.obtenerQuery() + 
                            ") AS " + relacion2.getNombre()
                            + procesarPredicado();
                else
                    return "SELECT " + this.formarColumnas() + 
                            " INTO " + tablaResultante +  
                            " FROM (" + relacion.obtenerQuery() + 
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
        if(predicado != null && !predicado.equals("")){
            throw new Exception("Un natural join no puede tener predicado");
        }
    }
    
    private void obtenerColumnas() throws Exception{ 
        if(relacion instanceof Relacion && relacion2 instanceof Relacion){
            try{
                columnasTabla1 = 
                        Controller.obtenerColumnas(relacion.obtenerQuery());
                columnasTabla2 = 
                        Controller.obtenerColumnas(relacion2.obtenerQuery());                
            }catch(Exception e){
                throw e;
            }
        }
        
        else if(relacion instanceof Relacion){
            try{
                columnasTabla1 = 
                        Controller.obtenerColumnas(relacion.obtenerQuery());
                columnasTabla2 = 
                        Controller.obtenerColumnasQuery(Controller.realizarOperacionAux(relacion2)); 
            }
            catch(Exception e){
                throw e;
            }
        }
        
        else if(relacion2 instanceof Relacion){
            try{
                columnasTabla1 = 
                        Controller.obtenerColumnasQuery(Controller.realizarOperacionAux(relacion));
                columnasTabla2 = 
                        Controller.obtenerColumnas(relacion2.obtenerQuery()); 
            }
            catch(Exception e){
                throw e;
            }
        }
        else{
            try{
                columnasTabla1 = Controller.obtenerColumnasQuery(Controller.realizarOperacionAux(relacion));
                columnasTabla2 =  Controller.obtenerColumnasQuery(Controller.realizarOperacionAux(relacion2));
            }
            catch(Exception e){
                throw e;
            }
        }
    }
    
    private String columnasIguales(ArrayList<String> lista1, ArrayList<String> lista2) throws Exception{
        for(String s1: lista1)
            for(String s2: lista2)
                if(s1.equals(s2))
                    return s1;
        throw new Exception("Las tablas no tienen atributos en común");
    }
    
    private String formarPredicado() throws Exception{ 
        try{
            String iguales = columnasIguales(columnasTabla1, columnasTabla2);
            return " ON " + relacion.getNombre() + "." + iguales + " = " 
                    + relacion2.getNombre() + "." + iguales;
        }catch(Exception e){
            throw e;
        }
    }

    private String formarColumnas() throws Exception{        
        try{
            String res = "";
            String igual = columnasIguales(columnasTabla1, columnasTabla2);
            
            for(String columna : columnasTabla1){
                res += relacion.getNombre() + "." + columna + ", ";
            }
            for(String columna : columnasTabla2){
                if(!columna.equals(igual))
                    res += relacion2.getNombre() + "." + columna + ", ";
            }
            
            res = res.substring(0, res.length()-2);
            
            return res;
        }catch(Exception e){
            throw e;
        }
    }
    
    @Override
    public String obtenerExpresion() throws Exception{
        try{
            return relacion.obtenerExpresion() + " ⨝ " + relacion2.obtenerExpresion();
        }
        catch(Exception e){
            throw e;
        }
    }
}

