package dictioGUI;

import dictioLexi.LexiNode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EnregistrerButtonListener implements ActionListener {

    /**
     * Méthode qui configure le résultat de l'appui du bouton Enregistrer
     * Precondition: appui du bouton
     * Postcondition: Enregistrement de la liste.
     * @param actionEvent
     */
    public void actionPerformed(ActionEvent actionEvent) {
        NavigationFichier nf = new NavigationFichier();

        try {
            LexiNode dictio = LexiNode.getInstance();

            nf.enregisterListe(dictio.listeAllMots()); // a changer selon fonctionnalité du code

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
