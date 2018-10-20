package cr.ac.cenfotec.bsquedadeaveras.webservices;

import java.io.File;
import java.util.List;

import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Averia;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Basic;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.Image;
import cr.ac.cenfotec.bsquedadeaveras.DB.entities.ImageResponse;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ServicioImgur {
    @Multipart
    @POST("3/image")
    Call<Basic<Image>> postImage(@Part("image") RequestBody image);
}
