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
import java.util.Vector;

public class servidor15 {

	private static ServerSocket Servidor;
	private static int puerto=5000;
	private static DataOutputStream salida;
	private static DataInputStream entrada;
	private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	private static String date = sdf.format(new Date());
	public static int numconexiones = 0;
	public static Vector conexiones = new Vector();
	public static String[] jugador = new String[2];
	public static int[] apuestas = new int[2];
	public static int numapuestas = 0;
	public static int[] apuestas2 = new int[2];
	public static int[] thread = new int [2];
	public static int resultado = 0;

	public static void main( String[] argumentos ) throws IOException
    {
		try {
			Servidor = new ServerSocket(puerto);
			Socket conexion;

			while (true) {
					conexion = Servidor.accept();
					Servidores15C nuevaConexion = new Servidores15C(conexion);
					nuevaConexion.start();

			}

		} catch (Exception e) {}

	}

}