/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.expresiones;

/**
 * @author gmc_2
 */
public class Agrupacion extends ExpresionRelacional{
    private String atributos;
    
    public Agrupacion(String predicado, ExpresionRelacional relacion,
            String atributos, String tabla){
        this.relacion = relacion;
        this.atributos = atributos;
        this.predicado = predicado;
        this.tablaResultante = tabla;
    }
    
    @Override
    public String procesarPredicado() throws Exception{
        try{
            validarPredicado();
            return predicado;
        }catch(Exception e){
            throw e;
        }
    }
    
    private String procesarAtributos() throws Exception{
        try{
            validarAtributos();
            return " GROUP BY " + atributos;
        }catch(Exception e){
            throw e;
        }
    }
    
    @Override
    public String obtenerQuery() throws Exception{
        try{
            if(tablaResultante.equals(""))
                return "SELECT " + atributos + ", " + procesarPredicado() 
                        + " FROM " +  relacion.obtenerQuery() 
                        + procesarAtributos();
            else
                return "SELECT " + atributos + ", " + procesarPredicado() 
                        + " INTO " + tablaResultante  
                        + " FROM " +  relacion.obtenerQuery() 
                        + procesarAtributos();
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
    
    protected void validarPredicado() throws Exception{
        try{
            revisarInyeccionSQL(predicado);
            //Revisa la forma general del predicado para seleccion y de haber error, se le explica al usuario la forma correcta
            if(!predicado.matches("((sum|count|min|max|avg)\\([a-zA-Z0-9_]+\\)){1} *(, (sum|count|min|max|avg)\\([a-zA-Z0-9_]+\\))*"))
                throw new Exception("El predicado debe ser de la forma: \n" +
                "sum(atributo), count(atributo2), etc.");
        }catch(Exception e){
            throw e;
        }
    }
    
    protected void validarAtributos() throws Exception{
        try{
            revisarInyeccionSQL(atributos);
            if (!atributos.matches("[a-zA-Z0-9_.]+(,{1} [a-zA-Z0-9_.]+)*"))
                throw new Exception("Los atributos deben estar escritos de la forma:\n"  +
                "atributo1, atributo2, ... , atributoN y debe tener al menos un atributo");
        }catch(Exception e){
            throw e;
        }
    }
    
    
    @Override
    public String obtenerExpresion() throws Exception{
        try{
            return atributos + " G " + predicado + "(" + relacion.obtenerExpresion() + ")";
        }
        catch(Exception e){
            throw e;
        }
    }
}
