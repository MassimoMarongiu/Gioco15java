package giocodel15;

import java.awt.Color;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.*;
//   Massimo Marongiu
public class GiocoDel15 {

    public static void main(String[] args) {

        JFrame frame;
        frame = new JFrame("                                                                      GIOCO DEL 15");
//        frame.setSize(800, 600);
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(650, 70);
        frame.setAlwaysOnTop(true);
//        frame.setLayout(null);
        Random rnd = new Random();
        final int N = 3 ;
        int DIM = N * N;
        JPanel[] pan = new JPanel[N];
        String[] pos = {"Center", "South", "North"};
        JButton bottoneMischia = new JButton("MISCHIA");
        JLabel[] label = new JLabel[N * N];
        JLabel in[] = new JLabel[N * N];
        JLabel labelNotifiche = new JLabel();
        JLabel labelVittoria = new JLabel();
        labelNotifiche.setText("");

        int[] r = new int[N * N];
        int[] result = new int[N * N];

        //         pannelli
        for (int i = 0; i < pos.length; i++) {
            pan[i] = new JPanel();
            frame.add(pan[i], pos[i]);

        }

//        bottone mischia
        ActionListener mischia;
        mischia = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                labelVittoria.setText("");
                //        random
                for (int i = 0; i < N * N; i++) {
                    r[i] = rnd.nextInt(N * N - i);
                    result[i] = r[i];
                    for (int j = i - 1; j >= 0; j--) {
                        if (result[i] >= r[j]) {
                            result[i]++;
                        }
                    }
                    label[i].setText(String.valueOf(result[i]));
                }
//                mischia numeri e setta il testo
                String nomeVuoto = "";
                int nomeNumeroVuoto = 0;
                String precedente = "";
                for (int i = 0; i < N * N; i++) {
                    if (Integer.parseInt(label[i].getText()) == 0) {
                        label[i].setText("");
                        System.out.println("");
                        nomeVuoto = label[i].getName();
                        nomeNumeroVuoto = Integer.parseInt(nomeVuoto);
                        label[nomeNumeroVuoto].setBackground(Color.BLUE);
                    }
                    if ((label[i].getText()) != "") {
                        label[i].setBackground(Color.orange);
                        precedente = label[i].getText();
                    }
                }

            }
        };
//        sposta tessere
        MouseListener sposta;
        sposta = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int val = 1;
                String premuto = ((JLabel) e.getSource()).getText();

                String nomeTastoPremuto = ((JLabel) e.getSource()).getName();
                int nomeNumeroTastoPremuto = Integer.parseInt(nomeTastoPremuto);

                String nomeVuoto = "";

                for (int i = 0; i < N * N; i++) {

                    if ((label[i].getText()) == "") {
                        nomeVuoto = label[i].getName();

                    }

                }

                int nomeNumeroVuoto = Integer.parseInt(nomeVuoto);
//          controllo bottoni schiacciati
                boolean controllo = false;
                if (((nomeNumeroTastoPremuto / N) == (nomeNumeroVuoto / N) || ((nomeNumeroTastoPremuto % N) == (nomeNumeroVuoto % N)))) {
                    controllo = true;
                }
                if (controllo == true) {

                    label[nomeNumeroTastoPremuto].setBackground(Color.BLUE);
                    if ((nomeNumeroTastoPremuto / N) - val == (nomeNumeroVuoto / N)) {
                        label[nomeNumeroVuoto].setText(label[nomeNumeroTastoPremuto].getText());
                        label[nomeNumeroTastoPremuto].setText("");
                        label[nomeNumeroVuoto].setBackground(Color.orange);
                    } else if ((nomeNumeroTastoPremuto / N) + val == (nomeNumeroVuoto / N)) {
                        label[nomeNumeroVuoto].setText(label[nomeNumeroTastoPremuto].getText());
                        label[nomeNumeroTastoPremuto].setText("");
                        label[nomeNumeroVuoto].setBackground(Color.orange);
                    } else if ((nomeNumeroTastoPremuto % N) + val == (nomeNumeroVuoto % N)) {
                        label[nomeNumeroVuoto].setText(label[nomeNumeroTastoPremuto].getText());
                        label[nomeNumeroTastoPremuto].setText("");
                        label[nomeNumeroVuoto].setBackground(Color.orange);
                    } else if ((nomeNumeroTastoPremuto % N) - val == (nomeNumeroVuoto % N)) {
                        label[nomeNumeroVuoto].setText(label[nomeNumeroTastoPremuto].getText());
                        label[nomeNumeroTastoPremuto].setText("");
                        label[nomeNumeroVuoto].setBackground(Color.orange);
                    }
                }
//              
//                VITTORIA 
//  array confronto
                for (int i = 0; i < N * N; i++) {
                    in[i] = new JLabel();
                    in[i].setText("" + (i + 1));
                }
//                confronto 2 array
                int k = 0;
                for (int i = 0; i < (N * N); i++) {

                    if (label[i].getText() == "") {
                        label[i].setText("" + 9);
                    }
                    if ((Integer.parseInt(label[i].getText())) == (Integer.parseInt(in[i].getText()))) {
                        k++;
                    } else {
                        k = 0;
                    }
                    if (label[i].getText() == "" + 9) {
                        label[i].setText("");
                    }
                    if (k == 9) {

                        labelVittoria.setText("          HAI VINTO!!!");

                    }
                }

            }

            @Override

            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

// pannello tessere
        for (int i = 0; i < N * N; i++) {
            label[i] = new JLabel();
            label[i].setFont(new Font("Serif", Font.BOLD, 360/N));
            label[i].setName(String.valueOf(i));
            label[i].setText(String.valueOf(i));
            label[i].setHorizontalAlignment(SwingConstants.CENTER);
            label[i].setVerticalAlignment(SwingConstants.CENTER);
            label[0].setText("");
            label[0].setBackground(Color.BLUE);
            label[i].setBackground(Color.orange);
            label[i].setOpaque(true);
            label[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            pan[0].add(label[i]);
            label[i].addMouseListener(sposta);

        }
        frame.add(pan[0], BorderLayout.CENTER);
        pan[0].setLayout(new GridLayout(N + 1, N * 1));
        pan[0].setBackground(Color.RED);
        pan[1].setLayout(new GridLayout(2, 1));
        pan[1].add(labelVittoria);
        pan[1].add(bottoneMischia);
        bottoneMischia.setForeground(Color.red);
        bottoneMischia.addActionListener(mischia);
        bottoneMischia.setBackground(Color.blue);
        bottoneMischia.setFont(new Font("Serif", Font.BOLD, 40));
        bottoneMischia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        labelVittoria.setFont(new Font("Serif", Font.BOLD, 50));
        labelVittoria.setForeground(Color.BLUE);
        pan[1].setBackground(Color.red);
        frame.add(pan[1], BorderLayout.SOUTH);
        pan[2].add(labelNotifiche);
        labelNotifiche.setFont(new Font("Serif", Font.BOLD, 30));
        labelNotifiche.setText("SPOSTA LE TESSERE CON UN CLICK");
        labelNotifiche.setForeground(Color.BLUE);
        pan[2].setBackground(Color.red);
        frame.add(pan[2], BorderLayout.NORTH);
        frame.setVisible(true);
    }

}
