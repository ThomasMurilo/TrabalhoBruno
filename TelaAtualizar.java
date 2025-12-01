import java.awt.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class TelaAtualizar extends JPanel {
    private JTextField txtCodigo;
    private JTextField txtNome;
    private JTextField txtDescricao;
    private JTextField txtPreco;
    private JTextField txtQuantidade;

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
        txtCodigo = new JTextField(20);
        // o código do produto não deve ser alterado pelo usuário
        txtCodigo.setEditable(false);
        txtCodigo.setBackground(new Color(220, 220, 220));
        txtCodigo.setToolTipText("Código atribuído automaticamente - não é possível editar");
        txtNome = new JTextField(20);
        txtDescricao = new JTextField(20);
        txtPreco = new JTextField(20);
        txtQuantidade = new JTextField(20);

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

        //  busca por código e atualiza os campos informados (sem validar com diálogos)
        btnAtualizar.addActionListener(e -> {
            String sCodigo = txtCodigo.getText().trim();
            String nome = txtNome.getText().trim();
            String descricao = txtDescricao.getText().trim();
            String sPreco = txtPreco.getText().trim();
            String sQtd = txtQuantidade.getText().trim();

            int codigo = -1;
            try {
                codigo = Integer.parseInt(sCodigo);
            } catch (NumberFormatException ex) {
                //  código inválido -> não prossegue
                return;
            }

            double preco = 0.0;
            int quantidade = 0;
            if (!sPreco.isEmpty()) { // formata o preço
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

            if (!sQtd.isEmpty()) { // verifica se estiver vazio, se sim, define para 0
                try {
                    quantidade = Integer.parseInt(sQtd);
                } catch (NumberFormatException ex) {
                    quantidade = 0;
                }
            }
            

            Produto existente = Produto.buscarPorId(codigo);
            if (existente == null) {
                // produto não encontrado -> não prossegue
                return;
            }

            // se algum campo estiver vazio, manter valor anterior
            String novoNome = nome.isEmpty() ? existente.getNome() : nome;
            String novaDescricao = descricao.isEmpty() ? existente.getDescricao() : descricao;
            double novoPreco = sPreco.isEmpty() ? existente.getPreco() : preco;
            int novaQuantidade = sQtd.isEmpty() ? existente.getQuantidade() : quantidade;

            boolean ok = Produto.atualizar(codigo, novoNome, novaDescricao, novoPreco, novaQuantidade);
            if (ok) {
                JOptionPane.showMessageDialog(TelaAtualizar.this, "Produto atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // tenta atualizar a tabela na tela principal imediatamente
                java.awt.Window w = SwingUtilities.getWindowAncestor(TelaAtualizar.this);
                if (w instanceof MainFrame) {
                    ((MainFrame) w).atualizarLista();
                }

                txtCodigo.setText("");
                txtNome.setText("");
                txtDescricao.setText("");
                txtPreco.setText("");
                txtQuantidade.setText("");
            } else {
                JOptionPane.showMessageDialog(TelaAtualizar.this, "Falha ao atualizar produto.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Montagem final
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    // preenche os campos desta tela com os dados do produto
    public void carregarProduto(Produto p) {
        if (p == null) return;
        txtCodigo.setText(String.valueOf(p.getId()));
        txtNome.setText(p.getNome());
        txtDescricao.setText(p.getDescricao());
        txtPreco.setText(String.valueOf(p.getPreco()));
        txtQuantidade.setText(String.valueOf(p.getQuantidade()));
    }
}
