package com.avathartech.sp1isc415;

import spark.Request;
import spark.Response;

import java.util.Set;

import static spark.Spark.*;

/**
 * Created by vacax on 29/05/17.
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("Servidor Práctica #1 - Disponible");
        staticFiles.location("/publico");

        get("/", (request, response) -> {
            return "Servidor práctica #1 - Cliente HTTP";
        });

        post("/formulario2", (request, response) -> {
            System.out.println("Formualario 2");
            return procesandoPeticionPost(request, response);
        });

        post("/formulario3", (request, response) -> {
            System.out.println("Formualario 3");
            return procesandoPeticionPost(request, response);
        });


    }

    /**
     * 
     * @param request
     * @param response
     * @return
     */
    static String procesandoPeticionPost(Request request, Response response){
        String salida = "";
        Set<String> parametros = request.queryParams();
        for(String para :  parametros){
            System.out.println(String.format("param['%s'] = %s", para, request.queryParams(para)));
        }
        salida += "La cantida de parametros recibidos: "+parametros.size()+"\n";
        
        String valor = request.queryParams("asignatura");
        if(valor!=null && valor.equalsIgnoreCase("practica1")){
            salida += "parametro asinatura recibido \n";
        } else{
            salida += "Parametro de asignatura no enviado \n";
        }

        String valorCookie = request.headers("matricula");
        if(valorCookie != null){
            salida += "Header matrícula "+valorCookie+", enviada :-D";
        }else{
            salida += "Header no enviada :S";
        }
        System.out.println("La salida: "+salida);
        return salida;
    }
}
