package dictioGUI;

import dictioLexi.LexiNode;

import javax.swing.*;

public class Observer {

    private BodyDictioPanel gui;

    public Observer(BodyDictioPanel composants) {
        this.gui = composants;
    }

    /**
     * va mettre a jour la liste des mots dans le UI
     * @param listeMots liste des mots (pas leurs définition) en String[]
     */
    public void documentTxtCharger(String[] listeMots) {
        gui.setListeAllMots(listeMots);
    }

    /**
     * va mettre a jour la liste des mots dans le UI et save le mot dans le dictionnaire LexiNode
     */
    public void ajouterEditer() {

        String motText = gui.getSaisieMotText();
        String def = gui.getSaisieMotDefinition();
        LexiNode dictio = LexiNode.getInstance();

       LexiNode mot = dictio.getLexiNode(motText, 0, motText.length() -1);
        String[] motaAjouter = {motText, def };
        if (mot != null) {
            mot.setDefinition(def);
        }
      else if (mot == null) {
           dictio.ajouterMot(motaAjouter, 0, motText.length()-1);
           gui.setListeAllMots(dictio.listeAllMots()[0]);
       }
    }
}
