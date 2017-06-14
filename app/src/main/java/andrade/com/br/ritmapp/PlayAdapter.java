package andrade.com.br.ritmapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import andrade.com.br.ritmapp.model.PlayAlong;

/**
 * Created by IrmaosAndrade on 08/06/2017.
 */

public class PlayAdapter extends RecyclerView.Adapter<PlayHolder>{

    ArrayList<PlayAlong> mPlayAlongs;
    Context mCtx;
    PlayAlong mPlayAlong;
    ClickItemPlay mListener;


    public PlayAdapter(Context context, ArrayList<PlayAlong> plays, ClickItemPlay listener ){
        mPlayAlongs = plays;
        mCtx = context;
        mListener = listener;
    }
    @Override
    public PlayHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_play_along, parent,false);

        final PlayHolder ph = new PlayHolder(layoutView);
        ph.itemView.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mListener != null) {
                            int pos = ph.getAdapterPosition();
                            PlayAlong playAlongItem = mPlayAlongs.get(pos);
                            mListener.itemClicado(playAlongItem);
                        }
                    }
                });

        return ph;
    }

    @Override
    public void onBindViewHolder(final PlayHolder holder, int position) {

        mPlayAlong = mPlayAlongs.get(position);
        Picasso.with(mCtx).load(mPlayAlong.getImagem()).into(holder.imgCapa);
        holder.txtAutor.setText(mPlayAlong.getAutor()+"  -  ");
        holder.txtMusica.setText(mPlayAlong.getMusica());
        holder.txtGenero.setText(mPlayAlong.getGenero());
        holder.txtInstrumento.setText(mPlayAlong.getInstrumento());
        holder.txtVelocidade.setText(String.valueOf(mPlayAlong.getVelocidade())+"bpm");
        holder.txtAnexo.setText(mPlayAlong.getAnexo());

    }

    @Override
    public int getItemCount() {
        return mPlayAlongs.size();
    }

    public interface ClickItemPlay{
        void itemClicado(PlayAlong playAlong);
    }
}
