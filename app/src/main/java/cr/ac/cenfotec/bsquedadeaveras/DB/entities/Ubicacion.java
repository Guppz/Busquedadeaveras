package cr.ac.cenfotec.bsquedadeaveras.DB.entities;

public class Ubicacion {
    double latitude , longitud;

    public Ubicacion(double latitude, double longitud) {
        this.latitude = latitude;
        this.longitud = longitud;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }
}
