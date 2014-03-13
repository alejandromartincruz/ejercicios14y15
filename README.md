# clases esenciales 14 & 15

The fourteenth and fifteenth exercise of the essencial classes section.


Ejercicio 14. Comunicaciones en red
Se modifica el protocolo “¿Qué hora es?” de tal modo que el cliente envía al
servidor un “0” para indicar que quiere la respuesta en el formato propuesto en el
ejercicio1 y un “1” si quiere la respuesta en el siguiente formato texto: [día de la
semana], [día del mes] de [mes], [hora]: [minutos]:[segundos]. Por ejemplo:
“Sábado, 21 de septiembre de 2013, 17:07:34 ”. Implementarlo para la versión del
servicio que queráis, TCP-no concurrente, TCP-concurrente o UDP.
Ejercicio 15. Comunicaciones en red
Se quiere implementar el juego de los chinos en red para dos personas. El protocolo
de transporte será TCP. El servidor organiza las partidas de dos en dos en orden de
llegada. El funcionamiento del servicio es el siguiente:
• Un cliente abre una conexión con el servidor y le envía el mensaje “PLAY
[nombre]”, donde [nombre] es un identificador que el cliente se asigna.
• Si el cliente es el primero de una partida de dos le envía el mensaje “WAIT”
• Si el cliente es el segundo de una partida de dos, el cliente envía a los
clientes el mensaje informativo “VERSUS [nombre]”, donde [nombre] es el
nombre del jugador contrario. Después le envía al primer jugador el mensaje
“YOUR BET” y al segundo jugador le envía el mensaje “WAIT BET”. Al recibir
el mensaje VERSUS el cliente mostrará por consola el nombre del jugador
contrario.
• Cuando los clientes reciben el mensaje “YOUR BET” responden con su jugada
en el siguiente formato MY BET [MONEDAS QUE JUEGA] [MONEDAS
TOTALES].
• Cuando el servidor recibe la respuesta “MY BET” del primer jugador le envía:
• a ambos jugadores el mensaje “BET OF [nombre] [MONEDAS
TOTALES]”, donde [nombre] es el nombre del jugador y [MONEDAS
TOTALES] su apuesta.
• al segundo el mensaje “YOUR BET”
• al primer jugador el mensaje “WAIT BET”.
• Cuando el segundo jugador responde con su jugada el servidor envía a los
dos jugadores el mensaje “BET OF [nombre] [MONEDAS TOTALES]” con la
apuesta del segundo jugador y el mensaje “WINNER [ganador]”, donde
[ganador] es el nombre del cliente que ha ganado o “NONE” en caso de que
no haya ganado ninguno de los dos. Una vez que el servidor envía este
mensaje cierra las conexiones con los clientes y éstos hacen lo mismo al
recibir el mensaje.
La aplicación cliente tiene una interfaz de consola por la que va visualizando las
acciones a
realizar y por la que puede introducir su jugada. Para simplificar el ejercicio,
considerar que los clientes no abandonan una partida a medias ni ningún otro tipo
de comportamientos que no cumplan estrictamente el flujo esperado