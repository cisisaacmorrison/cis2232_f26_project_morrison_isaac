package ca.hccis.threads;

import ca.hccis.entity.Controller;
import javax.swing.JOptionPane;

public class Runner2 implements Runnable{

    @Override
    public void run() {
        Controller.orderGUI();
    }
}
