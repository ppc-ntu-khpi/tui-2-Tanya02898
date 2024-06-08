package domain;

import jexer.TAction;
import jexer.TApplication;
import jexer.TField;
import jexer.TText;
import jexer.TWindow;
import jexer.TApplication.BackendType;
import jexer.event.TMenuEvent;
import jexer.menu.TMenu;

public class TUIdemo extends TApplication {
    private static final int ABOUT_APP = 2000;
    private static final int CUST_INFO = 2010;
    private Customer[] customers = new Customer[]{new Customer(1, "John Doe", "Checking", 200.0), new Customer(2, "Tim Smith", "Savings", 1500.5), new Customer(3, "Maria Soley", "Checking", 50.75)};

    public static void main(String[] args) throws Exception {
        TUIdemo tdemo = new TUIdemo();
        (new Thread(tdemo)).start();
    }

    public TUIdemo() throws Exception {
        super(BackendType.SWING);
        this.addToolMenu();
        TMenu fileMenu = this.addMenu("&File");
        fileMenu.addItem(2010, "&Customer Info");
        fileMenu.addDefaultItem(13);
        fileMenu.addSeparator();
        fileMenu.addDefaultItem(11);
        this.addWindowMenu();
        TMenu helpMenu = this.addMenu("&Help");
        helpMenu.addItem(2000, "&About...");
        this.setFocusFollowsMouse(true);
        this.ShowCustomerDetails();
    }

    protected boolean onMenu(TMenuEvent menu) {
        if (menu.getId() == 2000) {
            this.messageBox("About", "\t\t\t\t\t   Just a simple Jexer demo.\n\nCopyright © 2019 Alexander 'Taurus' Babich").show();
            return true;
        } else if (menu.getId() == 2010) {
            this.ShowCustomerDetails();
            return true;
        } else {
            return super.onMenu(menu);
        }
    }

    private void ShowCustomerDetails() {
        TWindow custWin = this.addWindow("Customer Window", 2, 1, 40, 10, 16);
        custWin.newStatusBar("Enter valid customer number and press Show...");
        custWin.addLabel("Enter customer number: ", 2, 2);
        final TField custNo = custWin.addField(24, 2, 3, false);
        final TText details = custWin.addText("Owner Name: \nAccount Type: \nAccount Balance: ", 2, 4, 38, 8);
        custWin.addButton("&Show", 28, 2, new TAction() {
            public void DO() {
                try {
                    int custNum = Integer.parseInt(custNo.getText());
                    Customer customer = TUIdemo.this.findCustomerById(custNum);
                    if (customer != null) {
                        details.setText(String.format("Owner Name: %s (id=%d)\nAccount Type: '%s'\nAccount Balance: $%.2f", customer.getName(), customer.getId(), customer.getAccountType(), customer.getAccountBalance()));
                    } else {
                        TUIdemo.this.messageBox("Error", "Customer not found!").show();
                    }
                } catch (Exception var3) {
                    TUIdemo.this.messageBox("Error", "You must provide a valid customer number!").show();
                }

            }
        });
    }

    private Customer findCustomerById(int id) {
        Customer[] var2 = this.customers;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Customer customer = var2[var4];
            if (customer.getId() == id) {
                return customer;
            }
        }

        return null;
    }
}