import java.awt.*;
import javax.swing.*;

public class TelaListar extends JPanel {
    public TelaListar() {
        setLayout(new BorderLayout());
        String[] colunas = { "Código", "Nome", "Descrição", "Preço", "Quantidade" };

        String[][] dados = {};

        JTable tabela = new JTable(dados, colunas);
        JScrollPane scroll = new JScrollPane(tabela);

        add(new JLabel("LISTA DE PRODUTOS", SwingConstants.CENTER), BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);

    }
}
