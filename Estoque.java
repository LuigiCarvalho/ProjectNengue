import java.io.File;
import java.util.HashMap;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileNotFoundException;

public class Estoque {
    private HashMap<String, Produto> produtos = new HashMap<>();
    private final String caminhoArquivo;

    public Estoque(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
        carregar();
    }

    /* ---------- Persistência ---------- */

    private void carregar() {
        File arquivo = new File(caminhoArquivo);

        try {
            Scanner scanner = new Scanner(arquivo);
            scanner.nextLine(); // cabeçalho
            String linha;
            while ((linha = scanner.nextLine()) != null) {
                Produto p = Produto.deCSV(linha);
                produtos.put(p.getId(), p);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void salvar() {
        try (PrintWriter pw = new PrintWriter(new File(caminhoArquivo))) {
            pw.println("id,nome,quantidade,preco");
            for (Produto p : produtos.values()) {
                pw.println(p.toString());
            }
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* ---------- Operações ---------- */

    public void cadastrarProduto(Produto p) {
        produtos.put(p.getId(), p);
        salvar();
    }

    public void removerProduto(String id) {
        produtos.remove(id);
        salvar();
    }

    public void alterarQuantidade(String id, int quantidade) {
        Produto p = produtos.get(id);
        if (p == null) return;
        p.setQuantidade(Math.max(p.getQuantidade() + quantidade, 0));
        salvar();
    }

    public void registrarVenda(String id, int quantidadeVendida) {
        alterarQuantidade(id, -quantidadeVendida);
    }

    public void listarProdutos() {
        for (Produto p : produtos.values()) {
            System.out.printf("%s | %s | qtd: %d | R$ %.2f%n",
                    p.getId(), p.getNome(), p.getQuantidade(), p.getPreco());
        }
    }
}
