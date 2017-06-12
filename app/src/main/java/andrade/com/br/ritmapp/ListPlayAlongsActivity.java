package andrade.com.br.ritmapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import andrade.com.br.ritmapp.model.PlayAlong;
import andrade.com.br.ritmapp.model.Playlist;

public class ListPlayAlongsActivity extends AppCompatActivity {

    private RecyclerView.LayoutManager mLayoutManager;
    private PlayAdapter mAdapter;
    Playlist playlist;
    RecyclerView recyclerView;
    ArrayList<PlayAlong> plays;
    public static DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_play_alongs);

         recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        playlist = new Playlist();
        plays = new ArrayList<>();
        playlist = (Playlist)getIntent().getSerializableExtra("list");
        plays = playlist.getPlayAlongs();
        setaRecyclerView();
        myRef = MainActivity.meuBanco;
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                myRef.child("Playlist").addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                playlist = new Playlist();
                                playlist = dataSnapshot.getValue(Playlist.class);
                                plays = playlist.getPlayAlongs();
                                setaRecyclerView();
                                mAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.w("2", "getUser:onCancelled", databaseError.toException());
                                // ...
                            }
                        });

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("erro", "Failed to read value.", error.toException());
            }
        });

    }
    public void setaRecyclerView(){

        //Aqui é instanciado o Recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new PlayAdapter(this,plays);
        recyclerView.setAdapter(mAdapter);

    }




}
