package andrade.com.br.ritmapp;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import andrade.com.br.ritmapp.model.PlayAlong;

import static andrade.com.br.ritmapp.R.id.seekbar;

public class DetalheActivity extends AppCompatActivity {

    ImageView imgCapa;
    TextView txtSeekBar, txtAutor, txtMusica, txtGenero, txtInstrumento, txtVelocidade, txtAnexo, txtTexto;
    PlayAlong mPlayAlong;
    boolean start = false;
    MediaPlayer mediaplayer;
    FloatingActionButton fabPlay;
    int oneTimeOnly = 0;
    private Handler myHandler;


    SeekBar skb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhe);

        mediaplayer = new MediaPlayer();
        mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

        myHandler = new Handler();


        skb = (SeekBar)findViewById(seekbar);
        txtSeekBar = (TextView)findViewById(R.id.txtSeekBar);
        imgCapa = (ImageView)findViewById(R.id.imgCapa);
        txtAutor = (TextView)findViewById(R.id.txtAutor);
        txtMusica = (TextView)findViewById(R.id.txtMusica);
        txtGenero = (TextView)findViewById(R.id.txtGenero);
        txtInstrumento = (TextView)findViewById(R.id.txtInstrumento);
        txtVelocidade = (TextView)findViewById(R.id.txtVelocidade);
        txtAnexo = (TextView)findViewById(R.id.txtAnexo);
        txtTexto = (TextView)findViewById(R.id.txtTexto);

        fabPlay = (FloatingActionButton)findViewById(R.id.fabPlay);



        mPlayAlong = new PlayAlong();
        mPlayAlong = (PlayAlong)getIntent().getSerializableExtra("playalong");

        Picasso.with(this).load(mPlayAlong.getImagem()).into(imgCapa);
        txtAutor.setText(mPlayAlong.getAutor()+"  -  ");
        txtMusica.setText(mPlayAlong.getMusica());
        txtGenero.setText(mPlayAlong.getGenero());
        txtInstrumento.setText(mPlayAlong.getInstrumento());
        txtVelocidade.setText(String.valueOf(mPlayAlong.getVelocidade())+"bpm");
        txtAnexo.setText(mPlayAlong.getAnexo());

        fabPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(start==false) {
                    try {

                        mediaplayer.setDataSource(mPlayAlong.getAudio());
                        mediaplayer.prepare();


                    } catch (IllegalArgumentException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (SecurityException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    if (oneTimeOnly == 0) {
                        skb.setMax((int) mediaplayer.getDuration());
                        oneTimeOnly = 1;
                    }

                    if (txtSeekBar.getText()==""){

                        txtSeekBar.setText(String.format("%d min, %d sec",
                                TimeUnit.MILLISECONDS.toMinutes((long) mediaplayer.getDuration()),
                                TimeUnit.MILLISECONDS.toSeconds((long) mediaplayer.getDuration()) -
                                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)
                                                mediaplayer.getDuration())))
                        );
                    }
                    skb.setVisibility(View.VISIBLE);
                    skb.setProgress((int)mediaplayer.getCurrentPosition());
                    myHandler.postDelayed(UpdateSongTime,100);

                    mediaplayer.start();


                    fabPlay.animate().scaleX(0).scaleY(0).setDuration(10000);

                    fabPlay.setImageResource(R.drawable.pause_ic);

                    fabPlay.setBackgroundColor(Color.parseColor("#32CD32"));
                    fabPlay.animate().scaleX(1).scaleY(1).setDuration(10000);

                    start=true;


                }
                else {
                    mediaplayer.pause();
                    fabPlay.animate().scaleX(0).scaleY(0).setDuration(5000);


                    fabPlay.setImageResource(R.drawable.play_ic);

                    fabPlay.animate().scaleX(1).scaleY(1).setDuration(100);
                    start=false;
                }


            }
        });


    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {

            txtSeekBar.setText(String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes((long) mediaplayer.getCurrentPosition()),
                    TimeUnit.MILLISECONDS.toSeconds((long) mediaplayer.getCurrentPosition()) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                                    toMinutes((long) mediaplayer.getCurrentPosition())))
            );
            skb.setProgress((int)mediaplayer.getCurrentPosition());
            myHandler.postDelayed(this, 100);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaplayer.release();
        myHandler.removeCallbacks(UpdateSongTime);

    }
}
