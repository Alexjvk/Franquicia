Proyecto de Franquicias

Este proyecto es una aplicación de gestión de franquicias que permite manejar sucursales y productos a través de una API REST. A continuación, se detalla cómo ejecutar el proyecto localmente y utilizar las funcionalidades disponibles.

Ejecución del Proyecto en Local

Construir el Proyecto
Asegúrate de tener Maven instalado. Desde el directorio del proyecto,


API Endpoints

La aplicación expone varios endpoints que puedes consumir con herramientas como Postman:

____________________________________________________________________________________________

Franquicias

Crear una Franquicia

Método: POST

URL: http://localhost:8080/api/franchises

Body (JSON):

{
  "name": "Nombre de la Franquicia"
}

____________________________________________________________________________________________

Actualizar el Nombre de una Franquicia

Método: PUT

URL: http://localhost:8080/api/franchises/{id-franquicia}/update-name

Body (JSON):

{
  "name": "Nuevo Nombre"
}

____________________________________________________________________________________________

Sucursales

Crear una Sucursal

Método: POST

URL: http://localhost:8080/api/branches/franchises/{id-franquicia}/branches

Body (JSON):

{
  "name": "Nombre de la Sucursal"
}

____________________________________________________________________________________________

Actualizar el Nombre de una Sucursal

Método: PUT

URL: http://localhost:8080/api/branches/{id-sucursal}/update-name
Body (JSON):

"Nuevo nombre"

____________________________________________________________________________________________

Productos

Crear un Producto

Método: POST

URL: http://localhost:8080/api/products/branches/{id-sucursal}

Body (JSON):

{
  "name": "Nombre del Producto",
  "stock": 100
}

____________________________________________________________________________________________

Actualizar el Stock de un Producto

Método: PUT

URL: http://localhost:8080/api/products/branches/{id-sucursal}/product/{id-producto}/stock?stock=100

en la pestaña params de postman:

key: stock ----- value: {nueva cantidad}

____________________________________________________________________________________________

Borrar un producto

Método: DELETE

URL: http://localhost:8080/api/products/{id-sucursal}/product/{id-producto}

____________________________________________________________________________________________

El producto de las sucursales de una franquicia con mas productos

Método: GET

URL: http://localhost:8080/api/franchises/{id-franquicia}/top-products

____________________________________________________________________________________________

Ejecución en Docker
Si prefieres ejecutar la aplicación en un contenedor Docker, sigue estos pasos:

Construir la Imagen Docker Desde el directorio del proyecto, ejecuta:

docker build -t nombre-de-tu-imagen .

Ejecutar el Contenedor Una vez construida la imagen, puedes ejecutar el contenedor con el siguiente comando:

docker run -p 8080:8080 nombre-de-tu-imagen

Y en postman ejecutar las URL que se definieron anteriormente.
