package Model.expresiones;

import java.util.ArrayList;

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
            return "";
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
        if(predicado != null){
            //Evita inyeccion sql viendo que no hayan palabras clave de sql
            if(predicado.matches("INSERT+|DROP+|CREATE+|DELETE+|UPDATE+|"))
                throw new Exception("El predicado no puede contener palabras reservadas de SQL");

            //Revisa la forma general del predicado para seleccion y de haber error, se le explica al usuario la forma correcta
            if(!predicado.matches("(([a-z]|[A-Z]|_)+ (=|<|>|>=|<=|¡=){1} ([a-z]|[A-Z]|[0-9])+)+" +
                    "( (AND|OR|and|or) ([a-z]|[A-Z])+ (=|<|>|>=|<=|¡=){1} ([a-z]|[A-Z]|[0-9])+)*"))

                throw new Exception("El predicado debe ser de la forma: \n" +
                "atributo1 [>|<|=|!=|>=|<=] atributo2 \n" +
                "Ademas pueden concatenerse predicados de la forma: \n" +
                "predicado1 (AND|OR) predicado2");
        }
        else
            throw new Exception("El predicado no puede estar vacío");
    }
}

