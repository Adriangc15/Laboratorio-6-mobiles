package example.abhiandriod.tablelayoutexample;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.style.AlignmentSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import example.abhiandriod.tablelayoutexample.Adapter.FormAdapter;
import example.abhiandriod.tablelayoutexample.Helper.RecyclerItemTouchHelper;
import example.abhiandriod.tablelayoutexample.Model.Formulario;
import example.abhiandriod.tablelayoutexample.Model.Model;

public class ADMListar extends AppCompatActivity implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener,FormAdapter.FormularioAdapterListener {

    private RecyclerView mRecyclerView;
    private FormAdapter mAdapter;
    private List<Formulario> formularioLista;
    private CoordinatorLayout coordinatorLayout;
    private SearchView searchView;
    private FloatingActionButton fab;
    private Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adm_listar);
        Toolbar toolbar = findViewById(R.id.toolbarCa);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recycler_formulario);
        formularioLista = new ArrayList<>();
        model = new Model();
        mAdapter = new FormAdapter(formularioLista,this);
        coordinatorLayout = findViewById(R.id.coordinator_layoutCa);

        whiteNotificationBar(mRecyclerView);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        mRecyclerView.setAdapter(mAdapter);

        fab = findViewById(R.id.addBtnCa);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAddUpdFormulario();
            }
        });

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT, (RecyclerItemTouchHelper.RecyclerItemTouchHelperListener) this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(mRecyclerView);

        checkIntentInformation();

        //refresh view
        mAdapter.notifyDataSetChanged();
    }
    private void checkIntentInformation() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Formulario aux;
            aux = (Formulario) getIntent().getSerializableExtra("addCarrera");
            if (aux == null) {
                aux = (Formulario) getIntent().getSerializableExtra("editCarrera");
                if (aux != null) {
                    //found an item that can be updated
                    boolean founded = false;
                    for (Formulario form : formularioLista) {
                        if (form.getId() == aux.getId()) {
                            form.setNombre(aux.getNombre());
                            form.setApellido(aux.getApellido());
                            founded = true;
                            break;
                        }
                    }
                    //check if exist
                    if (founded) {
                        Toast.makeText(getApplicationContext(), aux.getNombre() + " editado correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), aux.getNombre() + " no encontrado", Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                //found a new CarreraModel Object
                formularioLista.add(aux);
                Toast.makeText(getApplicationContext(), aux.getNombre() + " agregado correctamente", Toast.LENGTH_LONG).show();
            }
        }
    }
    private void goToAddUpdFormulario() {
        Intent intent = new Intent(this, activity_singup.class);
        intent.putExtra("editable", false);
        startActivity(intent);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (direction == ItemTouchHelper.START) {
            if (viewHolder instanceof FormAdapter.MyViewHolder) {
                // get the removed item name to display it in snack bar
                String name = formularioLista.get(viewHolder.getAdapterPosition()).getNombre();

                // save the index deleted
                final int deletedIndex = viewHolder.getAdapterPosition();
                // remove the item from recyclerView
                mAdapter.removeItem(viewHolder.getAdapterPosition());

                // showing snack bar with Undo option
                Snackbar snackbar = Snackbar.make(coordinatorLayout, name + " removido!", Snackbar.LENGTH_LONG);
                snackbar.setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // undo is selected, restore the deleted item from adapter
                        mAdapter.restoreItem(deletedIndex);
                    }
                });
                snackbar.setActionTextColor(Color.YELLOW);
                snackbar.show();
            }
        } else {
            //If is editing a row object
            Formulario aux = mAdapter.getSwipedItem(viewHolder.getAdapterPosition());
            //send data to Edit Activity
            Intent intent = new Intent(this, activity_singup.class);
            intent.putExtra("editable", true);
            intent.putExtra("Formulario", aux);
            mAdapter.notifyDataSetChanged(); //restart left swipe view
            startActivity(intent);
        }
    }

    @Override
    public void onItemMove(int source, int target) {
        mAdapter.onItemMove(source, target);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds carreraList to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);

        // Associate searchable configuration with the SearchView   !IMPORTANT
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change, every type on input
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() { //TODO it's not working yet
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        Intent a = new Intent(this, MainActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
        super.onBackPressed();
    }

    private void whiteNotificationBar(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int flags = view.getSystemUiVisibility();
            flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            view.setSystemUiVisibility(flags);
            getWindow().setStatusBarColor(Color.WHITE);
        }
    }

    @Override
    public void onContactSelected(Formulario form) {
        Toast.makeText(getApplicationContext(), "Selected: " + form.getId() + ", " + form.getNombre(), Toast.LENGTH_LONG).show();
    }
}
