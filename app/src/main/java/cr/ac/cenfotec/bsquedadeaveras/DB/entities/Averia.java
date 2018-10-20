package cr.ac.cenfotec.bsquedadeaveras.DB.entities;

import java.io.Serializable;

public class Averia implements Serializable {
    String id;
    String nombre,tipo,descripcion,imagen;
    Ubicacion ubicacion;
    Usuario usuario;
    String fecha;


    public Averia(String id, String nombre, String tipo, String descripcion, String imagen, Ubicacion ubicacion, Usuario usuario, String fecha) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.ubicacion = ubicacion;
        this.usuario = usuario;
        this.fecha = fecha;
    }

    public Averia(String id, String nombre, String tipo, String descripcion, String imagen, Ubicacion ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.ubicacion = ubicacion;
    }

    public String getId() { return id; }

    public void setId(String id) {
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

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Averia{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", ubicacion=" + ubicacion +
                '}';
    }
}
