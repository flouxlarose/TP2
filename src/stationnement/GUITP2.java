package stationnement;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class GUITP2 {
    private JPanel panel1;
    private JLabel labelLogo;
    private JPanel panelNumeros;
    private JPanel panelOperations;
    private JPanel panelComptant;
    private JTextArea champMessage;
    private JButton bouton25;
    private JButton bouton100;
    private JButton bouton200;
    private JPanel panelCredit;

    private JTextArea zoneRecu;
    private JButton boutonRapport;
    private JFormattedTextField champNumeroCarte;
    private JFormattedTextField champDateExp;
    private JButton boutonValiderDateExp;
    private JPanel panelControles;
    private JButton boutonMax;
    private JButton boutonPlus;
    private JButton boutonMoins;
    private JButton boutonOK;
    private JButton boutonAnnuler;
    private JPanel panelRecu;
    private JPanel panelGauche;
    private JPanel panelDroite;
    private JPanel panelMessages;

    private EcouteurNumero ecouteurNumero;
    private EcouteurCarteCredit ecouteurCarteCredit;
    private EcouteurMonnaie ecouteurMonnaie;
    private EcouteurControles ecouteurControles;
    private EcouteurEntree ecouteurEntree;

    // variables utiles pour vous
    String place =""; //place de stationnement choisie
    Borne b; // borne à créer dans le constructeur

    //formatage
    public GUITP2() throws ParseException {

        labelLogo.setIcon(new ImageIcon("logo.png"));
        boutonValiderDateExp.setMargin(new Insets(10,0, 10, 0));
        //Création des écouteurs
        ecouteurNumero = new EcouteurNumero();
        ecouteurCarteCredit = new EcouteurCarteCredit();
        ecouteurMonnaie = new EcouteurMonnaie();
        ecouteurControles = new EcouteurControles();
        ecouteurEntree = new EcouteurEntree();

        // panelNumeros avec la grille
        GridBagLayout gl = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        FlowLayout f = new FlowLayout(FlowLayout.CENTER);
        panelOperations.setLayout(f);

        panelNumeros.setLayout(gl);
        c.fill = GridBagConstraints.BOTH;
        c.weightx =1;
        c.weighty=1;
        for ( int i = 0; i <15 ; i++) {
            JButton temp = new JButton();
            temp.setFont(new Font("Tw Cen MT", Font.BOLD, 16));
            temp.setForeground(new Color(0,174,239));
            temp.addActionListener(ecouteurNumero);
            if  ( i ==0 )
                temp.setText("A");
            else if ( i ==1 )
                temp.setText("B");
            else if ( i==2 )
               temp.setText("G");
            else if (i==3) {
                c.gridwidth = GridBagConstraints.REMAINDER; //end row
                temp.setText("SQ");
            }
            else if (i<=6) {
                c.weightx=1;
                c.gridwidth = 1;
                temp.setText(String.valueOf(i-4));
            } else if (i==7) {
                c.gridwidth = GridBagConstraints.REMAINDER; //end row
                temp.setText(String.valueOf(i-4));
            } else if (i<=10) {
                c.weightx=1;
                c.gridwidth = 1;
                temp.setText(String.valueOf(i-4));
            } else if (i==11) {
                c.gridwidth = GridBagConstraints.REMAINDER; //end row
                temp.setText(String.valueOf(i-4));
            } else if (i<=13) {
                c.weightx=1;
                c.gridwidth = 1;
                temp.setText(String.valueOf(i-4));
            } else {
                c.gridwidth = GridBagConstraints.REMAINDER; //end row
                temp.setText("entrée");
                temp.removeActionListener(ecouteurNumero);
                temp.addActionListener(ecouteurEntree);
            }
            gl.setConstraints(temp, c );
            panelNumeros.add( temp);
        }

        // inscrire les sources à l'écouteur
        bouton25.addActionListener(ecouteurMonnaie);
        bouton100.addActionListener(ecouteurMonnaie);
        bouton200.addActionListener(ecouteurMonnaie);

        boutonMax.addActionListener(ecouteurCarteCredit);
        boutonPlus.addActionListener(ecouteurCarteCredit);
        boutonMoins.addActionListener(ecouteurCarteCredit);
        boutonValiderDateExp.addActionListener(ecouteurCarteCredit);
        boutonOK.addActionListener(ecouteurControles);
        boutonAnnuler.addActionListener(ecouteurControles);
        boutonRapport.addActionListener(ecouteurControles);

        //1. créer objet Borne
        b = new Borne();

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        try {
            champNumeroCarte = new JFormattedTextField(new MaskFormatter("#### #### #### ####"));
            champDateExp = new JFormattedTextField(new MaskFormatter("##/##"));
        }
        catch ( ParseException pe) {
            pe.printStackTrace();
        }
    }
    private void $$$setupUI$$$() {
        createUIComponents();
    }
    private class EcouteurNumero implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            //lettre ou chiffre du bouton qu'on a cliqué dessus
           String lettreChiffre = ((JButton)e.getSource()).getText();
           boutonNumeroLettre_actionPerformed( lettreChiffre);
        }
    }

    private class EcouteurEntree implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            boutonEntree_actionPerformed();
        }
    }

    private class EcouteurMonnaie implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ( e.getSource() == bouton25)
                bouton25_actionPerformed();
            else if ( e.getSource()==bouton100)
                bouton100_actionPerformed();
            else if ( e.getSource() == bouton200)
                bouton200_actionPerformed();
        }
    }

    private class EcouteurCarteCredit implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

             if ( e.getSource() == boutonPlus)
                boutonPlus_actionPerformed();
             else if ( e.getSource() == boutonMoins)
                 boutonMoins_actionPerformed();
            else if (e.getSource() == boutonMax)
                boutonMax_actionPerformed();
            else if ( e.getSource() == boutonValiderDateExp)
                boutonValiderDateExp_actionPerformed();
        }
    }

    private class EcouteurControles implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if ( e.getSource() == boutonOK)
                boutonOK_actionPerformed();
            else if ( e.getSource() == boutonRapport)
                boutonRapport_actionPerformed();
            else if ( e.getSource() == boutonAnnuler)
                boutonAnnuler_actionPerformed();
        }
    }

    public void boutonNumeroLettre_actionPerformed(String lettreChiffre) {
        if (b.getTransactionCourante() == null) {
            zoneRecu.setText("");
            place += lettreChiffre;
            champMessage.setText(place);
        }

    }

    private void boutonEntree_actionPerformed() {
        if (b.getTransactionCourante() == null){
            LocalDateTime present = LocalDateTime.now();
            if (b.validerPlace(place)){
                if (b.validerHeure(present, place)){
                    champMessage.setText("Place Validé");
                    Transaction t = new Transaction(place);
                    b.setTransactionCourante(t);
                }
                else{
                    champMessage.setText("Heure Non Tarifée");
                    place = "";
                }
            }
            else {
                champMessage.setText("Place Invalide... Réessayer");
                place = "";
            }
        }

    }

    private void bouton25_actionPerformed() {
        if (b.getTransactionCourante() != null && !(b.getTransactionCourante().getPaiement().equals("carte"))){
            b.getTransactionCourante().setPaiement("comptant");
            Piece piece25 = new Piece(25);
            b.ajouterMonaie(piece25);
            DecimalFormat df = new DecimalFormat("0.00$");
            champMessage.setText("Vous avez " + Math.round(b.getTransactionCourante().getTemps()) + " minutes pour " + (df.format((double) b.getTransactionCourante().getCout() / 100)) + ".");
        }
    }

    private void bouton100_actionPerformed() {
        if (b.getTransactionCourante() != null && !(b.getTransactionCourante().getPaiement().equals("carte"))){
            b.getTransactionCourante().setPaiement("comptant");
            Piece piece100 = new Piece(100);
            b.ajouterMonaie(piece100);
            DecimalFormat df = new DecimalFormat("0.00$");
            champMessage.setText("Vous avez " + Math.round(b.getTransactionCourante().getTemps()) + " minutes pour " + (df.format((double) b.getTransactionCourante().getCout() / 100)) + ".");
        }
    }

    private void bouton200_actionPerformed() {
        if (b.getTransactionCourante() != null && !(b.getTransactionCourante().getPaiement().equals("carte"))){
            b.getTransactionCourante().setPaiement("comptant");
            Piece piece100 = new Piece(100);
            b.ajouterMonaie(piece100);
            DecimalFormat df = new DecimalFormat("0.00$");
            champMessage.setText("Vous avez " + Math.round(b.getTransactionCourante().getTemps()) + " minutes pour " + (df.format((double) b.getTransactionCourante().getCout() / 100)) + ".");
        }
    }

    private void boutonValiderDateExp_actionPerformed(){
        if (b.getTransactionCourante() != null && !(b.getTransactionCourante().getPaiement().equals("comptant"))){
            String numeroCarte = champNumeroCarte.getText();
            champNumeroCarte.setText(numeroCarte);
            String dateExp = champDateExp.getText();
            if (b.verifNumCredit(numeroCarte) && b.verifExpCredit(dateExp)){
                b.getTransactionCourante().setPaiement("carte");
                b.getTransactionCourante().setCarteCredit(numeroCarte, dateExp);
                champMessage.setText("Carte Validée");
            }
            else{
                champNumeroCarte.setText("");
                champDateExp.setText("");
                champMessage.setText("Carte Invalide... Réessayer");
            }
        }
    }

    private void boutonPlus_actionPerformed() {
        if (b.getTransactionCourante() != null && b.getTransactionCourante().getPaiement().equals("carte")) {
            b.getTransactionCourante().ajouterTemps(15.0);
            DecimalFormat df = new DecimalFormat("0.00$");
            champMessage.setText("Vous avez " + Math.round(b.getTransactionCourante().getTemps()) + " minutes pour " + (df.format((double) b.getTransactionCourante().getCout() / 100)) + ".");
        }
    }

    private void boutonMoins_actionPerformed(){
        if (b.getTransactionCourante() != null && b.getTransactionCourante().getPaiement().equals("carte")) {
            b.getTransactionCourante().retirerTemps(15.0);
            DecimalFormat df = new DecimalFormat("0.00$");
            champMessage.setText("Vous avez " + Math.round(b.getTransactionCourante().getTemps()) + " minutes pour " + (df.format((double) b.getTransactionCourante().getCout() / 100)) + ".");
        }
    }

    private void boutonMax_actionPerformed() {
        if (b.getTransactionCourante() != null && b.getTransactionCourante().getPaiement().equals("carte")) {
            b.getTransactionCourante().ajouterTemps(120.0);
            DecimalFormat df = new DecimalFormat("0.00$");
            champMessage.setText("Vous avez " + Math.round(b.getTransactionCourante().getTemps()) + " minutes pour " + (df.format((double) b.getTransactionCourante().getCout() / 100)) + ".");
        }
    }

    private void boutonOK_actionPerformed() {
        if (b.getTransactionCourante() != null && (b.getTransactionCourante().getPaiement().equals("comptant") || b.getTransactionCourante().getPaiement().equals("carte"))){
            if (b.getTransactionCourante().getPaiement().equals("carte")){
                if (b.getTransactionCourante().getCarteCredit().getSolde() >= (b.getTransactionCourante().getCout()) / 100.0){
                    b.setBanque(b.getBanque() + b.getTransactionCourante().getCout());
                    b.getTransactionCourante().getCarteCredit().retirerMontant(b.getTransactionCourante().getCout());
                }
                else{
                    champMessage.setText("Fonds Insuffisants");
                    return;
                }
            }
            zoneRecu.setText(b.afficherRecu());
            b.terminerTransaction();
            place = "";
            champMessage.setText("");
        }
    }

    private void boutonAnnuler_actionPerformed() {
        if (b.getTransactionCourante() != null){
            champMessage.setText("Transaction Annulée");
            b.terminerTransaction();
            place = "";
        }
    }

    private void boutonRapport_actionPerformed() {
        if (b.getTransactionCourante() == null){
            champMessage.setText(b.afficherRapport());
        }
    }

    public static void main(String[] args) {
        try {
            JFrame frame = new JFrame("GUITP2");
            frame.setContentPane(new GUITP2().panel1);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(930, 815);
            //frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            System.out.println ( new GregorianCalendar(2021, 11, 5).get(Calendar.DAY_OF_WEEK));
        }
        catch ( Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
