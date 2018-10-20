package cr.ac.cenfotec.bsquedadeaveras.webservices;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GestorImgur {

        private static ServicioImgur singleton;
        public static ServicioImgur obtenerServicio(){
            if(singleton == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.imgur.com/3/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                singleton = retrofit.create(ServicioImgur.class);
            }
            return singleton;
        }




        public static ServicioImgur obtenerServicioAnon(){
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request authed = chain.request()
                    .newBuilder()
                            .addHeader("Authorization","Client-ID "+Imgur.IMGUR_CLIENT_ID)
                            .build();
                    return chain.proceed(authed);
                }
            }).build();
            return  new Retrofit.Builder()
                    .baseUrl("https://api.imgur.com/")
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ServicioImgur.class);
        }
}
