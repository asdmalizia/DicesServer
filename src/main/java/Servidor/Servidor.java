/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author aless
 */
public class Servidor {
    
    public static Controlador controlador;
    public int porta;
    
    public Servidor(){
        porta = 1406;
        controlador = new Controlador();
    }
    
    public static void main(String[] args) {
        Servidor servidor = new Servidor();
        try {
            ServerSocket socketServidor = new ServerSocket(servidor.porta);
            System.out.println("Porta " + servidor.porta + " aberta!"); 

            while(true){
                Socket cliente = socketServidor.accept();
                System.out.println("Nova conexão com o cliente " + cliente.getInetAddress().getHostAddress());
                
                PrintStream output = new PrintStream(cliente.getOutputStream());
                InputStream input = cliente.getInputStream();
                Tratamento tratamento = new Tratamento(input, output);
                new Thread(tratamento).start();
            }
        } catch (IOException ex) {
            System.out.println("Erro carteado: " + ex.getMessage());
        }
    }
    
    
}
