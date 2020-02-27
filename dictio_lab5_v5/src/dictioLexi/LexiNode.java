package dictioLexi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LexiNode {

    private static LexiNode dictionnaire;

    public static LexiNode getInstance() {
        if (dictionnaire == null)
            dictionnaire = new LexiNode(' ', null, null);
        return dictionnaire;
    }

    public static void ajouterListeMots(String[][] listeMots) {
        LexiNode dictio = getInstance();
        for (int i = 0; i < listeMots[0].length; i++) {
            String nomMot = listeMots[0][i].toLowerCase();
            String definitionMot = listeMots[1][i];

            dictio.ajouterMot(new String[] {nomMot, definitionMot}, 0, nomMot.length()-1);

        }

    }

    private char lettreRepresentante;
    private String motCourant; // null si il n'y a pas de mot dans le dictio
    private String definition; // null si mot n'existe pas
    private Collection<LexiNode> enfants;

    public LexiNode(char lettreRepresentante, String motCourant, String definition) {
        this.lettreRepresentante = lettreRepresentante;
        this.motCourant = motCourant;
        this.definition = definition;
        enfants = new ArrayList<>();
    }

    public char getLettreRepresentante() {
        return lettreRepresentante;
    }

    public String getMotCourant() {
        return motCourant;
    }

    public void setMotCourant(String motCourant) {
        this.motCourant = motCourant;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public void ajouterEnfant(LexiNode enfant) {
        this.enfants.add(enfant);
    }
    public void ajouterMot(String[] motAjoute, int indexLettreActuelle, int indexDerniereLettre) {

        if (indexLettreActuelle > indexDerniereLettre) {
            this.motCourant = motAjoute[0];
            this.definition = motAjoute[1];
        } else {
            char lettreMotCourant = motAjoute[0].charAt(indexLettreActuelle);
            LexiNode enfant = trouverEnfant(lettreMotCourant);
            if (enfant != null && indexLettreActuelle < indexDerniereLettre)
                enfant.ajouterMot(motAjoute, indexLettreActuelle + 1, indexDerniereLettre);
            else if (enfant == null && indexLettreActuelle <= indexDerniereLettre){
                LexiNode nouveauEnfant = new LexiNode(lettreMotCourant, null, null);
                this.ajouterEnfant(nouveauEnfant);
                nouveauEnfant.ajouterMot(motAjoute, indexLettreActuelle + 1, indexDerniereLettre);
            }
        }

    }

    public String[] getMotDisponibleToString(String baseMot) {

        ArrayList<LexiNode> listemot = getMotDisponible(baseMot);
       String[] listToString = new String[listemot.size()];
        for (int j = 0; j < listemot.size(); j++) {
            // Assign each value to String array
            listToString[j] = listemot.get(j).getMotCourant();
        }

        return listToString;

    }

    public String[] getAllMots() {

        ArrayList<LexiNode> listeMots = ChercherMots();

        String[] listToString = new String[listeMots.size()];
        for (int j = 0; j < listeMots.size(); j++) {
            // Assign each value to String array
            listToString[j] = listeMots.get(j).getMotCourant();
        }

        return listToString;


    }

    public ArrayList<LexiNode> getMotDisponible(String baseMot) {
        ArrayList<LexiNode> listeMotsDispo = new ArrayList<>();
        LexiNode mot = getLexiNode(baseMot, 0, baseMot.length()-1);
        if (mot == null)
            return listeMotsDispo;
        listeMotsDispo.addAll(mot.ChercherMots());
        return listeMotsDispo;
    }

    public String[][] listeAllMots() {
        Collection<LexiNode> listeLexi = ChercherMots();
        List<String> listeMots = new ArrayList<String>();
        List<String> listeDesc = new ArrayList<String>();
        for (LexiNode lexi : listeLexi) {
            listeMots.add(lexi.getMotCourant());
            listeDesc.add(lexi.getDefinition());
        }
        // Conversion des ArrayLists en une seule String[][]
        String[][] strArray = new String[2][0];
        strArray[0] = listeMots.toArray(new String[0]);
        strArray[1] = listeDesc.toArray(new String[0]);

        return strArray;
    }
    public ArrayList<LexiNode> ChercherMots() {
        ArrayList<LexiNode> listeMotsDispo = new ArrayList<>();
        if (motCourant != null)
            listeMotsDispo.add(this);

        if (enfants != null && enfants.size() > 0)
            for(LexiNode enfant : enfants) {
                listeMotsDispo.addAll(enfant.ChercherMots());
            }

        return listeMotsDispo;
    }
    public LexiNode getLexiNode(String baseMot, int indexLettreActuelle, int indexDerniereLettre) {

        if (indexLettreActuelle > indexDerniereLettre) {
            return this;
        }
        char lettre = baseMot.charAt(indexLettreActuelle);
        LexiNode enfant = trouverEnfant(lettre);
        if (enfant != null && indexLettreActuelle <= indexDerniereLettre) {
           return enfant.getLexiNode(baseMot, indexLettreActuelle +1, indexDerniereLettre);
        }

        return null;
    }

    public LexiNode trouverEnfant(char lettre) {

        for (LexiNode enfant : this.enfants){
            if (enfant.lettreRepresentante == lettre)
                return enfant;
        }
        return null;
    }
}
