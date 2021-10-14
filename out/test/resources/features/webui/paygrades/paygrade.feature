# new feature
# Tags: optional

Feature: Como empleado y con rol administrativo necesito administrar los grados de pago del aplicativo web
    para garantizar la disponibilidad de los mismos en el sistema.

  Scenario: Se permite añadir un grado de pago cuyo nombre NO existe y añadirle un tipo de moneda,
            así como salarios máximos y mínimos
    Given el administrador del sistema se encuentra en la seccion de Pay Grade
    When el administrador ingresa un name de grado de pago NO existente y presiona en el boton agregar,adicionalmente agrega los datos de la moneda: nombre, salario minimo y maximo y presiona guardar
    Then el sistema debera mostrar los datos de grado de pago guardados en el sistema.
