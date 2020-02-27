package dictioGUI;

import dictioLexi.LexiNode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ChargerButtonListener implements ActionListener {

    Observer observer;
    public ChargerButtonListener(Observer observer) {
        this.observer = observer;
    }

    public void actionPerformed(ActionEvent actionEvent) {

        NavigationFichier nf = new NavigationFichier();
        try {
            String[][] strArray = nf.chargerListe();
            LexiNode dictio = LexiNode.getInstance();
            dictio.ajouterListeMots(strArray);
            observer.documentTxtCharger(dictio.getAllMots());
            // afficher liste
         /*  String[][] listemots = BodyDictioPanel.getArrayMots();
             for (int i = 0; i < listemots[0].length; i++)
                System.out.println(listemots[0][i] + " -> "+ listemots[1][i]);*/


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
