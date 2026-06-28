package vidafacil.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoBanco {

    public static Connection conectar() {
        String pastaUsuario = System.getProperty("user.home");

        File diretorioApp = new File(pastaUsuario, ".vidafacil");

        if (!diretorioApp.exists()) {
            diretorioApp.mkdirs();
        }

        File arquivoBanco = new File(diretorioApp, "banco_offline.db");
        String url = "jdbc:sqlite:" + arquivoBanco.getAbsolutePath();

        try {
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            System.out.println("Falha catastrófica ao conectar com SQLite: " + e.getMessage());
            return null;
        }
    }
}