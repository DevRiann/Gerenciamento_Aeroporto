package helper;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;   
import java.util.concurrent.TimeUnit;

public class Utils 
{
    private static Random rand = new Random();
    private static Set<Integer> usados = new HashSet<>();
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
    
    // Conversão de horário para string
    public static String timeParaString (LocalTime horario)
    {
        return dtf.format(horario);
    }

    // Conversão de String para o formato de horário
    public static LocalTime stringParaLocalTime (String horario)
    {
        try {
            return LocalTime.parse(horario);    
        } 
        catch (DateTimeParseException e) {
            return null;
        }
    }

    // Geracao de numero aleatorio para a identificao do voo
    public static int gerarNumeroUnico(int min, int max)
    {
        int numero;
        do {
            numero = rand.nextInt(max - min) + min;
        } while (usados.contains(numero));

        usados.add(numero);
        return numero;
    }

    // Validacao de entrada para horario
    public static LocalTime lerHorario(Scanner entrada, String pergunta)
    {
        LocalTime horario = null;
        while (horario == null) 
        {
            System.out.print(pergunta);
            horario = Utils.stringParaLocalTime(entrada.nextLine());
            
            if (horario == null) 
            {
                System.out.println("Horario invalido, selecione entre 00:00 a 23:59");    
            }
        }
        return horario;

    }

    // Obrigacao de preenchimento dos campos
    public static String lerString(Scanner entrada, String pergunta)
    {
        String texto = "";
        while (texto.isEmpty()) 
        {
            System.out.print(pergunta);
            texto = entrada.nextLine();

            if(texto.isEmpty() )
            {
                System.out.println("Campo obrigatorio!! Insira algum dado");
            }
            
        }
        return texto;
    }

    public static void pausar(int segundos)
    {
        try {
            TimeUnit.SECONDS.sleep(segundos);    
        } 
        catch (InterruptedException e) {
            System.out.println("Erro ao esperar "+ segundos +" segundos");
        }
    }

}
