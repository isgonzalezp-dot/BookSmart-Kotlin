import kotlinx.coroutines.*

object GestorPrestamos {

    fun inicializarCatalogo(): List<Libro> {
        return listOf(
            LibroFisico("Estructuras de Datos", "Goodrich", 12990, 7, esReferencia = false),
            LibroFisico("Diccionario Enciclopédico", "Varios", 15990, 0, esReferencia = true),
            LibroDigital("Programación en Kotlin", "JetBrains", 9990, 10, drm = true),
            LibroDigital("Algoritmos Básicos", "Cormen", 11990, 10, drm = false)
        )
    }

    fun aplicarDescuento(precio: Int, tipoUsuario: String): Int {
        return when (tipoUsuario.lowercase()) {
            "estudiante" -> (precio * 0.9).toInt()
            "docente" -> (precio * 0.85).toInt()
            else -> precio
        }
    }

    fun calcularMulta(diasRetraso: Int, valorDiario: Int = 500): Int {
        return if (diasRetraso > 0) diasRetraso * valorDiario else 0
    }

    suspend fun procesarPrestamo(libro: Libro): EstadoPrestamo {
        delay(3000) // simula espera
        return if (libro is LibroFisico && libro.esReferencia) {
            EstadoPrestamo.Error("El libro ${libro.titulo} es de referencia y no se puede prestar.")
        } else {
            EstadoPrestamo.EnPrestamo
        }
    }
}
