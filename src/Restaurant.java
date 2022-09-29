/**
 * 
 * 	C20410104
 *  Ross Crowley
 *  
 *  I got both part 1 and 2 working separately,  but couldnt figure out how to notify the user 
 *  that the order was ready in the gui. to show question 1 works i kept the print out statements in the terminal window aswell.
 * 
 * */



import java.awt.GridLayout;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant extends JFrame implements ItemListener, ActionListener {

	private static final Insets bottomInsets = new Insets(10, 10, 10, 10);
	private static final Insets normalInsets = new Insets(10, 10, 0, 10);

	JCheckBox cbPepproni, cbMargherita, cbMarinara, cbHawaii, cbVeggie, cbMeaty, cbPersonal, cbSmall, cbMedium, cbLarge,
			cbCoke, cb7Up, cbWater;

	JTextArea textArea;

	JButton bProcessOrder;
	ButtonGroup group;

	ArrayList<Order> orders;
	Waiter waiter;
	Order o;
	String s;
	Scanner scan = new Scanner(System.in);

	public Restaurant() {

		JFrame frame = new JFrame("Pizza Bar");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());

		int gridy = 0;

		addComponent(mainPanel, createTitlePanel(), 0, gridy++, 2, 1, normalInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		addComponent(mainPanel, createPizzaTypePanel(), 0, gridy, 1, 1, normalInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(createSizePanel(), BorderLayout.WEST);
		panel.add(new JLabel(" "), BorderLayout.CENTER);
		panel.add(createDrinkPanel(), BorderLayout.EAST);
		panel.add(createButtonPanel(), BorderLayout.SOUTH);

		addComponent(mainPanel, panel, 1, gridy++, 1, 1, normalInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		addComponent(mainPanel, createTextAreaPanel(), 0, gridy++, 2, 1, bottomInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		this.add(mainPanel);
		this.pack();
		this.setVisible(true);

		orders = new ArrayList<Order>();

	}

	public static void main(String[] args) {

		Restaurant res = new Restaurant();

	}

	private JPanel createTitlePanel() {
		JPanel panel = new JPanel();

		JLabel lblWelcome = new JLabel("Welcome to Pizza Bar");
		Font titleFont = lblWelcome.getFont().deriveFont(25F);
		lblWelcome.setFont(titleFont);

		panel.add(lblWelcome);

		return panel;
	}

	private JPanel createPizzaTypePanel() {
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		Border emptyBorder = BorderFactory.createEmptyBorder(4, 10, 4, 10);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createCompoundBorder(border, emptyBorder));
		panel.setLayout(new GridLayout(0, 1));

		JLabel lblPiazzaType = new JLabel(" PIZZA");
		panel.add(lblPiazzaType);

		cbPepproni = new JCheckBox("Pepperoni");
		cbMargherita = new JCheckBox("Margherita");
		cbMarinara = new JCheckBox("Marinara");
		cbHawaii = new JCheckBox("Hawaii");
		cbVeggie = new JCheckBox("Veggie");
		cbMeaty = new JCheckBox("Meaty");

	    group = new ButtonGroup();

		// add components to optionBox1

		cbPepproni.addItemListener(this);
		cbMargherita.addItemListener(this);
		cbMarinara.addItemListener(this);
		cbHawaii.addItemListener(this);
		cbVeggie.addItemListener(this);
		cbMeaty.addItemListener(this);

		group.add(cbPepproni);
		group.add(cbMargherita);
		group.add(cbMarinara);
		group.add(cbHawaii);
		group.add(cbVeggie);
		group.add(cbMeaty);

		panel.add(cbPepproni);
		panel.add(cbMargherita);
		panel.add(cbMarinara);
		panel.add(cbHawaii);
		panel.add(cbVeggie);
		panel.add(cbMeaty);

		return panel;
	}

	private JPanel createSizePanel() {
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		Border emptyBorder = BorderFactory.createEmptyBorder(4, 10, 4, 10);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createCompoundBorder(border, emptyBorder));
		panel.setLayout(new GridLayout(0, 1));

		JLabel lblPizzaSize = new JLabel("Pizza Size");
		panel.add(lblPizzaSize);

		cbPersonal = new JCheckBox("Personal");
		cbSmall = new JCheckBox("Small");
		cbMedium = new JCheckBox("Medium");
		cbLarge = new JCheckBox("Large");

		// Create new ButtonGroup
		ButtonGroup group = new ButtonGroup();

		cbPersonal.addItemListener(this);
		cbSmall.addItemListener(this);
		cbMedium.addItemListener(this);
		cbLarge.addItemListener(this);

		group.add(cbPersonal);
		group.add(cbSmall);
		group.add(cbMedium);
		group.add(cbLarge);

		panel.add(cbPersonal);
		panel.add(cbSmall);
		panel.add(cbMedium);
		panel.add(cbLarge);

		return panel;
	}

	private JPanel createDrinkPanel() {
		Border border = BorderFactory.createLineBorder(Color.black, 2);
		Border emptyBorder = BorderFactory.createEmptyBorder(4, 10, 4, 10);

		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createCompoundBorder(border, emptyBorder));
		panel.setLayout(new GridLayout(0, 1));

		JLabel lblDrink = new JLabel("Drink");
		panel.add(lblDrink);

		cbCoke = new JCheckBox("Coke");
		cb7Up = new JCheckBox("7UP");
		cbWater = new JCheckBox("Water");

		// Create new ButtonGroup
		ButtonGroup group = new ButtonGroup();

		// add components to optionBox2 and grp1

		cbCoke.addItemListener(this);
		cb7Up.addItemListener(this);
		cbWater.addItemListener(this);

		group.add(cbCoke);
		group.add(cb7Up);
		group.add(cbWater);

		panel.add(cbCoke);
		panel.add(cb7Up);
		panel.add(cbWater);

		return panel;
	}

	JPanel createButtonPanel() {
		JPanel panel = new JPanel();

		bProcessOrder = new JButton("Process Order");
		bProcessOrder.addActionListener(this);
		panel.add(bProcessOrder);

		return panel;
	}

	JPanel createTextAreaPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());

		JLabel lblYourOrder = new JLabel("Your order:");
		panel.add(lblYourOrder, BorderLayout.NORTH);

		textArea = new JTextArea(6, 12);
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel.add(scrollPane, BorderLayout.CENTER);

		return panel;
	}

	// Method to add components into the frame
	private void addComponent(Container container, Component component, int gridx, int gridy, int gridwidth,
			int gridheight, Insets insets, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, 5.0D, 5.0D, anchor, fill,
				insets, 0, 0);
		container.add(component, gbc);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if (e.getSource() == bProcessOrder ) {
			
			String s = "food";
			
			
			o = new Order(s);
			orders.add(o);

			waiter = new Waiter(orders);

			waiter.start();
			
			
			//Could not understand why this wouldn't work. Im checking to see if the waiter is not alive and is done running to then display that message to the customer 
			if(!waiter.isAlive()) {
			JOptionPane.showMessageDialog(this,"Your Order is ready");
			}
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

		String selectedItems = "";
		String selectedSize = "";
		String selectedDrink = "";
		
		
		if (cbPepproni.isSelected()) {
			selectedItems += "PIZZA:  " + cbPepproni.getText()+ "\n";
		}
		
		if (cbMargherita.isSelected()) {
			selectedItems += "PIZZA:  " + cbMargherita.getText()+"\n";
		}
		
		if (cbMarinara.isSelected()) {
			selectedItems += "PIZZA:  " + cbMarinara.getText()+"\n";
		}
		if (cbHawaii.isSelected()) {
			selectedItems += "PIZZA:  " + cbHawaii.getText()+"\n";
		}
		if (cbVeggie.isSelected()) {
			selectedItems += "PIZZA:  " + cbVeggie.getText()+"\n";
		}
		if (cbMeaty.isSelected()) {
			selectedItems += "PIZZA:  " + cbMarinara.getText()+"\n";
		}
		
		textArea.setText(selectedItems);
		
		if (cbPersonal.isSelected()) {
			
			selectedSize += "SIZE:  " + cbPersonal.getText()+"\n";			
		}
		if (cbSmall.isSelected()) {
			
			selectedSize += "SIZE:  " + cbSmall.getText()+"\n";			
		}
		if (cbMedium.isSelected()) {
	
			selectedSize += "SIZE:  " + cbMedium.getText()+"\n";			
		}
		if (cbLarge.isSelected()) {
			
			selectedSize += "SIZE:  " + cbLarge.getText()+"\n";			
		}

		textArea.setText(selectedItems + selectedSize);
		
		if (cbCoke.isSelected()) {
			
			selectedDrink += "DRINK:  " + cbCoke.getText()+"\n";			
		}
		if (cb7Up.isSelected()) {
	
			selectedDrink += "DRINK:  " + cb7Up.getText()+"\n";			
		}
		if (cbWater.isSelected()) {
			
			selectedDrink += "DRINK:  " + cbWater.getText()+"\n";			
		}
		
		textArea.setText(selectedItems + selectedSize + selectedDrink);
		
	
		
		
	}

}

class Waiter extends Thread {

	Chef chef;
	ArrayList<Order> list = new ArrayList<Order>();

	public Waiter(ArrayList o) {

		list = o;

		chef = new Chef(list);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		synchronized (chef) {
			while (!list.isEmpty()) {

				if (!chef.isAlive()) {

					System.out.println("the chef will prepare your order now");
					chef.start();

				} else {

					System.out.println("the chef will prepare your order now");
					chef.notify();

				}

				try {
					chef.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}

class Chef extends Thread {

	ArrayList<Order> list = new ArrayList<Order>();

	public Chef(ArrayList o) {
		// TODO Auto-generated constructor stub

		list = o;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		Order o;

		synchronized (this) {
			while (!list.isEmpty()) {

				o = list.get(0);
				list.remove(0);

				try {
					sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				System.out.println("The order is ready");
				this.notify();
				try {
					this.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}

}
