package cr.ac.cenfotec.bsquedadeaveras.DB.entities;

import java.io.Serializable;

public class Ubicacion implements Serializable {
    double lat , lon;

    public Ubicacion(double latitude, double longitud) {
        this.lat = latitude;
        this.lon = longitud;
    }

    public double getLatitude() {
        return lat;
    }

    public void setLatitude(double latitude) {
        this.lat = latitude;
    }

    public double getLongitud() {
        return lon;
    }

    public void setLongitud(double longitud) {
        this.lon = longitud;
    }
}
