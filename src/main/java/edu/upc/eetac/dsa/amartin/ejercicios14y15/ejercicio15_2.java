/*
 * Copyright 2014 Alejandro Martin Cruz
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package edu.upc.eetac.dsa.amartin.ejercicios14y15;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ejercicio15_2 {
	public static String recibido="";
	public static String recibidovector [] = new String [10];
	public static Socket cliente;

	public static void main( String[] argumentos ) throws IOException

    {
		try{

			Socket cliente = new Socket("127.0.0.1", 5000);
			DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			salida.writeUTF("PLAY MIKI");

			while (true){
				recibido = entrada.readUTF(); 
				System.out.println("Recibido: " + recibido);

			recibidovector = recibido.split(" ");

				if (recibidovector[0].equals("WAIT")){
					if (recibidovector[1].equals("FOR")){
						System.out.println(recibido + ": Esperando al otro jugador");
					}
					else{
						System.out.println("Esperando Apuesta");
					}
				}
				else if (recibidovector[0].equals("VERSUS")){
					System.out.println("Soy: MIKI - " + "Rival: "+ recibidovector[1]);
				}
				else if (recibidovector[0].equals("YOUR")){
					salida.writeUTF("MY BET 3 3");
					System.out.println("MY BET 3 3");
				}
				else if (recibidovector[0].equals("BET")){
					System.out.println("Apuesta de " + recibidovector[2] + " de " + recibidovector[4] + " monedas");
				}
				else if (recibidovector[0].equals("WINNER")){
					System.out.println("WINNER: "+ recibidovector[1]);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
    }
}
