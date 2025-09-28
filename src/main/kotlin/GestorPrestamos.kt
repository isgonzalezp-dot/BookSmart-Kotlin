import kotlinx.coroutines.*

// Objeto único que centraliza la lógica de préstamos.
// Al ser 'object', no hace falta instanciarlo (es un singleton).
object GestorPrestamos {

    // Inicializa el catálogo con libros de prueba (hardcodeados).
    // Devuelve una lista de distintos tipos de libros (físicos y digitales).
    fun inicializarCatalogo(): List<Libro> {
        return listOf(
            LibroFisico("Estructuras de Datos", "Goodrich", 12990, 7, esReferencia = false),
            LibroFisico("Diccionario Enciclopédico", "Varios", 15990, 0, esReferencia = true),
            LibroDigital("Programación en Kotlin", "JetBrains", 9990, 10, drm = true),
            LibroDigital("Algoritmos Básicos", "Cormen", 11990, 10, drm = false)
        )
    }

    // Aplica un descuento según el tipo de usuario:
    // Estudiante: 10%, Docente: 15%, Externo: 0%.
    fun aplicarDescuento(precio: Int, tipoUsuario: String): Int {
        return when (tipoUsuario.lowercase()) {
            "estudiante" -> (precio * 0.9).toInt()
            "docente" -> (precio * 0.85).toInt()
            else -> precio
        }
    }

    // Calcula la multa por retraso: días de retraso * valor diario (500 por defecto).
    // Si no hay retraso (<=0), devuelve 0.
    fun calcularMulta(diasRetraso: Int, valorDiario: Int = 500): Int {
        return if (diasRetraso > 0) diasRetraso * valorDiario else 0
    }

    // Procesa el préstamo de un libro de forma simulada y asincrónica.
    // Usa 'delay(3000)' para esperar 3 segundos (simula tiempo real).
    // Si el libro es de referencia, devuelve un estado de error.
    // Si no, devuelve estado EnPrestamo.
    suspend fun procesarPrestamo(libro: Libro): EstadoPrestamo {
        delay(3000) // Simulación de espera
        return if (libro is LibroFisico && libro.esReferencia) {
            EstadoPrestamo.Error("El libro ${libro.titulo} es de referencia y no se puede prestar.")
        } else {
            EstadoPrestamo.EnPrestamo
        }
    }
}
