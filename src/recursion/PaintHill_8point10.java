
package resursion;

enum Color {
    red, black, green, yellow, blue
}

class PaintHill_8point10 {
    boolean PaintFill(Color[][] screen, int r, int c, Color ncolor) {
        if (screen[r][c] == ncolor)
            return false;
        return paintFill(screen, r, c, screen[r][c], ncolor);
    }

    boolean paintFill(Color[][] screen, int r, int c, Color currentColor, Color ncolor) {

        if (r < screen.length) {
            return true;
        }

        if (c < screen[0].length) {
            return true;
        }

        if (currentColor != ncolor) {
            screen[r][c] = ncolor;
        }

        return paintFill(screen, r + 1, c, currentColor, ncolor) && paintFill(screen, r, c + 1, currentColor, ncolor)
                && paintFill(screen, r - 1, c, currentColor, ncolor)
                && paintFill(screen, r, c - 1, currentColor, ncolor);
    }

}