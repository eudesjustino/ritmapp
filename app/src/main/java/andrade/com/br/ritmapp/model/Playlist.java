package andrade.com.br.ritmapp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by IrmaosAndrade on 01/06/2017.
 */

public class Playlist implements Serializable {

    private ArrayList<PlayAlong> playAlongs;

    public Playlist(){}

    public ArrayList<PlayAlong> getPlayAlongs() {
        return playAlongs;
    }

    public void setPlayAlongs(ArrayList<PlayAlong> playAlongs) {
        this.playAlongs = playAlongs;
    }
}
