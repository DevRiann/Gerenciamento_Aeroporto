package view;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import helper.Utils;
import model.Voo;

public class Aeroporto {
    
    static Scanner teclado = new Scanner(System.in);
    static ArrayList<Voo> PainelAeroporto = new ArrayList<>();
    static int sairPainel;
    static int opcao;
    static final String formato = "%-6S | %-15S | %-9S | %-11S | %-15S | %-10S%n";

    public static void main(String[] args) {
    menu();
    }

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
        System.out.println("1 - Cadastrar Voo \n2- Listar Voo \n3- Buscar Voo \n4- Alterar Voo \n5- Deletar Voo \n6- Sair do Sistema");
        
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
                listarVoos();
                break;
            case 3:
                buscarVoos();
                break;
            case 4:
                // alterar();
                break;
            case 5:
                // deletar();
                break;

            case 6:
                //sair();
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
        int numeroVoo = Utils.gerarNumeroUnico(1000, 9999);
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

    // ---------------------------------------------------------------
    // LISTAR VOOS
    // Exibe todos os voos cadastrados em formato de tabela
    // ---------------------------------------------------------------
    public static void listarVoos()
    {
        // Se a lista estiver vazia, avisa e volta ao menu
        if (PainelAeroporto.isEmpty())
        {
            System.out.println("Nenhum voo cadastrado no momento.");
            Utils.pausar(2);
            menu();
            return; // evita continuar o metodo depois de chamar o menu
        }

        // Cabecalho do painel
        System.out.println("--------------------------");
        System.out.println("| AEROPORTO DE SAO PAULO |");
        System.out.println("--------------------------");
        System.out.println();

        // Usa a constante "formato" para manter as colunas alinhadas
        System.out.printf(formato, "ID", "COMPANHIA AEREA", "EMBARQUE", "DESEMBARQUE", "DESTINO", "STATUS");

        // Percorre a lista e imprime uma linha para cada voo
        for (Voo voo : PainelAeroporto)
        {
            System.out.printf(formato, voo.getNumeroVoo(), voo.getCompanhia(),
                Utils.timeParaString(voo.getEmbarque()), Utils.timeParaString(voo.getDesembarque()),
                voo.getDestino(), voo.getStatus());
        }

        System.out.println();
        System.out.println("Total de voos: " + PainelAeroporto.size());
        System.out.println("Pressione [Enter] para voltar ao Menu");
        teclado.nextLine(); // espera o usuario apertar Enter
        menu();
    }



    public static Voo localizarVoo(int numeroVoo)
    {
        for (Voo voo : PainelAeroporto)
        {
            // Compara int com int, entao usamos == (nao precisa de equals)
            if (voo.getNumeroVoo() == numeroVoo)
            {
                return voo; // achou: devolve o voo e encerra o metodo
            }
        }
        return null; // percorreu tudo e nao achou
    }


    // ---------------------------------------------------------------
    // BUSCAR VOOS
    // Procura o voo pelo numero de ID
    // ---------------------------------------------------------------
    public static void buscarVoos()
    {
        System.out.println("Consultar Voo");
        int numero;

        // Tratamento de erro caso o usuario digite letras no lugar do numero
        try
        {
            // lerString garante que o campo nao fique vazio; trim remove espacos
            numero = Integer.parseInt(Utils.lerString(teclado, "Digite o numero do voo: ").trim());
        }
        catch (NumberFormatException e)
        {
            System.out.println("Numero invalido! Consulta nao realizada.");
            Utils.pausar(2);
            menu();
            return;
        }

        // Usa o metodo auxiliar para procurar o voo na lista
        Voo voo = localizarVoo(numero);

        if (voo == null)
        {
            // Regra de negocio: informar quando o voo nao existe
            System.out.println("Voo " + numero + " nao encontrado. Consulta nao realizada.");
        }
        else
        {
            // Mostra todas as informacoes do voo encontrado
            System.out.println("--------------------------");
            System.out.println("Numero do voo:       " + voo.getNumeroVoo());
            System.out.println("Companhia aerea:     " + voo.getCompanhia());
            System.out.println("Horario embarque:    " + Utils.timeParaString(voo.getEmbarque()));
            System.out.println("Horario desembarque: " + Utils.timeParaString(voo.getDesembarque()));
            System.out.println("Destino:             " + voo.getDestino());
            System.out.println("Status:              " + voo.getStatus());
            System.out.println("--------------------------");
            System.out.println("Consulta realizada com sucesso!");
        }

        Utils.pausar(2);
        menu();
    }

    
}







    
