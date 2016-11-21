package monti.com.mipetagram.vistas;

import monti.com.mipetagram.adapter.UsuarioPerfil;
import monti.com.mipetagram.pojo.Usuario;

import java.util.ArrayList;

/**
 * Created by Susana on 19/11/2016.
 */

public interface IPerfilFragment {

    public void generarLinearLayoutVertical();

    public void generaGridLayout();

    public UsuarioPerfil crearAdaptador(ArrayList<Usuario> usuarios);

    public void inicializarAdaptadorRV(UsuarioPerfil adaptador);

    public void completarPerfil(Usuario usuario);
}
