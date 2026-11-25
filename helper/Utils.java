package helper;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.Scanner; 
import java.util.concurrent.TimeUnit;

public class Utils 
{
    // Formatador de SAÍDA (sempre HH:mm)
    static DateTimeFormatter dtfSaida = DateTimeFormatter.ofPattern("HH:mm");

    // Formatador de ENTRADA (aceita múltiplos formatos)
    static DateTimeFormatter dtfEntrada = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("H:mm[:ss]"))   // Ex: 9:30, 14:30, 14:30:00
            .appendOptional(DateTimeFormatter.ofPattern("H'h'mm"))     // Ex: 9h30, 14h30
            .appendOptional(DateTimeFormatter.ofPattern("H'h'"))       // Ex: 9h, 14h
            .appendOptional(DateTimeFormatter.ofPattern("HHmm"))       // Ex: 1430, 0930
            .appendOptional(DateTimeFormatter.ofPattern("h:mm a", Locale.US)) // Ex: 2:30 PM
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0) // Garante minuto 00 se digitar só "14h"
            .toFormatter();

    // Conversão de horário para string (mantém o padrão HH:mm)
    public static String timeParaString(LocalTime horario) {
        if (horario == null) return null;
        return dtfSaida.format(horario);
    }

    // Conversão de String flexível para LocalTime
    public static LocalTime stringParaLocalTime(String horario) {
        if (horario == null || horario.isBlank()) {
            return null;
        }
        try {
            // .trim() evita erro se o usuário digitar espaço antes ou depois
            return LocalTime.parse(horario.trim(), dtfEntrada);
        } catch (DateTimeParseException e) {
            return null;
        }
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

    // Obrigação do preenchimento dos campos
    public static String lerString(Scanner entrada, String pergunta)
    {
        String texto = "";
        while (texto.isBlank())
        {
            System.out.print(pergunta);
            texto = entrada.nextLine().trim();
        
            if (texto.isBlank())
            {
                System.out.println("Campo obrigatorio!! Insira algum dado");
            }
        }
        return texto;
    }

    // Método para pausar o programa em alguns segundos
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
