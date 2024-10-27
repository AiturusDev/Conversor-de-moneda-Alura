import com.google.gson.annotations.SerializedName;

import java.sql.Time;
import java.time.LocalDateTime;

public class Divisas {

    private String moneda;
    private String cambio;
    private double cantidad;
    private float tasaDeCambio;
    private float valorCambio;
    private java.time.LocalDateTime LocalDateTime;

    LocalDateTime nuevoDate = java.time.LocalDateTime.now();


    public String getMoneda() {
        return moneda;
    }
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    public String getCambio() {
        return cambio;
    }
    public void setCambio(String cambio) {
        this.cambio = cambio;
    }
    public double getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    public Divisas(){
    }

    public Divisas(Conversion nuevaConversion){
        this.moneda= nuevaConversion.base_code();
        this.cambio=nuevaConversion.target_code();
        this.tasaDeCambio = Float.valueOf(nuevaConversion.conversion_rate()) ;
        this.valorCambio = Float.valueOf(nuevaConversion.conversion_result());
    }

    @Override
    public String toString() {
        return "El cambio para "+(this.valorCambio/this.tasaDeCambio)+" "+this.moneda + " es de " + this.valorCambio +
                " "+ this.cambio +" a una tasa de cambio de "+ this.tasaDeCambio+" "+this.cambio+" por "+this.moneda+
                ". (fecha y hora de consulta: "+nuevoDate+")";
    }
}
