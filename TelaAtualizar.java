import javax.swing.*;
import java.awt.*;

public class TelaAtualizar extends JPanel {

    public TelaAtualizar(MainFrame mainFrame) {
        setLayout(new GridLayout(7, 2, 5, 5));

        JTextField campoCodigo = new JTextField();
        JTextField campoNome = new JTextField();
        JTextField campoDesc = new JTextField();
        JTextField campoPreco = new JTextField();
        JTextField campoQtd = new JTextField();

        add(new JLabel("Código do Produto:"));
        add(campoCodigo);

        add(new JLabel("Novo Nome:"));
        add(campoNome);

        add(new JLabel("Nova Descrição:"));
        add(campoDesc);

        add(new JLabel("Novo Preço:"));
        add(campoPreco);

        add(new JLabel("Nova Quantidade:"));
        add(campoQtd);

        JButton btnAtualizar = new JButton("Atualizar");
        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> mainFrame.mostrarTela("menu"));

        add(btnAtualizar);
        add(btnVoltar);
    }
}
