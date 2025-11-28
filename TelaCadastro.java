import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaCadastro extends JPanel {
    public TelaCadastro() {
        setLayout(new BorderLayout());
        // Fundo mais claro e bonito
        setBackground(new Color(133, 138, 142));
        setLayout(new BorderLayout());

        // Painel central com margem e layout organizado
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(133, 138, 142));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 80, 40, 80)); // MARGEM

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 12, 12, 12); // espaço entre elementos
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;

        Font fonte = new Font("Segoe UI", Font.PLAIN, 18);

        // Campos maiores

        JTextField txtNome = new JTextField(20);
        JTextField txtDescricao = new JTextField(20);
        JTextField txtPreco = new JTextField(20);
        JTextField txtQuantidade = new JTextField(20);

        // Labels mais bonitos

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblDescricao = new JLabel("Descrição:");
        JLabel lblPreco = new JLabel("Preço:");
        JLabel lblQuantidade = new JLabel("Quantidade:");

        lblNome.setFont(fonte);
        lblDescricao.setFont(fonte);
        lblPreco.setFont(fonte);
        lblQuantidade.setFont(fonte);

        txtNome.setFont(fonte);
        txtDescricao.setFont(fonte);
        txtPreco.setFont(fonte);
        txtQuantidade.setFont(fonte);

        // --- Adiciona os componentes ao GridBag ---

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
        buttonPanel.setBackground(new Color(133, 138, 142));

        JButton btnSalvar = new JButton("Salvar");

        btnSalvar.setFont(new Font("Segoe UI", Font.BOLD, 18));

        btnSalvar.setPreferredSize(new Dimension(180, 45));

        btnSalvar.setBackground(new Color(44, 130, 181));
        btnSalvar.setForeground(Color.WHITE);

        // borda preta de 2px
        btnSalvar.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // ação do botão Salvar: cria Produto e adiciona ao repositório em memória
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = txtNome.getText().trim();
                String descricao = txtDescricao.getText().trim();
                String sPreco = txtPreco.getText().trim();
                String sQtd = txtQuantidade.getText().trim();

                // sem validações: aceita nome vazio e tenta parse dos números, em erro usa 0
                double preco = 0.0;
                int quantidade = 0;
                if (!sPreco.isEmpty()) {
                    String precoStr = sPreco.replace("R$", "").replace("r$", "").replaceAll("\\s+", "");
                    try {
                        preco = Double.parseDouble(precoStr.replace(',', '.'));
                    } catch (NumberFormatException ex2) {
                        try {
                            String alt = precoStr.replace(".", "").replace(',', '.');
                            preco = Double.parseDouble(alt);
                        } catch (NumberFormatException ex3) {
                            preco = 0.0;
                        }
                    }
                }

                if (!sQtd.isEmpty()) {
                    try {
                        quantidade = Integer.parseInt(sQtd);
                    } catch (NumberFormatException ex) {
                        quantidade = 0;
                    }
                }

                Produto p = new Produto(nome, descricao, preco, quantidade);
                Produto.adicionar(p);

                JOptionPane.showMessageDialog(TelaCadastro.this, "Produto salvo com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // limpa campos
                txtNome.setText("");
                txtDescricao.setText("");
                txtPreco.setText("");
                txtQuantidade.setText("");
            }
        });

        buttonPanel.add(btnSalvar);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        Font fonteLabel = new Font("Segoe UI", Font.BOLD, 20);
        Color corTexto = Color.WHITE; // destaque no fundo cinza

        lblNome.setFont(fonteLabel);
        lblDescricao.setFont(fonteLabel);
        lblPreco.setFont(fonteLabel);
        lblQuantidade.setFont(fonteLabel);

        lblNome.setForeground(corTexto);
        lblDescricao.setForeground(corTexto);
        lblPreco.setForeground(corTexto);
        lblQuantidade.setForeground(corTexto);

        // Bordas pretas nos campos
        txtNome.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        txtDescricao.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        txtPreco.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        txtQuantidade.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

    }
}
