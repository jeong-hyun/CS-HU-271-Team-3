import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * 
 * @author Jeong-Hyun Boo
 *
 */
public class GUI extends JFrame{
	public GUI() 
	{
		super("Calculator");
		
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel leftPanel = new JPanel();
		
		JPanel rightPanel = new JPanel();
		JPanel digitPanel = new JPanel();
		JPanel operationPanel = new JPanel();
		JPanel controlPanel = new JPanel();
		
		JLabel exceptionLabel = new JLabel("exception");
		JLabel valueLabel = new JLabel("value");
		
		getContentPane().add(leftPanel, BorderLayout.WEST);
		getContentPane().add(rightPanel, BorderLayout.EAST);
		leftPanel.add(digitPanel, BorderLayout.NORTH);
		leftPanel.add(exceptionLabel, BorderLayout.SOUTH);
		rightPanel.add(valueLabel, BorderLayout.NORTH);
		rightPanel.add(operationPanel, BorderLayout.CENTER);
		rightPanel.add(controlPanel, BorderLayout.SOUTH);
		
		pack();
		setVisible(true);
	}

	public static void main(String[] args) {
		new GUI();
	}
}
