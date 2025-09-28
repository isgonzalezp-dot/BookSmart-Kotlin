 BookSmart - Sistema de Gestión de Libros en Kotlin

Proyecto de consola en Kotlin desarrollado para la asignatura DSY1105 – Desarrollo de Aplicaciones Móviles**.  
Este sistema simula la gestión de préstamos en una biblioteca, aplicando **herencia, polimorfismo, corrutinas, colecciones y manejo de errores**.
---
---
---

 Funcionalidades principales
- Gestión de un catálogo de libros físicos y digitales.
- Aplicación de descuentos según el tipo de usuario:
  - Estudiante → 10%
  - Docente → 15%
  - Externo → 0%
- Cálculo automático de multas por retraso.
- **Asincronía con corrutinas (`delay` simula el tiempo de préstamo).
- Manejo de estados con `sealed class`:
  - `Pendiente`, `EnPrestamo`, `Devuelto`, `Error`.
- Validación de errores:
  - No se pueden prestar libros de referencia.
  - Días y precios deben ser positivos.
- Menú interactivo con opción de volver al inicio o salir.

---

 Estructura del proyecto
src
└── main
└── kotlin
├── Libro.kt # Clase base y subclases (herencia y polimorfismo)
├── EstadoPrestamo.kt # Estados de préstamo (sealed class)
├── GestorPrestamos.kt # Lógica de negocio
└── Main.kt # Punto de entrada del programa
