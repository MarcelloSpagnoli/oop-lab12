package it.unibo.es3;

import javax.swing.*;

import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class GUI extends JFrame {
    
    private final List<JButton> cells = new ArrayList<>();
    private final Logics logics;
    
    public GUI(int width) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70*width, 70*width);
        final JButton next = new JButton(">");
        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(BorderLayout.NORTH, next);
        logics = new LogicsImpl(width);

        next.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (logics.toQuit()) {
                    System.exit(0);
                }
                logics.nextStep();
                GUI.this.update(logics.getMarked());
            }
        });
        
        JPanel panel = new JPanel(new GridLayout(width,width));
        this.getContentPane().add(BorderLayout.CENTER, panel);
                
        for (int i=0; i<width; i++){
            for (int j=0; j<width; j++){
                final JButton jb = new JButton();
                this.cells.add(jb);
                panel.add(jb);
            }
        }
        this.update(logics.getMarked());
        this.setVisible(true);
    }

    private void update(Set<Pair<Integer, Integer>> marked) {      
        /*for (Pair<Integer,Integer> pair : marked) {
            int index = pair.getX() * this.logics.getSize() + pair.getY();     
            cells.get(index).setText("*");
        }*/
        marked.stream()
            .map(x -> x.getX() * logics.getSize() + x.getY())
            .forEach(y -> cells.get(y).setText("*"));

    }

    
}