import java.awt.*;
import javax.swing.*;

public class TelaMenu extends JPanel {

    private Image imagemOriginal;

    public TelaMenu() {

        setLayout(new BorderLayout());

        // Carrega a imagem
        ImageIcon icon = new ImageIcon("logo.png");
        imagemOriginal = icon.getImage();

        // Painel que redesenha a imagem responsivamente
        JPanel painelImagem = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Pega tamanho disponível
                int largura = getWidth();
                int altura = getHeight();

                // Mantém a proporção da imagem
                int imgLargura = imagemOriginal.getWidth(null);
                int imgAltura = imagemOriginal.getHeight(null);
                double proporcao = (double) imgAltura / imgLargura;

                // Calcula nova altura proporcional
                int novaAltura = (int) (largura * proporcao);

                // Se passar da tela, ajusta pela altura
                if (novaAltura > altura) {
                    novaAltura = altura;
                    largura = (int) (altura / proporcao);
                }

                // Desenha a imagem redimensionada
                g.drawImage(imagemOriginal, 0, 0, largura, novaAltura, null);
            }
        };

        add(painelImagem, BorderLayout.CENTER);
    }
}
