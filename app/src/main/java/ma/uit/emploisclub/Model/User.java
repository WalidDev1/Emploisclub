package ma.uit.emploisclub.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.joda.time.DateTime;

import java.util.Date;

public class User {

    @SerializedName("id")
    @Expose
    String id ;

    @SerializedName("role")
    @Expose
    String role ; // 1 = Admin , 2 = Client , 3 Coach , 4 other

    @SerializedName("nom")
    @Expose
    String nom ;

    @SerializedName("prenom")
    @Expose
    String prenom ;
    String adresse ;

    @SerializedName("userPhone")
    @Expose
    String tel ;

    String mail ;

    @SerializedName("photo")
    @Expose
    String imageProfile ;

    @SerializedName("contractDate")
    @Expose
    String dateContrat ;
    Date lastCnx ;

    public User() {
    }

    public User(String id, String nom, String prenom, String adresse, String tel, String mail ,String  role) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tel = tel;
        this.mail = mail;
        this.role = role ;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
