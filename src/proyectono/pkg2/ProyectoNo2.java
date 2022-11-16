/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package proyectono.pkg2;
import Formularios.*;
import java.io.*;

/**
 *
 * @author Roberto Moya
 */
public class ProyectoNo2 {

    public static void main(String[] args) {
        //Desplegar el formulario de ingreso, se válida la contraseña
        FileWriter Escribir;
        BufferedWriter bw;
        //Verifico usuario.txt
         try {
      File archivo = new File("C:\\MEIA\\usuario.txt");
      if (archivo.createNewFile()) {
        System.out.println("Archivo creado: " + archivo.getName());
      } else {
        System.out.println("Este archivo ya existe.");
      }
    } catch (IOException e) {
      System.out.println("Error.");
      e.printStackTrace();
    }
         //Verifico des_usuario.txt
         try {
      File archivo2 = new File("C:\\MEIA\\desc_usuario.txt");
      if (archivo2.createNewFile()) {
        String info = "nombre_simbolico: usuario.txt" + System.getProperty( "line.separator" )
                    + "fecha_creacion: " + System.getProperty( "line.separator" )
                    + "usuario_creacion: " + System.getProperty( "line.separator" )
                    + "fecha_modificación: " + System.getProperty( "line.separator" )
                    + "usuario_modificación: " + System.getProperty( "line.separator" )
                    + "#_registros: 0" + System.getProperty( "line.separator" )
                    + "registros_activos: 0" + System.getProperty( "line.separator" )
                    + "registros_inactivos: 0" + System.getProperty( "line.separator" )
                    + "Max. para reorganizar: 3";
        
        Escribir = new FileWriter(archivo2,true);
        bw = new BufferedWriter(Escribir);
        bw.write(info);
        bw.close();
        
        System.out.println("Archivo creado: " + archivo2.getName());
      } else {
        System.out.println("Este archivo ya existe.");
      }
    } catch (IOException e) {
      System.out.println("Error.");
      e.printStackTrace();
    }
           //Verifico bitacora_backup.txt
         try {
      File archivo3 = new File("C:\\MEIA\\bitacora_backup.txt");
      if (archivo3.createNewFile()) {
        System.out.println("Archivo creado: " + archivo3.getName());
        
      } else {
        System.out.println("Este archivo ya existe.");
      }
    } catch (IOException e) {
      System.out.println("Error.");
      e.printStackTrace();
    }
           //Verifico desc_bitacora_backup.txt
         try {
      File archivo4 = new File("C:\\MEIA\\desc_bitacora_backup.txt");
      if (archivo4.createNewFile()) {
        System.out.println("Archivo creado: " + archivo4.getName());
        
      } else {
        System.out.println("Este archivo ya existe.");
      }
    } catch (IOException e) {
      System.out.println("Error.");
      e.printStackTrace();
    }
     
          //Verifico bitacora_usuario.txt
         try {
      File archivo5 = new File("C:\\MEIA\\bitacora_usuario.txt");
      if (archivo5.createNewFile()) {
        System.out.println("Archivo creado: " + archivo5.getName());       
        
      } else {
        System.out.println("Este archivo ya existe.");
      }
    } catch (IOException e) {
      System.out.println("Error.");
      e.printStackTrace();
    }
         
            //Verifico bitacora_usuario.txt
         try {
      File archivo6 = new File("C:\\MEIA\\desc_bitacora_usuario.txt");
      if (archivo6.createNewFile()) {
        System.out.println("Archivo creado: " + archivo6.getName());
        String info = "nombre_simbolico: bitacora_usuario.txt" + System.getProperty( "line.separator" )
                    + "fecha_creacion: " + System.getProperty( "line.separator" )
                    + "usuario_creacion: " + System.getProperty( "line.separator" )
                    + "fecha_modificación: " + System.getProperty( "line.separator" )
                    + "usuario_modificación: " + System.getProperty( "line.separator" )
                    + "#_registros: 0" + System.getProperty( "line.separator" )
                    + "registros_activos: 0" + System.getProperty( "line.separator" )
                    + "registros_inactivos: 0" + System.getProperty( "line.separator" )
                    + "Max. para reorganizar: 3";
        Escribir = new FileWriter(archivo6,true);
        bw = new BufferedWriter(Escribir);
        bw.write(info);
        bw.close();
      } else {
        System.out.println("Este archivo ya existe.");
      }
    } catch (IOException e) {
      System.out.println("Error.");
      e.printStackTrace();
    }
         //**********
         
          //*********** Crear archivos del secuencial canciones
         
         try {
      File archivoC1 = new File("C:\\MEIA\\canciones.txt");
      if (archivoC1.createNewFile()) {
        System.out.println("Archivo creado: " + archivoC1.getName());
      } else {
        System.out.println("Este archivo ya existe.");
      }
    } catch (IOException e) {
      System.out.println("Error.");
      e.printStackTrace();
    }
         
         try {
      File archivoC2 = new File("C:\\MEIA\\desc_canciones.txt");
      if (archivoC2.createNewFile()) {
        String info = "nombre_simbolico: canciones.txt" + System.getProperty( "line.separator" )
                    + "fecha_creacion: " + System.getProperty( "line.separator" )
                    + "usuario_creacion: " + System.getProperty( "line.separator" )
                    + "fecha_modificación: " + System.getProperty( "line.separator" )
                    + "usuario_modificación: " + System.getProperty( "line.separator" )
                    + "#_registros: 0" + System.getProperty( "line.separator" )
                    + "registros_activos: 0" + System.getProperty( "line.separator" )
                    + "registros_inactivos: 0" + System.getProperty( "line.separator" )
                    + "Max. para reorganizar: 3";
        
        Escribir = new FileWriter(archivoC2,true);
        bw = new BufferedWriter(Escribir);
        bw.write(info);
        bw.close();
        
        System.out.println("Archivo creado: " + archivoC2.getName());
      } else {
        System.out.println("Este archivo ya existe.");
      }
    } catch (IOException e) {
      System.out.println("Error.");
      e.printStackTrace();
    }
         try {
      File archivoC5 = new File("C:\\MEIA\\bitacora_canciones.txt");
      if (archivoC5.createNewFile()) {
        System.out.println("Archivo creado: " + archivoC5.getName());       
        
      } else {
        System.out.println("Este archivo ya existe.");
      }
    } catch (IOException e) {
      System.out.println("Error.");
      e.printStackTrace();
    }
         try {
      File archivoC6 = new File("C:\\MEIA\\desc_bitacora_canciones.txt");
      if (archivoC6.createNewFile()) {
        System.out.println("Archivo creado: " + archivoC6.getName());
        String info = "nombre_simbolico: bitacora_canciones.txt" + System.getProperty( "line.separator" )
                    + "fecha_creacion: " + System.getProperty( "line.separator" )
                    + "usuario_creacion: " + System.getProperty( "line.separator" )
                    + "fecha_modificación: " + System.getProperty( "line.separator" )
                    + "usuario_modificación: " + System.getProperty( "line.separator" )
                    + "#_registros: 0" + System.getProperty( "line.separator" )
                    + "registros_activos: 0" + System.getProperty( "line.separator" )
                    + "registros_inactivos: 0" + System.getProperty( "line.separator" )
                    + "Max. para reorganizar: 3";
        Escribir = new FileWriter(archivoC6,true);
        bw = new BufferedWriter(Escribir);
        bw.write(info);
        bw.close();
        Escribir.close();
      } else {
        System.out.println("Este archivo ya existe.");
      }
    } catch (IOException e) {
      System.out.println("Error.");
      e.printStackTrace();
    }
         
         //***********
         
         
         //*********** Crear archivos del secuencial listas_canciones
         
         try {
      File archivoLC1 = new File("C:\\MEIA\\listas_canciones.txt");
      if (archivoLC1.createNewFile()) {
        System.out.println("Archivo creado: " + archivoLC1.getName());
      } else {
        System.out.println("Este archivo ya existe.");
      }
    } catch (IOException e) {
      System.out.println("Error.");
      e.printStackTrace();
    }
         
         try {
      File archivoLC2 = new File("C:\\MEIA\\desc_listas_canciones.txt");
      if (archivoLC2.createNewFile()) {
        String info = "nombre_simbolico: canciones.txt" + System.getProperty( "line.separator" )
                    + "fecha_creacion: " + System.getProperty( "line.separator" )
                    + "usuario_creacion: " + System.getProperty( "line.separator" )
                    + "fecha_modificación: " + System.getProperty( "line.separator" )
                    + "usuario_modificación: " + System.getProperty( "line.separator" )
                    + "#_registros: 0" + System.getProperty( "line.separator" )
                    + "registros_activos: 0" + System.getProperty( "line.separator" )
                    + "registros_inactivos: 0" + System.getProperty( "line.separator" )
                    + "Max. para reorganizar: 3";
        
        Escribir = new FileWriter(archivoLC2,true);
        bw = new BufferedWriter(Escribir);
        bw.write(info);
        bw.close();
        
        System.out.println("Archivo creado: " + archivoLC2.getName());
      } else {
        System.out.println("Este archivo ya existe.");
      }
    } catch (IOException e) {
      System.out.println("Error.");
      e.printStackTrace();
    }
         try {
      File archivoLC5 = new File("C:\\MEIA\\bitacora_listas_canciones.txt");
      if (archivoLC5.createNewFile()) {
        System.out.println("Archivo creado: " + archivoLC5.getName());       
        
      } else {
        System.out.println("Este archivo ya existe.");
      }
    } catch (IOException e) {
      System.out.println("Error.");
      e.printStackTrace();
    }
         try {
      File archivoLC6 = new File("C:\\MEIA\\desc_bitacora_listas_canciones.txt");
      if (archivoLC6.createNewFile()) {
        System.out.println("Archivo creado: " + archivoLC6.getName());
        String info = "nombre_simbolico: bitacora_canciones.txt" + System.getProperty( "line.separator" )
                    + "fecha_creacion: " + System.getProperty( "line.separator" )
                    + "usuario_creacion: " + System.getProperty( "line.separator" )
                    + "fecha_modificación: " + System.getProperty( "line.separator" )
                    + "usuario_modificación: " + System.getProperty( "line.separator" )
                    + "#_registros: 0" + System.getProperty( "line.separator" )
                    + "registros_activos: 0" + System.getProperty( "line.separator" )
                    + "registros_inactivos: 0" + System.getProperty( "line.separator" )
                    + "Max. para reorganizar: 3";
        Escribir = new FileWriter(archivoLC6,true);
        bw = new BufferedWriter(Escribir);
        bw.write(info);
        bw.close();
        Escribir.close();
      } else {
        System.out.println("Este archivo ya existe.");
      }
    } catch (IOException e) {
      System.out.println("Error.");
      e.printStackTrace();
    }
         
         //*********** ARBOL
          try {
    File indice = new File("C:\\MEIA\\arbol_binario.txt");
    if (indice.createNewFile()) {
        System.out.println("Archivo creado: " + indice.getName());              
        } else {
            System.out.println("Este archivo ya existe.");
        }
    } catch (IOException e) {
        System.out.println("Error.");
        e.printStackTrace();
    }            
          
          
      try {
    File desc_indice = new File("C:\\MEIA\\desc_arbol_binario.txt");
    if (desc_indice.createNewFile()) {
        System.out.println("Archivo creado: " + desc_indice.getName());
        String info = "nombre_simbolico: arbol_binario.txt" + System.getProperty( "line.separator" )
                    + "fecha_creacion: " + System.getProperty( "line.separator" )
                    + "usuario_creacion: " + System.getProperty( "line.separator" )
                    + "fecha_modificación: " + System.getProperty( "line.separator" )
                    + "usuario_modificación: " + System.getProperty( "line.separator" )                   
                    + "#_registros: 0" + System.getProperty( "line.separator" )
                    + "registros_activos: 0" + System.getProperty( "line.separator" )
                    + "registros_inactivos: 0" + System.getProperty( "line.separator" );
                    
                    
        Escribir = new FileWriter(desc_indice,true);
        bw = new BufferedWriter(Escribir);
        bw.write(info);
        bw.close();
        Escribir.close();
    } else {
        System.out.println("Este archivo ya existe.");
    }
    } catch (IOException e) {
        System.out.println("Error.");
        e.printStackTrace();
    }                    
         //**********
         
         
       try {
    File indice = new File("C:\\MEIA\\indice.txt");
    if (indice.createNewFile()) {
        System.out.println("Archivo creado: " + indice.getName());              
        } else {
            System.out.println("Este archivo ya existe.");
        }
    } catch (IOException e) {
        System.out.println("Error.");
        e.printStackTrace();
    }            
    
    try {
    File desc_indice = new File("C:\\MEIA\\desc_indice.txt");
    if (desc_indice.createNewFile()) {
        System.out.println("Archivo creado: " + desc_indice.getName());
        String info = "nombre_simbolico: indice.txt" + System.getProperty( "line.separator" )
                    + "fecha_creacion: " + System.getProperty( "line.separator" )
                    + "usuario_creacion: " + System.getProperty( "line.separator" )
                    + "fecha_modificación: " + System.getProperty( "line.separator" )
                    + "usuario_modificación: " + System.getProperty( "line.separator" )
                    + "reg_inicial: " + System.getProperty( "line.separator" )
                    + "#_registros: 0" + System.getProperty( "line.separator" )
                    + "registros_activos: 0" + System.getProperty( "line.separator" )
                    + "registros_inactivos: 0" + System.getProperty( "line.separator" )
                    + "Max. para reorganizar: 3" + System.getProperty( "line.separator" )
                    + "num_bloques: 0";
        Escribir = new FileWriter(desc_indice,true);
        bw = new BufferedWriter(Escribir);
        bw.write(info);
        bw.close();
    } else {
        System.out.println("Este archivo ya existe.");
    }
    } catch (IOException e) {
        System.out.println("Error.");
        e.printStackTrace();
    }               
        Login f1 = new Login();
        f1.setVisible(true);
    }
}
