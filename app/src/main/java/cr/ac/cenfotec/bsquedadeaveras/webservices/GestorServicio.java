package cr.ac.cenfotec.bsquedadeaveras.webservices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GestorServicio {

    private static ServicioPosts singleton;
    public static ServicioPosts obtenerServicio(){
        if(singleton == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://fn3arhnwsg.execute-api.us-west-2.amazonaws.com/produccion/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            singleton = retrofit.create(ServicioPosts.class);
        }
        return singleton;
    }
}
