package com.vsu.AI;

import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import com.vsu.pathfinder.PathfindingAlgorithms;
import com.vsu.pathfinder.PathfindingFactory;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class EntityMoveLogic {

    public static void randomMove(Grid grid, Entity entity) {
        List<Tile> neighbours = getNeighbours(grid, entity.getTile());
        Random random = new Random();
        Tile dest = neighbours.get(random.nextInt(0, neighbours.size()));
        moveTo(entity, dest);

    }

    protected static void moveTo(Entity entity, Tile dest) {
        entity.getTile().setEntity(null);
        entity.setTile(dest);
        dest.setEntity(new Entity(entity.getType(), entity.getTile()));

    }

    public static void seek(Grid grid, Entity seeker) {
       List<Tile> path = seeker.getPath();
       if (path.size() > 1) {
           moveTo(seeker, path.remove(path.size() - 1));
       }
    }

    public static void findPath(Grid grid, Entity seeker, Entity runner, PathfindingAlgorithms algorithm){
        List<Tile> path = seeker.getPath().size() == 0 ? new PathfindingFactory().getStrategy(algorithm)
                .findPath(grid, seeker.getTile(), runner.getTile()) : seeker.getPath();
        seeker.setPath(path);

    }

    protected static List<Tile> getNeighbours(Grid grid, Tile tile) {
        Tile tmp;
        List<Tile> neighbours = new ArrayList<>();

        tmp = tile.north(grid);
        if (tmp != null) {
            if (tmp.getType() != TileType.Wall) {
                neighbours.add(tmp);
            }
        }
        tmp = tile.south(grid);
        if (tmp != null) {
            if (tmp.getType() != TileType.Wall) {
                neighbours.add(tmp);
            }
        }
        tmp = tile.west(grid);
        if (tmp != null) {
            if (tmp.getType() != TileType.Wall) {
                neighbours.add(tmp);
            }
        }
        tmp = tile.east(grid);
        if (tmp != null) {
            if (tmp.getType() != TileType.Wall) {
                neighbours.add(tmp);
            }
        }

        return neighbours;
    }

}
