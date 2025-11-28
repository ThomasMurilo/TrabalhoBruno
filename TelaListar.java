import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;

import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;

public class TelaListar extends JPanel {
    private JTable tabela;

    public TelaListar() {

        setLayout(new BorderLayout());
        setBackground(new Color(133, 138, 142)); // fundo cinza

        // -------- TÍTULO DESTACADO --------
        JLabel titulo = new JLabel("LISTA DE PRODUTOS", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setForeground(Color.BLACK);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // -------- TABELA --------
        String[] colunas = { "Código", "Nome", "Descrição", "Preço", "Quantidade", "Ferramentas" };

        DefaultTableModel model = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // permitir edição apenas na coluna do botão (última)
                return column == getColumnCount() - 1;
            }
        };

        tabela = new JTable(model);

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

        // Centralizar texto das colunas (exceto a coluna do botão)
        DefaultTableCellRenderer centralizar = new DefaultTableCellRenderer();
        centralizar.setHorizontalAlignment(SwingConstants.CENTER);
        tabela.setDefaultRenderer(Object.class, centralizar);

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

        // popula tabela com os produtos existentes
        try {
            atualizarTabela();
        } catch (Exception ex) {
            // silencioso: se não houver Produto ainda
        }

        // painel de botões (Atualizar)
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        painelBotoes.setBackground(new Color(133, 138, 142));

        JButton btnRefresh = new JButton("Atualizar");
        btnRefresh.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnRefresh.setBackground(new Color(200, 200, 200));
        painelBotoes.add(btnRefresh);
        btnRefresh.addActionListener(ev -> atualizarTabela());

        // -------- MONTAGEM --------
        add(titulo, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(painelBotoes, BorderLayout.SOUTH);

        // adiciona coluna com botões (Editar / Excluir) renderizados por célula
        tabela.getColumn("Ferramentas").setCellRenderer(new ButtonRenderer());
        tabela.getColumn("Ferramentas").setCellEditor(new ButtonEditor(new JCheckBox()));
    }

    // Renderer para os botões na célula (painel com dois botões)
    private class ButtonRenderer extends JPanel implements javax.swing.table.TableCellRenderer {
        private final JButton btnEditar = new JButton("Editar");
        private final JButton btnExcluir = new JButton("Excluir");

        public ButtonRenderer() {
            setLayout(new FlowLayout(FlowLayout.CENTER, 6, 0));
            btnEditar.setFocusable(false);
            btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            btnEditar.setBackground(new Color(44, 130, 181));
            btnEditar.setForeground(Color.WHITE);
            btnExcluir.setFocusable(false);
            btnExcluir.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            btnExcluir.setBackground(new Color(220, 60, 60));
            btnExcluir.setForeground(Color.WHITE);
            add(btnEditar);
            add(btnExcluir);
        }

        @Override
        public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            return this;
        }
    }

    // Editor que contém os dois botões e lida com os cliques
    private class ButtonEditor extends javax.swing.AbstractCellEditor implements javax.swing.table.TableCellEditor, java.awt.event.ActionListener {
        private final JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 6, 0));
        private final JButton btnEditar = new JButton("Editar");
        private final JButton btnExcluir = new JButton("Excluir");
        private int row;

        public ButtonEditor(JCheckBox checkBox) {
            btnEditar.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            btnEditar.setBackground(new Color(44, 130, 181));
            btnEditar.setForeground(Color.WHITE);
            btnExcluir.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            btnExcluir.setBackground(new Color(220, 60, 60));
            btnExcluir.setForeground(Color.WHITE);

            btnEditar.addActionListener(this);
            btnExcluir.addActionListener(this);

            panel.add(btnEditar);
            panel.add(btnExcluir);
        }

        @Override
        public java.awt.Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            this.row = row;
            return panel;
        }

        @Override
        public Object getCellEditorValue() {
            return "";
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            String cmd = e.getActionCommand();
            int modelRow = tabela.convertRowIndexToModel(row);
            Object idObj = tabela.getModel().getValueAt(modelRow, 0);
            int id;
            try {
                id = Integer.parseInt(String.valueOf(idObj));
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(TelaListar.this, "ID do produto inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                fireEditingStopped();
                return;
            }

            if ("Editar".equals(cmd)) {
                Produto p = Produto.buscarPorId(id);
                if (p == null) {
                    JOptionPane.showMessageDialog(TelaListar.this, "Produto não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    fireEditingStopped();
                    return;
                }
                java.awt.Window w = SwingUtilities.getWindowAncestor(TelaListar.this);
                if (w instanceof MainFrame) {
                    ((MainFrame) w).abrirTelaAtualizarComProduto(p);
                }
            } else if ("Excluir".equals(cmd)) {
                int confirmar = JOptionPane.showConfirmDialog(TelaListar.this, "Confirma exclusão do produto de código " + id + "?", "Confirmar", JOptionPane.YES_NO_OPTION);
                if (confirmar != JOptionPane.YES_OPTION) {
                    fireEditingStopped();
                    return;
                }
                boolean ok = Produto.remover(id);
                if (ok) {
                    JOptionPane.showMessageDialog(TelaListar.this, "Produto excluído com sucesso.", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    java.awt.Window w = SwingUtilities.getWindowAncestor(TelaListar.this);
                    if (w instanceof MainFrame) {
                        ((MainFrame) w).atualizarLista();
                    }
                } else {
                    JOptionPane.showMessageDialog(TelaListar.this, "Produto não encontrado.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }

            fireEditingStopped();
        }
    }

    // método público para atualizar a tabela quando necessário
    public void atualizarTabela() {
        DefaultTableModel model = (DefaultTableModel) tabela.getModel();
        model.setRowCount(0);
        List<Produto> lista = Produto.getProdutos();
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        for (Produto p : lista) {
            String precoFormatado = nf.format(p.getPreco());
            model.addRow(new Object[] { p.getId(), p.getNome(), p.getDescricao(), precoFormatado, p.getQuantidade() });
        }
    }
}
