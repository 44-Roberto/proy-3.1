    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author AndresLima
 */
public class ArbolBinario {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH;mm;ss");
    
    public void Add(String key, String info, String pathMaster, String descPath, int keySize, String user){
        File master = new File(pathMaster);
        String[][] descriptor = getDescriptor(descPath);
        if (!Search(key, pathMaster, keySize).equals("null")) {//Si no se encuentra, retorna
            return;
        }
        String fechaMod;
        if (descriptor[1][1].equals(" ") && descriptor[2][1].equals(" ")) {//Estan vacíos?
            fechaMod = dtf.format(LocalDateTime.now()); //fecha creacion
            descriptor[1][1] = fechaMod;
            descriptor[2][1] = user; //Usuario creacion
        }
        int cantidad = Integer.parseInt(descriptor[5][1].trim());
        String data = String.join("|", "null", "null", key, info);
        String error = "";
        if (cantidad == 0) {
            LlenarArchivo(pathMaster, data, error);
            cantidad += 1;
        }else{
            ArrayList<String> lineas = getAll(pathMaster);
            String reg = lineas.get(0);
            String[] temp = reg.split("[|]");
            String keyReg = getKey(reg, keySize);
            String puntero = "";
            boolean dir = true;
            if(key.compareTo(keyReg) < 0){
                puntero = temp[0];
                dir = true;
            }else{
                puntero = temp[1];
                dir = false;
            }
            int pos = 0;            
            while(!puntero.equals("null")){
                pos = Integer.parseInt(puntero) -1;
                reg = lineas.get(pos);
                temp = reg.split("[|]");
                keyReg = getKey(reg, keySize);
                if(key.compareTo(keyReg) < 0){
                    puntero = temp[0];
                    dir = true;
                }else{
                    puntero = temp[1];
                    dir = false;
                }
            }
            cantidad += 1;
            if (dir) {            
                temp[0] = cantidad + "";
            }else{
                temp[1] = cantidad + "";
            }        
            String aux = String.join("|", temp);
            lineas.remove(pos);
            lineas.add(pos,aux);
            lineas.add(data);
            master.delete();
            try {
                master.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(SecuencialIndexado.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            for(var str : lineas){
                LlenarArchivo(pathMaster, str, error);
            }
        }
        
        fechaMod = dtf.format(LocalDateTime.now()); //fecha Modificacion
        descriptor[3][1] = fechaMod;
        descriptor[4][1] = user;
        descriptor[5][1] = cantidad + "";
        descriptor[6][1] = cantidad + "";
        setDescriptor(descriptor, descPath, 8);
        
    }
    
    public String Search(String id, String master, int keySize)
    {
        String resultado = "null";
        ArrayList<String> lineas = getAll(master);
        if (lineas.isEmpty()) {
            return resultado;
        }
        String reg = lineas.get(0);
        var temp = reg.split("[|]");
        String key = getKey(reg, keySize);
        while(id.compareTo(key) != 0){            
            int p;
            if(id.compareTo(key) < 0){
                if (temp[0].equals("null")) {
                    return "null";
                }else{
                    p = Integer.parseInt(temp[0]);
                    reg = lineas.get(p - 1);
                    temp = reg.split("[|]");
                    key = getKey(reg, keySize);
                }
            }else{
                if (temp[1].equals("null")) {
                    return "null";
                }else{
                    p = Integer.parseInt(temp[1]);
                    reg = lineas.get(p - 1);
                    temp = reg.split("[|]");
                    key = getKey(reg, keySize);
                }
            }
        }
        return reg;        
    }
    
    private String getKey(String reg, int keySize){
        var temp = reg.split("[|]");        
        String[] aux = new String[keySize];
        int c = 0;
        while(c < keySize){
            aux[c] = temp[c+2];
            c++;
        }
        String key = String.join("|", aux);
        return key;
    }
    
    public ArrayList<String> getAll(String pathMaster){
        ArrayList<String> resultado = new ArrayList<>();        
        File mast = new File(pathMaster);
        FileReader LecturaArchivo;
        BufferedReader LeerArchivo; 
        if (mast.exists()) {
            try {           
                LecturaArchivo = new FileReader(mast);
                LeerArchivo = new BufferedReader(LecturaArchivo);
                String Linea;                
                Linea=LeerArchivo.readLine();
                String[] registro;
                while(Linea != null){
                    if (!"".equals(Linea)) {                                                                        
                        if (!Linea.endsWith("1")) {
                            //Si el registro esta deshabilitado se regresa continua
                            continue;
                        }
                                                
                        resultado.add(Linea);
                    }
                    Linea=LeerArchivo.readLine();
                }
                LecturaArchivo.close();
                LeerArchivo.close();
                
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            } catch (IOException ex) {
                Logger.getLogger(ArchivoSecuencial.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        return resultado;
    }
    
    public String[][] getDescriptor(String ruta){
        String[][] fileInfo = new String[8][2];
        File file = new File(ruta);
        if (file.exists() == true) {
            try{
                FileReader LecturaArchivo = new FileReader(file);
                BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);
                for (int i = 0; i < 8; i++) {
                    String[] line = LeerArchivo.readLine().split(":");
                    fileInfo[i][0] = line[0];
                    fileInfo[i][1] = line[1];
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", WIDTH);
            }
        }
        return fileInfo;
    }
    
    private boolean LlenarArchivo(String strPath,String strContenido,String strError)
    {
        File Archivo = new File(strPath);

        try
        {
            try (FileWriter Escribir = new FileWriter(Archivo,true);
                 BufferedWriter bw = new BufferedWriter(Escribir)) {
                bw.write(strContenido+ System.getProperty( "line.separator" ));
                bw.close();
                Escribir.close();
            }
                
                return true;
        }
        catch(IOException ex)
        {
            strError= ex.getMessage();
            return false;
        } 
        
    }
    
    private void setDescriptor(String[][] descriptor, String Path, int n){
        try
        {     
            File Archivo = new File(Path);
            FileWriter Escribir = new FileWriter(Archivo);
            for (int i = 0; i < n; i++) {
                
                Escribir.write(descriptor[i][0]+":"+descriptor[i][1]+System.getProperty("line.separator" ));               
            }
                 Escribir.close();
                
                //return true;
        }
        catch(IOException ex)
        {            
            //return false;
        } 
    }
}
