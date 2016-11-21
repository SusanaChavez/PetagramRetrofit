package monti.com.mipetagram.presentador;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import monti.com.mipetagram.bd.ConstructorMascotas;
import monti.com.mipetagram.pojo.Usuario;
import monti.com.mipetagram.resApi.ConstantesResApi;
import monti.com.mipetagram.resApi.EndpointApi;
import monti.com.mipetagram.resApi.adapter.RestApiAdapter;
import monti.com.mipetagram.resApi.model.FotoResponse;
import monti.com.mipetagram.vistas.IPerfilFragment;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Susana on 19/11/2016.
 */

public class PerfilFragmentPresentador  implements IPerfilFragmentPresentador {
    private IPerfilFragment iPerfilFragment;
    private Context context;
    private ConstructorMascotas constructor;
    private Usuario usuario;
    private String cuenta = "susana.chvz";
    private ArrayList<Usuario> misUsuarios = new ArrayList<>();
    Bundle bundle;

    public PerfilFragmentPresentador(IPerfilFragment view, Context context, Bundle bundle) {
        this.iPerfilFragment = view;
        this.context = context;
        this.bundle = bundle;
        obtenerFotoUsuario();
    }

    @Override
    public void obtenerMascotasBD() {

    }

    @Override
    public void obtenerFotoUsuario() {
        RestApiAdapter restApiAdapter = new RestApiAdapter(); //Realiz una conexion con Instagrm
        Gson gsonFotoUsuario  = restApiAdapter.construyeGsonDeserializadorFotoUsuario();
        EndpointApi endpointApi = restApiAdapter.establecerConexionRestApiInstagram(gsonFotoUsuario);
        if(bundle.get("cuenta").equals("")){

        }else{
            cuenta = bundle.get("cuenta").toString();
        }
     //   Toast.makeText(context, cuenta, Toast.LENGTH_SHORT).show();

        Call<FotoResponse> fotoResponseCall = endpointApi.getFotoUsuario(cuenta, ConstantesResApi.ACCESS_TOKEN);

        //Para controlar el resultado de la respuesta
        fotoResponseCall.enqueue(new Callback<FotoResponse>() {
            @Override
            public void onResponse(Call<FotoResponse> call, Response<FotoResponse> response) {
                FotoResponse fotoResponse = response.body();
                if (fotoResponse == null) {
                     Toast.makeText(context, ".....ES NULL", Toast.LENGTH_SHORT).show();
                }else {
                    usuario = fotoResponse.getUsuario();
                    mostrarFotosRV();
                }
            }

            @Override
            public void onFailure(Call<FotoResponse> call, Throwable t) {
                    Toast.makeText(context, "Algo paso :(", Toast.LENGTH_SHORT).show();
                    Log.e("FALLO LA CONEXION", t.toString());
            }
        });
    }

    @Override
    public void mostrarFotosRV() {
        for(int i=0 ; i < 12; i++){
            misUsuarios.add(usuario);
        }
        iPerfilFragment.inicializarAdaptadorRV(iPerfilFragment.crearAdaptador(misUsuarios));
        iPerfilFragment.generaGridLayout();
        iPerfilFragment.completarPerfil(usuario);
    }

    public Usuario getUsuario(){
        return usuario;
    }

}
