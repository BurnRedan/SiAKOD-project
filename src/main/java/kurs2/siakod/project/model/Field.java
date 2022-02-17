package kurs2.siakod.project.model;

public class Field {
    public Cell[][] field;

    public Field(int x, int y){
        field = new Cell[x][y];
    }
}
