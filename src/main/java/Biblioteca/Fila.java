/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

/**
 *
 * @author marce
 */
public class Fila {
    
    private int id;
    private int idLeitor;
    private int idLivro;

    public Fila() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLeitor() {
        return idLeitor;
    }

    public void setIdLeitor(int idLeitor) {
        this.idLeitor = idLeitor;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    } 
    
    public Fila[] carregaFila(String fileName){
        File file = new File(fileName);
        Fila fila;
        Fila[] filas;
        Operacoes operacoes = new Operacoes();
        filas = new Fila[operacoes.contaLinhas(file)];
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader bf = new BufferedReader(isr);
            String linha;
            linha=bf.readLine();
            int linhas=0;
            while(true){
                linha=bf.readLine();
                if(linha==null)
                    break;
                String[] dados=linha.split(";");
                fila=new Fila();
                fila.setId(Integer.parseInt(dados[0]));
                fila.setIdLeitor(Integer.parseInt(dados[1]));
                fila.setIdLivro(Integer.parseInt(dados[2]));
                filas[linhas]=fila;
                linhas++;
            }
            bf.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return filas;
    }
    
    public void lista(Fila[] fil){
        System.out.println("Listando a Fila de espera");
        if(fil.length==0){
            System.out.println("Fila vazia");
            return;
        }
        for (Fila fila : fil) {
            System.out.println("Id......:"+fila.getId());
            System.out.println("Leitor..:"+fila.getIdLeitor());
            System.out.println("Livro...:"+fila.getIdLivro());
            System.out.println("----------------");
        }
    }
    
     public void gravaFilas(String filename,Fila fila){
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file,true);
            id=fila.getId();
            idLeitor=fila.getIdLeitor();
            idLivro=fila.getIdLivro();
            fw.write("\n"+id+";"+idLeitor+";"+idLivro);
            fw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
     public int[] verificarSeTemLeitorEsperando(Fila[] filas, int IdLivro){
        int[] resp;
        int tamFila=0;
        for (Fila fila : filas) {
            if(fila.getIdLivro()==IdLivro){
                tamFila++;
            }
        } 
        resp= new int[tamFila];
        int i=0;
        for (Fila fila : filas) {
            if(fila.getIdLivro()==IdLivro){
                resp[i++]= fila.getIdLeitor();
            }
        } 
        return resp;
     }
     
     public void removeDaFila(String filename,Fila[] filas,int idLivro,int idLeitor){
         int idLeitor_=0;
         try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file);
            fw.write("id;idLeitor;idLivro");
            for (int i = 0; i < filas.length; i++) {
                System.out.println(" idLeitor "+filas[i].getIdLeitor());
                System.out.println("id "+idLeitor);
                if(filas[i].getIdLeitor()!=idLeitor){
                    id=filas[i].getId();
                    idLeitor_=filas[i].getIdLeitor();
                    idLivro=filas[i].getIdLivro();
                    fw.write("\n"+id+";"+idLeitor_+";"+idLivro);
                }
            }
            fw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
     }
}
