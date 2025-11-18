import javax.swing.*;
import java.awt.*;

public class TelaCadastro extends JPanel {

    public TelaCadastro(MainFrame mainFrame) {

        setLayout(new GridLayout(6, 2, 5, 5));

        add(new JLabel("Código:"));
        add(new JTextField());

        add(new JLabel("Nome:"));
        add(new JTextField());

        add(new JLabel("Descrição:"));
        add(new JTextField());

        add(new JLabel("Preço:"));
        add(new JTextField());

        add(new JLabel("Quantidade:"));
        add(new JTextField());

        JButton btnSalvar = new JButton("Salvar");
        JButton btnVoltar = new JButton("Voltar");

        // Voltar para o menu
        btnVoltar.addActionListener(e -> mainFrame.mostrarTela("menu"));

        add(btnSalvar);
        add(btnVoltar);
    }
}
