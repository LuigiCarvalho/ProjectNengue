import javax.swing.*;
import java.awt.*;

public class JanelaEstoque extends JFrame {

    private Estoque estoque;

    // Campos de entrada
    private JTextField campoId = new JTextField();
    private JTextField campoNome = new JTextField();
    private JTextField campoQuantidade = new JTextField();
    private JTextField campoPreco = new JTextField();
    private JTextField campoDelta = new JTextField();
    private JTextArea areaLista = new JTextArea();

    public JanelaEstoque(Estoque estoque) {
        this.estoque = estoque;

        setTitle("Controle de Estoque");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(10, 2, 5, 5));

        painel.add(new JLabel("ID:"));
        painel.add(campoId);

        painel.add(new JLabel("Nome:"));
        painel.add(campoNome);

        painel.add(new JLabel("Quantidade:"));
        painel.add(campoQuantidade);

        painel.add(new JLabel("Preço:"));
        painel.add(campoPreco);

        painel.add(new JLabel("Alterar quantidade (+/-):"));
        painel.add(campoDelta);

        JButton btnCadastrar = new JButton("Cadastrar Produto");
        JButton btnRemover = new JButton("Remover Produto");
        JButton btnAlterar = new JButton("Alterar Quantidade");
        JButton btnVenda = new JButton("Registrar Venda");
        JButton btnListar = new JButton("Listar Produtos");

        painel.add(btnCadastrar);
        painel.add(btnRemover);
        painel.add(btnAlterar);
        painel.add(btnVenda);
        painel.add(btnListar);

        add(painel, BorderLayout.NORTH);

        areaLista.setEditable(false);
        add(new JScrollPane(areaLista), BorderLayout.CENTER);

        /* Ações */

        btnCadastrar.addActionListener(e -> {
            try {
                String id = campoId.getText().trim();
                String nome = campoNome.getText().trim();
                int quantidade = Integer.parseInt(campoQuantidade.getText().trim());
                double preco = Double.parseDouble(campoPreco.getText().trim());

                Produto p = new Produto(id, nome, quantidade, preco);
                estoque.cadastrarProduto(p);

                JOptionPane.showMessageDialog(this, "Produto cadastrado.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro nos dados.");
            }
        });

        btnRemover.addActionListener(e -> {
            estoque.removerProduto(campoId.getText().trim());
            JOptionPane.showMessageDialog(this, "Produto removido.");
        });

        btnAlterar.addActionListener(e -> {
            try {
                int delta = Integer.parseInt(campoDelta.getText().trim());
                estoque.alterarQuantidade(campoId.getText().trim(), delta);
                JOptionPane.showMessageDialog(this, "Quantidade alterada.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido.");
            }
        });

        btnVenda.addActionListener(e -> {
            try {
                int vendida = Integer.parseInt(campoDelta.getText().trim());
                estoque.registrarVenda(campoId.getText().trim(), vendida);
                JOptionPane.showMessageDialog(this, "Venda registrada.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Valor inválido.");
            }
        });

        btnListar.addActionListener(e -> listarProdutos());

        setVisible(true);
    }

    private void listarProdutos() {
        StringBuilder sb = new StringBuilder();
        for (Produto p : estoque.getTodos()) {
            sb.append(String.format(
                    "%s | %s | qtd: %d | R$ %.2f%n",
                    p.getId(), p.getNome(), p.getQuantidade(), p.getPreco()
            ));
        }
        areaLista.setText(sb.toString());
    }
}
