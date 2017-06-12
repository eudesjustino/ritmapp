package andrade.com.br.ritmapp.model;

import java.io.Serializable;

/**
 * Created by IrmaosAndrade on 31/05/2017.
 */

public class PlayAlong implements Serializable{

    private String anexo;
    private String audio;
    private String autor;
    private String genero;
    private String imagem;
    private String instrumento;
    private String musica;
    private String texto;
    //private String velocidade;
    private int velocidade;
    private String visualizacoes;

    public PlayAlong(){}

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getMusica() {
        return musica;
    }

    public void setMusica(String musica) {
        this.musica = musica;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }


    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    /*public String getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(String velocidade) {
        this.velocidade = velocidade;
    }*/

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public String getVisualizacoes() {
        return visualizacoes;
    }

    public void setVisualizacoes(String visualizacoes) {
        this.visualizacoes = visualizacoes;
    }
}
