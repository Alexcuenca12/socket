/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author UsuarioF
 */
public class Cliente {

    public static void main(String[] args) {

        try {
            //Datos
            String host = "localhost";
            int puerto = 6789;
            DataInputStream entrada;
            DataOutputStream salida;
            BufferedReader teclado;

            while (true) {
                //Recibe el host y el puerto en el que se va a conectar
                Socket socketTCP = new Socket(host, puerto);
                salida = new DataOutputStream(socketTCP.getOutputStream());
                teclado = new BufferedReader(new InputStreamReader(System.in));
                //Lectura de Info
                entrada = new DataInputStream(socketTCP.getInputStream());
                System.out.println("Ingrese una palabra");
                //Entrada de datos con el teclado
                salida.writeUTF(teclado.readLine());
                String mensaje = entrada.readUTF();
               // System.out.println(salida);
                System.out.println(mensaje);

            }

        } catch (Exception e) {
            // socketTCP.close();
            System.out.println(e);

        }

    }

}
