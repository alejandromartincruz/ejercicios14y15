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
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidores15C extends Thread{

	private Socket cliente;
	private static DataOutputStream salida;
	private static DataInputStream entrada;
	private static SimpleDateFormat sdf1;
	private static SimpleDateFormat sdf2;


	public Servidores15C(Socket cliente) throws IOException
	{
		this.cliente=cliente;

	}

	public synchronized void run(){
		servidor15.thread[servidor15.numconexiones]=servidor15.numconexiones+1;
		 int codigo=0;
		 int id = 0;
		 String[] entradavector;
		 String entradaread;

		try{
			entrada = new DataInputStream(cliente.getInputStream());
			salida = new DataOutputStream(cliente.getOutputStream());	

			if (servidor15.numconexiones==2){
				salida.writeUTF("Te jodes");
			}

			else {

				while (true){

					if (servidor15.numapuestas==2){
						System.out.println("Apuestas: "+ servidor15.apuestas2[0] + " + " + servidor15.apuestas2[1]);
						servidor15.resultado = servidor15.apuestas[0]+servidor15.apuestas[1];
						System.out.println("Resultado: " + servidor15.resultado);
						if (servidor15.resultado == servidor15.apuestas2[0]){
							salida = (DataOutputStream) servidor15.conexiones.get(0);
							salida.writeUTF("WINNER "+ servidor15.jugador[0]);
							salida = (DataOutputStream) servidor15.conexiones.get(1);
							salida.writeUTF("WINNER "+ servidor15.jugador[0]);
							System.out.println("WINNER "+ servidor15.jugador[0]);
						}

						else if (servidor15.resultado == servidor15.apuestas2[1]){
							salida = (DataOutputStream) servidor15.conexiones.get(1);
							salida.writeUTF("WINNER "+ servidor15.jugador[1]);
							salida = (DataOutputStream) servidor15.conexiones.get(0);
							salida.writeUTF("WINNER "+ servidor15.jugador[1]);
							System.out.println("WINNER "+ servidor15.jugador[1]);
						}
						else {
							salida = (DataOutputStream) servidor15.conexiones.get(0);
							salida.writeUTF("WINNER "+ "NONE");
							salida = (DataOutputStream) servidor15.conexiones.get(1);
							salida.writeUTF("WINNER "+ "NONE");
							System.out.println("WINNER " + "NONE");
						}
					}

					System.out.println("Inicio While Thread: " + servidor15.thread[id]);
					entradaread = entrada.readUTF();
					System.out.println("Recibido: " + entradaread);
					entradavector = entradaread.split(" ");
					System.out.println("Despues de leer:");

					if (entradavector[0].equals("PLAY")){
						if (servidor15.numconexiones==0){
							servidor15.conexiones.add(salida);
							id = servidor15.conexiones.size();
							servidor15.numconexiones++;
							servidor15.jugador[servidor15.numconexiones-1]=entradavector[1];
							System.out.println("ID: " + id + " Nombre: " + servidor15.jugador[servidor15.numconexiones-1]);
							String respuesta = "WAIT FOR";
							System.out.println("SENDING WAIT FOR A " + servidor15.jugador[servidor15.numconexiones-1]);
							salida.writeUTF(respuesta);
						}
						else {
						servidor15.conexiones.add(salida);
						id = servidor15.conexiones.size();
						servidor15.numconexiones++;
						servidor15.jugador[servidor15.numconexiones-1]=entradavector[1];
						System.out.println("ID: " + id + " Nombre: " + servidor15.jugador[servidor15.numconexiones-1]);
						salida = (DataOutputStream) servidor15.conexiones.get(1);
						salida.writeUTF("VERSUS "+servidor15.jugador[0]);
						System.out.println("SENDING VERSUS FOR A " + servidor15.jugador[1]);
						salida.writeUTF("WAIT BET");
						System.out.println("SENDING WAIT BET A " + servidor15.jugador[1]);
						salida = (DataOutputStream) servidor15.conexiones.get(0);
						salida.writeUTF("VERSUS "+servidor15.jugador[1]);
						System.out.println("SENDING VERSUS FOR A " + servidor15.jugador[1]);
						salida.writeUTF("YOUR BET");
						System.out.println("SENDING YOUR BET FOR A " + servidor15.jugador[0]);
						}
					}
					else if (entradavector[0].equals("MY")){
						System.out.println("Apuestas en memoria: " + servidor15.numapuestas);



						if (servidor15.numapuestas==1){
							servidor15.numapuestas++;
							servidor15.apuestas2[1]=Integer.valueOf(entradavector[3]);
							servidor15.apuestas[1]=Integer.valueOf(entradavector[2]);
							for (int j=0; j<2;j++){
								System.out.println("numapuestas 1 Soy el for "+ j);
								salida = (DataOutputStream) servidor15.conexiones.get(j); 
								System.out.println("Soy el numapuestas conex");
								salida.writeUTF("BET OF " + servidor15.jugador[id] + " DE " + entradavector[2] + " MONEDAS");
								System.out.println("SENDING BET A " + servidor15.jugador[j] + " DE " + servidor15.jugador[id-1] + " DE " + entradavector[2] + " MONEDAS");
							}
						}

						if (servidor15.numapuestas==0){

							servidor15.numapuestas++;
							servidor15.apuestas2[0]=Integer.valueOf(entradavector[3]);
							servidor15.apuestas[0]=Integer.valueOf(entradavector[2]);

							System.out.println("Soy Empieza el for:");
							for (int i=0; i<2;i++){
								System.out.println("Soy el FOR numero " + i);
								salida = (DataOutputStream) servidor15.conexiones.get(i); 
								salida.writeUTF("BET OF " + servidor15.jugador[id-1] + " DE " + entradavector[2] + " MONEDAS");
								System.out.println("SENDING BET A " + servidor15.jugador[i] + " DE " + servidor15.jugador[id-1] + " DE " + entradavector[2] + " MONEDAS");	
							}	

							salida = (DataOutputStream) servidor15.conexiones.get(1);
							salida.writeUTF("YOUR BET");
							System.out.println("SENDING YOUR BET FOR A " + servidor15.jugador[1]);

							salida = (DataOutputStream) servidor15.conexiones.get(0);
							salida.writeUTF("WAIT BET");
							System.out.println("SENDING WAIT BET A " + servidor15.jugador[0]);

						}
							}

					else if(entradavector[0].equals("BABABUI")){
						//Mensaje al thread
						String prueba = "Soy el numero " + servidor15.numconexiones + " Y estoy en la posicion "+ servidor15.conexiones.size();
						System.out.println(prueba);
						salida.writeUTF(prueba);

						//Mensaje a todos
						prueba = "Mensaje Broadcast -"+" Soy el " + id;
						Thread.sleep(10000);
						for (int i=0; i<=servidor15.numconexiones;i++){
							salida = (DataOutputStream) servidor15.conexiones.get(i);
							salida.writeUTF(prueba);
						}
					}
					System.out.println("Fin del While Thread: ");
				}
			}
		}
		catch(Exception e){}
		/*
		try {
			System.out.println("Inicio While Thread2:");
			entradaread = entrada.readUTF();
			System.out.println("Recibido: " + entradaread);
			entradavector = entradaread.split(" ");
			
			if (entradavector[0].equals("MY")){
				if (servidor15.numapuestas==1){
					servidor15.numapuestas++;
					servidor15.apuestas2[1]=Integer.valueOf(entradavector[3]);
					servidor15.apuestas[1]=Integer.valueOf(entradavector[2]);
					for (int i=0; i<2;i++){
						salida = (DataOutputStream) servidor15.conexiones.get(i); 
						salida.writeUTF("BET OF " + servidor15.jugador[id-2] + " DE " + entradavector[2] + " MONEDAS");
						System.out.println("SENDING BET A " + servidor15.jugador[i] + " DE " + servidor15.jugador[id-2] + " DE " + entradavector[2] + " MONEDAS");
					}
				}
			}
			System.out.println("Fin While Thread2:");
		}
		
		catch (Exception e){}
		*/
	}


}