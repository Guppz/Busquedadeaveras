package cr.ac.cenfotec.bsquedadeaveras.webservices;

import java.util.List;

import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Averia;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServicioPosts {
    @Headers("x-api-key: rabArf10E86thWRQ5u4MH3pFXVpiQiXv8jg1c4hO")
    @GET("averias")
    Call<List<Averia>> obtenerTodosLasAverias();
    @Headers("x-api-key: rabArf10E86thWRQ5u4MH3pFXVpiQiXv8jg1c4hO")
    @GET("averias/{id}")
    Call<Averia> obtenerDetallesDeAveria(@Path("id") String id);
    @Headers("x-api-key: rabArf10E86thWRQ5u4MH3pFXVpiQiXv8jg1c4hO")
    @DELETE("averias/{id}")
    Call<Averia> deleteAveria(@Path("id") String averiaId);


    @Headers("x-api-key: rabArf10E86thWRQ5u4MH3pFXVpiQiXv8jg1c4hO")
    @POST("averias")
    Call<Averia> crearNuevoAveria(@Body Averia nuevo);

    @Headers("x-api-key: rabArf10E86thWRQ5u4MH3pFXVpiQiXv8jg1c4hO")
    @POST("averias/{id}")
    Call<Averia> updateAveria(@Path("id")String id, @Body Averia averia);

}
