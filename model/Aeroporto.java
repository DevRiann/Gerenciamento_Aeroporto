package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import helper.Utils;


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
    
    public static boolean excluir(String numeroVoo) {
    Voo voo = localizarVoo(numeroVoo);

    if (voo == null) {
        return false;
    }

    if (!voo.getStatus().equalsIgnoreCase("DECOLOU")) {
        return false;
    }

    voos.remove(voo);

    return true;
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
        if (voo == null) {
            return false;
        }

        if (voo.getStatus().equalsIgnoreCase("DECOLOU")) {
            return false;
        } 
        else{
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
}

    
