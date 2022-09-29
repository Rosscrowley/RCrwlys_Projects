import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

public class Frame extends JFrame {

	private static final Insets bottomInsets = new Insets(10, 10, 10, 10);
	private static final Insets normalInsets = new Insets(10, 10, 0, 10);

	JCheckBox cbPepproni, cbMarinara, cbMargherita, cbVeggie, cbMeaty, cbNeopoliton;

	JRadioButton rbSmall;

	JLabel lblEachTopping, lblPizzaSize, lblPizzaType, lblWelcome, lblYourOrder;

	JButton btnProcessSelection;

	JCheckBox cbSausage,cbMushrooms,cbPineapple,cbOnion,cbBellPepper;

	JRadioButton rbMedium, rbLarge, rbThinCrust, rbMediumCrust, rbPan;

	ButtonGroup grp1, grp2;

	JTextArea textArea;

	CalculateButtonHandler cbHandler;

	JTextArea ta;
	JPanel pnl;

	public Frame() {

		// Create new labels
		lblEachTopping = new JLabel("Each Topping: $1.50");
		lblPizzaSize = new JLabel("Pizza Size");
		lblPizzaType = new JLabel("Pizza Type");
		lblWelcome = new JLabel("Welcome to Home Style Pizza Shop");
		lblYourOrder = new JLabel("Your order:");

		// Create new buttons
		btnProcessSelection = new JButton("Process Selection");
		cbHandler = new CalculateButtonHandler();
		btnProcessSelection.addActionListener(cbHandler);

		// Create new JCheckBoxes
		cbPepproni = new JCheckBox("Pepproni");
		cbSausage = new JCheckBox("Sausage");
		cbMushrooms = new JCheckBox("Mushrooms");
		cbPineapple = new JCheckBox("Pineapple");
		cbOnion = new JCheckBox("Onion");
		cbBellPepper = new JCheckBox("Bell Pepper");

		// Create radio buttons
		rbSmall = new JRadioButton("Small: $6.50");
		rbMedium = new JRadioButton("Medium: $8.50");
		rbLarge = new JRadioButton("Large: $10.00");
		rbThinCrust = new JRadioButton("Thin Crust");
		rbMediumCrust = new JRadioButton("Medium Crust");
		rbPan = new JRadioButton("Pan");

		// Create new TextArea
		textArea = new JTextArea(6, 10);

		// Set title
		setTitle("Pizza Shop");

		// Create new font layout
		Font font = new Font("New Times Roman", Font.BOLD, 18);

		// get the container
		Container pane = getContentPane();

		JPanel thePanel = new JPanel();
		thePanel.setLayout(new GridBagLayout());

		// set the layout
		pane.setLayout(new GridBagLayout());

		GridBagConstraints gridConstraints = new GridBagConstraints();

		gridConstraints.gridx = 1;
		gridConstraints.gridy = 1;
		gridConstraints.gridwidth = 1;
		gridConstraints.gridheight = 1;
		gridConstraints.weightx = 50;
		gridConstraints.weighty = 100;
		gridConstraints.insets = new Insets(5, 5, 5, 5);
		gridConstraints.anchor = GridBagConstraints.CENTER;
		gridConstraints.fill = GridBagConstraints.BOTH;

		// Place components in the pane
		thePanel.add(lblWelcome, gridConstraints);
		gridConstraints.anchor = GridBagConstraints.FIRST_LINE_START;
		gridConstraints.gridwidth = 20;
		gridConstraints.gridx = 15;
		lblWelcome.setFont(new Font("New Times Roman", Font.BOLD, 18));
		lblWelcome.setForeground(Color.RED);

		// Create new vertical box and place a titled border around it
		Box optionBox1 = Box.createVerticalBox();
		optionBox1.setBorder(BorderFactory.createTitledBorder(null, "Toppings", 0, 0,
				new Font("times new roman", Font.PLAIN, 14), Color.RED));
		// add components to optionBox1
		optionBox1.add(lblEachTopping);
		lblEachTopping.setForeground(Color.RED);
		optionBox1.add(cbPepproni);
		optionBox1.add(cbSausage);
		optionBox1.add(cbMushrooms);
		optionBox1.add(cbPineapple);
		optionBox1.add(cbOnion);
		optionBox1.add(cbBellPepper);
		// add optionBox1 to WEST quadrant
		thePanel.add(optionBox1, gridConstraints);
		gridConstraints.anchor = GridBagConstraints.LINE_START;
		gridConstraints.gridwidth = 1;
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 10;

		// Create new vertical box and place a titled border around it
		Box optionBox2 = Box.createVerticalBox();
		optionBox2.setBorder(BorderFactory.createTitledBorder(null, "Pizza Size", 0, 0,
				new Font("times new roman", Font.PLAIN, 14), Color.RED));
		// Create new ButtonGroup
		grp1 = new ButtonGroup();

		// add components to optionBox2 and grp1
		grp1.add(rbSmall);
		grp1.add(rbMedium);
		grp1.add(rbLarge);
		optionBox2.add(rbSmall);
		optionBox2.add(rbMedium);
		optionBox2.add(rbLarge);
		// add optionBox2 to CENTER
		thePanel.add(optionBox2, gridConstraints);
		gridConstraints.anchor = GridBagConstraints.CENTER;
		gridConstraints.gridx = 3;
		gridConstraints.gridy = 10;

		Box btnBox = Box.createHorizontalBox();
		btnBox.setBorder(BorderFactory.createEmptyBorder());
		btnBox.add(btnProcessSelection);
		thePanel.add(btnBox);
		gridConstraints.anchor = GridBagConstraints.LAST_LINE_END;
		gridConstraints.gridwidth = 5;
		gridConstraints.gridx = 3;
		gridConstraints.gridy = 12;

		// Create new vertical box and place a titled border around it
		Box optionBox3 = Box.createVerticalBox();
		optionBox3.setBorder(BorderFactory.createTitledBorder(null, "Pizza Type", 0, 0,
				new Font("times new roman", Font.PLAIN, 14), Color.RED));
		// Create new ButtonGroup
		grp2 = new ButtonGroup();

		// add components to optionBox2 and grp1
		grp2.add(rbThinCrust);
		grp2.add(rbMediumCrust);
		grp2.add(rbPan);
		optionBox3.add(rbThinCrust);
		optionBox3.add(rbMediumCrust);
		optionBox3.add(rbPan);
		// add optionBox3 to EAST
		thePanel.add(optionBox3, gridConstraints);
		gridConstraints.anchor = GridBagConstraints.LINE_END;
		gridConstraints.gridwidth = 1;
		gridConstraints.gridx = 5;
		gridConstraints.gridy = 10;

		// Create box for lblYourOrder and textArea and
		// add them to pane in SOUTH quadrant
		Box orderBox = Box.createVerticalBox();
		orderBox.setBorder(null);
		orderBox.add(lblYourOrder);
		orderBox.add(textArea);
		thePanel.add(orderBox, gridConstraints);
		gridConstraints.anchor = GridBagConstraints.LAST_LINE_START;
		gridConstraints.gridwidth = 30;
		gridConstraints.gridx = 1;
		gridConstraints.gridy = 13;

		pane.add(thePanel);

		// set window size and display it
		setSize(WIDTH, HEIGHT);
		setVisible(true);

		// Center frame
		this.setLocationRelativeTo(null);

		this.pack();

		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.setTitle("Pizza bar");
		this.setVisible(true);
		this.setSize(600, 400);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setLocationByPlatform(true);

	}
	
	 private class CalculateButtonHandler implements ActionListener
	    {

	        public void actionPerformed(ActionEvent e)
	        {

	        }

	    }

}
