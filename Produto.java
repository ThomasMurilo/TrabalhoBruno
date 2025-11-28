import java.util.ArrayList;
import java.util.List;

public class Produto {
    private static final List<Produto> produtos = new ArrayList<>();
    private static int nextId = 1;

    private final int id;
    private final String nome;
    private final String descricao;
    private final double preco;
    private final int quantidade;

    public Produto(String nome, String descricao, double preco, int quantidade) {
        this.id = nextId++;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    // construtor usado internamente para preservar o id ao atualizar
    private Produto(int id, String nome, String descricao, double preco, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public static synchronized void adicionar(Produto p) {
        produtos.add(p);
    }

    public static synchronized boolean atualizar(int id, String nome, String descricao, double preco, int quantidade) {
        for (int i = 0; i < produtos.size(); i++) {
            Produto atual = produtos.get(i);
            if (atual.id == id) {
                produtos.set(i, new Produto(id, nome, descricao, preco, quantidade));
                return true;
            }
        }
        return false;
    }

    public static synchronized boolean remover(int id) {
        for (int i = 0; i < produtos.size(); i++) {
            if (produtos.get(i).id == id) {
                produtos.remove(i);
                return true;
            }
        }
        return false;
    }

    public static synchronized Produto buscarPorId(int id) {
        for (Produto p : produtos) {
            if (p.id == id) return p;
        }
        return null;
    }

    public static synchronized List<Produto> getProdutos() {
        return new ArrayList<>(produtos);
    }
}
