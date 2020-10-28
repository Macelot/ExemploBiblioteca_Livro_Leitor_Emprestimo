/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Biblioteca;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

/**
 *
 * @author marce
 */
public class Operacoes {
    
    Livro livro;
    
    
    public int contaLinhas(File file){
        System.out.println("Contando as linhas");
        int total=0;
        try {
            FileReader isr = new FileReader(file);
            BufferedReader bf = new BufferedReader(isr);
            String linha;
            linha=bf.readLine();
            int linhas=0;
            while(true){
                linha=bf.readLine();
                if(linha==null)
                    break;
                linhas++;
            }
            total=linhas;
            bf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("t"+total);
        return total;
    }
    
    public void buscarLivros(Livro[] livros){
        livro = new Livro();
        String nome;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Digite o termo (Autor ou título) desejado");
        nome = teclado.nextLine();
        
        livro.buscar(livros, nome);
        
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
    
}
