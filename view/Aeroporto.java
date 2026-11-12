package view;

import model.Voo;
import java.util.ArrayList;
import java.util.Collections;
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
}

    
