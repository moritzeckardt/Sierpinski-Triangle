package com.company;

import java.awt.*;

public class SierpinskiTriangle extends SierpinskiTriangleAbstract {
    // Constructors
    public SierpinskiTriangle() {
        // Set color
        color = Color.BLACK;
    }

    // Methods
    @Override
    protected void drawTriangleRec(int ax, int ay, int bx, int by, int cx, int cy, int depth, Color color) {
        // Calculate width and height
        int width = bx - ax;
        int height  = ay - cy;

        // Check if abort conditions are reached (base case)
        if (depth < 0 || width < 2) {

        }
        else if (depth == 0) {
            g.setColor(color);
            g.fillPolygon(new int[] { ax, bx, cx }, new int[] { ay, by, cy }, 3);
        }
        else {
            // Calculate D, E, F
            int dx = ax + width / 4;
            int dy = cy + height / 2;

            int ex = bx - width / 4;
            int ey = dy;

            int fx = ax + width / 2;
            int fy = ay;

            // Fill inner triangle
            g.setColor(Color.WHITE);
            //g.fillPolygon(new int[] { dx, ex, fx }, new int[] { dy, ey, fy }, 3);

            // Handle recursive
            drawTriangleRec(ax, ay, fx, fy, dx, dy, depth - 1, color);
            drawTriangleRec(dx, dy, ex, ey, cx, cy, depth - 1, color);
            drawTriangleRec(fx, fy, bx, by, ex, ey, depth - 1, color);
        }
    }

    @Override
    protected void handleInput(int keyCode) {

    }

    @Override
    protected void toggleRandomColor() {

    }

    // Main
    public static void main(String[] args) {
        // Create triangle
        SierpinskiTriangle st = new SierpinskiTriangle();
    }
}
