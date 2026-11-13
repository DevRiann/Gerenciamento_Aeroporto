package model;

import java.time.LocalTime;

import helper.Utils;

public class Voo 
{
    private int numeroVoo;
    private String companhia;
    private LocalTime embarque;
    private LocalTime desembarque;
    private String destino;
    private static final String[] status = {"previsto", "confirmado", "embarque em curso", "embarque encerrado", "decolou"};
    private int posicaoStatus;

    public Voo (int numeroVoo, String companhia, LocalTime embarque, LocalTime desembarque, String destino)
    {
        this.numeroVoo = numeroVoo;
        this.companhia = companhia;
        this.embarque = embarque;
        this.desembarque = desembarque;
        this.destino = destino;
        this.posicaoStatus = 0;
    }
    
    
    public void setNumeroVoo(int numeroVoo) {
        this.numeroVoo = numeroVoo;
    }
    public int getNumeroVoo() {
        return numeroVoo;
    }
    public void setCompanhia(String companhia) {
        this.companhia = companhia;
    }
    public String getCompanhia() {
        return companhia;
    }
    public void setDestino(String destino) {
        this.destino = destino;
    }
    public String getDestino() {
        return destino;
    }
    public String getStatus() {
        return status[posicaoStatus];
    }
    public void setEmbarque(LocalTime embarque) {
        this.embarque = embarque;
    }
    public LocalTime getEmbarque() {
        return embarque;
    }
    public void setDesembarque(LocalTime desembarque) {
        this.desembarque = desembarque;
    }
    public LocalTime getDesembarque() {
        return desembarque;
    }
    public void setPosicaoStatus(int posicaoStatus) {
        this.posicaoStatus = posicaoStatus;
    }
    public int getPosicaoStatus() {
        return posicaoStatus;
    }

    @Override
    public String toString() 
    {
        return 
                ""+this.getNumeroVoo()+
                " "+this.getCompanhia()+
                " "+ Utils.timeParaString(this.getEmbarque())+
                " " + Utils.timeParaString(this.getDesembarque())+
                " "+this.getDestino()+
                " "+this.getStatus();    
    }

}
