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
	private JLabel chapterOneLabel = new JLabel("Chapter one");
	private JLabel chapterTwoLabel = new JLabel("Chapter Two");
	private JLabel chapterThreeLabel = new JLabel("Chapter three");
	private JLabel chapterFourLabel = new JLabel("Chapter four");
	private JLabel chapterFiveLabel = new JLabel("Chapter five");
	private JLabel explanation;
	private JTextField input;


	private JPanel leftPanel;
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

	private GridBagLayout chapterOneGrid;
	private GridBagLayout chapterTwoGrid;
	private GridBagLayout chapterThreeGrid;
	private GridBagLayout chapterFourGrid;
	private GridBagLayout chapterFiveGrid;

	private static final int FRAME_WIDTH = 962;
	private static final int FRAME_HEIGHT = 663;
	private static final int FIELD_WIDTH = 10;
	

	private void placeComponents(){

		createButtons();
		createTextField();
		creatLabel();

		leftPanel = new JPanel();
		chapterOne = new JPanel();
		generalPanel = new JPanel();

		jps = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, chapterOne);
		jps.setDividerSize(0);

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

		chapterOneGrid = new GridBagLayout();
		chapterOne.setLayout(chapterOneGrid);
		chapterOne.add(explanation);
		chapterOne.add(input);
		chapterOne.add(execute);
		chapterOne.add(output);

		chapterTwo = new JPanel();
		chapterTwoGrid = new GridBagLayout();
		chapterTwo.setLayout(chapterTwoGrid);
		chapterTwo.add(chapterTwoLabel);

		chapterThree = new JPanel();
		chapterThreeGrid = new GridBagLayout();
		chapterThree.setLayout(chapterThreeGrid);
		chapterThree.add(chapterThreeLabel);

		chapterFour = new JPanel();
		chapterFourGrid = new GridBagLayout();
		chapterFour.setLayout(chapterFourGrid);
		chapterFour.add(chapterFourLabel);

		chapterFive = new JPanel();
		chapterFiveGrid = new GridBagLayout();
		chapterFive.setLayout(chapterFiveGrid);
		chapterFive.add(chapterFiveLabel);

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
		ActionListener listener = new EquationListener();
		input.setText("");
		input.addActionListener(listener);
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
				jps.setRightComponent(chapterOne);
			} else if(pageName.equals("ch2")){
				jps.setRightComponent(chapterTwo);
			} else if(pageName.equals("ch3")){
				jps.setRightComponent(chapterThree);
			} else if(pageName.equals("ch4")){
				jps.setRightComponent(chapterFour);
			} else if(pageName.equals("ch5")){
				jps.setRightComponent(chapterFive);
			}
		}
	}
}