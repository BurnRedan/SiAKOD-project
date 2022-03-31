package com.vsu.pathfinder;

import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.policy.PassagePolicyFactory;
import lombok.*;

import java.util.*;

public class DijkstraStrategy extends PathfindingStrategy {


    public DijkstraStrategy() {
        algorithm =  PathfindingAlgorithms.Dijkstra;
    }

    @RequiredArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    protected static class Node implements Comparable<Node> {
        Tile tile;
        @EqualsAndHashCode.Exclude Integer cost;

        @Override
        public int compareTo(Node o) {
            if (this.cost < o.cost) {
                return -1;
            } else if (this.cost > o.cost) {
                return 1;
            }
            return 0;
        }

    }

    @Override
    public List<Tile> findPath(Grid grid, Tile source, Tile destination) {
        Map<Tile, Integer> dist = new HashMap<>();
        Map<Tile, Node> prev = new HashMap<>();
        PriorityQueue<Node> pq = new PriorityQueue<>();
        Set<Tile> visited = new HashSet<>();

        for (Tile[] row : grid.getMatrix()) {
            for (Tile tile : row) {
                if (tile.equals(source)) {
                    dist.put(tile, 0);
                    pq.add(new Node(tile, 0));
                } else {
                    dist.put(tile, Integer.MAX_VALUE);
                    pq.add(new Node(tile, Integer.MAX_VALUE));
                }
                prev.put(tile, null);
            }
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.tile.equals(destination)) {
                List<Tile> path = new ArrayList<>();
                source.setRoot(true);
                destination.setDest(true);
                path.add(source);
                while (prev.get(node.tile) != null) {
                    path.add(node.tile);
                    node = prev.get(node.tile);
                }
                return path;
            }

            if (dist.get(node.tile) == Integer.MAX_VALUE) {
                break;
            }

            List<Node> neighbours = neighbours(grid, node.tile);
            for (Node neighbour : neighbours) {
                Integer alt = new PassagePolicyFactory().getDistance(node.tile, neighbour.tile);
                if (alt < dist.get(neighbour.tile)) {
                    dist.put(neighbour.tile, alt);
                    if (!visited.contains(neighbour.tile)) {
                        prev.put(neighbour.tile, node);
                    }
                    visited.add(neighbour.tile);

                    for (Node n : pq) {
                        if (n.tile.equals(neighbour.tile)) {
                            pq.remove(n);
                            n.cost = alt;
                            pq.add(n);
                            break;
                        }
                    }
                }
            }
        }
        return new ArrayList<>(dist.keySet());
    }

    private List<Node> neighbours(Grid grid, Tile tile) {
        List<Node> neighbours = new ArrayList<>();
        List<Tile> n = super.getNeighbours(grid, tile);
        for (Tile t : n) {
            neighbours.add(new Node(t, null));
        }
        return neighbours;

    }

}
