import java.awt.Component;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Frame extends JFrame implements ActionListener, ItemListener {

	JTextArea ta;
	JMenu user;
	JMenu ingredients;
	JMenu chili;
	JMenu orders;
	JMenuItem login, logout, prep1, prep2;
	JRadioButtonMenuItem nochili, mild, hot, extraHot, habHot;
	JCheckBoxMenuItem tomatoSauce, cheese, pineapple, olives, mushroom, onion, pork, pepperoni, ham, bacon;

	public Frame() {

		user = new JMenu("USER");
		user.add(login = new JMenuItem("Login"));
		user.add(logout = new JMenuItem("Logout"));

		login.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK));
		login.addActionListener(this);

		logout.addActionListener(this);
		logout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Event.SHIFT_MASK));

		ingredients = new JMenu("INGREDIENTS");
		ingredients.add(tomatoSauce = new JCheckBoxMenuItem("Tomato Sauce"));
		ingredients.add(cheese = new JCheckBoxMenuItem("Cheese"));
		ingredients.add(pineapple = new JCheckBoxMenuItem("Pineapple"));
		ingredients.add(olives = new JCheckBoxMenuItem("Olives"));
		ingredients.add(mushroom = new JCheckBoxMenuItem("Mushroom"));
		ingredients.add(onion = new JCheckBoxMenuItem("Onion"));
		ingredients.addSeparator();
		ingredients.add(pork = new JCheckBoxMenuItem("Pork Meatball"));
		ingredients.add(pepperoni = new JCheckBoxMenuItem("Pepperoni"));
		ingredients.add(ham = new JCheckBoxMenuItem("Ham"));
		ingredients.add(bacon = new JCheckBoxMenuItem("Bacon"));

		tomatoSauce.addItemListener(this);
		cheese.addItemListener(this);
		pineapple.addItemListener(this);
		olives.addItemListener(this);
		mushroom.addItemListener(this);
		onion.addItemListener(this);
		pork.addItemListener(this);
		pepperoni.addItemListener(this);
		ham.addItemListener(this);
		bacon.addItemListener(this);

		chili = new JMenu("CHILI LEVEL");
		chili.add(nochili = new JRadioButtonMenuItem("Level 0: No Chili", true));
		chili.add(mild = new JRadioButtonMenuItem("Level 1: Mild"));
		chili.add(hot = new JRadioButtonMenuItem("Level 2: Hot"));
		chili.add(extraHot = new JRadioButtonMenuItem("Level 3: Extra-Hot"));
		chili.add(habHot = new JRadioButtonMenuItem("Level 4: Habanero-Hot"));

		ButtonGroup group = new ButtonGroup();
		group.add(nochili);
		group.add(mild);
		group.add(hot);
		group.add(extraHot);
		group.add(habHot);

		nochili.addActionListener(this);
		mild.addActionListener(this);
		hot.addActionListener(this);
		extraHot.addActionListener(this);
		habHot.addActionListener(this);

		orders = new JMenu("ORDER");
		orders.add(prep1 = new JMenuItem("Prepare Reg Pizza"));
		orders.add(prep2 = new JMenuItem("Prepare Veggie Pizza"));
		
		prep1.addActionListener(this);
		prep2.addActionListener(this);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(user);
		menuBar.add(ingredients);
		menuBar.add(chili);
		menuBar.add(orders);

		JPanel pnl = new JPanel();

		ta = new JTextArea();
		ta.setEditable(true);
		pnl.add(ta);

		this.setTitle("Pizza Gui");
		this.setVisible(true);
		this.setSize(600, 400);
		this.add(pnl);
		this.setJMenuBar(menuBar);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == login) {

			JPasswordField passField = new JPasswordField();
			JTextField userField = new JTextField();
			String message = "Please enter your user name and password ";
			// Object[] eventCache = null;
			int result = JOptionPane.showOptionDialog(this, new Object[] { message, userField, passField }, "Login",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);

			if (result == JOptionPane.OK_OPTION) {

				String welcome = ("Welcome " + userField.getText());

				ta.setText(ta.getText() + welcome);
			}
		}

		
		if (e.getSource() == logout) {

			int result1 = JOptionPane.showConfirmDialog(null, "Do you want to log out? ", "Warning",
					JOptionPane.YES_NO_OPTION);

			switch (result1) {
			case JOptionPane.YES_OPTION:
				System.exit(0);
				break;
			case JOptionPane.NO_OPTION:
				ta.setText(ta.getText() + "\n You have cancelled your logout");

			}
		}

		if (e.getSource() == nochili) {

			ta.setText(ta.getText() + "\n" + "Level 0: No chilli ");

		}

		if (e.getSource() == mild) {

			ta.setText(ta.getText() + "\n" + "Level 1: Mild chilli");

		}

		if (e.getSource() == hot) {

			ta.setText(ta.getText() + "\n" + "Level 2: Hot chilli");

		}

		if (e.getSource() == extraHot) {

			ta.setText(ta.getText() + "\n" + "Level 3: Extra-hot chilli");

		}

		if (e.getSource() == habHot) {

			ta.setText(ta.getText() + "\n" + "Level 4: Habanero-hot");

		}
		
		if (e.getSource() == prep1) {
			
			ta.setText(ta.getText() + "\n" + "Preparing a regular pizza");
			
		
			
			
		}
		
		if (e.getSource() == prep2) {
			
			ta.setText(ta.getText() + "\n" + "Preparing a Vegetarian pizza");
			
			
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {

		if (e.getSource() == tomatoSauce) {

			ta.setText(
					ta.getText() + "\n" + (e.getStateChange() == 1 ? "Tomato Sauce" : "Tomato Sauce has been removed"));

		}

		if (e.getSource() == cheese) {

			ta.setText(ta.getText() + "\n" + (e.getStateChange() == 1 ? "cheese" : "cheese has been removed"));

		}
		if (e.getSource() == pineapple) {

			ta.setText(ta.getText() + "\n" + (e.getStateChange() == 1 ? "pineapple" : "pineapple has been removed"));

		}

		if (e.getSource() == olives) {

			ta.setText(ta.getText() + "\n" + (e.getStateChange() == 1 ? "olives" : "olives has been removed"));

		}
		if (e.getSource() == mushroom) {

			ta.setText(ta.getText() + "\n" + (e.getStateChange() == 1 ? "mushroom" : "mushroom has been removed"));

		}

		if (e.getSource() == onion) {

			ta.setText(ta.getText() + "\n" + (e.getStateChange() == 1 ? "onion" : "onion has been removed"));

		}
		if (e.getSource() == ham) {

			ta.setText(ta.getText() + "\n" + (e.getStateChange() == 1 ? "Ham" : "Ham has been removed"));

		}

		if (e.getSource() == pork) {

			ta.setText(ta.getText() + "\n" + (e.getStateChange() == 1 ? "Pork" : "Pork has been removed"));

		}
		if (e.getSource() == pepperoni) {

			ta.setText(ta.getText() + "\n" + (e.getStateChange() == 1 ? "pepperoni" : "pepperoni has been removed"));

		}

		if (e.getSource() == bacon) {

			ta.setText(ta.getText() + "\n" + (e.getStateChange() == 1 ? "bacon" : "bacon has been removed"));

		}
	}

}
