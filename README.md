# Desarrollo de una aplicación para pedir desde la mesa de un restaurante-EasyOrder
## Proyecto final de la asignatura de Programación de Aplicaciones Telemáticas

En este proyecto hemos desarrollado una aplicación para pedir desde la mesa de un restaurante. Si fueras un cliente en un restaurante, escanearías un QR y este te llevaría a la página index.html, desde donde empieza el flujo de la aplicación que explicaremos más adelante. 

Esta aplicación creemos que facilita dos acciones que todo el mundo realiza al ir a un restaurante como son pedir y pagar. Con esta aplicación, puedes pedir y pagar desde la mesa sin tener que esperar a que un camarero venga a tomarte nota o traerte la cuenta. Además, esta aplicación también hace la vida más fácil a los restaurantes ya que podrán ver pedidos más rápidamente y modificar pedidos y platos desde un terminal muy sencillo de utilzar.

La aplicación se divide en dos "subaplicaciones". Por un lado, tenemos la parte que ve un cliente para pedir y pagar su comida. Esta parte esta diseñada para formato móvil, ya que es lo más común a la hora de escanear un qr en un restaurante.Por el otro lado, tenemos la parte del administrador a la que se accede con usuario y contraseña. Esta parte es la que permite modificar el estado de los pedidos, modificar los platos (su nombre, foto, ...) y añadir platos o eliminarlos. Esta "subaplicación" está diseñada para formato ordenador.

## Datos

Para el desarrollo de esta aplicación se hace uso de 5 tablas: tres principales (PEDIDOS, PLATOS y RESTAURANTES) y dos de relación (PEDIDO_PLATO y REST_PLATO). La información almacenada en estas tablas es la siguiente:
- **PEDIDOS** (PEDIDO_ID (pk), MESA, PRECIO, ESTADO, RESTAURANTE_ID): En PEDIDOS se almacenan todos los pedidos que se hayan realizado, diferenciándolos con un ID, la mesa y el restaurante. El precio que se guardará es el del pedido completo, y el estado puede variar entre confirmado (1), en cocina (2), listo (3) y entregado (4).
- **PLATOS** (PLATO_ID (pk), NOMBRE, PRECIO, FOTO, DESCRIPCION, SECCION): En PLATOS se almacenan todos los platos, sean del restaurante que sean, y como mínimo se requiere el ID, su nombre, su precio y su sección (Primero (1), Segundo (2), Postre (3) o Bebida (4)). El campo de descripcion sirve para añadir opcionalmente información extra del plato.
- **RESTAURANTES** (RESTAURANTE_ID (pk), NOMBRE, DIRECCION, FOTO): En RESTAURANTES se almacena la información básica de cada restaurante disponible en nuestro servicio.
- **PEDIDO_PLATO** (PEDIDO_ID (fk), PLATO_ID (fk), NUM_PLATOS): Esta tabla relaciona cada pedido con los platos que lo forman. Para cada pedido se guardarán en esta tabla tantas filas como platos distintos se hayan pedido. Además, se añade el campo num_platos en el caso de que se pidan varias unidades del mismo.
- **REST_PLATO** (RESTAURANTE_ID (fk), PLATO_ID (fk)): Finalmente, esta tabla relaciona cada plato con el restaurante que lo ofrece. Se usa esta tabla en vez de implementar a cada plato su RESTAURANTE_ID en la tabla PLATOS debido a que puede haber platos o bebidas que pertenecen a varios restaurantes (por ejemplo, si hubiesen varios locales de la misma cadena de restaurantes en nuestra base de datos).

## Ventanas cliente
(menendez? puedo hacerlo tambien parte yo)
## Ventanas admin

Las ventanas y javascripts relacionados con la parte de administración son **admin, admin_acciones, admin_menu, add_plato, mod_plato** y **mod_pedido**.
La pantalla inicial del flujo de acceso de administrador es **admin.html**, pero al intentar acceder a cualquiera de las páginas mencionadas por primera vez el sistema requerirá un usuario y contraseña. El usuario y contraseña que hemos determinado son ambos "**admin**", pero esta parte se explicará más adecuadamente en la sección de seguridad. Una vez completado el login, la primera pantalla que verá el administrador es la siguiente:
![admin.html](enlace de imagen admin.html)
En esta página se verán los restaurantes disponibles, y el administrador eligirá el suyo, el que desea gestionar. Una vez elegido, se almacena el id del restaurante elegido de manera local y se muestra la siguiente pantalla:
![admin_acciones.html](enlace de imagen admin_acciones.html)
Esta página presenta las opciones: revisar pedidos y modificar el menú. La primera opción es la gestión de pedidos:
![mod_pedidos.html](enlace de imagen mod_pedidos.html)
En esta ventana se muestran, divididos según su estado, los pedidos del restaurante al que se ha accedido. Para cada pedido se pueden ver su ID, la mesa que lo solicitó, el precio total y los platos y bebidas que componen el pedido. Para cada pedido, el administrador tiene disponible un desplegable para cambiar el estado del pedido. Al pulsar **Modificar**, la página se refresca y se mueve el pedido al estado elegido. Cuando un pedido ha sido entregado (estado final), la opción de modificar se sustituye por la de **Eliminar**, lo cual permite eliminar el pedido una vez ha sido entregado para no llenar la página y la base de datos de pedidos ya entregados.
![mod_pedidos.html: opcion entregado](enlace de imagen mod_pedidos.html opcion entregado)
La segunda opción es la de modificar el menú:
![admin_menu.html](enlace de imagen admin_menu.html)
En esta página se muestran todos los platos y bebidas disponible en el restaurante, con toda la información sobre los mismos, y ordenados según la sección a la que pertenecen. Cada plato presenta la opción de **Modificar** y de **Eliminar**. Eliminar el plato lo borra de la base de datos y refresca la página para mostrar el cambio. Si se elige modificar el plato, la página que se nos presenta es la siguiente:
![mod_plato.html](enlace de imagen mod_plato.html)
Esta página sigue el formato de un formulario, donde se pueden cambiar todos los campos menos el ID del plato, y actualizarlos en la base de datos. El formulario tiene comprobación de que el campo se ha rellenado para el precio y el nombre (la sección siempre tendrá una opción elegida así que no es necesario implantarlo), y además el campo de precio tiene una comprobación de formato correcto. 
Una vez actualizados los datos, al pulsar "Volver" volveremos a la página de administración de platos. En esta página tenemos también la opción de añadir platos. La ventana **add_plato.html** es igual a la de modificar, con la diferencia de que todos los campos están vacíos, y que no hay campo de ID, ya que éste se asigna según los ya existentes para evitar repetidos. Las mismas comprobaciones de formulario de **mod_plato.html** existen en **add_plato.html**

Como nota final, todas las páginas tienen un botón **Volver** para navegar entre las distintas opciones. La ventana admin_acciones.html es la única que no la tiene, porque se tiene en cuenta que, una vez se haya marcado tu restaurante, ese sería el único que se administra.

## Seguridad
(menendez)
## Testing
(menendez)
## Dependencias
(no se)
