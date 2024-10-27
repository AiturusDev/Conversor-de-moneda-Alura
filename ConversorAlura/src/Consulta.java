import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Scanner;

public class Consulta {

    private String busqueda = "https://v6.exchangerate-api.com/v6/c14c1c59ac21f57b0d5b02c0/pair/";
    private ArrayList<Divisas> consultas = new ArrayList<>();

    Menu nuevoMenu = new Menu();
    Divisas nuevaDivisa = new Divisas();

    public ArrayList<Divisas> getConsultas() {
        return consultas;
    }
    public void clasificacion() throws IOException, InterruptedException {

        System.out.println(nuevoMenu.toString());
        Scanner scanner = new Scanner(System.in);
        var opcion = scanner.nextInt();

        while (opcion <7){

            switch(opcion){
                case 1:
                    nuevaDivisa.setMoneda("USD");
                    nuevaDivisa.setCambio("ARS");
                    break;
                case 2:
                    nuevaDivisa.setMoneda("ARS");
                    nuevaDivisa.setCambio("USD");
                    break;

                case 3:
                    nuevaDivisa.setMoneda("USD");
                    nuevaDivisa.setCambio("BRL");
                    break;

                case 4:
                    nuevaDivisa.setMoneda("BRL");
                    nuevaDivisa.setCambio("USD");
                    break;

                case 5:
                    nuevaDivisa.setMoneda("USD");
                    nuevaDivisa.setCambio("COP");
                    break;

                case 6:
                    nuevaDivisa.setMoneda("COP");
                    nuevaDivisa.setCambio("USD");
                    break;

            }
            System.out.println("Ingrese el monto de " +nuevaDivisa.getMoneda()+" a convertir");
            nuevaDivisa.setCantidad(scanner.nextInt());
            consultaApi();

            System.out.println(nuevoMenu.toString());
            opcion= 0;
            opcion= scanner.nextInt();
        }

        System.out.println("Gracias por usar nuestros servicios.");

    }

    public void consultaApi() throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(busqueda+nuevaDivisa.getMoneda()+"/"+nuevaDivisa.getCambio()+"/"+nuevaDivisa.getCantidad()))
                .build();

        HttpResponse<String> response = client
                .send(request,HttpResponse.BodyHandlers.ofString());

        String json = response.body();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .create();

        Conversion nuevaConversion = gson.fromJson(json, Conversion.class);
        Divisas nuevaDivisa = new Divisas(nuevaConversion);
        System.out.println(nuevaDivisa.toString());

        consultas.add(nuevaDivisa);

    }
}
