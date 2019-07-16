package id.web.koding.crudapp;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mybiodatamahasiswa.R;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private MahasiswaAdapter mAdapter;
    private List<Mahasiswa>listMahasiswa= new ArrayList<>();
    private RecyclerView recyclerView;
    private TextView noDataView;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recycler_view);
        noDataView = findViewById(R.id.empty_view);

        db = new DatabaseHelper(this);

        listMahasiswa.addAll(db.getAllMahasiswa());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {                showFormDialog(false, null, -1);
            }
        });

        mAdapter = new MahasiswaAdapter(this, listMahasiswa);        RecyclerView.LayoutManager mLayoutManager = new
                LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this,
                LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(mAdapter);
        toggleEmptyData();

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this,
                recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {                showActionsDialog(position);
            }
        }));
    }

    private void toggleEmptyData() {
// you can check listMahasiswa.size() > 0

        if (db.getMahasiswaCount() >0) {
            noDataView.setVjenis_kelaminbility(View.GONE);
        } else {
            noDataView.setVjenis_kelaminbility(View.VJENIS_KELAMINBLE);
        }
    }

    private void showActionsDialog(final int position) {
        CharSequence colors[] = new CharSequence[]{"Edit", "Delete"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);        builder.setTitle("Choose option");        builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {                    showFormDialog(true, listMahasiswa.get(position), position);
                } else {
                    deleteMahasiswa(position);
                }
            }        });
        builder.show();
    }

    private void showFormDialog(final boolean isUpdate, final Mahasiswamahasiswa, final int position) {
        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.form_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);        alertDialogBuilderUserInput.setView(view);

        final EditText inputNama = view.findViewById(R.id.nama);
        final EditText inputJenis_kelamin = view.findViewById(R.id.jenis_kelamin);        TextView dialogTitle = view.findViewById(R.id.dialog_title);        dialogTitle.setText(!isUpdate ? getString(R.string.lbl_Tambah_mahasiswa) : getString(R.string.lbl_Edit_mahasiswa));

        if (isUpdate &&mahasiswa != null) {            inputNama.setText(mahasiswa.getNama());            inputJenis_kelamin.setText(mahasiswa.getJenis_kelamin());
        }         alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(isUpdate ? "update" : "save", new
                        DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id)
                            {

                            }
                        })
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new
                                                                                      View.OnClickListener() {
                                                                                          @Override
                                                                                          public void onClick(View v) {
// Show toast message when no text is entered
                                                                                              if (TextUtils.isEmpty(inputNama.getText().toString())) {
                                                                                                  Toast.makeText(MainActivity.this, "Jenis_kelaminnamamahasiswa!",
                                                                                                          Toast.LENGTH_SHORT).show();
                                                                                                  return;
                                                                                              } else {
                                                                                                  alertDialog.dismiss();
                                                                                              }

// check if user updating note
                                                                                              if (isUpdate &&mahasiswa!= null) {
// update note by it's id
                                                                                                  updateMahasiswa(inputNama.getText().toString(), inputJenis_kelamin.getText().toString(), position);
                                                                                              } else {
// create new note
                                                                                                  createMahasiswa(inputNama.getText().toString(), inputJenis_kelamin.getText().toString());
                                                                                              }
                                                                                          }
                                                                                      });
    }

    private void createMahasiswa(String nama, String jenis_kelamin) {
// inserting mahasiswa in db and getting
        // newly inserted mahasiswa id
        long id = db.insertMahasiswa(nama, jenis_kelamin);
// get the newly inserted note from db
        Mahasiswamahasiswa = db.getMahasiswa(id);

        if (mahasiswa != null) {
// adding new note to array list at 0 position
            listMahasiswa.add(0, mahasiswa);

// refreshing the list
            mAdapter.notifyDataSetChanged();
            toggleEmptyData();
        }
    }

    private void updateMahasiswa(String nama, String jenis_kelamin, int position) {
        Mahasiswamahasiswa = listMahasiswa.get(position);

// updating nama
        mahasiswa.setNama(nama);
// updateing jenis_kelamin
        mahasiswa.setJenis_kelamin(jenis_kelamin);

// updating mahasiswa in db        db.updateMahasiswa(mahasiswa);

// refreshing the list
        listMahasiswa.set(position, mahasiswa);
        mAdapter.notifyItemChanged(position);
        toggleEmptyData();
    }

    private void deleteMahasiswa(int position) {
// deleting the mahasiswa from db
        db.deleteMahasiswa(listMahasiswa.get(position));

// removing the mahasiswa from the list
        listMahasiswa.remove(position);
        mAdapter.notifyItemRemoved(position);

        toggleEmptyData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
// Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
// Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}




