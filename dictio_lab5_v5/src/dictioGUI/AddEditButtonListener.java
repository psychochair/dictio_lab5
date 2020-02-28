package dictioGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEditButtonListener implements ActionListener {

    Observer observer;

    public AddEditButtonListener(Observer observer) {
        this.observer = observer;
    }

    /**
     * Méthode qui configure le résultat de l'appui du bouton ajouterEditer
     * Precondition: appui du bouton
     * Postcondition: Ajout d'un mot, ou modification de la desc d'un mot.
     * @param actionEvent
     */
    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        observer.ajouterEditer();
    }
}
