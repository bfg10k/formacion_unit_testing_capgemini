package org.capgemini.gestor;
import spark.Spark;

public class App {
    public static void main(String[] args) {
        Spark.post("/register", ExpedienteController::register);
    }
}