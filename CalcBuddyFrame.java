import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class CalcBuddyFrame extends JFrame{
	
	private JButton execute;
	private JButton ch1;
	private JButton ch2;
	private JButton ch3;
	private JButton ch4;
	private JButton ch5;

	private JLabel title;
	private JTextField input;
	private JPanel panel;
	private JTextField output;

	private static final int FRAME_WIDTH = 962;
	private static final int FRAME_HEIGHT = 663;
	private static final int FIELD_WIDTH = 10;
	BorderLayout borderLayout = new BorderLayout();
	int hgap = 10;
	borderLayout.setHgap(hgap);

	private void placeComponents(){
		createButtons();
		createTextField();
		creatLabel();

		panel = new JPanel();
		panel.add(title, BorderLayout.PAGE_START);
		panel.add(input);
		panel.add(execute);
		panel.add(output);


		
		add(panel);

		
	}

	public CalcBuddyFrame(){
		placeComponents();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	private void createButtons(){
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
		title = new JLabel("CalcBuddy");
	}

	class EquationListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			String equation = input.getText();
			PowerRule a = new PowerRule(equation);
			output.setText(a.calcAnswer());
		}
	}
}