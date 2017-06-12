package andrade.com.br.ritmapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by IrmaosAndrade on 08/06/2017.
 */



public class PlayHolder extends RecyclerView.ViewHolder{

    public ImageView imgCapa;
    public TextView txtAutor, txtMusica, txtGenero, txtInstrumento, txtVelocidade, txtAnexo;
    public PlayHolder(View itemView) {
        super(itemView);
        imgCapa = (ImageView)itemView.findViewById(R.id.imgCapa);
        txtAutor = (TextView)itemView.findViewById(R.id.txtAutor);
        txtMusica = (TextView)itemView.findViewById(R.id.txtMusica);
        txtGenero = (TextView)itemView.findViewById(R.id.txtGenero);
        txtInstrumento = (TextView)itemView.findViewById(R.id.txtInstrumento);
        txtVelocidade = (TextView)itemView.findViewById(R.id.txtVelocidade);
        txtAnexo = (TextView)itemView.findViewById(R.id.txtAnexo);
    }

}
