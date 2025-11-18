
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PanelHandler implements ActionListener {
	JFrame frame;
	JButton[] buttons = new JButton[5];
	JPanel[] panels = new JPanel[5];

	PanelHandler() {
		frame = new JFrame("Panel Handler");
		frame.setLayout(new BorderLayout());

		JPanel panelButtons = new JPanel();
		panelButtons.setLayout(new GridLayout(5, 1));

		for (int i = 0; i < 5; i++) {
			panels[i] = new JPanel();
			JLabel label = new JLabel("Painel " + i);
			panels[i].add(label);

			buttons[i] = new JButton("Mostrar Painel " + i);
			buttons[i].addActionListener(this);
			panelButtons.add(buttons[i]);
		}

		frame.add(panels[0], BorderLayout.CENTER);

		frame.setSize(500,500);
		frame.add(panelButtons, BorderLayout.WEST);
		frame.setVisible(true);

	}

	public void actionPerformed(ActionEvent e) {
		BorderLayout layout = (BorderLayout) frame.getContentPane().getLayout();

		// System.out.print(e.getSource());
		for (int i = 0; i < 5; i++) {
			if (e.getSource() == buttons[i]) {
				frame.remove(layout.getLayoutComponent(BorderLayout.CENTER));
				frame.add(panels[i], BorderLayout.CENTER);
				frame.revalidate();
				frame.repaint();
				break;
			}
		}
	}

	public static void main(String[] args) {
		PanelHandler p = new PanelHandler();
	}
}