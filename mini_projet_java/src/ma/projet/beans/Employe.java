package ma.projet.beans;

public class Employe {
    private int id ;
    private String nom;
    private String prenom ;
    private String titre;
    private int affectation;
    private String niveau;
    private double salaire;

    public Employe() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getAffectation() {
        return affectation;
    }

    public void setAffectation(int affectation) {
        this.affectation = affectation;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public double getSalaire() {
        return salaire;
    }

    public void setSalaire(double salaire) {
        this.salaire = salaire;
    }



    public Employe(int id, String nom, String prenom, String titre, int affectation, String niveau, double salaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.titre = titre;
        this.affectation = affectation;
        this.niveau = niveau;
        this.salaire = salaire;
    }



    @Override
    public String toString() {
        return "Employe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", titre='" + titre + '\'' +
                ", affectation='" + affectation + '\'' +
                ", niveau='" + niveau + '\'' +
                ", salaire='" + salaire + '\'' +
                '}';
    }
}
