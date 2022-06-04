package Models;
import java.util.ArrayList;
import java.security.*;
import java.math.*;

public class Conta {
    String username;
    String senhaCripto;
    ArrayList<String> amigoList = new ArrayList<>();
    ArrayList<String> amigoPendentesList = new ArrayList<>();
    ArrayList<Mensagem> mensagensRecebidas = new ArrayList<>();

    public Conta(String username, String senhaCripto) throws NoSuchAlgorithmException {
        this.username = username;
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(senhaCripto.getBytes(), 0, senhaCripto.length());
        this.senhaCripto = new BigInteger(1, md5.digest()).toString(16);
    }

    public String getNome() {
        return this.username;
    }

    public ArrayList<String> getListaAmigosPendentes() {
        return this.amigoPendentesList;
    }

    public ArrayList<Mensagem> getListaMensagens() {
        return this.mensagensRecebidas;
    }

    public void ResetarSenha(String senhaNova) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(senhaNova.getBytes(), 0, senhaNova.length());
        this.senhaCripto = new BigInteger(1, md5.digest()).toString(16);
    }

    public boolean ValidarLogin(String username, String senha) throws NoSuchAlgorithmException {
        boolean retorno = false;
        if (username.equals(this.username)) {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(senha.getBytes(), 0, senha.length());
            if ((new BigInteger(1, md5.digest()).toString(16)).equals(this.senhaCripto)) {
                retorno = true;
            }
        }
        return retorno;
    }

    public void AdcionarMensagensRecebidas(String username, String mensagens) {
        Mensagem aux = new Mensagem(username, this.username, mensagens);
        mensagensRecebidas.add(aux);
    }

    public void ExibirMensagens(boolean ler) {
        for (int i = 0; i < mensagensRecebidas.size(); i++) {

            if (!ler) {
                if (!mensagensRecebidas.get(i).getLida()) {
                    System.out.println(mensagensRecebidas.get(i).RetornarResumo());
                }
            } else {
                System.out.println(mensagensRecebidas.get(i).RetornarResumo());
            }
            if (ler) {
                mensagensRecebidas.get(i).setLida();
            }
        }
        
        if (mensagensRecebidas.size() == 0) {
            System.out.println("Lista Vazia !!");
        }
    }

    public void ExibirAmigosPendentes() {
        for (int i = 0; i < amigoPendentesList.size(); i++) {
            System.out.println(
                    "Convite N " + i + " - " + amigoPendentesList.get(i) + " Mandou uma solicitação de amizade");
        }

        if (amigoPendentesList.size() == 0) {
            System.out.println("Lista Vazia !!");
        }
    }

    public void ReceberConviteAmizade(String username) {
        amigoPendentesList.add(username);
    }

    public void AceitarConviteDeAmizade(String username) {

        amigoPendentesList.remove(username);
        amigoList.add(username);
        System.out.println("Convite Aceito de " + username + " !!");

    }
}
