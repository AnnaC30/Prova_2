import java.util.*;
import java.time.*;

public class App {
    static List<Produto> produtos = new ArrayList<>();
    static List<Venda> vendas = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Incluir produto");
            System.out.println("2. Consultar produto");
            System.out.println("3. Listagem de produtos");
            System.out.println("4. Vendas por período – detalhado");
            System.out.println("5. Realizar venda");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao) {
                case 1 -> incluirProduto();
                case 2 -> consultarProduto();
                case 3 -> listarProdutos();
                case 4 -> relatorioVendas();
                case 5 -> realizarVenda();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    static void incluirProduto() {
        System.out.print("Código: ");
        int codigo = sc.nextInt();
        sc.nextLine();
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Valor: ");
        double valor = sc.nextDouble();
        System.out.print("Quantidade: ");
        int quantidade = sc.nextInt();

        produtos.add(new Produto(codigo, nome, valor, quantidade));
        System.out.println("Produto adicionado.");
    }

    static void consultarProduto() {
        System.out.print("Digite o código do produto: ");
        int cod = sc.nextInt();
        for (Produto p : produtos) {
            if (p.getCodigo() == cod) {
                System.out.println(p);
                return;
            }
        }
        System.out.println("Produto não encontrado.");
    }

    static void listarProdutos() {
        System.out.println("\n=== LISTA DE PRODUTOS ===");
        double soma = 0, max = Double.MIN_VALUE, min = Double.MAX_VALUE;
        for (Produto p : produtos) {
            System.out.println(p);
            double v = p.getValor();
            soma += v;
            if (v > max) max = v;
            if (v < min) min = v;
        }
        double media = produtos.size() > 0 ? soma / produtos.size() : 0;
        System.out.printf("Média: R$%.2f | Máximo: R$%.2f | Mínimo: R$%.2f\n", media, max, min);
    }

    static void realizarVenda() {
        System.out.print("Código do produto: ");
        int cod = sc.nextInt();
        Produto escolhido = null;
        for (Produto p : produtos) {
            if (p.getCodigo() == cod) {
                escolhido = p;
                break;
            }
        }

        if (escolhido == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Quantidade a vender: ");
        int qtd = sc.nextInt();
        if (qtd > escolhido.getQuantidade()) {
            System.out.println("Estoque insuficiente!");
            return;
        }

        escolhido.setQuantidade(escolhido.getQuantidade() - qtd);
        vendas.add(new Venda(LocalDate.now(), escolhido, qtd));
        System.out.println("Venda realizada com sucesso.");
    }

    static void relatorioVendas() {
        System.out.print("Data inicial (AAAA-MM-DD): ");
        LocalDate ini = LocalDate.parse(sc.nextLine());
        System.out.print("Data final (AAAA-MM-DD): ");
        LocalDate fim = LocalDate.parse(sc.nextLine());

        System.out.println("\n=== RELATÓRIO DE VENDAS ===");
        System.out.println("Período: " + ini + " até " + fim);

        double total = 0;
        int count = 0;
        for (Venda v : vendas) {
            if (!v.getData().isBefore(ini) && !v.getData().isAfter(fim)) {
                System.out.println(v);
                total += v.getValorTotal();
                count++;
            }
        }
        if (count > 0) {
            System.out.printf("Média de vendas no período: R$%.2f\n", total / count);
        } else {
            System.out.println("Nenhuma venda no período.");
        }
    }
}
