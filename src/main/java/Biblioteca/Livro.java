/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author marce
 */
public class Livro {
    private int id; 
    private String titulo;
    private String autor;
    private String editora;

    public Livro() {
        this.id = 0;
        this.titulo = "";
        this.autor = "";
        this.editora = "";
    }

    public Livro(int id, String titulo, String autor, String editora) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
    
    public Livro[] carregaLivros(String fileName){
        File file = new File(fileName);
        Livro livro;
        Livro[] livros;
        Operacoes operacoes = new Operacoes();
        livros = new Livro[operacoes.contaLinhas(file)];
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
                livro=new Livro();
                livro.setId(Integer.parseInt(dados[0]));
                livro.setTitulo(dados[1]);
                livro.setAutor(dados[2]);
                livros[linhas]=livro;
                linhas++;
            }
            bf.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return livros;
    }
     
    public int listaLivros(Livro[] livros){
        int total=0;
        System.out.println("Listando os Livros");
        for (Livro livro : livros) {
            System.out.println("Id......:"+livro.getId());
            System.out.println("Título..:"+livro.getTitulo());
            System.out.println("Autor...:"+livro.getAutor());
            System.out.println("----------------");
            total++;
        }
        return total;
    }
    
    public Livro[] listaOrdenado(Livro[] livros){
        Livro livroTemp;
        String a1,a2;
        for (int i = 0; i < livros.length; i++) {
            for (int j = 0; j < livros.length-1-i; j++) {
                a1=livros[j].getTitulo().trim();
                a2=livros[j+1].getTitulo().trim();
                if(a1.charAt(0)>a2.charAt(0)){
                    livroTemp=livros[j];
                    livros[j]=livros[j+1];
                    livros[j+1]=livroTemp;
                }
            }
        }
        return livros;
    }
    
    public void listaOrdenadoAutor(Livro[] livros){
        Livro livroTemp;
        String a1,a2;
        for (int i = 0; i < livros.length; i++) {
            for (int j = 0; j < livros.length-1-i; j++) {
                a1=livros[j].getAutor().trim();
                a2=livros[j+1].getAutor().trim();
                if(a1.charAt(0)>a2.charAt(0)){
                    livroTemp=livros[j];
                    livros[j]=livros[j+1];
                    livros[j+1]=livroTemp;
                }
            }
        }
        int t = this.listaLivros(livros);
    }
    
    public void buscar(Livro[] livros, String nome){    
        for (Livro livro : livros) {
            //buscar por títulos que tenham parte daquilo que foi digitado
            if(livro.getTitulo().toLowerCase().contains(nome.toLowerCase())){
                System.out.println("Id......:"+livro.getId());
                System.out.println("Título..:"+livro.getTitulo());
                System.out.println("Autor...:"+livro.getAutor());
                System.out.println("----------------");
            }
            //buscar por autores que tenham parte daquilo que foi digitado, por exmeplo pode digitar apenas o sobre nome
            if(livro.getAutor().toLowerCase().contains(nome.toLowerCase())){
                System.out.println("Id......:"+livro.getId());
                System.out.println("Título..:"+livro.getTitulo());
                System.out.println("Autor...:"+livro.getAutor());
                System.out.println("----------------");
            }
        }
        
    }
    
    public Livro buscarPorId(Livro[] livros,int idBuscar){
        Livro l = new Livro();
        for (Livro livro : livros) {
            if(livro.getId()==idBuscar){
                id = livro.getId();
                titulo = livro.getTitulo();
                autor = livro.getAutor();
                System.out.println("Id......:"+id);
                System.out.println("Título..:"+titulo);
                System.out.println("Autor...:"+autor);
                System.out.println("----------------");
                l.setId(id);
                l.setAutor(autor);
                l.setTitulo(titulo);
                break;
            }
        }
        return l;
    }
      
}
