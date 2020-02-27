package dictioGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEditButtonListener implements ActionListener {

    Observer observer;

    public AddEditButtonListener(Observer observer) {
        this.observer = observer;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        observer.ajouterEditer();
    }
}
