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
import java.util.Scanner;

/**
 *
 * @author marce
 */
public class Emprestimo {
    
    private int id;
    private int idLeitor;
    private int idLivro;
    private int status;//1=emprestado 0=devolvido

    public Emprestimo() {
        this.id = 0;
        this.idLeitor = 0;
        this.idLivro = 0;
        
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
    public int lista(Emprestimo[] emps){
        int total=0;
        System.out.println("Listando os Empréstimos");
        for (Emprestimo emprestimo : emps) {
            total++;
            //if(emprestimo.getStatus()==1){
                System.out.println("Id......:"+emprestimo.getId());
                System.out.println("Leitor..:"+emprestimo.getIdLeitor());
                System.out.println("Livro...:"+emprestimo.getIdLivro());
                System.out.println("----------------");
            //} 
        }
        return total;
    }
    
    public Emprestimo buscarLivroEmprestado(Emprestimo[] emps, int idLivro){
        Emprestimo emprestimo=new Emprestimo();
        for (Emprestimo e : emps) {
            if(e.getStatus()==1){
                if(e.getIdLivro()==idLivro){
                    emprestimo.setId(e.getId());
                    emprestimo.setIdLeitor(e.getIdLeitor());
                    emprestimo.setIdLivro(e.getIdLivro());

                }
            }
 
        }
        
        return emprestimo;
        
    }
    
    public Emprestimo[] carregaEmprestimos(String fileName){
        File file = new File(fileName);
        Emprestimo emprestimo;
        Emprestimo[] emprestimos;
        Operacoes operacoes = new Operacoes();
        emprestimos = new Emprestimo[operacoes.contaLinhas(file)];
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
                emprestimo=new Emprestimo();
                emprestimo.setId(Integer.parseInt(dados[0]));
                emprestimo.setIdLeitor(Integer.parseInt(dados[1]));
                emprestimo.setIdLivro(Integer.parseInt(dados[2]));
                emprestimo.setStatus(Integer.parseInt(dados[3]));
                emprestimos[linhas]=emprestimo;
                linhas++;
            }
            bf.close();
        }  catch (Exception e) {
            e.printStackTrace();
        }
        return emprestimos;
    }
    
    public Emprestimo buscarEmprestimoPorIdDeLivro(Emprestimo[] emprestimos,int idBuscar){
        Emprestimo emp = new Emprestimo();
        for (Emprestimo emprestimo : emprestimos) {    
            if(emprestimo.getStatus()==1){//apenas os emprestados serão considerados
                if(emprestimo.getIdLivro()==idBuscar){ //aqui é idLivro pois vamos verificar se o livro se encontra emprestado
                    id = emprestimo.getId();
                    idLeitor = emprestimo.getIdLeitor();
                    idLivro = emprestimo.getIdLivro();
                    status = emprestimo.getStatus();
                    emp.setId(id);
                    emp.setIdLeitor(idLeitor);
                    emp.setIdLivro(idLivro);
                    emp.setStatus(status);
                    break;
                }
            }    
        }
        return emp;
    }
    
    public Emprestimo[] adicionaEmprestimo(Emprestimo[] emps, Emprestimo emp){
        Emprestimo[] emprestimos = new Emprestimo[emps.length+1];
        for (int i = 0; i < emps.length; i++) {
            emprestimos[i]=emps[i];
            System.out.println("ee "+emprestimos[i].getId());
        }
        emprestimos[emps.length]=emp; 
        //gravar no arquivo
        this.gravaEmprestimos("emprestimos.txt", emp);//vamos ter tudo no arquivo
        return emprestimos;//temos tudo no novo array
    }
    
    public void gravaEmprestimos(String filename,Emprestimo emprestimo){
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file,true);
            id=emprestimo.getId();
            idLeitor=emprestimo.getIdLeitor();
            idLivro=emprestimo.getIdLivro();
            status=emprestimo.getStatus();    
            fw.write("\n"+id+";"+idLeitor+";"+idLivro+";"+status);
            fw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void atualizaEmprestimos(String filename,Emprestimo[] emprestimos, Emprestimo emp){
        try {
            File file = new File(filename);
            FileWriter fw = new FileWriter(file);
            fw.write("id;idLeitor;idLivro;status");
            for (int i = 0; i < emprestimos.length; i++) {
                id=emprestimos[i].getId();
                idLeitor=emprestimos[i].getIdLeitor();
                idLivro=emprestimos[i].getIdLivro();
                if(emprestimos[i].getId()!=emp.getId()){
                    status=emprestimos[i].getStatus();
                }else{
                    status=0;
                }
                fw.write("\n"+id+";"+idLeitor+";"+idLivro+";"+status);
            }
            fw.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
            
    public void realizarEmprestimo(Emprestimo[] emprestimos, Livro[] livros, Leitor[] leitores,Fila[] filas){
        int idLeitor,idLivro;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Digite o id do livro");
        idLivro = teclado.nextInt();
        System.out.println("Digite o id do leitor");
        idLeitor = teclado.nextInt();
        
        //verificar se o livro realmente existe
        Livro livro = new Livro();
        livro = livro.buscarPorId(livros, idLivro);
        
        //verificar se o leitor realmente existe
        Leitor leitor = new Leitor();
        leitor = leitor.buscarPorId(leitores, idLeitor);
        
        if(livro.getId()==0){
            System.out.println("Livro não existente");
            return;
        }
        if(leitor.getId()==0){
            System.out.println("Leitor não existente");
            return;
        }
        
        //verificar se o livro esta disponivel
        Emprestimo empTeste;
        empTeste = new Emprestimo();
        empTeste = empTeste.buscarEmprestimoPorIdDeLivro(emprestimos, idLivro);
        
        if(empTeste.getIdLeitor()!=0){
            System.out.println("Livro emprestado");
            System.out.println("Deseja entrar na fila de espera? (s/n)");
            //caso nao estiver disponivel, ler a fila de espera 
            //adicionar na lista de espera
            teclado = new Scanner(System.in);
            String confirma;
            confirma = teclado.nextLine();
            if(confirma.equals("s")){
                Fila fi = new Fila();
                fi.setId(filas.length+1);
                fi.setIdLeitor(idLeitor);
                fi.setIdLivro(idLivro);
                fi.gravaFilas("fila.txt", fi);
            }
            
            
        }else{
            //teclado.close();
            System.out.println("Confirma Empréstimo? (s/n)");
            teclado = new Scanner(System.in);
            String confirma;
            confirma = teclado.nextLine();
            if(confirma.equals("s")){
                //adicionar na lista de emprestimos
                System.out.println("Empréstimo confirmado");
                Emprestimo emp = new Emprestimo();
                emp.setId(emprestimos.length+1);
                emp.setIdLeitor(idLeitor);
                emp.setIdLivro(idLivro);
                emp.setStatus(1);
                //adicionaEmprestimo(emprestimos,emp);
                gravaEmprestimos("emprestimos.txt", emp);//vamos ter tudo no arquivo
            }
            //teclado.close();
        }

    }
    
    public void devolverLivro(Emprestimo[] emprestimos, Livro[] livros, Leitor[] leitores,Fila[] filas){
        int idLeitor,idLivro;
        String opcao;
        Scanner teclado = new Scanner(System.in);
        System.out.println("Digite o id do livro");
        idLivro = teclado.nextInt();
        
        //verificar se o livro realmente esta emprestado
        Emprestimo emprestimo = new Emprestimo();
        emprestimo = buscarLivroEmprestado(emprestimos,idLivro);
        
        if(emprestimo.getIdLivro()==0){
            System.out.println("Livro não está emprestado"); 
            return;//sai do método devolverLivro
        }
        teclado = new Scanner(System.in);
        
        System.out.println("Confirma Devolução? (s/n)");
        teclado = new Scanner(System.in);
        String confirma;
        confirma = teclado.nextLine();
        if(confirma.equals("s")){
            //setar status 0 no empréstimo
            atualizaEmprestimos("emprestimos.txt", emprestimos, emprestimo);
            System.out.println("Livro Devolvido");
        }
        
        //verificar se esta na fila e fazer alerta
        Fila fila= new Fila();
        int[] tem = fila.verificarSeTemLeitorEsperando(filas,idLivro);
        if(tem.length>0){
            System.out.println("Temos "+tem.length+" na fila");
            for (int i = 0; i < tem.length; i++) {
                Leitor l=new Leitor();
                l=l.buscarPorId(leitores, tem[i]);
                System.out.println("O leitor "+l.getNome()+" está na fila");
            }
            //tirar da fila de espera se for o caso 
            fila.removeDaFila("fila.txt",filas,idLivro,tem[0]);
            
            //realizar emprestimo já sabendo idLivro e idLeitor
            emprestimo.realizarEmprestimo(emprestimos,livros,leitores,filas); 
            emprestimos=emprestimo.carregaEmprestimos("emprestimos.txt");
            //filas=fila.carregaFila("fila.txt");
        }
        
    }
    
}
