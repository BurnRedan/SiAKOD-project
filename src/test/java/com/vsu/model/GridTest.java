package com.vsu.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GridTest {

    @Test
    public void equalTest1(){
        Grid grid1 = new Grid();
        grid1.setMatrix(1,1);
        assertNotNull(grid1.matrix);
    }
}
