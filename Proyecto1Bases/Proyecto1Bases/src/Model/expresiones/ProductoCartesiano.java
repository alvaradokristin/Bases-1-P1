package Model.expresiones;

/**
 * @author gmc_2
 */
public class ProductoCartesiano extends ExpresionRelacional{
    private ExpresionRelacional relacion2;
    
    public ProductoCartesiano(String predicado, ExpresionRelacional relacion,
            ExpresionRelacional relacion2, String tabla){
        this.relacion = relacion;
        this.relacion2 = relacion2;
        this.predicado = predicado;
        this.tablaResultante = tabla;
    }
    
    @Override
    public String procesarPredicado() throws Exception{
        try{
            validarPredicado();
            return "";
        }catch(Exception e){
            throw e;
        }
    }
    
    @Override
    public String obtenerQuery() throws Exception{
        try{
            if(relacion instanceof Relacion && relacion2 instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT * FROM " +  relacion.obtenerQuery() 
                            + " CROSS JOIN " + relacion.obtenerQuery()
                            + procesarPredicado();
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM " +  relacion.obtenerQuery() 
                            + " CROSS JOIN " + relacion.obtenerQuery()
                            + procesarPredicado(); 
            
            if(relacion instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT * FROM " + relacion.obtenerQuery() +
                            " CROSS JOIN (" + relacion2.obtenerQuery() + ")" 
                            + procesarPredicado();
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM " + relacion.obtenerQuery() +
                            " CROSS JOIN (" + relacion2.obtenerQuery() + ")" 
                            + procesarPredicado();
            
            if(relacion2 instanceof Relacion)
                if(tablaResultante.equals(""))
                    return "SELECT * FROM (" + relacion.obtenerQuery() + 
                            ") CROSS JOIN " + relacion2.obtenerQuery()
                            + procesarPredicado();
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM (" + relacion.obtenerQuery() + 
                            ") CROSS JOIN " + relacion2.obtenerQuery()
                            + procesarPredicado();
            
            else
                if(tablaResultante.equals(""))
                    return "SELECT * FROM (" + relacion.obtenerQuery() + 
                            ") CROSS JOIN (" + relacion2.obtenerQuery() + ")"
                            + procesarPredicado();
                else
                    return "SELECT * INTO " + tablaResultante  
                            + " FROM (" + relacion.obtenerQuery() + 
                            ") CROSS JOIN (" + relacion2.obtenerQuery() + ")"
                            + procesarPredicado();
        }
        catch(Exception e){
            throw e;
        }
    }
    
    @Override
    public void validarPredicado() throws Exception{
        if(!predicado.equals("") || predicado != null){
            throw new Exception("El producto cartesiano no tiene predicado");
        }
    }
}
