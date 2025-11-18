import javax.swing.*;
import java.awt.*;

public class TelaMenu extends JPanel {

    public TelaMenu(MainFrame mainFrame) {
        setLayout(new GridLayout(6, 1, 5, 5));

        JLabel titulo = new JLabel("MENU PRINCIPAL", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));

        JButton btnCadastrar = new JButton("Cadastrar Produto");
        JButton btnListar = new JButton("Listar Produtos");
        JButton btnAtualizar = new JButton("Atualizar Produto");
        JButton btnExcluir = new JButton("Excluir Produto");
        JButton btnSair = new JButton("Sair");

        btnCadastrar.addActionListener(e -> mainFrame.mostrarTela("cadastro"));
        btnListar.addActionListener(e -> mainFrame.mostrarTela("listar"));
        btnAtualizar.addActionListener(e -> mainFrame.mostrarTela("atualizar"));
        btnExcluir.addActionListener(e -> mainFrame.mostrarTela("excluir"));
        btnSair.addActionListener(e -> System.exit(0));

        add(titulo);
        add(btnCadastrar);
        add(btnListar);
        add(btnAtualizar);
        add(btnExcluir);
        add(btnSair);
    }
}
