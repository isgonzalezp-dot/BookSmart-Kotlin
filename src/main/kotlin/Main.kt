import kotlinx.coroutines.runBlocking

// Punto de entrada del programa
// runBlocking permite ejecutar funciones suspendidas (corrutinas) desde main.
fun main() = runBlocking {
    // Inicializamos el catálogo de libros
    val catalogo = GestorPrestamos.inicializarCatalogo()

    println("=== Bienvenido a BookSmart ===")

    // Bucle infinito para que siempre vuelva al menú
    while (true) {
        println("\n Catálogo de Libros:")
        // Mostrar catálogo con índice
        catalogo.forEachIndexed { index, libro ->
            println("${index + 1}. ${libro.descripcion()} - Precio: ${libro.precioBase}")
        }
        // Opción para salir
        println("${catalogo.size + 1}. Salir")

        try {
            // Usuario selecciona un libro
            print("\nSeleccione un libro (número): ")
            val opcion = readlnOrNull()?.toIntOrNull() ?: throw Exception("Entrada inválida")

            // Si selecciona la opción "Salir"
            if (opcion == catalogo.size + 1) {
                println(" Gracias por usar BookSmart. ¡Hasta pronto!")
                break
            }

            // Validar que la opción sea válida
            if (opcion !in 1..catalogo.size) throw Exception("Opción fuera de rango")

            val libroSeleccionado = catalogo[opcion - 1]

            // Solicitar el tipo de usuario
            print("Ingrese tipo de usuario (Estudiante/Docente/Externo): ")
            val tipoUsuario = readlnOrNull() ?: "Externo"

            // Calcular el precio con descuento según el usuario
            val precioConDescuento = GestorPrestamos.aplicarDescuento(
                libroSeleccionado.costoFinal(),
                tipoUsuario
            )

            // Procesar préstamo (simula asincronía con delay en GestorPrestamos)
            println("\nProcesando préstamo de '${libroSeleccionado.titulo}'...")
            val estado = GestorPrestamos.procesarPrestamo(libroSeleccionado)

            // Evaluar el estado del préstamo usando sealed class
            when (estado) {
                is EstadoPrestamo.EnPrestamo -> {
                    println(" Préstamo realizado con éxito")
                    print("¿Hubo retraso en la devolución? Ingrese días (): ")
                    val diasRetraso = readlnOrNull()?.toIntOrNull() ?: 0
                    val multa = GestorPrestamos.calcularMulta(diasRetraso)

                    val total = precioConDescuento + multa
                    println("\n Resumen del préstamo:")
                    println("Subtotal: ${libroSeleccionado.costoFinal()}")
                    println("Descuento aplicado: $precioConDescuento")
                    println("Multa por retraso: $multa")
                    println("Total a pagar: $total")
                }
                // Si el libro es de referencia, devuelve un error
                is EstadoPrestamo.Error -> println(" Error: ${estado.msg}")
                // Para cualquier otro estado
                else -> println("⚠️ Estado inesperado")
            }

        } catch (e: Exception) {
            // Captura errores como entradas inválidas
            println("⚠️ Error en la ejecución: ${e.message}")
        }
    }
}
