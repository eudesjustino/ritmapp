package andrade.com.br.ritmapp.model;

/**
 * Created by IrmaosAndrade on 05/06/2017.
 */

public class Usuario {
    private String nome, email, imagem;

    public Usuario(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
