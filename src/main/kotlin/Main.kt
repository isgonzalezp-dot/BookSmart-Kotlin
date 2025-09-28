import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val catalogo = GestorPrestamos.inicializarCatalogo()

    println("=== Bienvenido a BookSmart ===")

    while (true) {
        println("\n📚 Catálogo de Libros:")
        catalogo.forEachIndexed { index, libro ->
            println("${index + 1}. ${libro.descripcion()} - Precio: ${libro.precioBase}")
        }
        println("${catalogo.size + 1}. Salir")

        try {
            print("\nSeleccione un libro (número): ")
            val opcion = readlnOrNull()?.toIntOrNull() ?: throw Exception("Entrada inválida")

            if (opcion == catalogo.size + 1) {
                println("👋 Gracias por usar BookSmart. ¡Hasta pronto!")
                break
            }

            if (opcion !in 1..catalogo.size) throw Exception("Opción fuera de rango")

            val libroSeleccionado = catalogo[opcion - 1]

            print("Ingrese tipo de usuario (Estudiante/Docente/Externo): ")
            val tipoUsuario = readlnOrNull() ?: "Externo"

            val precioConDescuento = GestorPrestamos.aplicarDescuento(libroSeleccionado.costoFinal(), tipoUsuario)

            println("\nProcesando préstamo de '${libroSeleccionado.titulo}'...")
            val estado = GestorPrestamos.procesarPrestamo(libroSeleccionado)

            when (estado) {
                is EstadoPrestamo.EnPrestamo -> {
                    println("✅ Préstamo realizado con éxito")
                    print("¿Hubo retraso en la devolución? Ingrese días (): ")
                    val diasRetraso = readlnOrNull()?.toIntOrNull() ?: 0
                    val multa = GestorPrestamos.calcularMulta(diasRetraso)

                    val total = precioConDescuento + multa
                    println("\n📊 Resumen del préstamo:")
                    println("Subtotal: ${libroSeleccionado.costoFinal()}")
                    println("Descuento aplicado: $precioConDescuento")
                    println("Multa por retraso: $multa")
                    println("Total a pagar: $total")
                }
                is EstadoPrestamo.Error -> println("❌ Error: ${estado.msg}")
                else -> println("⚠️ Estado inesperado")
            }

        } catch (e: Exception) {
            println("⚠️ Error en la ejecución: ${e.message}")
        }
    }
}
