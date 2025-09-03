package service;

import model.Funcionario;
import model.Pessoa;
import util.DateFormatter;
import util.FuncionarioUtil;

import javax.swing.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;

public class FuncionarioService {
    private static FuncionarioService instance;

    private FuncionarioService() {
    }
    public static FuncionarioService getInstance() {
        if (instance == null) {
            instance = new FuncionarioService();
        }
        return instance;
    }

    public void listarFuncionarios(List<Funcionario> funcionarios) {
        StringBuilder sb = new StringBuilder("LISTA DE FUNCIONÁRIOS:\n\n");
        for (Funcionario f : funcionarios) {
            sb.append(f.toString()).append("\n");
        }
        JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea(sb.toString())));
    }

    public void removerJoao(List<Funcionario> funcionarios) {
        boolean removido = funcionarios.removeIf(f -> f.getNome().equalsIgnoreCase("João"));
        if (removido) {
            JOptionPane.showMessageDialog(null, "Funcionário João removido com sucesso!");
        } else {
            JOptionPane.showMessageDialog(null, "Funcionário João não encontrado!");
        }
    }

    public void aplicarAumento(List<Funcionario> funcionarios) {
        for (Funcionario f : funcionarios) {
            BigDecimal novoSalario = f.getSalario().multiply(new BigDecimal("1.10"));
            f.setSalario(novoSalario.setScale(2, RoundingMode.HALF_UP));
        }
        JOptionPane.showMessageDialog(null, "Aumento de 10% aplicado a todos os funcionários!");
    }

    public void agruparPorFuncao(List<Funcionario> funcionarios) {
        Map<String, List<Funcionario>> agrupado = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        StringBuilder sb = new StringBuilder("FUNCIONÁRIOS AGRUPADOS POR FUNÇÃO:\n\n");
        for (Map.Entry<String, List<Funcionario>> entry : agrupado.entrySet()) {
            sb.append("--- ").append(entry.getKey()).append(" ---\n");
            for (Funcionario f : entry.getValue()) {
                sb.append("  • ").append(f.getNome()).append("\n");
            }
            sb.append("\n");
        }
        JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea(sb.toString())));
    }

    public void aniversariantesOutDez(List<Funcionario> funcionarios) {
        StringBuilder sb = new StringBuilder("ANIVERSARIANTES OUTUBRO/DEZEMBRO:\n\n");
        for (Funcionario f : funcionarios) {
            int mes = f.getDataNascimento().getMonthValue();
            if (mes == 10 || mes == 12) {
                sb.append(f.getNome())
                        .append(" - ")
                        .append(f.getDataNascimento().format(DateFormatter.DATE_FORMATTER))
                        .append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public void funcionarioMaisVelho(List<Funcionario> funcionarios) {
        Funcionario maisVelho = Collections.max(funcionarios,
                Comparator.comparing(Pessoa::getIdade));

        String mensagem = "FUNCIONÁRIO MAIS VELHO:\n" +
                "Nome: " + maisVelho.getNome() +
                "\nIdade: " + maisVelho.getIdade() + " anos" +
                "\nData Nascimento: " + maisVelho.getDataNascimento().format(DateFormatter.DATE_FORMATTER);
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public void ordenarAlfabeticamente(List<Funcionario> funcionarios) {
        List<Funcionario> ordenados = new ArrayList<>(funcionarios);
        ordenados.sort(Comparator.comparing(Pessoa::getNome));

        StringBuilder sb = new StringBuilder("FUNCIONÁRIOS ORDENADOS ALFABETICAMENTE:\n\n");
        for (Funcionario f : ordenados) {
            sb.append(f.getNome()).append("\n");
        }
        JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea(sb.toString())));
    }

    public void totalSalarios(List<Funcionario> funcionarios) {
        BigDecimal total = BigDecimal.ZERO;
        for (Funcionario f : funcionarios) {
            total = total.add(f.getSalario());
        }

        String totalFormatado = String.format("%,.2f", total).replace(",", "X")
                .replace(".", ",").replace("X", ".");

        JOptionPane.showMessageDialog(null, "TOTAL DE SALÁRIOS: R$ " + totalFormatado);
    }

    public void salariosEmMinimos(List<Funcionario> funcionarios) {
        StringBuilder sb = new StringBuilder("SALÁRIOS EM SALÁRIOS MÍNIMOS (R$ 1.212,00):\n\n");
        for (Funcionario f : funcionarios) {
            BigDecimal minimos = f.getSalario().divide(FuncionarioUtil.SALARIO_MINIMO, 2, RoundingMode.HALF_UP);
            String minimosFormatado = String.format("%,.2f", minimos).replace(",", "X")
                    .replace(".", ",").replace("X", ".");

            sb.append(f.getNome()).append(": ").append(minimosFormatado).append(" salários mínimos\n");
        }
        JOptionPane.showMessageDialog(null, new JScrollPane(new JTextArea(sb.toString())));
    }
}
