package com.vsu.pathfinder;

import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.*;

public class AStarStrategy extends PathfindingStrategy {
    @RequiredArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    protected  class Node implements Comparable<AStarStrategy.Node> {
        Tile tile;
        @EqualsAndHashCode.Exclude Integer cost;
        int dist = 0;
        Node prev = null;
        public Node(Tile tile, Node prev) {// Node состоит из текущей позиции и её предка
            this.tile = tile;
            if(prev != null && tile != null) {
                dist = tile.getWeight() + prev.dist; //подсчёт пройденного пути
                cost = HeuristicToPositional(tile, dist);// стоимость прохода через клетку
            }
            this.prev = prev;
        }

        @Override
        public int compareTo(AStarStrategy.Node o) { //инструкция сравнения Node
            if (this.cost < o.cost) {
                return -1;
            } else if (this.cost > o.cost) {
                return 1;
            }
            return 0;
        }

    }

    public AStarStrategy() {
        algorithm =  PathfindingAlgorithms.AStar;
    }
    private Grid grid;
    private PriorityQueue<Node> Open = new PriorityQueue<>();
    private ArrayList<Tile> Close = new ArrayList<>();
    private Tile finish;
    private Tile start;
    private Node pos;
    @Override

    public List<Tile> findPath(Grid grid, Tile start, Tile finish) {//наш основной алгоритм поиска пути

        this.grid = grid;
        this.start = start;
        this.finish = finish;

        Node first = new Node(start, null);
        Open.add(first);//добавляем в очередь клетку старта
        while (!Open.isEmpty()){//проходим по очереди с приоритетом, пока она не закончится
            pos = Open.poll();
            Close.add(pos.tile);//добавляем только саму клетку
            if (pos.tile.equals(finish)){ //проверка на то что мы достигли цели, если достигли, то останавливаемся
                return dist(pos);//звращаем пройденый нами путь
            }
            Neighbours();//вызов метода который проверяет соседей и нужных добавляет в очередь
        }
        return new ArrayList();//если выхода нет, то возвращаем пустой список
    }


    private int HeuristicToPositional(Tile tile, int dist){ //Эвристическая оценка
        return  dist + Heuristic(tile) + tile.getWeight();
    }

    private List<Tile> dist(Node node){//нахождение пройденного пути(добавление его в список)
        List<Tile> list = new LinkedList<>();
        while(node.prev != null){
            list.add(node.tile);
            node = node.prev;
        }
        return list;
    }

    private int Heuristic(Tile tile){ //Эвристика
        return (Math.abs(tile.row - finish.row)
                + Math.abs(tile.col - finish.col));
    }

    private void Neighbours(){ //проверка и добавление соседей в очередь с приоритетом
        LinkedList<Node> ListOfNeighbours = new LinkedList<>();
        ListOfNeighbours.add(new Node(pos.tile.north(grid), pos));
        ListOfNeighbours.add(new Node(pos.tile.south(grid), pos));
        ListOfNeighbours.add(new Node(pos.tile.west(grid), pos));
        ListOfNeighbours.add(new Node(pos.tile.east(grid), pos));
        for(int i = 0; i < ListOfNeighbours.size(); i++){

            if (!Close.contains(ListOfNeighbours.get(i).tile)//проверка на то, проходили ли мы эту клетку
                    && ListOfNeighbours.get(i).tile != null//проверка на то, существует ли эта клетка и мы не выходим за пределы
                    && ListOfNeighbours.get(i).tile.getType() != TileType.Wall){//проверка на то, не является ли клетка стеной
                Open.add(ListOfNeighbours.get(i));
            }

        }
    }

}



