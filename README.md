# Desarrollo de una aplicación para pedir desde la mesa de un restaurante-EasyOrder
## Proyecto final de la asignatura de Programación de Aplicaciones Telemáticas

## Link a Gitpod
[![](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/#https://github.com/Menendez6/PAT_final)

## Tabla de contenidos
1. [Introduccion](#breve-explicación-proyecto)
2. [Datos](#datos)
3. [APIs](#apis)
4. [Ventanas cliente](#ventanas-cliente)
5. [Ventanas admin](#ventanas-admin)
6. [Seguridad](#seguridad)
7. [Testing](#testing)
8. [Dependecias](#dependencias)

## Breve explicación proyecto

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

## APIs

### APIs Pedidos
- *api/add_pedido*: Api para añadir un nuevo elemento a la tabla de pedidos. El nuevo elemento se añadirá con el siguiente id de pedido, la mesa y el restaurante donde se haya pedido, el precio correspondiente al pedido y el estado 1 (confirmado).

- *api/getId*: Obtiene el id del último pedido añadido. Al crear un pedido, necesitamos conocer el id del pedido que se acaba de realizad, por lo que utilizamos esta API

- *api/addPlatoPedido*: Añadir un elemento a la tabla plato-pedidos que guarda la info de los platos correspondientes a un pedido.

- *api/getPlatosPedidos*: Obtener toda la tabla de plato-pedido para comprobar que se guarda bien

- *api/getPedido/{id}*: Obtener un pedido por su id. Se obtiene únicamente el pedido, no los platos asociados a él.

- *api/getPedido2/{id}*: Obtener un pedido por su id. En esta ocasión sí se obtienen los platos asociados al pedido.

- *api/getPedidoMesa/{id}/{mesa}*: Obtener todos los pedidos de una mesa específica de un restaurante. Una mesa puede tener varios pedidos

- *api/getPedidos/restaurante/{id}*: Obtiene todos los pedidos de un restaurante con sus platos.

- *api/pedido/update/{id}*: Actualiza el estado de un pedido.

- *api/pedido/{id}* (delete): Elimina un pedido de una tabla.

### APIs Platos

- *api/platos/{id}*: Obtiene todos los platos de un restaurante, en función de su id de restaurante.

- *api/platos/delete/{id}*: Elimina un plato en función del id de plato.

- *api/platos/update/{id}*: Actualiza los valores de un plato en función de su id de plato.

- *api/platos/add*: Añade un plato a la tabla de platos y, además, añade una fila con el plato y el restaurante a la tabla rest-plato

### APIs Restaurantes

- *api/restaurants*: Obtiene todos los restaurantes

- *api/restaurants/{id}*: Obtiene un restaurante en función de su id.

## Ventanas cliente
Las ventanas y javascripts relacionados con la parte de administración son **index.html (app.js), mesa, menu, carrito, pago, confirmado, estado** y **pedidos**.

Como se ha comentado anteriormente, estas ventanas han sido diseñadas para versión móvil ya que es como los clientes van a visualizar la aplicación. La pantalla inicial será la de **index.html**.

### Index

![index.html](/img/index1.png)
![index.html](/img/index2.png)

En esta ventana, aparecen todos los restaurantes extraidos de la api **api/restaurants**. En la pantalla, el cliente selecciona el restaurante en el que está sentado y esto le redirecciona a la página de **mesa.html**.

### Mesa

![mesa.html](/img/mesa.png)

En esta ventana, el cliente simplemente selecciona la mesa en la que está sentado para que le lleven el pedido. En el caso en el que ya haya un pedido en proceso para esa mesa, se le mandará a la página **pedidos.html**. Si no tiene ningún pedido asociado a su mesa, se mandará a **menu.html** para que comience el pedido.

### Pedidos

![pedidos.html](/img/pedidos.png)

En esta ventana se muestran los pedidos en curso asociados a una mesa. Para cada pedido puedes ver todos los datos del pedido y además un botón que te redirecciona para ver el estado de tu pedido en **estado.html**. Si quieres realizar otro pedido aunque ya tengas uno, es posible. Simplemente en el botón de "Hacer otro pedido", y llegarás a la página de **menu.html**.

### Menu

![menu.html](/img/menu1.png)
![menu.html](/img/menu2.png)
![menu.html](/img/menu3.png)

En esta ventana, el cliente ve todos los platos. Inicialmente, al empezar el pedido, el carrito de arriba a la derecha aparece en 0 porque no hay platos añadidos y todos los platos en 1. Si quisieras añadir un plato para pedir, simplemente seleccionas la cantidad con los botones de más o menos y le das a añadir. Al pulsar añadir, el carrito se actualizará con el número de platos elegidos. 

Si se pulsa en el carrito, se pasará a la página **carrito.html**.

### Carrito

![menu.html](/img/carrito1.png)
![menu.html](/img/carrito2.png)

Como se puede observar, en esta ventana aparecen todos los platos pedidos. Puedes eliminar los platos o volver atrás y seguir pidiendo. Cuando hayas terminado de revisar el pedido, se pulsa el botón de confirmar y te redirecciona a la pasarela de **pago.html**

### Pago

![pago.html](/img/pago.png)

Para realizar el pago, el cliente debe insertar los datos de su tarjeta VISA. Si la tarjeta no es VISA (empiece en 4 y tenga o 13 o 16 dígitos), no le permitirá realizar el pago. Si está caducada o la fecha no está en el formato válido, saltará una alerta. Si el código de seguridad no es válido, saltará una alerta. 

No hemos implementado una pasarela de pago real. Al pulsar el botón de pagar, te redirecciona directamente a la pantalla de **confirmado.html**, si todo es correcto.

### Confirmado

![confirmado.html](/img/confirmado.png)

Desde esta ventana, puedes volver a la ventana principal (**index.html**) o ver el estado de tu pedido (**estado.html**)

### Estado

![estado.html](/img/estado.png)

En esta ventana aparecerá el estado del pedido seleccionado. Desde ella podrá o bien volver a **index.html** o hacer otro pedido que le llevará a **mesa.html**.


## Ventanas admin

Las ventanas y javascripts relacionados con la parte del cliente son **admin, admin_acciones, admin_menu, add_plato, mod_plato** y **mod_pedido**.
La pantalla inicial del flujo de acceso de administrador es **admin.html**, pero al intentar acceder a cualquiera de las páginas mencionadas por primera vez el sistema requerirá un usuario y contraseña. El usuario y contraseña que hemos determinado son ambos "**admin**", pero esta parte se explicará más adecuadamente en la sección de seguridad. Una vez completado el login, la primera pantalla que verá el administrador es la siguiente:

![admin.html](/img/admin.png)

En esta página se verán los restaurantes disponibles, y el administrador eligirá el suyo, el que desea gestionar. Una vez elegido, se almacena el id del restaurante elegido de manera local y se muestra la siguiente pantalla:

![admin_acciones.html](/img/admin_acciones.png)

Esta página presenta las opciones: revisar pedidos y modificar el menú. La primera opción es la gestión de pedidos:

![mod_pedidos.html](/img/mod_pedidos.png)

En esta ventana se muestran, divididos según su estado, los pedidos del restaurante al que se ha accedido. Para cada pedido se pueden ver su ID, la mesa que lo solicitó, el precio total y los platos y bebidas que componen el pedido. Para cada pedido, el administrador tiene disponible un desplegable para cambiar el estado del pedido. Al pulsar **Cambiar**, la página se refresca y se mueve el pedido al estado elegido. Cuando un pedido ha sido entregado (estado final), la opción de modificar se sustituye por la de **Eliminar**, lo cual permite eliminar el pedido una vez ha sido entregado para no llenar la página y la base de datos de pedidos ya entregados.

![mod_pedidos.html: opcion entregado](/img/mod_pedidos_entregados.png)

La segunda opción es la de modificar el menú:

![admin_menu.html](/img/admin_menu.png)

En esta página se muestran todos los platos y bebidas disponible en el restaurante, con toda la información sobre los mismos, y ordenados según la sección a la que pertenecen. Cada plato presenta la opción de **Modificar** y de **Eliminar**. Eliminar el plato lo borra de la base de datos y refresca la página para mostrar el cambio. Si se elige modificar el plato, la página que se nos presenta es la siguiente:

![mod_plato.html](/img/mod_platos.png)

Esta página sigue el formato de un formulario, donde se pueden cambiar todos los campos menos el ID del plato, y actualizarlos en la base de datos. El formulario tiene comprobación de que el campo se ha rellenado para el precio y el nombre (la sección siempre tendrá una opción elegida así que no es necesario implantarlo), y además el campo de precio tiene una comprobación de formato correcto. 
Una vez actualizados los datos, al pulsar "Volver" volveremos a la página de administración de platos. En esta página tenemos también la opción de añadir platos. La ventana **add_plato.html** es igual a la de modificar, con la diferencia de que todos los campos están vacíos, y que no hay campo de ID, ya que éste se asigna según los ya existentes para evitar repetidos. Las mismas comprobaciones de formulario de **mod_plato.html** existen en **add_plato.html**

Como nota final, todas las páginas tienen un botón **Volver** para navegar entre las distintas opciones. La ventana admin_acciones.html es la única que no la tiene, porque se tiene en cuenta que, una vez se haya marcado tu restaurante, ese sería el único que se administra.

## Seguridad

La parte de la aplicación que va dedicada al cliente, no tiene aspectos de seguridad, ya que está pesada para ser de libre acceso. No hace falta hacer login como usuario porque buscamos un acceso de manera cómoda para el cliente.

La parte de la aplicación del administrador debe tener seguridad. Para ello hemos utilizado la seguridad de springboot. Denegamos el acceso a las páginas de administrador si no está autenticado. Para el resto de páginas no hay restricciones.

![login.html](/img/seguridad.png)

## Testing

Para comprobar que nuestra aplicación funciona correctamente, hemos realizado una serie de tests E2E.

Hemos comprobado el funcionamiento de todas las APIs que se ven en el apartado de APIs. Hemos comprobado que, en el caso de los gets, la información obtenida es la correcta y en el caso de los posts o deletes, las tablas se actualizaban correctamente.

![login.html](/img/tests.png)

## Dependencias

Para este proyecto hemos utilizado las siguientes dependencias de SpringBoot:
- 
