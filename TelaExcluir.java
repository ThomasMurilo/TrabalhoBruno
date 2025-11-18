import javax.swing.*;
import java.awt.*;

public class TelaExcluir extends JPanel {

    public TelaExcluir(MainFrame mainFrame) {
        setLayout(new GridLayout(3, 2, 5, 5));

        JTextField campoCodigo = new JTextField();

        add(new JLabel("Código do Produto:"));
        add(campoCodigo);

        JButton btnExcluir = new JButton("Excluir");
        JButton btnVoltar = new JButton("Voltar");

        btnVoltar.addActionListener(e -> mainFrame.mostrarTela("menu"));

        add(btnExcluir);
        add(btnVoltar);
    }
}
