public class Main {
    public static void main(String[] args) {
        Estoque estoque = new Estoque("estoque.csv");
        new JanelaEstoque(estoque);
    }
}
