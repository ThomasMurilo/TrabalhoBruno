import java.awt.*;
import javax.swing.*;

public class TelaExcluir extends JPanel {
    public TelaExcluir() {
        setLayout(new BorderLayout());
        setBackground(new Color(133, 138, 142)); // Fundo cinza

        // Painel central
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(133, 138, 142));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Fonte destacada
        Font fonteLabel = new Font("Segoe UI", Font.BOLD, 20);  // destaque
        Font fonteCampo = new Font("Segoe UI", Font.PLAIN, 18);

        // Label destacado
        JLabel lblCodigo = new JLabel("Código do Produto que deseja excluir:");
        lblCodigo.setFont(fonteLabel);
        lblCodigo.setForeground(Color.BLACK); // garante destaque no cinza

        // Campo com borda preta
        JTextField campoCodigo = new JTextField(20);
        campoCodigo.setFont(fonteCampo);
        campoCodigo.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // Adicionando no layout
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblCodigo, gbc);

        gbc.gridx = 1;
        formPanel.add(campoCodigo, gbc);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        buttonPanel.setBackground(new Color(133, 138, 142));

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnExcluir.setPreferredSize(new Dimension(180, 45));

        btnExcluir.setBackground(new Color(44, 130, 181));
        btnExcluir.setForeground(Color.WHITE);
        btnExcluir.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        buttonPanel.add(btnExcluir);

        // Montagem final
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}