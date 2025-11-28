import java.awt.*;
import java.awt.event.*;
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

        // ação do botão Excluir
        btnExcluir.addActionListener(e -> {
            String sCodigo = campoCodigo.getText().trim();
            if (sCodigo.isEmpty()) {
                JOptionPane.showMessageDialog(TelaExcluir.this, "Informe o código do produto.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int codigo;
            try {
                codigo = Integer.parseInt(sCodigo);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TelaExcluir.this, "Código inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int confirmar = JOptionPane.showConfirmDialog(TelaExcluir.this, "Confirma exclusão do produto de código " + codigo + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmar != JOptionPane.YES_OPTION) return;

            boolean ok = Produto.remover(codigo);
            if (ok) {
                JOptionPane.showMessageDialog(TelaExcluir.this, "Produto excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                // atualizar lista se possível
                java.awt.Window w = SwingUtilities.getWindowAncestor(TelaExcluir.this);
                if (w instanceof MainFrame) {
                    ((MainFrame) w).atualizarLista();
                }

                campoCodigo.setText("");
            } else {
                JOptionPane.showMessageDialog(TelaExcluir.this, "Produto não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        });

        // Montagem final
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}