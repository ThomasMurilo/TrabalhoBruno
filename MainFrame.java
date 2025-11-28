import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {

    JButton btnMenu, btnCadastro, btnListar;
    JPanel[] telas = new JPanel[5]; // 0=menu, 1=cadastro, 2=listar, 3=atualizar, 4=excluir

    public MainFrame() {
        super("Sistema de Produtos");

        setLayout(new BorderLayout(10, 10));

        // ----- Criando as telas -----
        telas[0] = new TelaMenu();
        telas[1] = new TelaCadastro();
        telas[2] = new TelaListar();
        telas[3] = new TelaAtualizar();
        telas[4] = new TelaExcluir();

        // ----- Painel lateral com botões -----
        // ----- Painel lateral com botões -----
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(3, 1, 0, 10)); // espaçamento entre botões (apenas 3 itens agora)
        painelBotoes.setPreferredSize(new Dimension(180, 0)); // largura fixa, altura responsiva

        // Borda lateral responsiva
        painelBotoes.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK));

        btnMenu = new JButton("Menu");
        btnCadastro = new JButton("Cadastro");
        btnListar = new JButton("Listar");
        // Não há botões laterais para Atualizar/Excluir — ações via coluna "Ferramentas"

        btnMenu.setBackground(new Color(44, 130, 181));
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFocusPainted(false);

        btnCadastro.setBackground(new Color(44, 130, 181));
        btnCadastro.setForeground(Color.WHITE);
        btnCadastro.setFocusPainted(false);

        btnListar.setBackground(new Color(44, 130, 181));
        btnListar.setForeground(Color.WHITE);

        // botões Atualizar/Excluir removidos do menu lateral

        JButton[] botoes = { btnMenu, btnCadastro, btnListar };

        for (JButton b : botoes) {
            b.addActionListener(this);
            painelBotoes.add(b);
        }

        // Tela inicial
        add(telas[0], BorderLayout.CENTER);

        add(painelBotoes, BorderLayout.WEST);

        // Configurações da janela
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Remove o painel atual do centro
        BorderLayout layout = (BorderLayout) getContentPane().getLayout();
        Component atual = layout.getLayoutComponent(BorderLayout.CENTER);
        if (atual != null) {
            remove(atual);
        }

        // Escolhe qual tela mostrar
        if (e.getSource() == btnMenu)
            add(telas[0], BorderLayout.CENTER);
        else if (e.getSource() == btnCadastro)
            add(telas[1], BorderLayout.CENTER);
        else if (e.getSource() == btnListar) {
            // antes de mostrar, atualiza os dados da tabela
            try {
                if (telas[2] instanceof TelaListar) {
                    ((TelaListar) telas[2]).atualizarTabela();
                }
            } catch (Exception ex) {
                // ignorar se acontecer algo ao atualizar
            }
            add(telas[2], BorderLayout.CENTER);
        }

        // Atualiza layout
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }

    // permite que outras telas peçam atualização da lista
    public void atualizarLista() {
        try {
            if (telas[2] instanceof TelaListar) {
                ((TelaListar) telas[2]).atualizarTabela();
            }
        } catch (Exception ex) {
            // ignorar
        }
    }

    // mostra a tela de atualizar no centro
    public void mostrarTelaAtualizar() {
        BorderLayout layout = (BorderLayout) getContentPane().getLayout();
        Component atual = layout.getLayoutComponent(BorderLayout.CENTER);
        if (atual != null) remove(atual);
        add(telas[3], BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // carrega um produto na TelaAtualizar e a exibe
    public void abrirTelaAtualizarComProduto(Produto p) {
        try {
            if (telas[3] instanceof TelaAtualizar) {
                ((TelaAtualizar) telas[3]).carregarProduto(p);
            }
        } catch (Exception ex) {
            // ignorar
        }
        mostrarTelaAtualizar();
    }
}
