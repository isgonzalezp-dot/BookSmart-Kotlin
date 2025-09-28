// Clase base (superclase). Se marca como 'open' para permitir herencia.
open class Libro(
    val titulo: String,       // Título del libro
    val autor: String,        // Autor del libro
    val precioBase: Int,      // Precio base del préstamo
    val diasPrestamo: Int     // Días permitidos de préstamo
) {
    // Método abierto que calcula el costo final. Por defecto devuelve el precio base.
    open fun costoFinal(): Int {
        return precioBase
    }

    // Método abierto que devuelve una descripción general.
    open fun descripcion(): String {
        return "$titulo de $autor - $diasPrestamo días"
    }
}

// Clase hija que representa libros físicos, hereda de Libro.
class LibroFisico(
    titulo: String,
    autor: String,
    precioBase: Int,
    diasPrestamo: Int,
    val esReferencia: Boolean // Atributo propio: si es de referencia (no prestable)
) : Libro(titulo, autor, precioBase, diasPrestamo) {

    // Sobrescribe el cálculo del costo final.
    // Si es de referencia → costo 0 (no se presta).
    override fun costoFinal(): Int {
        if (esReferencia) return 0
        return precioBase
    }

    // Sobrescribe la descripción para indicar si es de referencia o normal.
    override fun descripcion(): String {
        return if (esReferencia) "$titulo (Referencia - No se presta)"
        else "$titulo (Físico)"
    }
}

// Clase hija que representa libros digitales, hereda de Libro.
class LibroDigital(
    titulo: String,
    autor: String,
    precioBase: Int,
    diasPrestamo: Int,
    val drm: Boolean // Atributo propio: si tiene protección DRM
) : Libro(titulo, autor, precioBase, diasPrestamo) {

    // Sobrescribe el cálculo del costo final.
    // Si tiene DRM → se aumenta un 10% del precio base.
    override fun costoFinal(): Int {
        return if (drm) (precioBase * 1.1).toInt() else precioBase
    }

    // Sobrescribe la descripción para indicar si tiene DRM o es libre.
    override fun descripcion(): String {
        return if (drm) "$titulo (Digital con DRM)" else "$titulo (Digital libre)"
    }
}
