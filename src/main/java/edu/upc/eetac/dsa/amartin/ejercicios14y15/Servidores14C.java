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
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Servidores14C extends Thread{

	private Socket cliente;
	private static DataOutputStream salida;
	private static DataInputStream entrada;
	private static SimpleDateFormat sdf1;
	private static SimpleDateFormat sdf2;
	private static int codigo;

	public Servidores14C(Socket cliente)
	{
		this.cliente=cliente;
	}

	public void run(){
		try{
			entrada = new DataInputStream(cliente.getInputStream());
			codigo = Integer.valueOf(entrada.readUTF());

			switch (codigo){

			case 0:
				sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				String date1 = sdf1.format(new Date());
				salida = new DataOutputStream(cliente.getOutputStream());
				salida.flush();
				entrada = new DataInputStream(cliente.getInputStream());
				System.out.println(date1);
				salida.writeUTF(date1);
			break;

			case 1:
				sdf2 = new SimpleDateFormat("E,d MMM, HH:mm:ss");
				String date2 = sdf2.format(new Date());
				//[día de la semana], [día del mes] de [mes], [hora]: [minutos]:[segundos]
				salida = new DataOutputStream(cliente.getOutputStream());
				salida.flush();
				entrada = new DataInputStream(cliente.getInputStream());
				System.out.println(date2);
				salida.writeUTF(date2);
				break;
			}
		}
		catch(Exception e){}

	}
}