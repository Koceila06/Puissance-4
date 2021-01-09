import java.util.ArrayList;
class Noeud {
private Grille grille;
private int score;
private ArrayList<Noeud> fils;
Noeud(Grille g,int scr){
this.grille=g;
this.fils=new ArrayList();
this.score=scr;
}
public boolean getFeuille(){return this.fils==null;}
Noeud(Grille g){
this.grille=g;
this.fils=new ArrayList();
this.score=-999999990;
}
public Grille getGrille_Noeud(){
    return this.grille;
}
public void Add_Fils(Noeud node){
this.fils.add(node);
}
public ArrayList<Noeud> getFils(){
return this.fils;
}
public void setScore(int a){
    this.score=a;
}
public int getScore(){return this.score;}
}