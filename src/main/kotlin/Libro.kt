open class Libro(
    val titulo: String,
    val autor: String,
    val precioBase: Int,
    val diasPrestamo: Int
) {
    open fun costoFinal(): Int {
        return precioBase
    }

    open fun descripcion(): String {
        return "$titulo de $autor - $diasPrestamo días"
    }
}

class LibroFisico(
    titulo: String,
    autor: String,
    precioBase: Int,
    diasPrestamo: Int,
    val esReferencia: Boolean
) : Libro(titulo, autor, precioBase, diasPrestamo) {

    override fun costoFinal(): Int {
        if (esReferencia) return 0
        return precioBase
    }

    override fun descripcion(): String {
        return if (esReferencia) "$titulo (Referencia - No se presta)"
        else "$titulo (Físico)"
    }
}

class LibroDigital(
    titulo: String,
    autor: String,
    precioBase: Int,
    diasPrestamo: Int,
    val drm: Boolean
) : Libro(titulo, autor, precioBase, diasPrestamo) {

    override fun costoFinal(): Int {
        return if (drm) (precioBase * 1.1).toInt() else precioBase
    }

    override fun descripcion(): String {
        return if (drm) "$titulo (Digital con DRM)" else "$titulo (Digital libre)"
    }
}
