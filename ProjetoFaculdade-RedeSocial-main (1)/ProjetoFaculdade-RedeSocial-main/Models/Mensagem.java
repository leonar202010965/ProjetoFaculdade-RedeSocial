package Models;
import java.util.Date;

public class Mensagem {
    String contaEnviou;
    String contaRecebeu;
    String mensagem;
    boolean lida;
    Date dataEnviou;

    public Mensagem(String enviou, String recebeu, String mensagem) {
        this.contaEnviou = enviou;
        this.contaRecebeu = recebeu;
        this.mensagem = mensagem;
        this.lida = false;
        dataEnviou = new Date();
    }

    public String RetornarResumo(){
        return this.contaEnviou + " Enviou uma mensagem: " + this.mensagem +  " às " +  dataEnviou;
    }

    public boolean getLida(){
        return this.lida;
    }

    public void setLida(){
        this.lida = true;
    }
}
