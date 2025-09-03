package model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private final String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() { return salario; }
    public void setSalario(BigDecimal salario) { this.salario = salario; }

    public String getFuncao() { return funcao; }
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String salarioFormatado = String.format("%,.2f", salario).replace(",", "X")
                .replace(".", ",").replace("X", ".");

        return "Nome: " + getNome() +
                " | Data Nascimento: " + getDataNascimento().format(formatter) +
                " | Salário: R$ " + salarioFormatado +
                " | Função: " + funcao +
                " | Idade: " + getIdade() + " anos";
    }
}