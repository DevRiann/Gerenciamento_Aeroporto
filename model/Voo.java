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
    private String status;

    public Voo (int numeroVoo, String companhia, LocalTime embarque, LocalTime desembarque, String destino)
    {
        this.numeroVoo = numeroVoo;
        this.companhia = companhia;
        this.embarque = embarque;
        this.desembarque = desembarque;
        this.destino = destino;
        this.status = "previsto";
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
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
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
