public class Produto {
    private String id;
    private String nome;
    private int quantidade;
    private double preco;

    public Produto(String id, String nome, int quantidade, double preco) {
        this.id         = id;
        this.nome       = nome;
        this.quantidade = quantidade;
        this.preco      = preco;
    }

    public String getId() { 
      return id; 
    }

    public String getNome() { 
      return nome; 
    }

    public int getQuantidade() {
       return quantidade; 
      }

    public double getPreco() {
       return preco; 
      }

    public void setQuantidade(int novaQuantidade) {
        this.quantidade = novaQuantidade;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return id + "," + nome + "," + quantidade + "," + preco;
    }

    public static Produto deCSV(String linha) {
        String[] p = linha.split(",");
        return new Produto(
            p[0],
            p[1],
            Integer.parseInt(p[2]),
            Double.parseDouble(p[3])
        );
    }
}
