package stationnement;

public class Piece {
    private int valeur; // en cents

    public Piece(int valeur) {
        setValeur(valeur);
    }

    public int getValeur() {
        return valeur;
    }

    private void setValeur(int valeur) {
        if (valeur == 25 || valeur == 100 || valeur == 200) {
            this.valeur = valeur;
        }
        else {
            this.valeur = 0; // valeur invalide
        }
    }
}
