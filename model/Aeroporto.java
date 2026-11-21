package model;

import helper.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Aeroporto 
{
    private static final ArrayList<Voo> voos = new ArrayList<>();

    // ---------------------------------------------------------------
    // CADASTRAR VOOS
    // Apenas armazena os dados do voo
    // ---------------------------------------------------------------
    public static void cadastrar(Voo voo)
    {
        voos.add(voo);
    }

    // ---------------------------------------------------------------
    // Lista de Voos (encapsulamento)
    // Apenas exibe a lista de voos, não permitindo fazer alterações
    // ---------------------------------------------------------------
    public static List<Voo> getVoos()
    {
       return Collections.unmodifiableList(voos); 
    }

    // ---------------------------------------------------------------
    // ORDENAR VOOS POR ORDEM DE EMBARQUE
    // Pega a lista e comparar o horario de embarque, em ordem crescente
    // ---------------------------------------------------------------
    public static void ordenarVoo()
    {
        voos.sort(Comparator.comparing(Voo::getEmbarque));
    }

    // ---------------------------------------------------------------
    // BUSCAR VOOS
    // Procura o voo pelo numero de ID
    // ---------------------------------------------------------------
    public static Voo localizarVoo(String numeroVoo)
    {
        for (Voo voo : voos)
        {
            if (voo.getNumeroVoo().equalsIgnoreCase(numeroVoo))
            {
                return voo;
            }
        }
        return null;
    }
    
    // ---------------------------------------------------------------
    // ATUALIZAR VOOS
    // Recebe os atributos do voo, faz uma busca do voo, verifica o status e tenta atualizar o voo
    // ---------------------------------------------------------------
    public static boolean atualizar (String numero, String novaCompanhia, String novoEmbarque, String novoDesembarque, String novoDestino) {
        Voo voo = localizarVoo(numero);
        if (voo == null) 
        {
            return false;
        }

        if (voo.getStatus().equalsIgnoreCase("DECOLOU")) 
        {
            return false;
        } 
        else
        {
            voo.setCompanhia(novaCompanhia);
            voo.setEmbarque(Utils.stringParaLocalTime(novoEmbarque));
            voo.setDesembarque(Utils.stringParaLocalTime(novoDesembarque));
            voo.setDestino(novoDestino);
        }

        return true;
    }

    // ---------------------------------------------------------------
    // ATUALIZAR O STATUS DO VOOS
    // Verifica o status atual e pula para o proximo status
    // ---------------------------------------------------------------
    public static boolean atualizarStatus(String numeroVoo)
    {
        Voo voo = localizarVoo(numeroVoo);
        if (voo == null)
        {
            return false;
        }
        return voo.avancarStatus();
    }

    // ---------------------------------------------------------------
    // EXCLUIR VOOS
    // Processamento para excluir dados do voo
    // ---------------------------------------------------------------
    public static boolean excluir(String numeroVoo)
    {
        Voo voo = localizarVoo(numeroVoo);

        if (voo == null) 
        {
            return false;
        }

        if (!voo.getStatus().equalsIgnoreCase("DECOLOU")) 
        {
            return false;
        }

        voos.remove(voo);

        return true;
    }

    // ---------------------------------------------------------------
    // EXIBIR QUANTIDADE DE VOOS POR STATUS
    // Verificar toda lista e gerar um contador por status
    // ---------------------------------------------------------------
    public static int contarStatus (String status)
    {
        int quantidade = 0;
        for (Voo voo : Aeroporto.getVoos())
        {
            if (voo.getStatus().equals(status)) 
            {
                quantidade++;
            }
        }
        return quantidade;
    }
}

    
