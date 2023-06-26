# java-template
En la clase **AppointmentController** se han creado dos metodos para controlar el alta de citas:

1.- El método filterAppointmentSameTimeSamePlace recibe como argumento la Appointment a dar de alta y devuelve una lista de Appointment tras filtrar por las citas de la misma sala y en el mismo intervalo de tiempo

2.- El método differenceBetween20MinAppointments controla que las citas no empiecen y acaben a la misma vez y, además para mayor eficacia en la atención, que haya, al menos, un intervalo de 20 minutos de duración. 

Recibe el Appointment a dar de alta y devuelve un int que representa lo siguiente:
int > 0 si la diferencia de tiempo es menor que 20 minutos
int < 0 si la diferencia de tiempo es mayor que 20 minutos
int = 0 si no hay diferencia 

Por último el metodo createAppointment que había que modificar siguiendo lo especificado en la clase AppointmentControllerUnitTest,recibe un la Appointment a dar de alta primero comprueba que no haya citas en la misma sala y en el mismo periodo, si las hubiera devuelve HttpStatus.NOT_ACCEPTABLE, posteriormente que las citas no tenga una duracion de 0 minutos, si esto sucede devuelve HttpStatus.BAD_REQUEST, por último si todo es correcto devuelve una lista con todas las Appointment y HttpStatus.OK

La clase ha pasado todos los test propuestos en la clase AppointmentControllerUnitTest

En cuanto a las clases **EntityControllerUnitTest** y **EntityUnitTes** se han implementatado test para probar todos los métodos, logrando un 100 % de cobertura.

Por último, se han incorporado los ficheros **Dockerfile.mysql** y **Dockerfile.maven** siguiendo las especificaciones del reto.
