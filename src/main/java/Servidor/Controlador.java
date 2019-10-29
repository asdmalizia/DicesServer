/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Jogo.Jogador;
import Jogo.Sala;
import java.io.PrintStream;
import java.util.ArrayList;

/**
 *
 * @author aless
 */
public class Controlador {    
   
    public ArrayList<Sala> Salas;
    
    public Controlador(){
        Salas = new ArrayList<>();
    }
    
    public String criarSala(){
        Sala s = new Sala();
        Salas.add(s);
        return s.codigo;
    }
    
    public int criarJogador(String nome, String codSala, PrintStream ps){
        Jogador j = new Jogador(nome, ps);
        for(Sala sala : Salas){
            if(sala.codigo.equals(codSala)){
                sala.jogadores.add(j);
                break;
            }
        }
        return j.id;
    }
    
}
