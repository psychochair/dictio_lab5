package dictioGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DictioGUI extends JFrame {

    private JTextField 	barreRecherche, definition;
    private JPanel menu;
    private BodyDictioPanel body;
    private JButton charger, enregistrer, addEdit;

    public static int FRAME_WIDTH  = 800;
    public static int FRAME_HEIGHT = 600;

    public DictioGUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        this.setTitle("Dictio");
        this.setBounds((int)((screenWidth-FRAME_WIDTH)/2),
                (int)((screenHeight-FRAME_HEIGHT)/2),
                FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setMinimumSize(new Dimension(400, 250));


        Container contenu = getContentPane();

        body = new BodyDictioPanel();
        contenu.add(body, BorderLayout.CENTER);

        menu = new JPanel();
        contenu.add(menu, BorderLayout.NORTH);

        Observer observer = new Observer(body);

        addEdit = new JButton("Ajouter/modifier");
        addEdit.addActionListener(new AddEditButtonListener(observer));
        contenu.add(addEdit, BorderLayout.SOUTH);


        charger = new JButton("Charger");
        charger.addActionListener(new ChargerButtonListener(observer));

        enregistrer = new JButton("Enregistrer");
        enregistrer.addActionListener(new EnregistrerButtonListener());

        menu.add(charger);
        menu.add(enregistrer);
    }
}
