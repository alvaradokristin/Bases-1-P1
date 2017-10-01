package Model.expresiones;

/**
 * @author gmc_2
 */

public class Seleccion extends ExpresionRelacional{

    public Seleccion(String predicado, ExpresionRelacional relacion){
        this.predicado = predicado;
        this.relacion = relacion;
        this.tablaResultante = null;
    }

    public Seleccion(String predicado, ExpresionRelacional relacion, String tablaResultante){
        this.predicado = predicado;
        this.relacion = relacion;
        this.tablaResultante = tablaResultante;
    }

    /*public void realizarOperacion() throws Exception{
        //Codigo de conexion con la base de datos
    }*/

    public String obtenerQuery() throws Exception{
        try{
            if(tablaResultante == null || tablaResultante.equals("")){
                if(relacion instanceof Relacion)
                    return "SELECT * FROM " + relacion.obtenerQuery() + " " + procesarPredicado();
                else
                    return "SELECT * FROM (" + relacion.obtenerQuery() + ") " + procesarPredicado();
            }
            else{
                if(relacion instanceof Relacion)
                    return "SELECT * INTO" + tablaResultante +
                        "FROM " + relacion.obtenerQuery() + " " + procesarPredicado();
                else
                    return "SELECT * INTO" + tablaResultante +
                        "FROM (" + relacion.obtenerQuery() + ") " + procesarPredicado();
            }
        }catch(Exception e){
            throw e;
        }
    }

    protected String procesarPredicado() throws Exception{
        try{
            validarPredicado();
            return "WHERE " + predicado;
        }catch(Exception e){
            throw new Exception("El predicado no es válido; " + e.toString());
        }
    }

    protected void validarPredicado() throws Exception{
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
