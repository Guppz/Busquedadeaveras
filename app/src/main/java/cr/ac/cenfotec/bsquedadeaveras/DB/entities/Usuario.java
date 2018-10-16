package cr.ac.cenfotec.bsquedadeaveras.DB.entities;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class Usuario implements Serializable {
    @DatabaseField(columnName = "correo" , canBeNull = false,unique = true)
    public String correo;
    @DatabaseField(columnName = "nombre" , canBeNull = false)
    public String nombre;
    @DatabaseField(columnName = "telefono" , canBeNull = false)
    public String tel;
    @DatabaseField(columnName = "cedula" , canBeNull = false)
    public String cedula;
    @DatabaseField(columnName = "password" , canBeNull = false)
    public String password;

    public Usuario(String correo, String nombre, String tel, String cedula,String password) {
        this.correo = correo;
        this.nombre = nombre;
        this.tel = tel;
        this.cedula = cedula;
        this.password =password;
    }
    public Usuario(String correo, String nombre, String tel, String cedula) {
        this.correo = correo;
        this.nombre = nombre;
        this.tel = tel;
        this.cedula = cedula;
    }

    public Usuario() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    @Override
    public String toString() {
        return "Usuario{" +
                "correo='" + correo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", tel='" + tel + '\'' +
                ", cedula='" + cedula + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
