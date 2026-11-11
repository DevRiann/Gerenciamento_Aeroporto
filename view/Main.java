package view;

import model.*;

import java.util.Scanner;

import helper.*;

public class Main
{
    public static void main(String[] args) 
    {
        Main.menu();
    }
    
    private static Scanner teclado = new Scanner(System.in);
    private static final String formato = "%-6S | %-15S | %-9S | %-11S | %-15S | %-10S%n";

    public static void menu()
    {
        int opcao = 0;
        do
        {
            System.out.println("\n========================================");
            System.out.println("   SISTEMA DE PAINEL DE VOOS - AEROPORTO");
            System.out.println("========================================");
            System.out.println("1. Cadastrar novo voo");
            System.out.println("2. Consultar painel de voos");
            System.out.println("3. Alterar informações de um voo");
            System.out.println("4. Alterar o status de um voo");
            System.out.println("5. Excluir um voo");
            System.out.println("6. Consultar um voo específico");
            System.out.println("7. Encerrar o sistema");
            System.out.print("Escolha uma opção: ");
            if (teclado.hasNextInt()) 
            {
                opcao = Integer.parseInt(teclado.nextLine());
            } else {
                System.out.println("Opção inválida. Por favor, digite um número de 1 a 7.");
                continue;
            }

            switch (opcao) 
            {
                case 1:
                    // cadastrar();
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
                    break;
            }

            teclado.close();
            
        } while (opcao != 5);
    }
}
