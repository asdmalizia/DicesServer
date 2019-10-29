/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import java.io.PrintStream;
import java.util.Random;

/**
 *
 * @author aless
 */
public class Jogador {
    public String nome;
    public int id;
    public int[] resultado;
    public PrintStream ps;
    
    public Jogador(String nome, PrintStream ps){
        this.nome = nome;
        this.ps = ps;
        resultado = new int[5];
        id = new Random().nextInt(100);
    }
    
    
}
