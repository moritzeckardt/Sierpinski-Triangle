package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class SierpinskiTriangle extends SierpinskiTriangleAbstract {
    // Constants
    private final int MIN_DEPTH = 0;
    private final int MAX_DEPTH = 7;

    // Methods
    @Override
    protected void drawTriangleRec(int ax, int ay, int bx, int by, int cx, int cy, int depth, Color color) {
        // Draw outer triangle
        g.setColor(color);
        g.fillPolygon(new int[] { ax, bx, cx }, new int[] { ay, by, cy }, 3);

        // Calculate dimensions
        int width = bx - ax;
        int height  = ay - cy;

        if (depth > 0 && width >= 2) {
            // Calculate missing points D, E, F
            int dx = ax + width / 4;
            int dy = cy + height / 2;

            int ex = bx - width / 4;
            int ey = dy;

            int fx = ax + width / 2;
            int fy = ay;

            // Draw inner triangle
            g.setColor(Color.WHITE);
            g.fillPolygon(new int[] { dx, ex, fx }, new int[] { dy, ey, fy }, 3);

            // Call function recursively for each of the resulting dark triangles
            drawTriangleRec(ax, ay, fx, fy, dx, dy, depth - 1, color);
            drawTriangleRec(dx, dy, ex, ey, cx, cy, depth - 1, color);
            drawTriangleRec(fx, fy, bx, by, ex, ey, depth - 1, color);
        }
    }

    @Override
    protected void handleInput(int keyCode) {
        // React to key code, abort if invalid
        switch (keyCode) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_KP_UP:
                depth++;
                break;

            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_KP_DOWN:
                depth--;
                break;

            case KeyEvent.VK_SPACE:
                toggleRandomColor();
                break;

            default:
                return;
        }

        // Keep depth inside range
        depth = Math.min(MAX_DEPTH, Math.max(MIN_DEPTH, depth));

        // Repaint triangle
        paint(getGraphics());
    }

    @Override
    protected void toggleRandomColor() {
        useRandomColor = !useRandomColor;
    }

    @Override
    protected void drawTriangle() {
        color = useRandomColor ? new Color((int)(Math.random() * 0xFFFFFF)) : Color.BLACK;
        super.drawTriangle();
    }

    // Main
    public static void main(String[] args) {
        // Create triangle
        new SierpinskiTriangle();
    }
}