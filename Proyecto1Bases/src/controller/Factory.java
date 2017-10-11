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
                return new Seleccion(predicado, new Relacion(tabla), tablaResultante);
            }
            case "Proyección generalizada":{
                //Crea el objeto de tipo proyeccion
                return new Proyeccion(predicado, new Relacion(tabla), tablaResultante);
            }
            case "Unión":{
                //Crea el objeto de tipo proyeccion
                return new Union(predicado, new Relacion(tabla), new Relacion(tabla2), tablaResultante);
            }
            case "Producto cartesiano":{
                //Crea el objeto de tipo producto cartesiano
                return new ProductoCartesiano(predicado, new Relacion(tabla), new Relacion(tabla2), tablaResultante);
            }
            case "Concatenación natural (natural join)":{
                //Crea el objeto de tipo proyeccion
                return new NaturalJoin(predicado, new Relacion(tabla), new Relacion(tabla2), tablaResultante);
            }
            case "Concatenación (join)":{
                //Crea el objeto de tipo proyeccion
                return new Join(predicado, new Relacion(tabla), new Relacion(tabla2), tablaResultante);
            }
            case "Intersección":{
                //Crea el objeto de tipo proyeccion
                return new Interseccion(predicado, new Relacion(tabla), new Relacion(tabla2), tablaResultante);
            }
            case "Diferencia de conjuntos":{
                //Crea el objeto de tipo proyeccion
                return new Diferencia(predicado, new Relacion(tabla), new Relacion(tabla2), tablaResultante);
            }
            case "División":{
                return new Division(predicado, new Relacion(tabla), new Relacion(tabla2), tablaResultante);
            }
            case "Agrupación":{
                return new Agrupacion(predicado, new Relacion(tabla), tabla2, tablaResultante);
            }
            case "Agregación":{
                return new Agregacion(predicado, new Relacion(tabla), tablaResultante);
            }
            default:
            {
                return null;
            }
        }
    }
}
