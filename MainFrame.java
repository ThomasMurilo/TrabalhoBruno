
import java.awt.*;
import javax.swing.*;

public class MainFrame extends JFrame {

    CardLayout card;
    JPanel mainPanel;

    public MainFrame() {
        super("Sistema de Produtos");

        card = new CardLayout();
        mainPanel = new JPanel(card);

        mainPanel.add(new TelaMenu(this), "menu");
        mainPanel.add(new TelaCadastro(this), "cadastro");
        mainPanel.add(new TelaListar(), "listar");
        mainPanel.add(new TelaAtualizar(this), "atualizar");
        mainPanel.add(new TelaExcluir(this), "excluir");

        add(mainPanel);

        setSize(1300, 1000);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void mostrarTela(String nomeTela) {
        card.show(mainPanel, nomeTela);
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}
