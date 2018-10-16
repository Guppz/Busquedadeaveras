package cr.ac.cenfotec.bsquedadeaveras.DB.entities;

public class Averia {
    int id;
    String nombre,tipo,fecha,descripcion,imagen;
    Usuario usuario;
    Ubicacion ubicacion;

    public Averia(int id, String nombre, String tipo, String fecha, String descripcion, String imagen, Usuario usuario, Ubicacion ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.usuario = usuario;
        this.ubicacion = ubicacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }
}
