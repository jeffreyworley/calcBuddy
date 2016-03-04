import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalcBuddyFrame extends JFrame{
	
	private JButton execute;
	private JLabel label;
	private JTextField input;
	private JPanel panel;
	private JTextField output;

	private static final int FRAME_WIDTH = 450;
	private static final int FRAME_HEIGHT = 600;
	private static final int FIELD_WIDTH = 10;

	private void placeComponents(){
		createButton();
		createTextField();
		creatLabel();
		panel = new JPanel();
		panel.add(label);
		panel.add(input);
		panel.add(execute);
		panel.add(output);
		add(panel);
	}

	public CalcBuddyFrame(){
		placeComponents();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	private void createButton(){
		execute = new JButton("calculate");
		ActionListener listener = new EquationListener();
		execute.addActionListener(listener);
	}

	private void createTextField(){
		input = new JTextField(FIELD_WIDTH);
		input.setText("");
		output = new JTextField(FIELD_WIDTH);
	}

	private void creatLabel(){
		label = new JLabel("Please type in your equation");
	}

	class EquationListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String equation = input.getText();
			PowerRule a = new PowerRule(equation);
			output.setText(a.calcAnswer());
		}
	}
}