package view;

import model.*;
import helper.*;
import java.time.LocalTime;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) 
    {
        Main.menu();
    }
    
    private static Scanner teclado = new Scanner(System.in);
    private static final String formato = "%-6S | %-15S | %-9S | %-11S | %-15S | %-10S%n";
    private static int opcao;

    public static void menu()
    {
        do
        {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE PAINEL DE VOOS - AEROPORTO");
            System.out.println("========================================");
            System.out.println("1. Cadastrar novo voo");
            System.out.println("2. Consultar painel de voos");
            System.out.println("3. Consultar um voo especifico ");
            System.out.println("4. Alterar informações de um voo");
            System.out.println("5. Alterar o status de um voo");
            System.out.println("6. Excluir um voo");
            System.out.println("7. Encerrar o sistema");
            System.out.print("Escolha uma opção: ");

            try 
            {
                opcao = Integer.parseInt(teclado.nextLine());
            } 
            catch (NumberFormatException e) {
                opcao = 0;
                Utils.pausar(2);
            }

            switch (opcao) 
            {
                case 1:
                    cadastrarVoos();
                    break;
                case 2:
                    listarVoos();
                    break;
                case 3:
                    buscarVoos();
                    break;
                case 4:
                    atualizarVoos();
                    break;
                case 5: 
                    atualizarStatusVoos();
                    break;
                case 6:
                    deletarVoos();
                    break;
                case 7:
                    System.out.println("Volte Sempre!!");
                    System.out.println();
                    break;
                default:
                    System.out.println("Opcao Invalida, tente novamente");
                    break;
            }
            
        } while (opcao != 7);
    }

    public static void cadastrarVoos()
    {
        System.out.println();
        System.out.println("Cadastrando um novo Voo");
        String numeroVoo = Utils.lerString(teclado, "Digite o numero do voo (ex.: LA3456): ").trim().toUpperCase();
        while (Aeroporto.localizarVoo(numeroVoo) != null)
        {
            System.out.println("Erro: ja existe um voo com o numero " + numeroVoo);
            numeroVoo = Utils.lerString(teclado, "Digite outro numero de voo: ").trim().toUpperCase();
        }

        String companhia = Utils.lerString(teclado, "Digite a Companhia Aerea: ");
        LocalTime embarque = Utils.lerHorario(teclado, "Digite o Horario de Embarque (no formato HH:mm): ");
        LocalTime desembarque = Utils.lerHorario(teclado, "Digite o Horario de Desembarque (no formato HH:mm): ");

        while (desembarque.isBefore(embarque) || desembarque.equals(embarque)) 
        {
            System.out.println("Erro: O Horario de Desembarque esta inferior ou igual ao Horario de Embarque");
            desembarque = Utils.lerHorario(teclado, "Digite o Horario de Desembarque (no formato HH:mm): ");    
        }

        String destino = Utils.lerString(teclado, "Digite o destino: ");

        Voo voo = new Voo(numeroVoo, companhia, embarque, desembarque, destino);
        Aeroporto.cadastrar(voo);
        System.out.println("Cadastro do Voo concluido com sucesso!!");
        System.out.println("--------------------------------------");
        Utils.pausar(2);
        menu();
    }

    public static void listarVoos()
    {
        // Se a lista estiver vazia, avisa e volta ao menu
        if (Aeroporto.getVoos().isEmpty())
        {
            System.out.println("Nenhum voo cadastrado no momento.");
            Utils.pausar(2);
            return; 
        }
        
        System.out.println("--------------------------");
        System.out.println("| AEROPORTO DE SAO PAULO |");
        System.out.println("--------------------------");
        System.out.println();

        Aeroporto.ordenarVoo(); // Ordenar voo por ordem de Embarque

        System.out.printf(formato, "ID", "COMPANHIA AEREA", "EMBARQUE", "DESEMBARQUE", "DESTINO", "STATUS");
        for (Voo voo : Aeroporto.getVoos())
        {
            System.out.printf(formato, voo.getNumeroVoo(), voo.getCompanhia(),
                Utils.timeParaString(voo.getEmbarque()), Utils.timeParaString(voo.getDesembarque()),
                voo.getDestino(), voo.getStatus());
        }

        System.out.println();
        System.out.println("Total de voos: " + Aeroporto.getVoos().size());
        System.out.println("Voos com status (Previsto): " + Aeroporto.contarStatus("previsto"));
        System.out.println("Voos com status (Confirmado): " + Aeroporto.contarStatus("confirmado"));
        System.out.println("Voos com status (Embarque em Curso): " + Aeroporto.contarStatus("embarque em curso"));
        System.out.println("Voos com status (Embarque Encerrado): " + Aeroporto.contarStatus("embarque encerrado"));
        System.out.println("Voos com status (Decolou): " + Aeroporto.contarStatus("decolou"));
        System.out.println("Pressione [Enter] para voltar ao Menu");
        teclado.nextLine(); // espera o usuario apertar Enter
        return;
    }

    public static void buscarVoos()
    {
        System.out.println();
        System.out.println("CONSULTAR VOO");
        String numeroVoo = Utils.lerString(teclado, "Digite o numero do voo: ").trim();

        // Usa o metodo auxiliar para procurar o voo na lista
        Voo voo = Aeroporto.localizarVoo(numeroVoo);

        if (voo == null)
        {
            // Regra de negocio: informar quando o voo nao existe
            System.out.println("Voo " + numeroVoo + " nao encontrado. Consulta nao realizada.");
        }
        else
        {
            // Mostra todas as informacoes do voo encontrado
            System.out.println("--------------------------");
            System.out.println("Numero do voo:       " + voo.getNumeroVoo());
            System.out.println("Companhia aerea:     " + voo.getCompanhia().toUpperCase());
            System.out.println("Horario embarque:    " + Utils.timeParaString(voo.getEmbarque()).toUpperCase());
            System.out.println("Horario desembarque: " + Utils.timeParaString(voo.getDesembarque()).toUpperCase());
            System.out.println("Destino:             " + voo.getDestino().toUpperCase());
            System.out.println("Status:              " + voo.getStatus().toUpperCase());
            System.out.println("--------------------------");
        }
        Utils.pausar(2);
        return;
    }

    public static void atualizarVoos()
    {
        System.out.println();
        System.out.println("Consultar Voo");
        String numeroVoo = Utils.lerString(teclado, "Digite o numero do voo: ").trim();

        String companhia = Utils.lerString(teclado, "Digite a nova Companhia Aerea: ");
        LocalTime novoEmbarque = Utils.lerHorario(teclado, "Digite o novo Horario de Embarque (no formato HH:mm): ");
        LocalTime novoDesembarque = Utils.lerHorario(teclado, "Digite o novo Horario de Desembarque (no formato HH:mm): ");

        while (novoDesembarque.isBefore(novoEmbarque) || novoDesembarque.equals(novoEmbarque)) 
        {
            System.out.println("Erro: O Horario de Desembarque esta inferior ou igual ao Horario de Embarque");
            novoDesembarque = Utils.lerHorario(teclado, "Digite o novo Horario de Desembarque (no formato HH:mm): ");    
        }

        String destino = Utils.lerString(teclado, "Digite o novo Destino: ");

        // Valida o êxito da atualização do voo e exibe na tela
        if (Aeroporto.atualizar(numeroVoo, companhia, Utils.timeParaString(novoEmbarque), Utils.timeParaString(novoDesembarque), destino)) 
        {
            System.out.println("Dados do voo " + numeroVoo + " atualizados com sucesso!!");
            
        } 
        else 
        {
            System.out.println("Erro: Nao foi possivel atualizar o voo numero: " + numeroVoo + ". Verifique novamente o numero e o status.");
        }

        Utils.pausar(2);
        return;
    }

    public static void atualizarStatusVoos(){
        System.out.println("");
        System.out.println("ATUALIZAR O STATUS DO VOO");
        String numeroVoo = Utils.lerString(teclado, "Digite o numero do voo: ").trim();

        Voo voo = Aeroporto.localizarVoo(numeroVoo);
        if (voo == null)
        {
            System.out.println("Erro: Voo " + numeroVoo + " nao encontrado.");
        }
        else
            {
                String proximo = voo.getProximoStatus();
            
                if (proximo == null)
                {
                    System.out.println("Erro: O voo " + numeroVoo + " ja decolou e nao possui proximo status.");
                }
                else
                {
                    System.out.println("Status atual:   " + voo.getStatus().toUpperCase());
                    System.out.println("Proximo status: " + proximo.toUpperCase());
                    String resposta = Utils.lerString(teclado, "Confirmar a alteracao? (S/N): ");
            
                    if (resposta.trim().equalsIgnoreCase("S"))
                    {
                        Aeroporto.atualizarStatus(numeroVoo);
                        System.out.println("Status atualizado com sucesso!!");
                        System.out.println("Status atual: " + voo.getStatus().toUpperCase());
                    }
                    else
                    {
                        System.out.println("Operacao cancelada.");
                    }
                }
            }
        
        Utils.pausar(2);
        return;

        }
        public static void deletarVoos()
        {

            System.out.println();
            System.out.println("EXCLUIR VOO");

            String numeroVoo = Utils.lerString(
            teclado, "Digite o numero do voo: "
            ).trim();

            Voo voo = Aeroporto.localizarVoo(numeroVoo);

            if (voo == null) 
            {
                System.out.println("Erro: Voo nao encontrado.");
            }

            else if (!voo.getStatus().equalsIgnoreCase("DECOLOU")) 
            {
                System.out.println("Erro: Nao e permitido excluir um voo que ainda nao decolou.");
            }

            else 
            {
                System.out.println("Voo encontrado: " + voo.getNumeroVoo().toUpperCase());
                System.out.println("Destino: " + voo.getDestino().toUpperCase());

                String resposta = Utils.lerString(
                teclado, "Deseja realmente excluir este voo? (S/N): "
                );

                if (resposta.equalsIgnoreCase("S")) 
                {
                    if (Aeroporto.excluir(numeroVoo)) 
                    {
                        System.out.println("Voo excluido com sucesso!");
                    } else {
                        System.out.println("Erro ao excluir o voo.");
                    }

                } 
                else 
                {
                    System.out.println("Operacao cancelada.");
                }
            }

            Utils.pausar(2);
        }
}