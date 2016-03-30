import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JSplitPane;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import java.util.ArrayList;
import javax.swing.JScrollPane;

/**
	controls the Jframe for the GUI for PowerRuleSolver
*/

public class PowerRuleFrame extends JFrame{
	
	private JButton execute;
	private JButton ch1;

	private JLabel title;
	private JLabel instruction;
	private JTextArea explanation;
	private JTextArea details;
	private final String DETAILS_STRING = "Explanation of how the answer was found:\n";
	private String newDetailsString = "";
	private JTextField input;
	private JScrollPane sp;

	private JPanel titlePanel;
	private JPanel powerRule;
	private JPanel outputPanel;
	private TextPanel detailPanel;

	private JTextField output;
	private JSplitPane tb;

	private BorderLayout bl; 
	private BorderLayout bl1;

	private ArrayList<FunctionProperties> evaluatedFunction = new ArrayList<FunctionProperties>();

	private static final int FRAME_WIDTH = 600;
	private static final int FRAME_HEIGHT = 500;
	
	/**
	initializes all of the possible components to display and sets values for the frame
	*/
	private void placeComponents(){

		createButtons();
		createTextField();
		creatLabel();

		powerRule = new JPanel();
		titlePanel = new JPanel();
		outputPanel = new JPanel();

		tb = new JSplitPane(JSplitPane.VERTICAL_SPLIT, titlePanel, powerRule);
		tb.setDividerSize(0);

		bl = new BorderLayout(10, 10);
		bl1 = new BorderLayout(10, 10);

		powerRule.setLayout(bl);
		outputPanel.setLayout(bl1);

		powerRule.add(explanation, BorderLayout.PAGE_START);
		powerRule.add(instruction, BorderLayout.LINE_START);
		powerRule.add(input, BorderLayout.CENTER);
		powerRule.add(execute, BorderLayout.LINE_END);
		powerRule.add(outputPanel, BorderLayout.PAGE_END);
		outputPanel.add(output, BorderLayout.PAGE_START);
		detailPanel = new TextPanel();
		detailPanel.setPreferredSize(new Dimension(590, 250));
		outputPanel.add(detailPanel, BorderLayout.CENTER);

		titlePanel.add(title);
		add(tb);
		setVisible(true);
		setTitle("PowerRuleSolver");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
	}

	/**
		initializes the init of the components
	*/
	public PowerRuleFrame(){
		placeComponents();
	}

	/**
		initializes all of the buttons
	*/
	private void createButtons(){
		execute = new JButton("calculate");
		ActionListener listener = new EquationListener();
		execute.addActionListener(listener);
	}

	/**
		initializes the JTextFields and the JtextArea for the definiton of power rule for PowerRuleSolver
	*/
	private void createTextField(){
		input = new JTextField();
		ActionListener listener = new EquationListener();
		input.setText("");
		input.addActionListener(listener);
		input.setPreferredSize(new Dimension(100, 10));
		output = new JTextField();
		output.setPreferredSize(new Dimension(10, 50));
		explanation = new JTextArea("In calculus, a derivative is the slope of the function at any given point usually denoted as\n X. One possible way of solving a derivative is known as the Power Rule. In the power rule, you\n multiply the coefficient by the exponent and then subtract 1 from the exponent. For example,\n 2x^5 becomes 10x^4.");
		explanation.setPreferredSize(new Dimension(10, 80));
	}

	/**
		initializes the Jlabels for calc buddy
	*/
	private void creatLabel(){
		title = new JLabel("Power Rule Solver");
		instruction = new JLabel("Please type in an eqution to derive");
		
	}

	/**
		listener for the equation text feild
	*/
	class EquationListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String equation = input.getText();
			PowerRule a = new PowerRule(equation);
			try{
				int currentStart = 0;
				int currentEnd = newDetailsString.length()==0? 0:newDetailsString.length()-1;
				newDetailsString = DETAILS_STRING;
				System.out.println("newDetailsString: " + newDetailsString);
				output.setText(a.calcAnswer());
				evaluatedFunction = new ArrayList<FunctionProperties>(a.getFunctionPorperties());
				for(int i = evaluatedFunction.size() - 1; i >= 0; i--){
					System.out.println(evaluatedFunction.get(i).getFunction());
					newDetailsString += "\n" + "isolate the function: " + evaluatedFunction.get(i).getFunction() + "\n" + "Identify the coefficient: " + evaluatedFunction.get(i).getCoef() + "\n" + "identify the exponent: " + evaluatedFunction.get(i).getExpo() + "\n" + "finally, solve: " + evaluatedFunction.get(i).getDerivative() + "\n";
				}
				detailPanel.addText(newDetailsString, currentStart, currentEnd);
			} catch(Exception ex){
				output.setText("an error has occured");
			}
			
		}
	}

	/**
		class that handles the changing details JTextArea
	*/
	public class TextPanel extends JPanel {
	    private JTextArea details;
	    
	    /**
			constructor for TextPanel
	    */
	    public TextPanel() {
	        newDetailsString = DETAILS_STRING;
			details = new JTextArea(newDetailsString);
	        setLayout(new BorderLayout());
	        add(new JScrollPane(details), BorderLayout.CENTER);
	    }
	    
	    /**
			replaces the old text on the details text are with new text
			@param text the test to be placed in the text area
			@param start starting position of old text in text area
			@param end ending position of old text in text area
	    */
	    public void addText(String text, int start, int end) {
	        details.replaceRange(text, start, end);
    	}
	}	

}