import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class TelaListar extends JPanel {
    public TelaListar() {

        setLayout(new BorderLayout());
        setBackground(new Color(133, 138, 142)); // fundo cinza

        // -------- TÍTULO DESTACADO --------
        JLabel titulo = new JLabel("LISTA DE PRODUTOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(Color.BLACK);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // -------- TABELA --------
        String[] colunas = { "Código", "Nome", "Descrição", "Preço", "Quantidade" };
        String[][] dados = {};

        JTable tabela = new JTable(dados, colunas);

        // Fonte da tabela
        tabela.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        tabela.setRowHeight(28);

        // Borda preta nas células
        tabela.setGridColor(Color.BLACK);

        // Cabeçalho destacado
        JTableHeader header = tabela.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 18));
        header.setBackground(new Color(200, 200, 200));
        header.setForeground(Color.BLACK);
        header.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Centralizar texto das colunas
        DefaultTableCellRenderer centralizar = new DefaultTableCellRenderer();
        centralizar.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setDefaultRenderer(Object.class, centralizar);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // -------- MONTAGEM --------
        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }
}
