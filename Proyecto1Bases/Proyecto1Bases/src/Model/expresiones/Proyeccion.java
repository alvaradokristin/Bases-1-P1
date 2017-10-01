package Model.expresiones;

/**
 * @author gmc_2
 */

public class Proyeccion extends ExpresionRelacional{

    public Proyeccion(String predicado, ExpresionRelacional relacion){
        this.predicado = predicado;
        this.relacion = relacion;
        this.tablaResultante = null;
    }

    public Proyeccion(String predicado, ExpresionRelacional relacion, String tablaResultante){
        this.predicado = predicado;
        this.relacion = relacion;
        this.tablaResultante = tablaResultante;
    }

    /*@Override
    public void realizarOperacion() throws Exception {
        //Codigo de conexion con la base de datos
    }*/

    @Override
    public String obtenerQuery() throws Exception{
        try{
            return "SELECT " + procesarPredicado() + " FROM (" + relacion.obtenerQuery() + ")";
        }catch(Exception e){
            throw e;
        }
    }

    @Override
    protected String procesarPredicado() throws Exception{
        try{
            validarPredicado();
            return predicado;
        }catch(Exception e){
            throw new Exception("El predicado no es válido; " + e.toString());
        }
    }

    @Override
    protected void validarPredicado() throws Exception{
        if(predicado != null) {
            //Evita inyeccion sql viendo que no hayan palabras clave de sql
            if (predicado.matches("INSERT+|DROP+|CREATE+|DELETE+|UPDATE+|"))
                throw new Exception("El predicado no puede contener palabras reservadas de SQL");
            if (!predicado.matches("([A-Z]|[a-z]|[0-9])+(,{1} ([A-Z]|[a-z]|[0-9])+)+"))
                throw new Exception("El predicado debe ser de la forma:\n"  +
                "atributo1, atributo2, ... , atributoN y debe tener al menos un atributo");
        }
        else
            throw new Exception("El predicado no puede estar vacío");

    }
}
