import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));


        System.out.println("\n=========================================");
        System.out.println("3.3 – Imprimir todos os funcionários");
        System.out.println("=========================================\n");

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        for (Funcionario funcionario : funcionarios) {
            System.out.println("Nome: " + funcionario.getNome() +
                    ", Data Nascimento: " + funcionario.getDataNascimento().format(dateFormatter) +
                    ", Salário: " + decimalFormat.format(funcionario.getSalario()) +
                    ", Função: " + funcionario.getFuncao());
        }

        System.out.println("\n=========================================");
        System.out.println("3.4 – Imprimir aumento de 10% no salário");
        System.out.println("=========================================\n");

        for (Funcionario funcionario : funcionarios) {
            BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
            funcionario.setSalario(novoSalario);
            System.out.println("Nome: " + funcionario.getNome() + ", Novo Salário: " + funcionario.getSalario());
        }

        // 3.5 – Agrupar por função
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("\n=========================================");
        System.out.println("3.6 – Imprimir funcionarios agrupado por função");
        System.out.println("=========================================\n");

        for (Map.Entry<String, List<Funcionario>> entry : funcionariosPorFuncao.entrySet()) {
            System.out.println("Função: " + entry.getKey());
            for (Funcionario funcionario : entry.getValue()) {
                System.out.println("  Nome: " + funcionario.getNome());
            }
        }

        System.out.println("\n=========================================");
        System.out.println("3.8 – Imprimir aniversariantes do mês 10 e 12");
        System.out.println("=========================================\n");

        System.out.println("Aniversariantes do mês 10 e 12:");
        for (Funcionario funcionario : funcionarios) {
            int mes = funcionario.getDataNascimento().getMonthValue();
            if (mes == 10 || mes == 12) {
                System.out.println("Nome: " + funcionario.getNome() + ", Mês: " + mes);
            }
        }

        System.out.println("\n=========================================");
        System.out.println("3.9 – Imprimir funcionário mais velho");
        System.out.println("=========================================\n");

        Funcionario maisVelho = Collections.min(funcionarios, Comparator.comparing(Funcionario::getDataNascimento));
        System.out.println("Funcionário mais velho: " + maisVelho.getNome() + ", Idade: " + (LocalDate.now().getYear() - maisVelho.getDataNascimento().getYear()));

        System.out.println("\n=========================================");
        System.out.println("3.10 –Imprimir funcionarios em ordem alfabética");
        System.out.println("=========================================\n");

        funcionarios.sort(Comparator.comparing(Funcionario::getNome));
        System.out.println("Funcionários em ordem alfabética:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario.getNome());
        }

        System.out.println("\n=========================================");
        System.out.println("3.11 –Imprimir total dos salários");
        System.out.println("=========================================\n");

        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("Total dos salários: " + decimalFormat.format(totalSalarios));

        System.out.println("\n=========================================");
        System.out.println("3.12 –Imprimir salários em termos de salários mínimos");
        System.out.println("=========================================\n");

        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("Salários em termos de salários mínimos:");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(funcionario.getNome() + ": " + salariosMinimos);
        }
    }

}