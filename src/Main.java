import model.Funcionario;
import service.FuncionarioService;
import util.DataLoaderUtil;

import javax.swing.*;
import java.util.*;





public class Main {
    private static List<Funcionario> funcionarios = new ArrayList<>();


    public static void main(String[] args) {
        DataLoaderUtil.inicializarFuncionarios(funcionarios);
        exibirMenu();
    }

    private static void exibirMenu() {
        String[] opcoes = {
                "Listar Funcionários", "Remover João", "Aplicar Aumento 10%",
                "Agrupar por Função", "Aniversariantes Out/Dez", "Funcionário Mais Velho",
                "Ordenar Alfabeticamente", "Total Salários", "Salários em Mínimos", "Sair"
        };

        while (true) {
            int escolha = JOptionPane.showOptionDialog(
                    null,
                    "Sistema de Gestão de Funcionários\nSelecione uma opção:",
                    "Menu Principal",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    opcoes,
                    opcoes[0]
            );

            switch (escolha) {
                case 0: FuncionarioService.getInstance().listarFuncionarios(funcionarios); break;
                case 1: FuncionarioService.getInstance().removerJoao(funcionarios); break;
                case 2: FuncionarioService.getInstance().aplicarAumento(funcionarios); break;
                case 3: FuncionarioService.getInstance().agruparPorFuncao(funcionarios); break;
                case 4: FuncionarioService.getInstance().aniversariantesOutDez(funcionarios); break;
                case 5: FuncionarioService.getInstance().funcionarioMaisVelho(funcionarios); break;
                case 6: FuncionarioService.getInstance().ordenarAlfabeticamente(funcionarios); break;
                case 7: FuncionarioService.getInstance().totalSalarios(funcionarios); break;
                case 8: FuncionarioService.getInstance().salariosEmMinimos(funcionarios); break;
                case 9:
                    JOptionPane.showMessageDialog(null, "Saindo do sistema...");
                    System.exit(0);
                default: break;
            }
        }
    }
}