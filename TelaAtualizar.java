import java.awt.*;
import javax.swing.*;

public class TelaAtualizar extends JPanel {
    public TelaAtualizar() {
        setLayout(new BorderLayout());
        
        // Fundo suave
        setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        // Painel central
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font fonte = new Font("Segoe UI", Font.PLAIN, 18);

        // Campos
        JTextField txtCodigo = new JTextField(20);
        JTextField txtNome = new JTextField(20);
        JTextField txtDescricao = new JTextField(20);
        JTextField txtPreco = new JTextField(20);
        JTextField txtQuantidade = new JTextField(20);

        txtCodigo.setFont(fonte);
        txtNome.setFont(fonte);
        txtDescricao.setFont(fonte);
        txtPreco.setFont(fonte);
        txtQuantidade.setFont(fonte);

        // Labels
        JLabel lblCodigo = new JLabel("Código do Produto:");
        JLabel lblNome = new JLabel("Novo Nome:");
        JLabel lblDescricao = new JLabel("Nova Descrição:");
        JLabel lblPreco = new JLabel("Novo Preço:");
        JLabel lblQuantidade = new JLabel("Nova Quantidade:");

        lblCodigo.setFont(fonte);
        lblNome.setFont(fonte);
        lblDescricao.setFont(fonte);
        lblPreco.setFont(fonte);
        lblQuantidade.setFont(fonte);
// Adicionando ao GridBag
        gbc.gridx = 0; gbc.gridy = 0; formPanel.add(lblCodigo, gbc);
        gbc.gridx = 1; formPanel.add(txtCodigo, gbc);

        gbc.gridx = 0; gbc.gridy = 1; formPanel.add(lblNome, gbc);
        gbc.gridx = 1; formPanel.add(txtNome, gbc);

        gbc.gridx = 0; gbc.gridy = 2; formPanel.add(lblDescricao, gbc);
        gbc.gridx = 1; formPanel.add(txtDescricao, gbc);

        gbc.gridx = 0; gbc.gridy = 3; formPanel.add(lblPreco, gbc);
        gbc.gridx = 1; formPanel.add(txtPreco, gbc);

        gbc.gridx = 0; gbc.gridy = 4; formPanel.add(lblQuantidade, gbc);
        gbc.gridx = 1; formPanel.add(txtQuantidade, gbc);

        // Painel de botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 20));
        buttonPanel.setBackground(new Color(235, 235, 235));

        JButton btnAtualizar = new JButton("Atualizar");
        

        btnAtualizar.setFont(new Font("Segoe UI", Font.BOLD, 18));
        
        btnAtualizar.setPreferredSize(new Dimension(180, 45));
        

        buttonPanel.add(btnAtualizar);
        
        // Montagem final
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}



