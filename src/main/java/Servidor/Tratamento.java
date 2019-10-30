/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import static Servidor.Servidor.controlador;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * @author aless
 */
public class Tratamento implements Runnable{
    
    public InputStream input;
    public PrintStream output;
    
    public Tratamento(InputStream input, PrintStream output){
        this.input = input;
        this.output = output;
    }
    
    @Override
    public void run(){
        Scanner s = new Scanner(input);
        String msg;
        while(s.hasNextLine()){
            msg = s.nextLine();
            if(msg.contains("criarSala")){
                String codigo = controlador.criarSala();
                output.println(codigo);
            }
            if(msg.contains("criarJogador")){
                String[] args = msg.split("/");
                String codSala = args[1];
                System.out.println(codSala);
                String nome = args[2];
                System.out.println(nome);
                int x = controlador.criarJogador(nome, codSala, output);
                output.println(x);
            }
            if(msg.contains("verJogador")){
                String[] args = msg.split("/");
                int id = Integer.valueOf(args[1]);
                output.println(controlador.verJogador(id));
            }
            if(msg.contains("avisar")){
                String[] args = msg.split("/");
                String aviso = args[1];
                String codSala = args[2];
                controlador.avisar(aviso, codSala);
            }
        }
    s.close();    
    }
}
