import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JSplitPane;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

public class CalcBuddyFrame extends JFrame{
	
	private JButton execute;
	private JButton ch1;
	private JButton ch2;
	private JButton ch3;
	private JButton ch4;
	private JButton ch5;

	private JLabel title;
	private JLabel explanation;
	private JTextField input;


	private JPanel leftPanel;
	private JPanel rightPanel;
	private JPanel generalPanel;
	private JPanel chapterOne;
	private JPanel chapterTwo;
	private JPanel chapterThree;
	private JPanel chapterFour;
	private JPanel chapterFive;

	private JTextField output;
	private JSplitPane tb;
	private JSplitPane jps;
	private GridBagLayout leftGrid;
	private GridBagConstraints gbcL;
	private GridBagConstraints gbcR;
	private GridBagLayout rightGrid;

	private static final int FRAME_WIDTH = 962;
	private static final int FRAME_HEIGHT = 663;
	private static final int FIELD_WIDTH = 10;
	

	private void placeComponents(){

		createButtons();
		createTextField();
		creatLabel();

		leftPanel = new JPanel();
		rightPanel = new JPanel();
		generalPanel = new JPanel();

		jps = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
		jps.setDividerSize(10);

		tb = new JSplitPane(JSplitPane.VERTICAL_SPLIT, generalPanel, jps);
		tb.setDividerSize(0);

		leftGrid = new GridBagLayout();
		gbcL = new GridBagConstraints();
		leftPanel.setLayout(leftGrid);
		gbcL.gridwidth = GridBagConstraints.REMAINDER;
		leftGrid.setConstraints(ch1, gbcL);
		leftPanel.add(ch1);
		leftGrid.setConstraints(ch2, gbcL);
		leftPanel.add(ch2);
		leftGrid.setConstraints(ch3, gbcL);
		leftPanel.add(ch3);
		leftGrid.setConstraints(ch4, gbcL);
		leftPanel.add(ch4);
		leftGrid.setConstraints(ch5, gbcL);
		leftPanel.add(ch5);

		rightGrid = new GridBagLayout();
		rightPanel.setLayout(rightGrid);
		rightPanel.add(explanation);
		rightPanel.add(input);
		rightPanel.add(execute);
		rightPanel.add(output);

		generalPanel.add(title);
		add(tb);
		
	}

	public CalcBuddyFrame(){
		placeComponents();
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}

	private void createButtons(){
		execute = new JButton("calculate");
		ActionListener listener = new EquationListener();
		execute.addActionListener(listener);

		ch1 = new JButton("Chapter 1");
		ch1.setSize(new Dimension(40, 40));
		ActionListener chapterOneListener = new PageListener();
		ch1.setActionCommand("ch1");
		ch1.addActionListener(chapterOneListener);
		

		ch2 = new JButton("Chapter 2");
		ch2.setSize(new Dimension(40, 40));
		ActionListener chapterTwoListener = new PageListener();
		ch2.setActionCommand("ch2");
		ch2.addActionListener(chapterTwoListener);

		ch3 = new JButton("Chapter 3");
		ch3.setSize(new Dimension(40, 40));
		ActionListener chapterThreeListener = new PageListener();
		ch3.setActionCommand("ch3");
		ch3.addActionListener(chapterThreeListener);

		ch4 = new JButton("Chapter 4");
		ch4.setSize(new Dimension(40, 40));
		ActionListener chapterFourListener = new PageListener();
		ch4.setActionCommand("ch4");
		ch4.addActionListener(chapterFourListener);

		ch5 = new JButton("Chapter 5");
		ch5.setSize(new Dimension(40, 40));
		ActionListener chapterFiveListener = new PageListener();
		ch5.setActionCommand("ch5");
		ch5.addActionListener(chapterFiveListener);


	}

	private void createTextField(){
		input = new JTextField(FIELD_WIDTH);
		input.setText("");
		output = new JTextField(FIELD_WIDTH);
	}

	private void creatLabel(){
		title = new JLabel("CalcBuddy");
		explanation = new JLabel("Please type in an eqution to derive.");
	}

	class EquationListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String equation = input.getText();
			PowerRule a = new PowerRule(equation);
			output.setText(a.calcAnswer());
		}
	}

	class PageListener implements ActionListener{

		public void actionPerformed(ActionEvent e){
			String pageName = e.getActionCommand();
			if(pageName.equals("ch1")){

			} else if(pageName.equals("ch2")){

			} else if(pageName.equals("ch3")){

			} else if(pageName.equals("ch4")){

			} else if(pageName.equals("ch5")){

			}
		}
	}
}