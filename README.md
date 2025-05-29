# Inventario de Productos (Consola - Java)

Este es un sistema de inventario desarrollado en Java con una interfaz de línea de comandos. Permite gestionar productos perecederos y no perecederos, almacenando los datos de manera persistente en un archivo.

## ✅ Funcionalidades principales

- Agregar productos (perecederos y no perecederos)
- Eliminar productos por nombre
- Buscar productos
- Listar todos los productos
- Ordenar productos alfabéticamente por nombre
- Guardado automático de datos tras cada operación

## 🏗️ Estructura del proyecto
inventario/
├── src/
│ ├── app/ # Clase principal con el menú (Main.java)
│ ├── model/ # Clases de modelo: Producto, ProductoPerecedero, etc.
│ ├── service/ # Lógica del inventario (InventarioService.java)
│ └── utils/ # Utilidades para guardar y cargar archivos (ArchivoUtils.java)
└── resources/
└── inventario.dat # Archivo donde se guardan los productos (serializado)


## 🧪 Clases principales

- **`Producto`**: clase abstracta base con atributos comunes.
- **`ProductoPerecedero`** y **`ProductoNoPerecedero`**: extienden de `Producto`.
- **`InventarioService`**: lógica central para gestionar la lista de productos.
- **`ArchivoUtils`**: utilidades para guardar y cargar productos usando serialización.

## 💾 Guardado de datos

Los productos se almacenan en `src/main/resources/inventario.dat` mediante **serialización**. El guardado se realiza automáticamente tras agregar productos, así como al salir del programa.

## 🔧 Requisitos

- JDK 11 o superior
- IDE recomendado: IntelliJ IDEA / Eclipse / NetBeans
- Sistema operativo: multiplataforma (Windows, Linux, macOS)

## 🚀 Próximamente

- Interfaz gráfica con JavaFX
- Exportación a CSV o JSON
- Filtros por tipo de producto y fechas de vencimiento

## 📦 Cómo ejecutar

1. Clonar el repositorio (cuando esté en GitHub):
   ```bash
   git clone https://github.com/usuario/inventario-java.git
   cd inventario-java

2. Compilar y ejecutar desde el IDE o con:

javac -d out src/**/**/*.java
java -cp out app.Main

Desarrollado por GRODONV
Proyecto educativo para gestión de inventario usando Java y programación orientada a objetos.

