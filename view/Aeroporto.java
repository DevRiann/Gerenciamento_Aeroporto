package view;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import helper.Utils;
import model.Voo;

public class Aeroporto 
{
    
    static Scanner teclado = new Scanner(System.in);
    static ArrayList<Voo> PainelAeroporto = new ArrayList<>();
    static int sairPainel;
    static int opcao;
    static final String formato = "%-6S | %-15S | %-9S | %-11S | %-15S | %-10S%n";

    // Metodo para as visualizações do voos
    public static void painel()
    {
        if (PainelAeroporto.size() > 0)
        {
            System.out.println("--------------------------");
            System.out.println("| AEROPORTO DE SAO PAULO |");
            System.out.println("--------------------------");
            System.out.println();
            System.out.printf(formato, "ID", "COMPANHIA AEREA", "EMBARQUE", "DESEMBARQUE", "DESTINO", "STATUS");

            for(Voo voo : PainelAeroporto)
            {
                System.out.printf(formato, voo.getNumeroVoo(), voo.getCompanhia(), 
                Utils.timeParaString(voo.getEmbarque()), Utils.timeParaString(voo.getDesembarque()), 
                voo.getDestino(), voo.getStatus());
            }

            System.out.println("Pressione [0] para ir ao Menu");
            
            // Tratamento de erro, caso o usuário digite outro número ou caracter aleatório
            try 
            {
                sairPainel = Integer.parseInt(teclado.nextLine());

                if (sairPainel == 0) 
                {
                    System.out.println();
                    menu();    
                }
                else 
                {
                
                    painel();
                }
            } 
            catch (NumberFormatException e) 
            {
                Utils.pausar(2);
                painel();    
            }
            
        }
        else
        {
            Utils.pausar(2);
            cadastrar();
        }
    }

    public static void menu()
    {
        
        System.out.println("O que voce deseja fazer agora? 💭");
        System.out.println("1 - Cadastrar Voo \n2- Atualizar Voo \n3- Listar Voo \n4- Deletar Voo \n5- Sair do Sistema");
        
        try {
            opcao = Integer.parseInt(teclado.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opcao invalida!!");
            Utils.pausar(2);
            menu();
        }

        switch (opcao) {
            case 1:
                cadastrar();
                break;
            case 2:
                // atualizar();
                break;
            case 3:
                // listar();
                break;
            case 4:
                // deletar();
                break;
            case 5:
                // sair();
                break;
            default:
                System.out.println("Opcao invalida!!");
                Utils.pausar(2);
                menu();
                break;
        }

    }

    public static void cadastrar()
    {
        System.out.println("Cadastrando um novo Voo");
        int numeroVoo = Utils.gerarNumeroUnico(100, 9999);
        String companhia = Utils.lerString(teclado, "Digite a Companhia Aerea: ");
        LocalTime embarque = Utils.lerHorario(teclado, "Digite o Horario de Embarque (no formato HH:mm): ");
        LocalTime desembarque = Utils.lerHorario(teclado, "Digite o Horario de Desembarque (no formato HH:mm): ");

        while (desembarque.isBefore(embarque) || desembarque.equals(embarque)) 
        {
            System.out.println("O Horario de Desembarque esta inferior ou igual ao Horario de Embarque");
            desembarque = Utils.lerHorario(teclado, "Digite o Horario de Desembarque (no formato HH:mm): ");    
        }

        String destino = Utils.lerString(teclado, "Digite o destino: ");

        Voo voo = new Voo(numeroVoo, companhia, embarque, desembarque, destino);
        PainelAeroporto.add(voo);
        System.out.println("Cadastro do Voo concluido com sucesso!!");
        System.out.println("--------------------------------------");
        Utils.pausar(2);
        painel();
    }

    
}

    
