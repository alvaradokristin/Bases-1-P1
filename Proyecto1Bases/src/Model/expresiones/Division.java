package Model.expresiones;

import controller.Controller;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 * @author gmc_2
 */

public class Division extends ExpresionRelacional{
    private ExpresionRelacional relacion2;
    private ArrayList<String> columnasTabla1;
    private ArrayList<String> columnasTabla2;
    
    public Division(String predicado, ExpresionRelacional relacion,
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
            return "WHERE " + formarPredicado();
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
                    return "SELECT DISTINCT " + formarColumnas() + 
                            " FROM " + relacion.obtenerQuery() + 
                            " R1 WHERE NOT EXISTS(SELECT * FROM " + relacion2.obtenerQuery() +
                            " S WHERE NOT EXISTS(SELECT * FROM " + relacion.obtenerQuery() +
                            " R2 " + procesarPredicado() + "))";
                else
                    return "SELECT DISTINCT " + formarColumnas() + 
                            " INTO " + tablaResultante +
                            " FROM " + relacion.obtenerQuery() + 
                            " R1 WHERE NOT EXISTS(SELECT * FROM " + relacion2.obtenerQuery() +
                            " S WHERE NOT EXISTS(SELECT * FROM " + relacion.obtenerQuery() +
                            " R2 " + procesarPredicado() + "))";
            
            if(relacion instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT DISTINCT " + formarColumnas() + 
                            " FROM " + relacion.obtenerQuery() + 
                            " R1 WHERE NOT EXISTS(SELECT * FROM " + relacion2.obtenerQuery() +
                            " S WHERE NOT EXISTS(SELECT * FROM " + relacion.obtenerQuery() +
                            " R2 " + procesarPredicado() + "))";
                else
                    return "SELECT DISTINCT " + formarColumnas() + 
                            " INTO " + tablaResultante +
                            " FROM " + relacion.obtenerQuery() + 
                            " R1 WHERE NOT EXISTS(SELECT * FROM " + relacion2.obtenerQuery() +
                            " S WHERE NOT EXISTS(SELECT * FROM " + relacion.obtenerQuery() +
                            " R2 " + procesarPredicado() + "))";
            
            if(relacion2 instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT DISTINCT " + formarColumnas() + 
                            " FROM " + relacion.obtenerQuery() + 
                            " R1 WHERE NOT EXISTS(SELECT * FROM " + relacion2.obtenerQuery() +
                            " S WHERE NOT EXISTS(SELECT * FROM " + relacion.obtenerQuery() +
                            " R2 " + procesarPredicado() + "))";
                else
                    return "SELECT DISTINCT " + formarColumnas() + 
                            " INTO " + tablaResultante +
                            " FROM " + relacion.obtenerQuery() + 
                            " R1 WHERE NOT EXISTS(SELECT * FROM " + relacion2.obtenerQuery() +
                            " S WHERE NOT EXISTS(SELECT * FROM " + relacion.obtenerQuery() +
                            " R2 " + procesarPredicado() + "))";
            
            else
                if(tablaResultante.equals(""))
                    return "SELECT DISTINCT " + formarColumnas() + 
                            " FROM " + relacion.obtenerQuery() + 
                            " R1 WHERE NOT EXISTS(SELECT * FROM " + relacion2.obtenerQuery() +
                            " S WHERE NOT EXISTS(SELECT * FROM " + relacion.obtenerQuery() +
                            " R2 " + procesarPredicado() + "))";
                else
                    return "SELECT DISTINCT " + formarColumnas() + 
                            " INTO " + tablaResultante +
                            " FROM " + relacion.obtenerQuery() + 
                            " R1 WHERE NOT EXISTS(SELECT * FROM " + relacion2.obtenerQuery() +
                            " S WHERE NOT EXISTS(SELECT * FROM " + relacion.obtenerQuery() +
                            " R2 " + procesarPredicado() + "))";
        }
        catch(Exception e){
            throw e;
        }
    }
    
    @Override
    public void validarPredicado() throws Exception{
        if(predicado != null && !predicado.equals("")){
            throw new Exception("Una division no puede tener predicado");
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
    
    private ArrayList<String> columnasIguales() throws Exception{
        ArrayList<String> iguales = new ArrayList<>();
        for(String s1: columnasTabla1)
            for(String s2: columnasTabla2)
                if(s1.equals(s2))
                    iguales.add(s1);
        
        if(!iguales.isEmpty())
            return iguales;
        else
            throw new Exception("Las tablas no tienen atributos en común");
    }
    
    private String formarPredicado() throws Exception{
        try{
            ArrayList<String> iguales = columnasIguales();
            String res = "";
            
            for(String columna : columnasTabla1)
                res += "R1." + columna + " = " + "R2." + columna + " AND ";
            
            for(String columna : iguales)
                res += "R2." + columna + " = " + "S." + columna + " AND ";
            
            res = res.substring(0, res.length()-5);
            
            return res;
        }catch(Exception e){
            throw e;
        }
    }
    
    private String formarColumnas() throws Exception{
        try{
            String res = "";
            for(String columna : columnasTabla1){
                if(!columnasTabla2.contains(columna))
                    res += "R1." + columna + ", ";
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
            return relacion.obtenerExpresion() + " ÷ " + relacion2.obtenerExpresion();
        }
        catch(Exception e){
            throw e;
        }
    }
}
