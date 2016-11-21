package monti.com.mipetagram.resApi.adapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import monti.com.mipetagram.resApi.ConstantesResApi;
import monti.com.mipetagram.resApi.EndpointApi;
import monti.com.mipetagram.resApi.deserializador.FotoDeserializador;
import monti.com.mipetagram.resApi.deserializador.MascotaDeserializador;
import monti.com.mipetagram.resApi.model.FotoResponse;
import monti.com.mipetagram.resApi.model.MascotaResponse;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Susana on 17/11/2016.
 */

public class RestApiAdapter {
    public EndpointApi establecerConexionRestApiInstagram(Gson gson){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesResApi.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(EndpointApi.class);
    }

    public Gson construyeGsonDeserializadorMediaRecent(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(MascotaResponse.class, new MascotaDeserializador());
        return gsonBuilder.create();
    }

    public Gson construyeGsonDeserializadorFotoUsuario(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(FotoResponse.class, new FotoDeserializador());
        return gsonBuilder.create();
    }
}
