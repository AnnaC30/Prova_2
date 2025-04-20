import java.time.LocalDate;

public class Venda {
    private LocalDate data;
    private Produto produto;
    private int quantidadeVendida;

    public Venda(LocalDate data, Produto produto, int quantidadeVendida) {
        this.data = data;
        this.produto = produto;
        this.quantidadeVendida = quantidadeVendida;
    }

    public LocalDate getData() { return data; }
    public Produto getProduto() { return produto; }
    public int getQuantidadeVendida() { return quantidadeVendida; }

    public double getValorTotal() {
        return produto.getValor() * quantidadeVendida;
    }

    @Override
    public String toString() {
        return data + " - " + produto.getNome() + " - Qtd: " + quantidadeVendida + " - Unit: R$" + produto.getValor() + " - Total: R$" + getValorTotal();
    }
}
