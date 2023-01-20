/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author UsuarioF
 */
public class Servidor {

    public static void main(String[] args) {

        try {

            HashMap<String, String> map = new HashMap<String, String>();
            map.put("Java", "Lenguaje de programacion");
            map.put("C++", "Lenguaje de programacion");
            map.put("Carro", "Vehiculo que se usa para movilizarce en tierra");
            map.put("CPU", "Unidad Central de Procesamiento");
            map.put("Teclado", "Parte de un computador");
            map.put("String", "Tipo de dato no numerico ");
            map.put("Int", "Tipo de dato numerico usado en enteros");
            map.put("Ascii", "Estandares de caracteres");
            map.put("HTML", "HyperText Markup Language");
            map.put("MVC", "Modelo vista controlador");

            System.out.println("Esperando Conexion");
            //Variables
            int puerto = 6789;
            Socket client_conex;
            DataOutputStream salida;
            DataInputStream entrada;
            ServerSocket socketTCP = new ServerSocket(puerto);

            //Server Socket ---->Dos parametros Puerto-Backlog(Cuantas conexiones permite el servidor) 
            while (true) {
                client_conex = socketTCP.accept();
                salida = new DataOutputStream(client_conex.getOutputStream());
                // salida.writeUTF("Solicitud Aceptada!!!");
                System.out.println("Cliente" + client_conex.getInetAddress().getHostName());
                entrada = new DataInputStream(client_conex.getInputStream());
                String valor = entrada.readUTF();
                boolean bandera1 = false;
                for (HashMap.Entry<String, String> entry : map.entrySet()) {
                    if (valor.equals("") || valor.matches("[0-9]*")) {
                        String mensaje = "Por favor digite un valor o no digite un valor numerico";
                        salida.writeUTF(mensaje);
                    } else {
                        if (entry.getKey().equalsIgnoreCase(valor)) {
                            String mensaje = "Significado : " + entry.getValue();
                            salida.writeUTF(mensaje);
                            bandera1 = true;
                        }

                    }
                }
                if (bandera1 == false) {
                    String notFound = "No existe la palabra";
                    salida.writeUTF(notFound);
                }

            }

        } catch (Exception e) {
            System.out.println(e);

        }

    }

}
