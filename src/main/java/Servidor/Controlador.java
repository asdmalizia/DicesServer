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
    public ArrayList<Jogador> Jogadores;
    
    public Controlador(){
        Salas = new ArrayList<>();
        Jogadores = new ArrayList<>();
    }
    
    public String criarSala(){
        Sala s = new Sala();
        Salas.add(s);
        return s.codigo;
    }
    
    public int criarJogador(String nome, String codSala, PrintStream ps){
        Jogador j = new Jogador(nome, codSala, ps);
        for(Sala sala : Salas){
            if(sala.codigo.equals(codSala)){
                sala.jogadores.add(j);
                break;
            }
        }
        Jogadores.add(j);
        return j.id;
    }
    
    public String verJogador(int id){
        for(Jogador j : Jogadores){
            if (j.id == id){
                return j.nome + "/" + j.codSala;
            }
        }
        return "Erro";
    }
    
    public void avisar(String aviso, String codSala){
        for (Sala sala : Salas){
            if(sala.codigo.equals(codSala)){
                sala.jogadores.forEach((j) -> {
                    j.ps.println(aviso);
                });
            }
        }
    }
    
    public void iniciar(String codSala){
        avisar("Jogo iniciado", codSala);
        Salas.stream().filter((sala) -> (sala.codigo.equals(codSala))).forEachOrdered((sala) -> {
            avisar("proximo/" + Integer.toString(sala.jogadores.get(sala.proximo).id), codSala);
        });
    }
    
    public Boolean jogar(int id, String codSala){
        for(Sala sala : Salas){
            if(sala.codigo.equals(codSala)){
                for(Jogador j : sala.jogadores){
                    if(j.id == id && id == sala.jogadores.get(sala.proximo).id){
                        j.jogar();
                        String msg = "";
                        for(int dado : j.resultado){
                            msg = msg + Integer.toString(dado) + ",";
                        }
                        msg = msg.substring(0, msg.length() - 1);
                        avisar(msg, codSala);
                        sala.atualizaProximo();
                        if(sala.proximo == 0){ avisar("vencedor/" + Integer.toString(sala.vencedor()), codSala);}
                        else{ avisar("proximo/" + Integer.toString(sala.jogadores.get(sala.proximo).id), codSala);}
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public int verPontos(int id){
        int pontuacao = 0;
        for(Jogador j : Jogadores){
            if (j.id == id){
                for(int i = 0; i < j.resultado.length; i++){
                    pontuacao += j.resultado[i];
                }
            }
        }
        return pontuacao;
    }
}
