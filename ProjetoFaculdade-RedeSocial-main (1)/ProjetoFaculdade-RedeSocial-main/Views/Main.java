package Views;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import Controller.*;

import Models.Conta;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        try (Scanner in = new Scanner(System.in)) {
            String usernameTemp, senhaTemp;
            int controllerNumeroContaLista = 0;

            boolean controllerSaidaConta = false, controllerAchouConta = false, saidaPrograma = true;
            ArrayList<Conta> contasList = new ArrayList<>();

            while (saidaPrograma) {

                MenuController.exibirMenuInicial(contasList.size());
                int senha = in.nextInt();

                switch (senha) {
                    case 1:
                        System.out.println("Informe seu Username: ");
                        usernameTemp = in.next();
                        System.out.println("Informe sua Senha: ");
                        senhaTemp = in.next();

                        for (int i = 0; i < contasList.size(); i++) {
                            if (!controllerAchouConta) {
                                controllerAchouConta = contasList.get(i).ValidarLogin(usernameTemp, senhaTemp);
                                controllerNumeroContaLista = i;
                            }
                        }
                        if (controllerAchouConta) {
                            System.out.println("Conta encontrada !!! Bem vindo "
                                    + contasList.get(controllerNumeroContaLista).getNome() + "\n");
                            controllerSaidaConta = true;
                        } else {
                            System.out.println("Usuario ou senha INCORRETA !!");
                        }

                        break;
                    case 2:
                        System.out.println("Informe um Username: ");
                        usernameTemp = in.next();
                        System.out.println("Informe uma Senha: ");
                        senhaTemp = in.next();
                        Conta contaTemporaria = new Conta(usernameTemp, senhaTemp);
                        contasList.add(contaTemporaria);
                        controllerNumeroContaLista = contasList.size() - 1;
                        break;
                    case 3:
                        saidaPrograma = false;
                        System.out.println("Programa Finalizado !!");
                        break;
                    default:
                        System.out.println("Opção inválida !!");
                }

                // Menu da Redes Social
                while (controllerSaidaConta) {

                    if (contasList.get(controllerNumeroContaLista).getListaMensagens().size() >= 1) {
                        contasList.get(controllerNumeroContaLista).ExibirMensagens(false);
                    }

                    if (contasList.get(controllerNumeroContaLista).getListaAmigosPendentes().size() >= 1) {
                        contasList.get(controllerNumeroContaLista).ExibirAmigosPendentes();
                    }

                    MenuController.exibirMenuRedeSocial();
                    int op = in.nextInt();

                    switch (op) {
                        case 1:
                            System.out.println("Qual usuario que voce deseja mandar o Convite ?");
                            String usuarioTemp1 = in.next();
                            String mensagem1 = "Usuario não encontrado";
                            for (int i = 0; i < contasList.size(); i++) {
                                if (contasList.get(i).getNome().equals(usuarioTemp1)) {
                                    contasList.get(i).ReceberConviteAmizade(
                                            contasList.get(controllerNumeroContaLista).getNome());
                                    mensagem1 = "Convite enviado com sucesso !!";
                                }
                            }
                            System.out.println(mensagem1);
                            break;
                        case 2:
                            boolean saidaAmigos = true;
                            while (saidaAmigos) {
                                System.out.println("");
                                contasList.get(controllerNumeroContaLista).ExibirAmigosPendentes();
                                System.out.println("");
                                System.out.println(
                                        "Você deseja aceitar algum convite? Se sim digite o username do convite, ou digite 1 para sair do MENU de Amigos");
                                String num = in.next();
                                saidaAmigos = !num.equals("1") || contasList.get(controllerNumeroContaLista)
                                        .getListaAmigosPendentes().size() == 0;
                                contasList.get(controllerNumeroContaLista).AceitarConviteDeAmizade(num);
                            }
                            break;
                        case 3:
                            contasList.get(controllerNumeroContaLista).ExibirMensagens(true);
                            break;
                        case 4:
                            System.out.println("Qual usuario que voce deseja mandar a mensagem ?");
                            String usuarioTemp = in.next();
                            System.out.println("Qual a mensagem que você deseja mandar ?");
                            String mensagemTemp = in.next();
                            String mensagem = "Usuario não encontrado";
                            for (int i = 0; i < contasList.size(); i++) {
                                if (contasList.get(i).getNome().equals(usuarioTemp)) {
                                    contasList.get(i).AdcionarMensagensRecebidas(
                                            contasList.get(controllerNumeroContaLista).getNome(), mensagemTemp);
                                    mensagem = "Mensagem enviada com sucesso !!";
                                }
                            }
                            System.out.println(mensagem);
                            break;
                        case 5:
                            System.out.println("Qual sua senha antiga ?");
                            String senhaAntigaTemp = in.next();
                            if (contasList.get(controllerNumeroContaLista).ValidarLogin(
                                    contasList.get(controllerNumeroContaLista).getNome(), senhaAntigaTemp)) {
                                System.out.println("Qual sua nova senha ?");
                                String senhaNovaTemp = in.next();
                                contasList.get(controllerNumeroContaLista).ResetarSenha(senhaNovaTemp);
                            } else {
                                System.out.println("Senha antiga INCORRETA !!");
                            }
                            break;

                        case 6:
                            controllerAchouConta = false;
                            controllerSaidaConta = false;
                            controllerNumeroContaLista = 0;
                            System.out.println("Você saiu da sua conta");
                            break;
                        default:
                            System.out.println("Opção inválida !!");
                    }
                }
            }
        }
    }
}