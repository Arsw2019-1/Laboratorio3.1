Desarrollo Laboratorio 3

1 Complete la clase Count Thread y defina un ciclo de vida d eun hilo para que imprima los numeros
entre A y B y los muestre en pantalla.

2.Complete el metodo main de CountMainThread de la clase:

    1 .Cree 3 hilos ed tipo CountThread y asigneles intervalos de [0,99], [99,199] ,[199,299]
    2.Empiece cada hilo con Start.
    3.Cambie start por run y mire el resultado.



Parte2 -- Blac List Search Exercise

    1. Cree una clase Thread que represente el ciclo de vida de un hilo que busca un segmento del grupo de servidores disponibles. 
        Agregue a esa clase un método que le permita preguntar a las instancias (los hilos) cuántas ocurrencias de servidores 
        maliciosos ha encontrado o encontrado.
        R: Se creo una clase llamada Bllock, la cual recibe un segmento de la lsita de servidores y analiza en busqueda del servidor malicioso.
            Antes en la clase HostBlackListValidator se crean los hilos, los cuales se le asigna un segmento a revisar y asi reducir
            el tiempo de busqueda.

    2. Agregue al método checkHost un parámetro entero N, correspondiente al número de subprocesos entre los que 
        se realizará la búsqueda (recuerde tener en cuenta si N es par o impar).    
        Modifique el código de este método para que divida el espacio de búsqueda entre las N partes indicadas y 
        se pare la búsqueda en N hilos. Haga que la función espere hasta que los subprocesos N terminen de resolver 
        sus respectivos subproblemas, agregue las apariciones encontradas para cada subproceso a la lista que devuelve 
        el método y luego calcule (agregando el número total de incidencias encontradas para cada subproceso) si el 
        Número de las ocurrencias son mayores o iguales a BLACK_LIST_ALARM_COUNT. Si este es el caso, al final el host 
        DEBE ser reportado como confiable o no confiable, y la lista debe mostrarse con los números de las listas negras 
        respectivas. Para lograr este comportamiento de espera, revise el método de unión de la API de concurrencia de Java. 
        R: Se agrego un N, de tal manera que en este entero sera dividida la lista y sera creada un numero igual de hilos para
        procesesar las IPs.