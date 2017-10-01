package controller;

import Model.expresiones.*;

/**
 * @author gmc_2
 */

public class Factory {
    
    public static ExpresionRelacional crearExpresion(String tipo, String tabla, String tabla2,
            String predicado, String tablaResultante){
        switch(tipo){
            case "Selección":{
                
                //Crea el objeto de tipo seleccion
                if(tablaResultante != null){
                    return new Seleccion(predicado, new Relacion(tabla), tablaResultante);
                }else{
                    return new Seleccion(predicado, new Relacion(tabla));
                }
                
            }
            default:
            {
                return null;
            }
        }
    }
}
