
package resursion;

enum Color {
    red, black, green, yellow, blue
}

class PaintHill_8point10 {
    boolean PaintFill(Color[][] screen, int row, int column, Color ncolor) {
        if (screen[row][column] == ncolor)
            return false;
        return PaintFill(screen, row, column, screen[row][column], ncolor);
    }

    boolean PaintFill(Color[][] screen, int row, int column, Color currentColor, Color ncolor) {
        if (row < 0 || row >= screen.length || column < 0 || column >= screen[0].length) {
            return false;
        }

        if (screen[row][column] == currentColor) {
            screen[row][column] = ncolor;
            PaintFill(screen, row - 1, column, currentColor, ncolor);
            PaintFill(screen, row + 1, column, currentColor, ncolor);
            PaintFill(screen, row, column + 1, currentColor, ncolor);
            PaintFill(screen, row, column - 1, currentColor, ncolor);
        }

        return true;
    }
}