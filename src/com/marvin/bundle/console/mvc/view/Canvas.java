package com.marvin.bundle.console.mvc.view;

import org.fusesource.jansi.Ansi;

public class Canvas extends ConsoleComponent {
    
    public Canvas(int x, int y, int h, int w) {
        super(x, y, h, w);
    }
    
    public void rect(int x, int y, int h, int w) {
        rect(x, y, h, w, Ansi.Color.BLUE);
    }
    
    public void write(int x, int y, String value, Ansi.Color color) {
        getAnsi().cursor(x, y).bg(color).a(value).bgDefault();
    }
    
    public void rect(int x, int y, int h, int w, Ansi.Color color) {
        x = Math.max(1, x);
        y = Math.max(1, y);
        line(x, y, x, w, color);
        line(x, w, h, w, color);
        line(x, y, h, y, color);
        line(h, y, h, w, color);
    }
    
    public void line(int fromX, int fromY, int toX, int toY, Ansi.Color color) {
//        double angle = Math.atan(toY - fromY / toX - fromX);

//        double limitV = Math.sqrt(2) / 2;
//        double limitX = Math.sqrt(2) / 2;
        
        int d = Math.max(toX - fromX, toY - fromY);
        int x = fromX;
        int y = fromY;
        
        for (int i = 0; i <= d; i++) {
//            double angle = Math.atan(toY - y / toX - x);
//            double angleX = Math.floor(Math.sin(angle));
//            double angleY = Math.floor(Math.cos(angle));
            
            String line = " ";

//            if ((angleY == 1 || angleY == -1) && angleX == 0) {
//                line = "|";
//            }
            
//            if ((angleX == 1 || angleX == -1) && angleY == 0) {
//                line = "|";
//            }
//            
//            if (angleX == 0 && angleY == 0) {
//                line = "-";
//            }
//            
//            if (d == i || i == 0) {
//                line = "o";
//            }
            
//            System.out.format("\nline([%s, %s], [%s, %s]) = %s", x, y, toX, toY, line);
//            System.out.format("\nf(%s, %s) = %s\n", angleX, angleY, angle);
            getAnsi().cursor(x, y).bg(color).a(line).bgDefault();
            
            if (x - toX > 0) {
                x--;
            } else if (x - toX < 0) {
                x++;
            }
            
             if (y - toY > 0) {
                y--;
            } else if (y - toY < 0) {
                y++;
            }
            
//            if (angle < 0) {
//                x--;
//            } else if(angle != 0) {
//                x++;
//            }
            
//            if (angle < 0.5 && angle > -0.5) {
//                y--;
//            } else {
//                if(angle > 1.5 && angle < -1.5) {
//                    y++;
//                }
//            }
                
                
//                if(angle != 1 && angle != -1) {
//                y++;
//            }

        }
        
//        getAnsi().cursor(fromX, fromY).a(line);
//        
//        getAnsi().newline().format("line([%s, %s], [%s, %s], %s, %s)", 
//                fromX, fromY, toX, toY, line, angle)
//                .newline();
//        getAnsi().a("-", x, w);
//        
//        for (int i = 0; i < w; i++) {
//            getAnsi().cursorDownLine();
//            getAnsi().a("|", x, 1);
//            getAnsi().a("|", x + h, 1);
//        }
//        
//        getAnsi().a("-", x + h, w);
    }
    
}
