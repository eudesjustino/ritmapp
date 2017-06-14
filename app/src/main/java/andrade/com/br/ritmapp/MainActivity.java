package andrade.com.br.ritmapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import andrade.com.br.ritmapp.model.Playlist;
import andrade.com.br.ritmapp.model.Usuario;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    Usuario user;
    Playlist pl;
    Button btn;
    ProgressBar pgb;
    boolean carregamento = false;
    GoogleApiClient mGoogleApiClient;
    private FirebaseAuth mAuth;
    public static DatabaseReference meuBanco = null;
    public static final int RC_SIGN_IN = 55;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pgb = (ProgressBar) findViewById(R.id.progress);
        txt = (TextView) findViewById(R.id.txt);

        //SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        //signInButton.setSize(SignInButton.SIZE_STANDARD);
//region Google Sing in

       // GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
         //       .requestIdToken("563651024997-gtbj9tn5a0qgd2cvc6mcdfpk6fqnirul.apps.googleusercontent.com")
           //     .requestEmail()
             //   .build();

      //  mGoogleApiClient = new GoogleApiClient.Builder(this)
        //        .enableAutoManage(this /* FragmentActivity */, new GoogleApiClient.OnConnectionFailedListener() {
          //          @Override
            //        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

              //      }
                //} /* OnConnectionFailedListener */)
                //.addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                //.build();

       /* findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
        });*/
       //endregion
    //    btn = (Button) findViewById(R.id.btn);

        baixarLista();



    }

    public void baixarLista()
    {

        pgb.setVisibility(View.VISIBLE);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("");
        meuBanco = myRef;

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                myRef.child("Playlist").addListenerForSingleValueEvent(
                        new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                pl = new Playlist();
                                pl = dataSnapshot.getValue(Playlist.class);


                                if(pl!=null) {
                                    pgb.setVisibility(View.GONE);

                                    Intent it = new Intent(MainActivity.this, ListPlayAlongsActivity.class);

                                    it.putExtra("list", pl);
                                    finish();
                                    startActivity(it);

                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                Log.w("Erro Firebase", "onCancelled", databaseError.toException());

                            }
                        });

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Erro Firebase", "Failed to read value.", error.toException());
            }
        });

    }


    //region Sing in Google
 /*   private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            System.out.println(data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                this.firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {

        mAuth = FirebaseAuth.getInstance();

        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential).addOnCompleteListener(MainActivity.this, myListener);
    }

    OnCompleteListener myListener = new OnCompleteListener() {

        @Override
        public void onComplete(@NonNull Task task) {

            System.out.println("ENTROU --------------------------------------------------------------------------");
            if (task.isSuccessful()) {
                Toast.makeText(getApplicationContext(), "Authentication OK.",
                        Toast.LENGTH_SHORT).show();
            }else {
                Log.w("MY TAG", "signInWithCredential", task.getException());
                Toast.makeText(MainActivity.this, "Authentication failed.",
                        Toast.LENGTH_SHORT).show();
            }
        }
    };*/
 //endregionSing in

}
