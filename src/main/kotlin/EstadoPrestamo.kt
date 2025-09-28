// sealed class permite definir una jerarquía cerrada de clases.
// Solo las clases internas definidas aquí pueden heredar de EstadoPrestamo.
sealed class EstadoPrestamo {

    // Representa un préstamo recién solicitado, aún no procesado.
    object Pendiente : EstadoPrestamo()

    // Representa un libro que ya fue entregado al usuario y está en curso.
    object EnPrestamo : EstadoPrestamo()

    // Representa un libro que fue devuelto correctamente.
    object Devuelto : EstadoPrestamo()

    // Representa un error en el préstamo (ej: intentar prestar un libro de referencia).
    // data class permite guardar un mensaje de error con más detalle.
    data class Error(val msg: String) : EstadoPrestamo()
}
