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

public class ejercicio14 {

	public static void main( String[] argumentos ) throws IOException
    {
		try{
			Socket cliente = new Socket("127.0.0.1", 5000);
			DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());
			DataInputStream entrada = new DataInputStream(cliente.getInputStream());
			salida.writeUTF("0");
			String hora = entrada.readUTF();
			System.out.println(hora);

		}catch(Exception e){
			e.printStackTrace();
		}
    }
}
