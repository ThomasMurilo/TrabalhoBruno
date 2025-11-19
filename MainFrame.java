import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainFrame extends JFrame implements ActionListener {

    JButton btnMenu, btnCadastro, btnListar, btnAtualizar, btnExcluir;
    JPanel[] telas = new JPanel[5]; // 0=menu, 1=cadastro, 2=listar, 3=atualizar, 4=excluir

    public MainFrame() {
        super("Sistema de Produtos");

        setLayout(new BorderLayout());

        // ----- Criando as telas -----
        telas[0] = new TelaMenu();
        telas[1] = new TelaCadastro();
        telas[2] = new TelaListar();
        telas[3] = new TelaAtualizar();
        telas[4] = new TelaExcluir();

        // ----- Painel lateral com botões -----
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(5, 1));

        btnMenu = new JButton("Menu");
        btnCadastro = new JButton("Cadastro");
        btnListar = new JButton("Listar");
        btnAtualizar = new JButton("Atualizar");
        btnExcluir = new JButton("Excluir");

        JButton[] botoes = { btnMenu, btnCadastro, btnListar, btnAtualizar, btnExcluir };

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
        else if (e.getSource() == btnListar)
            add(telas[2], BorderLayout.CENTER);
        else if (e.getSource() == btnAtualizar)
            add(telas[3], BorderLayout.CENTER);
        else if (e.getSource() == btnExcluir)
            add(telas[4], BorderLayout.CENTER);

        // Atualiza layout
        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainFrame());
    }
}
