/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.expresiones;

/**
 * @author gmc_2
 */
public class Agregacion extends ExpresionRelacional{
    
    public Agregacion(String predicado, ExpresionRelacional relacion, 
            String tablaResultante){
        this.predicado = predicado;
        this.relacion = relacion;
        this.tablaResultante = tablaResultante;
    }

    public String obtenerQuery() throws Exception{
        try{
            if(tablaResultante == null || tablaResultante.equals("")){
                if(relacion instanceof Relacion)
                    return "SELECT " + procesarPredicado() + " FROM " + relacion.obtenerQuery();
                else
                    return "SELECT " + procesarPredicado() + " FROM (" + relacion.obtenerQuery() + ")";
            }
            else{
                if(relacion instanceof Relacion)
                    return "SELECT " + procesarPredicado() + " INTO " + tablaResultante +
                        " FROM " + relacion.obtenerQuery();
                else
                    return "SELECT " + procesarPredicado() + " INTO " + tablaResultante +
                        " FROM (" + relacion.obtenerQuery() + ")";
            }
        }catch(Exception e){
            throw e;
        }
    }

    protected String procesarPredicado() throws Exception{
        try{
            validarPredicado();
            return predicado;
        }catch(Exception e){
            throw new Exception("El predicado no es válido; " + e.toString());
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
    
    @Override
    public String obtenerExpresion() throws Exception{
        try{
            return "G" + predicado + " (" + relacion.obtenerExpresion() + ")";
        }
        catch(Exception e){
            throw e;
        }
    }
}
