package monti.com.mipetagram.presentador;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import monti.com.mipetagram.bd.ConstructorMascotas;
import monti.com.mipetagram.pojo.Mascota;
import monti.com.mipetagram.resApi.EndpointApi;
import monti.com.mipetagram.resApi.adapter.RestApiAdapter;
import monti.com.mipetagram.resApi.model.MascotaResponse;
import monti.com.mipetagram.vistas.IRecyclerViewFragmentView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Susana on 15/10/2016.
 */

public class RecyclerViewFragmentPresentador implements IRecyclerViewFragmentPresentador {
    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructor;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresentador(IRecyclerViewFragmentView view, Context context) {
        this.iRecyclerViewFragmentView = view;
        this.context = context;
      //  obtenerMascotasBD();
        obtenerMedioRecientes();
    }

    @Override
    public void obtenerMascotasBD() {
        constructor = new ConstructorMascotas(context);
  //     mascotas = constructor.obtenerDatos();
        mostrarMascotasRV();
    }


    @Override
    public void obtenerMedioRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter(); //Realiz una conexion con Instagrm
        Gson gsonMediaRecent  = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointApi endpointApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
    //    Call<MascotaResponse> mascotaResponseCall = endpointApi.getRecentMedia();

        Call<MascotaResponse> mascotaResponseCall = endpointApi.getRecentMediaAmigos();

        //Para controlar el resultado de la respuesta
        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Algo paso :(", Toast.LENGTH_SHORT).show();
                Log.e("FALLO LA CONEXION", t.toString());
//                Log.e("FALLO LA CONEXION", t.getLocalizedMessage());
//                Log.e("FALLO LA CONEXION", t.getCause().toString());
            }
        });
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generaGridLayout();
    }
}
