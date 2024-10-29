import java.util.Scanner;

// Classe principal
public class MainApp {
    private static GerenciadorDespesas gerenciadorDespesas = new GerenciadorDespesas();
    private static Scanner scanner = new Scanner(System.in);
    private static double salario = 0.0;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();  // Consumir a nova linha
            switch (opcao) {
                case 1:
                    adicionarDespesa();
                    break;
                case 2:
                    editarDespesa();
                    break;
                case 3:
                    removerDespesa();
                    break;
                case 4:
                    gerenciadorDespesas.exibirDespesas();
                    break;
                case 5:
                    gerarRelatorio();
                    break;
                case 6:
                    inserirSalario();
                    break;
                case 7:
                    exibirSaldo();
                    break;
                case 0:
                    running = false;
                    System.out.println("Saindo do programa...");
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Adicionar Despesa");
        System.out.println("2. Editar Despesa");
        System.out.println("3. Remover Despesa");
        System.out.println("4. Exibir Despesas");
        System.out.println("5. Gerar Relatório");
        System.out.println("6. Inserir Salário");
        System.out.println("7. Exibir Saldo Total");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void adicionarDespesa() {
        System.out.print("Descrição da despesa: ");
        String descricao = scanner.nextLine();
        System.out.print("Valor da despesa: ");
        double valor = scanner.nextDouble();
        scanner.nextLine();  // Consumir nova linha
        System.out.print("Categoria (Ex: Alimentação, Transporte): ");
        String categoria = scanner.nextLine();
        gerenciadorDespesas.adicionarDespesa(descricao, valor, categoria);
        System.out.println("Despesa adicionada com sucesso.");
    }

    private static void editarDespesa() {
        System.out.print("ID da despesa a editar: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Consumir nova linha
        System.out.print("Nova descrição da despesa: ");
        String novaDescricao = scanner.nextLine();
        System.out.print("Novo valor da despesa: ");
        double novoValor = scanner.nextDouble();
        scanner.nextLine();  // Consumir nova linha
        System.out.print("Nova categoria: ");
        String novaCategoria = scanner.nextLine();
        gerenciadorDespesas.editarDespesa(id, novaDescricao, novoValor, novaCategoria);
        System.out.println("Despesa editada com sucesso.");
    }

    private static void removerDespesa() {
        System.out.print("ID da despesa a remover: ");
        int id = scanner.nextInt();
        gerenciadorDespesas.removerDespesa(id);
        System.out.println("Despesa removida com sucesso.");
    }

    private static void gerarRelatorio() {
        System.out.println("\nRelatório de despesas:");
        System.out.println("Total gasto: R$" + gerenciadorDespesas.calcularTotalGasto());
        gerenciadorDespesas.gerarRelatorioPorCategoria();
    }

    private static void inserirSalario() {
        System.out.print("Insira o valor do seu salário: ");
        salario = scanner.nextDouble();
        scanner.nextLine();  // Consumir nova linha
        System.out.println("Salário registrado com sucesso: R$" + salario);
    }

    private static void exibirSaldo() {
        double totalGasto = gerenciadorDespesas.calcularTotalGasto();
        double saldoTotal = salario - totalGasto;
        System.out.println("\nSeu saldo total é: R$" + saldoTotal);
    }
}

// Classe Despesa
class Despesa {
    private static int contador = 0;
    private int id;
    private String descricao;
    private double valor;
    private String categoria;

    public Despesa(String descricao, double valor, String categoria) {
        this.id = ++contador;
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Descrição: " + descricao + ", Valor: R$" + valor + ", Categoria: " + categoria;
    }
}

// Classe GerenciadorDespesas
class GerenciadorDespesas {
    private ArrayList<Despesa> despesas = new ArrayList<>();

    public void adicionarDespesa(String descricao, double valor, String categoria) {
        Despesa novaDespesa = new Despesa(descricao, valor, categoria);
        despesas.add(novaDespesa);
    }

    public void editarDespesa(int id, String novaDescricao, double novoValor, String novaCategoria) {
        for (Despesa despesa : despesas) {
            if (despesa.getId() == id) {
                despesa.setDescricao(novaDescricao);
                despesa.setValor(novoValor);
                despesa.setCategoria(novaCategoria);
                break;
            }
        }
    }

    public void removerDespesa(int id) {
        despesas.removeIf(despesa -> despesa.getId() == id);
    }

    public void exibirDespesas() {
        if (despesas.isEmpty()) {
            System.out.println("Nenhuma despesa cadastrada.");
        } else {
            for (Despesa despesa : despesas) {
                System.out.println(despesa);
            }
        }
    }

    public double calcularTotalGasto() {
        double total = 0;
        for (Despesa despesa : despesas) {
            total += despesa.getValor();
        }
        return total;
    }

    public void gerarRelatorioPorCategoria() {
        System.out.println("\nRelatório por categoria:");
        despesas.stream()
                .map(Despesa::getCategoria)
                .distinct()
                .forEach(categoria -> {
                    double totalCategoria = despesas.stream()
                            .filter(d -> d.getCategoria().equals(categoria))
                            .mapToDouble(Despesa::getValor)
                            .sum();
                    System.out.println(categoria + ": R$" + totalCategoria);
                });
    }
}
