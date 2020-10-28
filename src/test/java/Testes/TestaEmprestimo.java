/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Testes;

import Biblioteca.Emprestimo;
import org.testng.annotations.Test;

/**
 *
 * @author marce
 */
public class TestaEmprestimo {
    
    @Test
    public void testaListaEmprestimo(){
        Emprestimo emprestimo=new Emprestimo();
        Emprestimo[] emprestimos = emprestimo.carregaEmprestimos("emprestimos.txt");
        int total=8;
        assert(emprestimo.lista(emprestimos)==8);
  
    
    }
    
    
}
