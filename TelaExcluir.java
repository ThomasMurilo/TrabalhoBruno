import java.awt.*;
import javax.swing.*;

public class TelaExcluir extends JPanel {
    public TelaExcluir() {
        setLayout(new BorderLayout());
        // Fundo suave
        setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        // Painel central com margem e organização
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font fonte = new Font("Segoe UI", Font.PLAIN, 18);

        // Campo
        JLabel lblCodigo = new JLabel("Código do Produto que deseja excluir:");
        lblCodigo.setFont(fonte);

        JTextField campoCodigo = new JTextField(20);
        campoCodigo.setFont(fonte);

        // Adicionando ao GridBag
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(lblCodigo, gbc);

        gbc.gridx = 1;
        formPanel.add(campoCodigo, gbc);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        buttonPanel.setBackground(new Color(235, 235, 235));

        JButton btnExcluir = new JButton("Excluir");
        

        btnExcluir.setFont(new Font("Segoe UI", Font.BOLD, 18));
        

        btnExcluir.setPreferredSize(new Dimension(180, 45));
        

        

        buttonPanel.add(btnExcluir);
        

        // Montagem final
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}