package kurs2.siakod.project.model;

public class Cell {
    Cell south, west, east, north;
    int x, y;
    Unit stayingUnit;

    public Cell(Cell south, Cell west, Cell east, Cell north, int x, int y){
        this.south = south;
        this.west = west;
        this.east = east;
        this.north = north;
        this.x = x;
        this.y =y;
    }

    public Cell(Cell south, Cell west, Cell east, Cell north, int x, int y, Unit unit){
        this.south = south;
        this.west = west;
        this.east = east;
        this.north = north;
        this.x = x;
        this.y =y;
        this.stayingUnit = unit;
    }
}
