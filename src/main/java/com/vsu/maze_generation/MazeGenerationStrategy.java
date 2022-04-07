package com.vsu.maze_generation;

import com.vsu.model.Grid;
import com.vsu.model.Tile;
import com.vsu.model.TileType;
import com.vsu.service.GenerationTileType;
import lombok.Getter;

@Getter
public abstract class MazeGenerationStrategy {

    MazeGenAlgorithms type;
    boolean isSimpleMaze;
    GenerationTileType generationTileType;

    public MazeGenerationStrategy(boolean isSimpleMaze) {
        this.isSimpleMaze = isSimpleMaze;
        if (!isSimpleMaze) {
            generationTileType = new GenerationTileType();
        }
    }

    public abstract void generate(Grid grid);

    public void setPathTileType(Tile tile) {
        if (isSimpleMaze) {
            tile.setType(TileType.Pavement);
        } else {
            tile.setType(generationTileType.generate(tile.row, tile.col));
        }
    }
}
