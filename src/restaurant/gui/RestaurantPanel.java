package restaurant.gui;

import restaurant.CustomerAgent;
import restaurant.HostAgent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

/**
 * Panel in frame that contains all the restaurant information,
 * including host, cook, waiters, and customers.
 */
public class RestaurantPanel extends JPanel {

    //Host, cook, waiters and customers
    private HostAgent host = new HostAgent("Sarah");
    private HostGui hostGui = new HostGui(host);

    private Vector<CustomerAgent> customers = new Vector<CustomerAgent>();

    private JPanel restLabel = new JPanel();
    private ListPanel customerPanel = new ListPanel(this, "Customers");
    private JPanel group = new JPanel();

    private RestaurantGui gui; //reference to main gui

    public RestaurantPanel(RestaurantGui gui) {
        this.gui = gui;
        host.setGui(hostGui);

        gui.animationPanel.addGui(hostGui);
        host.startThread();

        setLayout(new GridLayout(1, 2, 20, 20));
        group.setLayout(new GridLayout(1, 2, 10, 10));

        group.add(customerPanel);

        initRestLabel();
        add(restLabel);
        add(group);
    }

    /**
     * Sets up the restaurant label that includes the menu,
     * and host and cook information
     */
    private void initRestLabel() {
        JLabel label = new JLabel();
        //restLabel.setLayout(new BoxLayout((Container)restLabel, BoxLayout.Y_AXIS));
        restLabel.setLayout(new BorderLayout());
        label.setText(
                "<html><h3><u>Tonight's Staff</u></h3><table><tr><td>host:</td><td>" + host.getName() + "</td></tr></table><h3><u> Menu</u></h3><table><tr><td>Steak</td><td>$15.99</td></tr><tr><td>Chicken</td><td>$10.99</td></tr><tr><td>Salad</td><td>$5.99</td></tr><tr><td>Pizza</td><td>$8.99</td></tr></table><br></html>");

        restLabel.setBorder(BorderFactory.createRaisedBevelBorder());
        restLabel.add(label, BorderLayout.CENTER);
        restLabel.add(new JLabel("               "), BorderLayout.EAST);
        restLabel.add(new JLabel("               "), BorderLayout.WEST);
    }

    /**
     * When a customer or waiter is clicked, this function calls
     * updatedInfoPanel() from the main gui so that person's information
     * will be shown
     *
     * @param type indicates whether the person is a customer or waiter
     * @param name name of person
     */
    public void showInfo(String type, String name) {

        if (type.equals("Customers")) {

            for (int i = 0; i < customers.size(); i++) {
                CustomerAgent temp = customers.get(i);
                if (temp.getName() == name)
                    gui.updateInfoPanel(temp);
            }
        }
    }

    /**
     * Adds a customer or waiter to the appropriate list
     *
     * @param type indicates whether the person is a customer or waiter (later)
     * @param name name of person
     */
    public void addPerson(String type, String name) {

    	if (type.equals("Customers")) {
    		CustomerAgent c = new CustomerAgent(name);	
    		CustomerGui g = new CustomerGui(c, gui);

    		gui.animationPanel.addGui(g);// dw
    		c.setHost(host);
    		c.setGui(g);
    		customers.add(c);
    		c.startThread();
    	}
    }

}
