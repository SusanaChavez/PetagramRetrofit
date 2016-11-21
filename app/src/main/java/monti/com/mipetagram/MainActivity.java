package monti.com.mipetagram;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import monti.com.mipetagram.adapter.PageAdapter;
import monti.com.mipetagram.vistas.PerfilFragment;
import monti.com.mipetagram.vistas.RecyclerViewFragment;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String cuenta = "susana.chvz";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //       getSupportFragmentManager().beginTransaction().add(R.id.)

//***********
//        Toolbar miActionBar = (Toolbar) findViewById(R.id.miActionBar);
//        setSupportActionBar(miActionBar);

//        ActionBar actionBar = getSupportActionBar();
        //  actionBar.setDisplayUseLogoEnabled(true);

//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setTitle("");
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayShowTitleEnabled(true);
//        actionBar.setLogo(R.drawable.huellaback);
        // actionBar.setHomeAsUpIndicator(R.drawable.back);


        //Add the following code to make the up arrow white:
        //final Drawable upArrow = getResources().getDrawable(R.drawable.abc_ic_ab_back_mtrl_am_alpha);
        //upArrow.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_ATOP);
        //getSupportActionBar().setHomeAsUpIndicator(upArrow);

        //Estoy probando
        // actionBar.setIcon(android.R.color.transparent);
        // actionBar.setDisplayUseLogoEnabled(false);

        /* para cambiar la barra
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);


        ActionBar actionBar = getSupportActionBar(); // or getActionBar();
getSupportActionBar().setTitle("My new title"); // set the top title
String title = actionBar.getTitle().toString(); // get the title
actionBar.hide();
         */
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Mascotas");
        }

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setUpViewpager();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void setUpViewpager() {
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.house);
        tabLayout.getTabAt(0).setTag("uno");
        tabLayout.getTabAt(1).setIcon(R.drawable.face);
        tabLayout.getTabAt(0).setTag("dos");
    }

    private ArrayList<Fragment> agregarFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new RecyclerViewFragment());

        Bundle bundle = new Bundle();
        PerfilFragment perfilFragment = new PerfilFragment();
        bundle.putString("cuenta", cuenta);
        perfilFragment.setArguments(bundle);
        fragments.add(perfilFragment);

        return fragments;
    }

    public void irFavoritas() {
        Intent intent = new Intent(this, Favoritas.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //   getMenuInflater().inflate(R.menu.menu, menu);
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        // or call onBackPressed()
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        //   Toast.makeText(ListaMascotas.this, Integer.toString(item.getItemId()), Toast.LENGTH_SHORT).show();
        switch (item.getItemId()) {
            case R.id.vStart:
                irFavoritas();
                break;
            case R.id.mpContacto:
                intent = new Intent(this, Contacto.class);
                startActivity(intent);
                break;
            case R.id.mpAcerca:
                intent = new Intent(this, Acerca.class);
                startActivity(intent);
                break;
            case R.id.mpConfigurar:
                intent = new Intent(this, ConfigurarCuenta.class);
                startActivityForResult(intent, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) switch (resultCode) {
            case RESULT_OK:
                cuenta = data.getExtras().getString("cuenta");
                Bundle bundle = new Bundle();
                bundle.putString("cuenta", cuenta);
                PerfilFragment perfilFragment = new PerfilFragment();
                perfilFragment.setArguments(bundle);

                // Reload current fragment
                //http://stackoverflow.com/questions/34310592/how-open-fragment-from-recyclerview-adaptercardadapter-viewholder
                //http://www.programcreek.com/java-api-examples/index.php?api=android.support.v4.app.FragmentStatePagerAdapter
/*
                    perfilFragment = (PerfilFragment) this.getSupportFragmentManager().findFragmentById(this.getTaskId());
                    //Fragment fg = (PerfilFragment) this.getSupportFragmentManager().findFragmentById(this.getTaskId());
                    final FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
                    ft.detach(perfilFragment);
                    ft.attach(perfilFragment);
                    ft.commit();
                    */

                //viewPager.getAdapter().setPrimaryItem(this, 1, perfilFragment);

                break;
            case RESULT_CANCELED:
                //Toast.makeText(this, "Rechazó las condiciones", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
