package Model.expresiones;

/**
 * @author gmc_2
 */

/*
Clase especial de expresiones, es solo la tabla, por lo que no deberia tener que realizarse
ninguna operacion sobre ella, igual se espera que se comporte como cualquier otra operacion.
*/
public class Relacion extends ExpresionRelacional{
    
    public Relacion(String predicado){
        this.predicado = predicado;
    }

    /*public void realizarOperacion() throws Exception{
        //Codigo de conexion con la base de datos
    }*/

    public String obtenerQuery() throws Exception{
        try{
            return procesarPredicado();
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
        if(predicado != null){
            //Evita inyeccion sql viendo que no hayan palabras clave de sql
            if(predicado.matches("INSERT+|DROP+|CREATE+|DELETE+|UPDATE+|"))
                throw new Exception("El predicado no puede contener palabras reservadas de SQL");
        }
        else
            throw new Exception("El predicado no puede estar vacío");
    }
}
