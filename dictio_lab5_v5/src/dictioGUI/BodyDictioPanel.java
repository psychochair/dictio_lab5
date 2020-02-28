package dictioGUI;

import dictioLexi.LexiNode;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class BodyDictioPanel extends JPanel {
    private JList<String> listeMotsProposes, listeAllMots;
    private JTextArea saisieMotDefinition;
    private JTextField saisieMot;
    private JPanel recherchePanel;

    private static String[][] arrayMots;

    public void setSaisieMotDefinition(String definition) {
        saisieMotDefinition.setText(definition);
    }

     /**
     * Modifie une JList pour setter une liste de Mot String
     * @param listeMots la liste des mots
     */
    public void setListeAllMots(String[] listeMots) {
        listeAllMots.setListData(listeMots);
    }

     /**
     * Getter pour le String brute de JTextField saisieMot
     * @return le mot saisie par le user.
     */
    public String getSaisieMotText() {
        return saisieMot.getText();
    }

    /**
     * Getter pour le String brute de JTextField saisieDefinition
     * @return la définition.
     */
    public String getSaisieMotDefinition() {
        return  saisieMotDefinition.getText();
    }

    /**
     * Constructeur qui va instancier le JPanel et ses différentes composantes GUI
     * Precondition: null
     * Postcondition: Instanciation d'un JPanel et des composantes de l'app
     */
    public BodyDictioPanel() {

        this.setLayout(new GridLayout());

        saisieMot = new JTextField();
        saisieMot.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
               // alerter pour update la liste des mots proposés
              String text =  saisieMot.getText();
              LexiNode dictio =  LexiNode.getInstance();
             String[] listMotsDispo = dictio.getMotDisponibleToString(text);

            listeMotsProposes.setListData(listMotsDispo);
              LexiNode motActuel =  dictio.getLexiNode(text, 0, text.length()-1);
              if (motActuel != null) {
                  saisieMotDefinition.setText(motActuel.getDefinition());
              }
            }
        });
        saisieMot.setPreferredSize(new Dimension(10,50));

        recherchePanel = new JPanel();
        recherchePanel.setLayout(new BorderLayout());
        recherchePanel.add(saisieMot, BorderLayout.NORTH);

        listeMotsProposes = new JList<String>();
        JScrollPane scrollMotsPropose = new JScrollPane(listeMotsProposes);

        recherchePanel.add(scrollMotsPropose,BorderLayout.CENTER);
        listeAllMots = new JList<String>();
        saisieMotDefinition = new JTextArea();

        JScrollPane scrollDefinition = new JScrollPane(saisieMotDefinition);

        this.add(recherchePanel, BorderLayout.WEST);
        this.add(scrollDefinition,BorderLayout.CENTER);

        JScrollPane scrollAllMots = new JScrollPane(listeAllMots);
        this.add(scrollAllMots, BorderLayout.EAST);
    }

}
