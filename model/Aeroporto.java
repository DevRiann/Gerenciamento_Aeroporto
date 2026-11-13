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
    public static Voo localizarVoo(int numeroVoo)
    {
        for (Voo voo : voos)
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
    // ATUALIZAR VOOS
    // Recebe os atributos do voo, faz uma busca do voo, verifica o status e tenta atualizar o voo
    // ---------------------------------------------------------------
    public static boolean atualizar (int numero, String novaCompanhia, String novoEmbarque, String novoDesembarque, String novoDestino) {
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
    public static boolean atualizarStatus (int numeroVoo) {
        
        for (Voo voo : getVoos())
        {
            int proximoStatus = voo.getPosicaoStatus();
            if (proximoStatus == voo.getStatus().length()-1) {
                return false;
            } 
            else{
                proximoStatus++;
                voo.setPosicaoStatus(proximoStatus);
            }
        }
        return true;
    }
}

    
