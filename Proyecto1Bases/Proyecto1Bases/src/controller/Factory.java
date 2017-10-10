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
                    return new Seleccion(predicado, new Relacion(tabla), null);
                }
                
            }
            case "Proyección generalizada":{
                //Crea el objeto de tipo proyeccion
                if(tablaResultante != null){
                    return new Proyeccion(predicado, new Relacion(tabla), tablaResultante);
                }else{
                    return new Proyeccion(predicado, new Relacion(tabla), null);
                }
            }
            case "Unión":{
                //Crea el objeto de tipo proyeccion
                if(tablaResultante != null){
                    return new Union(predicado, new Relacion(tabla), new Relacion(tabla2), tablaResultante);
                }else{
                    return new Union(predicado, new Relacion(tabla), new Relacion(tabla2), null);
                }
            }
            case "Producto cartesiano":{
                //Crea el objeto de tipo producto cartesiano
                if(tablaResultante != null){
                    return new ProductoCartesiano(predicado, new Relacion(tabla), new Relacion(tabla2), tablaResultante);
                }else{
                    return new ProductoCartesiano(predicado, new Relacion(tabla), new Relacion(tabla2), null);
                }
            }
            case "Concatenación natural (natural join)":{
                //Crea el objeto de tipo proyeccion
                if(tablaResultante != null){
                    return new NaturalJoin(predicado, new Relacion(tabla), new Relacion(tabla2), tablaResultante);
                }else{
                    return new NaturalJoin(predicado, new Relacion(tabla), new Relacion(tabla2), null);
                }
            }
            case "Concatenación (join)":{
                //Crea el objeto de tipo proyeccion
                if(tablaResultante != null){
                    return new Join(predicado, new Relacion(tabla), new Relacion(tabla2), tablaResultante);
                }else{
                    return new Join(predicado, new Relacion(tabla), new Relacion(tabla2), null);
                }
            }
            case "Intersección":{
                //Crea el objeto de tipo proyeccion
                if(tablaResultante != null){
                    return new Interseccion(predicado, new Relacion(tabla), new Relacion(tabla2), tablaResultante);
                }else{
                    return new Interseccion(predicado, new Relacion(tabla), new Relacion(tabla2), null);
                }
            }
            case "Diferencia de conjuntos":{
                //Crea el objeto de tipo proyeccion
                if(tablaResultante != null){
                    return new Diferencia(predicado, new Relacion(tabla), new Relacion(tabla2), tablaResultante);
                }else{
                    return new Diferencia(predicado, new Relacion(tabla), new Relacion(tabla2), null);
                }
            }
            default:
            {
                return null;
            }
        }
    }
}
