package dictioGUI;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NavigationFichier extends JFrame {


    /**
     * Constructeur par défault
     */
    public NavigationFichier(){
    }

    /**
     * Permet le chargement d'une liste de mots
     * Precondition: Le fichier doit être dans un format valide, et non-null
     * Postcondition: retourne une String[][] avec des mots et leurs descriptions
     * @return String[][] contenant les mots et leurs description
     * @throws IOException
     */
    public String[][] chargerListe() throws IOException{
        // Le fichier source est trouvé en utilisant la méthode getFichierFromUser()
        File source = getFichierFromUser();

        if(source != null) { // verification de la precondition

            Scanner lecteur = new Scanner(source);

            // On creer 2 arrayList<String> pour les mots, et leurs descriptions, respectivement.
            List<String> listeMots = new ArrayList<String>();
            List<String> listeDesc = new ArrayList<String>();

            // Boucle qui parcout le fichier au complet.
            while (lecteur.hasNext()) {
                // Le code prend une ligne complète du fichier, et la divise à chaque fois que "&" est recontré.
                String tempString = lecteur.nextLine();
                String[] tempStrArr = tempString.split("&");

                if (tempStrArr.length == 2) {
                    // Les mots et les descriptions sont ajoutés dans leur listes respectives
                    listeMots.add(tempStrArr[0].trim());
                    listeDesc.add(tempStrArr[1].trim());
                }

            }

            // Conversion des ArrayLists en une seule String[][]
            String[][] strArray = new String[2][0];
            strArray[0] = listeMots.toArray(new String[0]);
            strArray[1] = listeDesc.toArray(new String[0]);

            // retour de la String[][]
            return strArray;
        }else{
            return null;
        }
    }

    /**
     * Enregistre une String[][] dans un fichier texte
     * Precondition: Un tableau 2D doit être passée en paramètre
     * Postcondition: L'opération doit soit être complétée au annulée
     * @param liste     Utilises une String[][]
     * @throws IOException
     */
    public void enregisterListe(String[][] liste) throws IOException{
        // Initialisation et configuration d'un JFileChooser
        JFileChooser fc = new JFileChooser();

        // On veut seulement voir les fichiers ".txt"
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
        fc.setFileFilter(filter);

        // On configure le dialogue du JFileChooser à Save
        int retval = fc.showSaveDialog(this);
        if (retval == JFileChooser.APPROVE_OPTION) {    // une fois que l'emplacement de sauvegarde a été choisi
            File file = new File(fc.getSelectedFile() + ".txt");    // creation d'un fichier .txt
            ecrireListeToFile(liste, file); // appelle de la méthode qui converti une String[][] en texte
        } else {
            System.out.println("Annulé");   // Si l'opération est annulée.
        }
    }

    /**
     * Permet de demander a l'utilisateur de choisir un fichier
     * Preconditon:
     * Postcondition: L'opération doit soit être complétée au annulée
     * @return File     retourne le fichier choisi par l'utilisateur
     */
    private File getFichierFromUser(){
        // Initialisation et configuration d'un JFileChooser
        JFileChooser fc = new JFileChooser();

        // On veut seulement voir les fichiers ".txt"
        FileNameExtensionFilter filter = new FileNameExtensionFilter(".txt", "txt");
        fc.setFileFilter(filter);

        // On configure le dialogue du JFileChooser à Open
        if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {   // une fois que le fichier a été choisi
            File f = fc.getSelectedFile();
            return f.getAbsoluteFile(); // retour du fichier choisi
        } else {
            System.out.println("Annulé");   // si l'opération est annulé
            return null;
        }
    }

    /**
     * Écrit un tableau2D dans un fichier txt selon le format du logiciel
     * Precondition: Il faut un tableau2D avec un formattage valide, et un emplacement de fichier existant
     * Postcondition: Le fichier doit être une copie du tableau2D passé en paramètre
     * @param source    Utilises une String[][] qui va être convertie.
     * @param destination   Emplacement ou la String[][] va être convertie.
     * @throws IOException
     */
    private void ecrireListeToFile(String[][] source, File destination) throws IOException {
        // Création d'un BufferedWriter
        BufferedWriter destinationWriter = null;

        // Initialisation du BufferedWriter avec un new FileWriter qui utilise le fichier destination en paramètre.
        destinationWriter = new BufferedWriter(new FileWriter(destination));

        // Boucle qui parcout la string[][] source
        for (int i = 0; i < source[0].length; i++) {
            // On écrit un mot, suivi du charactère '&', la description du mot et finalement on passe une ligne.
            destinationWriter.write(source[0][i]+" & "+source[1][i]);
            destinationWriter.newLine();
        }

        // Flush et close du bufferedwriter
        destinationWriter.flush();
        destinationWriter.close();
    }
}

