package ma.uit.emploisclub.Controllers.CardItem;

public class CardModel {
    private String nom ;
    private String prenom ;
    private String text1 ;
    private String text2 ;

    public CardModel(String nom, String prenom, String text1) {
        this.nom = nom;
        this.prenom = prenom;
        this.text1 = text1;
    }

    public CardModel(String nom, String prenom, String text1, String text2) {
        this.nom = nom;
        this.prenom = prenom;
        this.text1 = text1;
        this.text2 = text2;
    }

    public CardModel() {
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }
}
