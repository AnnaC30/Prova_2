public class Produto {
    private int codigo;
    private String nome;
    private double valor;
    private int quantidade;

    public Produto(int codigo, String nome, double valor, int quantidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
        this.quantidade = quantidade;
    }
    
    public int getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public double getValor() { return valor; }
    public int getQuantidade() { return quantidade; }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return codigo + " - " + nome + " - R$" + valor + " - Estoque: " + quantidade;
    }
}
