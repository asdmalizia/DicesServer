/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author aless
 */
public class Sala {
    public String codigo;
    public ArrayList<Jogador> jogadores;
    public int proximo;
    
    public Sala(){
        Random r = new Random();
        codigo = Integer.toString(Math.abs(r.nextInt()), 36);
        jogadores = new ArrayList<>();
        proximo = 0;
    }
    
    public void atualizaProximo(){
        proximo = (proximo + 1) % jogadores.size();    
    }
}
