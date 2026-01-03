import java.time.LocalDate;
import java.util.Random;

public class Idoso {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private LocalDate dataNascimento;
    private String codigoConexao;

    public Idoso() {
    	
    }
    public Idoso(String nome, String email, String senha, LocalDate dataNascimento, String cpf) {
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.codigoConexao = gerarCodigoConexao();


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

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getNome() {
	return nome;
}

public void setNome(String nome) {
	this.nome = nome;
}

public String getCpf() {
	return cpf;
}

public void setCpf(String cpf) {
	this.cpf = cpf;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getSenha() {
	return senha;
}

public void setSenha(String senha) {
	this.senha = senha;
}

public LocalDate getDataNascimento() {
	return dataNascimento;
}

public void setDataNascimento(LocalDate dataNascimento) {
	this.dataNascimento = dataNascimento;
}

public String getCodigoConexao() {
	return codigoConexao;
}

public void setCodigoConexao(String codigoConexao) {
	this.codigoConexao = codigoConexao;
}

}
