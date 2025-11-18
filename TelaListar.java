import javax.swing.*;
import java.awt.*;

public class TelaListar extends JPanel {

    public TelaListar() {
        setLayout(new BorderLayout());

        String[] colunas = { "Código", "Nome", "Descrição", "Preço", "Quantidade" };

        // Dados vazios (depois podemos conectar ao DAO)
        String[][] dados = {};

        JTable tabela = new JTable(dados, colunas);
        JScrollPane scroll = new JScrollPane(tabela);

        add(new JLabel("LISTA DE PRODUTOS", SwingConstants.CENTER), BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }
}
