import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TelaAtualizar extends JPanel {
    public TelaAtualizar() {
        setLayout(new BorderLayout());

        // Fundo cinza suave (mesmo padrão das outras telas)
        Color fundo = new Color(133, 138, 142);
        setBackground(fundo);

        // Painel central
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(fundo);
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font fonte = new Font("Segoe UI", Font.PLAIN, 18);
        Font fonteNegrito = new Font("Segoe UI", Font.BOLD, 18);

        // Campos com destaque e borda preta
        JTextField txtCodigo = new JTextField(20);
        JTextField txtNome = new JTextField(20);
        JTextField txtDescricao = new JTextField(20);
        JTextField txtPreco = new JTextField(20);
        JTextField txtQuantidade = new JTextField(20);

        JTextField[] campos = { txtCodigo, txtNome, txtDescricao, txtPreco, txtQuantidade };

        for (JTextField campo : campos) {
            campo.setFont(fonte);
            campo.setBorder(new LineBorder(Color.BLACK, 2));
        }

        // Labels destacadas
        JLabel lblCodigo = new JLabel("Código do Produto:");
        JLabel lblNome = new JLabel("Novo Nome:");
        JLabel lblDescricao = new JLabel("Nova Descrição:");
        JLabel lblPreco = new JLabel("Novo Preço:");
        JLabel lblQuantidade = new JLabel("Nova Quantidade:");

        JLabel[] labels = { lblCodigo, lblNome, lblDescricao, lblPreco, lblQuantidade };

        for (JLabel lbl : labels) {
            lbl.setFont(fonteNegrito);
            lbl.setForeground(Color.BLACK);
        }

        // Adicionando ao GridBag
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblCodigo, gbc);
        gbc.gridx = 1;
        formPanel.add(txtCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblNome, gbc);
        gbc.gridx = 1;
        formPanel.add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblDescricao, gbc);
        gbc.gridx = 1;
        formPanel.add(txtDescricao, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblPreco, gbc);
        gbc.gridx = 1;
        formPanel.add(txtPreco, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lblQuantidade, gbc);
        gbc.gridx = 1;
        formPanel.add(txtQuantidade, gbc);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        buttonPanel.setBackground(new Color(120, 125, 128));

        JButton btnAtualizar = new JButton("Atualizar");

        btnAtualizar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btnAtualizar.setPreferredSize(new Dimension(180, 45));

        // cor do botão (#2C82B5)
        btnAtualizar.setBackground(new Color(44, 130, 181)); // fundo azul
        btnAtualizar.setForeground(Color.WHITE); // texto branco

        btnAtualizar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // borda preta de 2px

        buttonPanel.add(btnAtualizar);

        // Montagem final
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
