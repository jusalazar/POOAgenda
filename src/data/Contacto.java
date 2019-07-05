
package data;

import java.io.Serializable;

public class Contacto implements Serializable {
    
    String name;
    String numeroDeCelular;
    
    
    public Contacto(String name,String numeroDeCelular) {
        this.name = name;

        if(name.length() > 40){
            this.name = "error no ingresaste un nombre que posea menos de 40 caracteres de longitud incluyendo espacios";
        }
        if(name.length() < 40){
            this.name = name;
        }
        
        boolean resultadoNumValido = CelEsUnNumeroValido(numeroDeCelular);
    
        if(numeroDeCelular.length() < 10 || numeroDeCelular.length() > 10 || resultadoNumValido == false ){
           this.numeroDeCelular = "Error no ingresaste un numero de celular valido con solamente 10 digitos sin espacios ni comas";
        }
        if(numeroDeCelular.length() == 10 && resultadoNumValido == true ){
            this.numeroDeCelular = numeroDeCelular;
        }    
    }

    public Contacto() {
        this.name = null;
        this.numeroDeCelular = null;
    }
    
    public String getName() {
        return name;
    }

    public String getNumeroDeCelular() {
        return numeroDeCelular;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setNumeroDeCelular(String numeroDeCelular) {
        this.numeroDeCelular = numeroDeCelular;
    }


    @Override
    public String toString() { // para el gui
        return "   "+ name +"     "+ numeroDeCelular +"    "+"\n";
    }
    
    public boolean CelEsUnNumeroValido(String numeroCelular){
        boolean resultado;
        try{
            Float.parseFloat(numeroCelular);
            return resultado = true;
        }catch(NumberFormatException e){
            return resultado = false;
        }
    }
}
