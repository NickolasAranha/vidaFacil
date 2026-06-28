package vidafacil.model;

import java.time.LocalDate;
import java.util.Random;

public class Idoso extends Usuario {
    private String CodigoConexao;

    public Idoso(String nome, String senha, String email, String cpf, LocalDate dataNascimento) {
        super(nome, senha, cpf, email, dataNascimento);
        this.CodigoConexao = gerarCodigoConexao();
    }

    public static String gerarCodigoConexao() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(10);
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }

        return sb.toString();
    }

    public String getCodigoConexao() {
        return CodigoConexao;
    }

    public void setCodigoConexao(String codigoConexao) {
        CodigoConexao = codigoConexao;
    }
}