package Model.expresiones;

/**
 * @author gmc_2
 */

public class Proyeccion extends ExpresionRelacional{

    public Proyeccion(String predicado, ExpresionRelacional relacion, 
            String tablaResultante){
        this.predicado = predicado;
        this.relacion = relacion;
        this.tablaResultante = tablaResultante;
    }

    @Override
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
        try{
            revisarInyeccionSQL(predicado);
            if (!predicado.matches("[a-zA-Z0-9_]+(,{1} [a-zA-Z0-9_]+)*"))
                throw new Exception("El predicado debe ser de la forma:\n"  +
                "atributo1, atributo2, ... , atributoN y debe tener al menos un atributo");
        }catch(Exception e){
            throw e;
        }
    }
}
