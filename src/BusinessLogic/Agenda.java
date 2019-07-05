
package BusinessLogic;

import data.Contacto;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Agenda {
    
    
    public static void MostrarContactos(Contacto[] ListaContactos){
        String mostrar;
        mostrar = "";
        int casillaArreglo = 0;
        while(casillaArreglo < ListaContactos.length){
            if(ListaContactos[casillaArreglo] != null){
                mostrar = mostrar + ListaContactos[casillaArreglo].toString();
            }
            if(ListaContactos[casillaArreglo] == null){ 
                break;
            }
            casillaArreglo++; 
        }
        
        JOptionPane.showMessageDialog(null,mostrar, "Lista de Contactos", JOptionPane.DEFAULT_OPTION);
    }
    public static boolean CelEsUnNumeroValido(String numeroCelular){
        boolean resultado;
        try{
            Float.parseFloat(numeroCelular);
            return resultado = true;
        }catch(NumberFormatException e){
            return resultado = false;
        }
    } 
    public static void AgregarContactoAlArchivo(String nombre,String numero) {
		FileWriter flwriter = null;
		try {
			flwriter = new FileWriter("C:\\Users\\mauri\\Documents\\Universidad\\Segundo semestre\\POO\\T7. Agenda\\Agenda 2\\Agenda\\src\\data\\ContactosAgenda.txt",true);
			BufferedWriter bfwriter = new BufferedWriter(flwriter);
                        if(nombre.length() > 40){
                            JOptionPane.showMessageDialog(null," El nombre "+ nombre + " es un nombre muy largo" + "\n" + "Por favor intentalo de nuevo con un nombre que posea una cantidad"
                                    + " de caracteres no mayor a 40", "Error al ingresar nombre", JOptionPane.WARNING_MESSAGE);
                                    bfwriter.close();
                        }
                        boolean resultadoNumValido = CelEsUnNumeroValido(numero);
                        if(numero.length() < 10 || numero.length() > 10 || resultadoNumValido == false ){
                            JOptionPane.showMessageDialog(null," El numero de celular "+ numero + " es un numero invalido" + "\n" + "Por favor intentalo de nuevo con un numero que posea una cantidad"
                                    + " de 10 digitos sin espacios ni letras", "Error al ingresar nombre", JOptionPane.WARNING_MESSAGE);
                                    bfwriter.close();
                        } 
                        bfwriter.write(nombre + ","+numero +"\n");
                        bfwriter.close();
			} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (flwriter != null) {
				try {
					flwriter.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
    public static void LeerTodosLosContactos (Contacto[] ListaContactos,int cantidadDeContactos){
       FileReader lectorDeArchivo = null; // Lector de archivo de texto
       try{ // Va a intentar encontrar el archivo en el que leera los contactos
            lectorDeArchivo = new FileReader("C:\\Users\\mauri\\Documents\\Universidad\\Segundo semestre\\POO\\T7. Agenda\\Agenda 2\\Agenda\\src\\data\\ContactosAgenda.txt");
        }catch(FileNotFoundException error){
            JOptionPane.showMessageDialog(null, error);
        }
        BufferedReader textoArchivo; // Variable donde se almacena lo que leyo el lector del archivo
        textoArchivo = new BufferedReader(lectorDeArchivo);  // Le estamos diciendo al buffer de que lector va a recibir
        
        int casillaArreglo = 0;
        while(casillaArreglo < ListaContactos.length){
            String lineaTexto;
            try {
                lineaTexto = textoArchivo.readLine(); // Creo la variable donde se va a guardar cada sub cadena del archivo
            } catch (IOException error) {//en caso de que falle muestres el mensaje de error y termine ciclo
                JOptionPane.showMessageDialog(null, error);
                return; 
            }
            String[] valoresArchivo; //Creo el arreglo donde se guardaran los datos de cada linar (Primero lo hara con la primera, luego se borrara y hara lo mismo con la segunda linea)
            if (lineaTexto == null){
                break;
            }
            valoresArchivo = lineaTexto.split(",");
            String nombreContacto = valoresArchivo[0];
            String numeroContacto = valoresArchivo[1];
            
            ListaContactos[casillaArreglo] = new Contacto(nombreContacto,numeroContacto);
            casillaArreglo++;
            cantidadDeContactos++;      
        } 
    }
    public static void BuscarUnContacto (String nombre, String numero){
        FileReader lectorDeArchivo = null; // Lector de archivo de texto
       try{ // Va a intentar encontrar el archivo en el que leera los contactos
            lectorDeArchivo = new FileReader("C:\\Users\\mauri\\Documents\\Universidad\\Segundo semestre\\POO\\T7. Agenda\\Agenda 2\\Agenda\\src\\data\\ContactosAgenda.txt");
        }catch(FileNotFoundException error){
            JOptionPane.showMessageDialog(null, error);
        }
        BufferedReader textoArchivo; // Variable donde se almacena lo que leyo el lector del archivo
        textoArchivo = new BufferedReader(lectorDeArchivo);  // Le estamos diciendo al buffer de que lector va a recibir
        
        int casillaArreglo = 0;
        int vecesBuscando = 100;
        boolean resultadoNumValido = CelEsUnNumeroValido(numero);
        if(nombre.length() == 0 && (numero.length() == 10 && resultadoNumValido == true)){ 
        }
        if((numero.length() == 0) && (nombre.length() < 40) ){
        }
        if((numero.length() == 0) && (nombre.length() > 40) ){
            JOptionPane.showMessageDialog(null," El nombre "+ nombre + " es un nombre muy largo" + "\n" + "Por favor intentalo de nuevo con un nombre que posea una cantidad"
             + " de caracteres no mayor a 40", "Error al ingresar nombre", JOptionPane.WARNING_MESSAGE);
        }
        if(nombre.length() == 0 && (numero.length() != 10 || resultadoNumValido == false)){ 
            JOptionPane.showMessageDialog(null," El numero de celular "+ numero + " es un numero invalido" + "\n" + "Por favor intentalo de nuevo con un numero que posea una cantidad"
                    + " de 10 digitos sin espacios ni letras", "Error al ingresar numero", JOptionPane.WARNING_MESSAGE);
        }
        if((numero.length() < 10 || numero.length() > 10 || resultadoNumValido == false)&& nombre.length() > 40){
            JOptionPane.showMessageDialog(null," El nombre "+ nombre + " es un nombre muy largo" + "\n" + "Por favor intentalo de nuevo con un nombre que posea una cantidad"
                    + " de caracteres no mayor a 40", "Error al ingresar nombre", JOptionPane.WARNING_MESSAGE);
            JOptionPane.showMessageDialog(null," El numero de celular "+ numero + " es un numero invalido" + "\n" + "Por favor intentalo de nuevo con un numero que posea una cantidad"
                    + " de 10 digitos sin espacios ni letras", "Error al ingresar numero", JOptionPane.WARNING_MESSAGE);
        }

        while(casillaArreglo < vecesBuscando){
            String lineaTexto;
            try {
                lineaTexto = textoArchivo.readLine(); // Creo la variable donde se va a guardar cada sub cadena del archivo
            } catch (IOException error) {//en caso de que falle muestres el mensaje de error y termine ciclo
                JOptionPane.showMessageDialog(null, error);
                return; 
            }
            if (lineaTexto == null){
                break;
            }
            String [] valores = lineaTexto.split(",");
            if(valores[0].equalsIgnoreCase(nombre)){
                JOptionPane.showMessageDialog(null,valores[0] + " " + valores[1], "Resultado de busqueda", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            if(valores[1].equals(numero)){
                JOptionPane.showMessageDialog(null,valores[0] + " " + valores[1], "Resultado de busqueda", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            casillaArreglo++;
        }
        JOptionPane.showMessageDialog(null,"ningun resultado", "Resultado de busqueda", JOptionPane.INFORMATION_MESSAGE);


    } 
    
    
    public static void main(){
        Contacto[] ListaContactos; //Arreglo en donde se guardaran los contactos
        int cantidadDeContactos = 20;
        ListaContactos = new Contacto [cantidadDeContactos];
        LeerTodosLosContactos (ListaContactos,cantidadDeContactos);
        //////////////////////////////////////////////////////////Hasta aqui lee el archivo y almacena los objetos en un arreglo
        MostrarContactos(ListaContactos);
    }   

}
   
    
        
  
    


