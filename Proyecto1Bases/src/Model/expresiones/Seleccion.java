package Model.expresiones;

/**
 * @author gmc_2
 */

public class Seleccion extends ExpresionRelacional{
    private static int cont;

    public Seleccion(String predicado, ExpresionRelacional relacion, 
            String tablaResultante){
        cont++;
        this.predicado = predicado;
        this.relacion = relacion;
        this.tablaResultante = tablaResultante;
    }

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
                    return "SELECT * INTO " + tablaResultante +
                        " FROM " + relacion.obtenerQuery() + " " + procesarPredicado();
                else
                    return "SELECT * INTO " + tablaResultante +
                        " FROM (" + relacion.obtenerQuery() + ") " + procesarPredicado();
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
        try{
            revisarInyeccionSQL(predicado);
            //Revisa la forma general del predicado para seleccion y de haber error, se le explica al usuario la forma correcta
            if(!predicado.matches("([a-zA-Z0-9_]+ (=|<|>|>=|<=|¡=){1} [a-zA-Z0-9_]+)+"
                    + "( (AND|OR|and|or) [a-zA-Z0-9_]+ (=|<|>|>=|<=|¡=){1} [a-zA-Z0-9_]+)*"))

                throw new Exception("El predicado debe ser de la forma: \n" +
                "atributo1 [>|<|=|!=|>=|<=] atributo2 \n" +
                "Ademas pueden concatenerse predicados de la forma: \n" +
                "predicado1 (AND|OR) predicado2");
        }catch(Exception e){
            throw e;
        }
    }
    
    @Override
    public String getNombre(){
        return "Sel" + String.valueOf(cont);
    }
}
