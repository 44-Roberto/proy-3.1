/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Funciones;

import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
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
public class SecuencialIndexado {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH;mm;ss");
    public String Search(String id, String pathInd, String pathIndDesc){
        String[][] descriptor = getDescriptorInd(pathIndDesc);
        if (Integer.parseInt(descriptor[6][1].trim()) == 0) {//Vacio?
            return "null";//Si, retornar null
        }
        int pos = Integer.parseInt(descriptor[5][1].trim()); //Posicion en el registro
        ArrayList<String> indice = getIndice(pathInd);
        while(pos != -1){            
            String temporal = indice.get(pos -1);
            if (temporal.contains(id) && temporal.endsWith("1")) { //Registro coincide y esta activo
                return temporal; //Se devuelve el valor
            }else{
                String[] reg = temporal.split("[|]");
                int p = reg.length -2;
                if (reg[p].equals("null")) { // Llego al final?
                    break; //Si, se rompe el ciclo
                }else{
                    pos = Integer.parseInt(reg[p]); //Se mueve en la lista
                }
            }
        }        
        return "null";
    }
    
    public ArrayList<String> getAll(String key, String pathInd, String pathIndDesc){
        ArrayList<String> resultado = new ArrayList<>();
        String[][] descriptor = getDescriptorInd(pathIndDesc);
        if (Integer.parseInt(descriptor[6][1]) == 0) {
            return null;
        }
        int keyLength = key.length();        
        int pos = Integer.parseInt(descriptor[5][1]);        
        ArrayList<String> indice = getIndice(pathInd);
        String registro = indice.get(pos - 1);
        String keyReg = registro.substring(6, 6 + keyLength);
        var temporal = registro.split("[|]");
        int p = temporal.length - 2;
        while(!temporal[p].equals("null")){
            if(key.equals(keyReg)){
                resultado.add(registro);
            }
            pos = Integer.parseInt(temporal[p]);
            registro = indice.get(pos - 1);
            keyReg = registro.substring(6, 6 + keyLength);
            temporal = registro.split("[|]");
        }
        if(key.equals(keyReg)){
            resultado.add(registro);
        }
        return resultado;
    }
    
    public void Add(String key, String ingreso,String pathInd, String IndDesc, String user){        
        String[][] DescriptorInd = getDescriptorInd(IndDesc); //Se genera el dec del indice
        
        String fechaMod;
        if (DescriptorInd[1][1].equals(" ") && DescriptorInd[2][1].equals(" ")) {//Estan vacíos?
            fechaMod = dtf.format(LocalDateTime.now()); //fecha creacion
            DescriptorInd[1][1] = fechaMod;
            DescriptorInd[2][1] = user; //Usuario creacion
        }               
                
        int bloque = Integer.parseInt(DescriptorInd[10][1].trim());//Numero de bloques
        int maxOrg = Integer.parseInt(DescriptorInd[9][1].trim()); //Max reorganización
        if (bloque == 0) {//Indice vacío, bloque 0?
            bloque += 1; //Aumentar cant de bloques
            createBlock("C:\\MEIA\\bloque_" + bloque + ".txt", user);//Se crea un nuevo bloque
        }
        String blockPath = "C:\\MEIA\\bloque_" + bloque + ".txt";//Ruta del bloque
        String blockDesc = "C:\\MEIA\\desc_bloque_" + bloque + ".txt";//ruta descriptor bloque
        String[][] descriptorB = getDescriptorBloque(blockDesc);
        
        String searchResult = Search(key,pathInd, IndDesc);
        if (!searchResult.equals("null")) {//La llave ya esta en el indice?
            return; //Si, no se agrega al archivo
        }
        
        int cantBloque = Integer.parseInt(descriptorB[5][1].trim());
        if (cantBloque < maxOrg) {
            addToBlock(blockPath, blockDesc, ingreso, user);
            //Añadir al indice, actualizar lista, actualizar descriptor
        }else{
            bloque += 1;           
            createBlock("C:\\MEIA\\bloque_" + bloque + ".txt", user);//Se un nuevo bloque
            DescriptorInd[10][1] = bloque + "";
            setDescriptor(DescriptorInd, IndDesc, 11);
            Add(key, ingreso,pathInd, IndDesc, user);
            
            return;            
        }
        
        String[][] descB = getDescriptorBloque(blockDesc);
        int keyLength = key.length();        
        cantBloque = Integer.parseInt(descB[5][1].trim());//Cantidad elementos en el bloque       
        int reg = Integer.parseInt(DescriptorInd[6][1].trim());
        String info, error = "";
        if (reg == 0) {
            reg += 1;
            //*******Manejar strings
            String regi=reg+"";
            String posi=bloque+"."+cantBloque;
                        
            info = String.join("|", regi,posi,key,"null","1" );
            LlenarArchivo(pathInd, info, error);
            DescriptorInd[3][1] = dtf.format(LocalDateTime.now()); //Fecha mod
            DescriptorInd[4][1] = user; //Usuario mod
            DescriptorInd[5][1] = reg + "";//Registro inicial
            DescriptorInd[6][1] = reg + ""; //Cant registros
            DescriptorInd[7][1] = reg + "";//Cantidad reg activos
            DescriptorInd[10][1] = bloque + ""; //cantidad de bloques
            setDescriptor(DescriptorInd, IndDesc, 11);
        }
        else {
            reg += 1;
            ArrayList<String> indice = getIndice(pathInd); //Todos los registros del indice
            int pos = Integer.parseInt(DescriptorInd[5][1].trim()); //Posición reg inicial
            String registro = indice.get(pos - 1);
            String keyReg = registro.substring(6, 6 + keyLength);            
            if (key.compareTo(keyReg) < 0) {//El nuevo registro es menor que el de la posición inicial
                //****Manejo strings
                String regi=reg+"";
                String posi=bloque+"."+cantBloque;
                String sig=pos+"";
                info = String.join("|",regi,posi,key,sig,"1");
                DescriptorInd[5][1] = reg + ""; //Se actualiza el descriptor 
                indice.add(info);
            }else{
                boolean flag = false;
                String[] temporal = registro.split("[|]");
                int p = temporal.length - 2;
                int posAnt = pos;
                String regAnt = registro;
                while(!temporal[p].equals("null")){
                    pos = Integer.parseInt(temporal[p]);
                    registro = indice.get(pos - 1);
                    temporal = registro.split("[|]");
                    keyReg = registro.substring(6, 6 + keyLength);
                    if (key.compareTo(keyReg) < 0){//El registro es menor que el registro actual
                        String[] aux = regAnt.split("[|]");
                        int posT = Integer.parseInt(aux[p]);
                        aux[p] = reg + "";
                        String algo = String.join("|", aux);
                        indice.remove(posAnt - 1);
                        indice.add(posAnt - 1, algo);
                        //*******Manejar string
                        String regi=reg+"";
                        String posi=bloque+"."+cantBloque;
                        String sig=posT+"";
                        info = String.join("|",regi,posi,key,sig,"1");
                        indice.add(info); 
                        flag = true;
                        break;
                    }else{
                        posAnt = pos;
                        regAnt = registro;
                    }
                }
                if (temporal[p].equals("null") && flag == false) {
                    temporal[p] = reg + "";
                    String aux = String.join("|", temporal);
                    indice.remove(posAnt - 1);
                    indice.add(posAnt - 1, aux);
                    //**Separo por strings
                    String regi=reg+"";
                    String posi=bloque+"."+cantBloque;
                    info = String.join("|",regi,posi,key,"null","1");
                    indice.add(info);
                }
            }
            File ind = new File(pathInd);
            ind.delete();
            try {
                ind.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(SecuencialIndexado.class.getName()).log(Level.SEVERE, null, ex);
            }
            for(var s : indice){
                LlenarArchivo(pathInd, s, error);
            }
            DescriptorInd[3][1] = dtf.format(LocalDateTime.now()); //Fecha mod
            DescriptorInd[4][1] = user; //Usuario mod
            DescriptorInd[6][1] = reg + ""; //Cant registros
            DescriptorInd[7][1] = reg + "";//Cantidad reg activos
            DescriptorInd[10][1] = bloque + "";
            setDescriptor(DescriptorInd, IndDesc, 11);
        }        
        
        
    }
    
    private String[][] getDescriptorInd(String ruta){
        String[][] fileInfo = new String[11][2];
        File file = new File(ruta);
        if (file.exists() == true) {
            try{
                FileReader LecturaArchivo = new FileReader(file);
                BufferedReader LeerArchivo = new BufferedReader(LecturaArchivo);
                for (int i = 0; i < 11; i++) {
                    String[] line = LeerArchivo.readLine().split(":");
                    fileInfo[i][0] = line[0];
                    fileInfo[i][1] = line[1];
                }
                LecturaArchivo.close();
                LeerArchivo.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", WIDTH);
            }
        }
        return fileInfo;
    }
    
    private String[][] getDescriptorBloque(String ruta){
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
                LecturaArchivo.close();
                LeerArchivo.close();
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", WIDTH);
            }
        }
        return fileInfo;
    }
    
    private ArrayList<String> getIndice(String pathInd){
        ArrayList<String> resultado = new ArrayList<>();
        File indice = new File(pathInd);
        FileReader LecturaArchivo;
        BufferedReader LeerArchivo;
        if (indice.exists()) {
            try {
                LecturaArchivo = new FileReader(indice);
                LeerArchivo = new BufferedReader(LecturaArchivo);
                String Linea;
                Linea=LeerArchivo.readLine();
                while(Linea != null){
                    if(Linea.endsWith("1")){
                        resultado.add(Linea);
                    }
                    Linea=LeerArchivo.readLine();
                }
                LecturaArchivo.close();
                LeerArchivo.close();
            } catch (IOException ex) {
                Logger.getLogger(SecuencialIndexado.class.getName()).log(Level.SEVERE, null, ex);                
            }
        }        
        return resultado;
    }
    
    private void createBlock(String path, String user){
        File block = new File(path);
        try {
            block.createNewFile();
            String descName = "desc_" + block.getName();
            String descPath = "C:\\MEIA\\" + descName;
            File blockDesc = new File(descPath);
            blockDesc.createNewFile();
            String fechaMod = dtf.format(LocalDateTime.now());
            String descInfo = "nombre_simbolico: " + block.getName() + System.getProperty( "line.separator" )
                    + "fecha_creacion: " + fechaMod +System.getProperty( "line.separator" )
                    + "usuario_creacion: " + user + System.getProperty( "line.separator" )
                    + "fecha_modificación: " + System.getProperty( "line.separator" )
                    + "usuario_modificación: " + System.getProperty( "line.separator" )                    
                    + "#_registros: 0" + System.getProperty( "line.separator" )
                    + "registros_activos: 0" + System.getProperty( "line.separator" )
                    + "registros_inactivos: 0";
            FileWriter Escribir = new FileWriter(descPath,true);;
            BufferedWriter bw = new BufferedWriter(Escribir);
            bw.write(descInfo);
            bw.close();
            Escribir.close();
        } catch (IOException ex) {
            Logger.getLogger(SecuencialIndexado.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    private void addToBlock(String path, String pathDesc, String info, String user){
        String[][] descriptor = getDescriptorBloque(pathDesc);        
        String error = "";
        int cantidad = Integer.parseInt(descriptor[5][1].trim()) + 1;
        int regAct = Integer.parseInt(descriptor[6][1].trim()) + 1;
        descriptor[3][1] = dtf.format(LocalDateTime.now());
        descriptor[4][1] = user;
        descriptor[5][1] = cantidad + "";
        descriptor[6][1] = regAct + "";
        LlenarArchivo(path, info, error);
        setDescriptor(descriptor, pathDesc, 8);
    }
    
    private boolean LlenarArchivo(String strPath,String strContenido,String strError)
    {
        File Archivo = new File(strPath);

        try//********************************************************************************************************************************************************************
        {//FileWriter Escribir = new FileWriter(Archivo,false);//*************************************************************
            try (FileWriter Escribir = new FileWriter(Archivo,true); //*************************************************************
                 BufferedWriter bw = new BufferedWriter(Escribir)) {//*************************************************************
                bw.write(strContenido+ System.getProperty( "line.separator" ));//*************************************************************
                bw.close();//*************************************************************
                Escribir.close();//*************************************************************
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

