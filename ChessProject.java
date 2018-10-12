import java.awt.*;
import java.awt.event.*;
import java.net.CookieHandler;
import java.util.*;
import javax.swing.*;

/*
    This class can be used as a starting point for creating your Chess game project. The only piece that
    has been coded is a white pawn...a lot done, more to do!
 */

/*
   Author: Kevin Carmody
   Project: Chess
   Year:2018( Final Year )
 */

public class ChessProject extends JFrame implements MouseListener, MouseMotionListener {
JLayeredPane layeredPane;
JPanel chessBoard;
JLabel chessPiece;
String atackedPiece;
int xAdjustment;
int yAdjustment;
int startX;
int startY;
int initialX;
int initialY;
JPanel panels;
JLabel pieces;

public ChessProject() {
        Dimension boardSize = new Dimension(600, 600);

        // Use a Layered Pane for this application
        layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);
        layeredPane.setPreferredSize(boardSize);
        layeredPane.addMouseListener(this);
        layeredPane.addMouseMotionListener(this);

        // Add a chess board to the Layered Pane
        chessBoard = new JPanel();
        layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
        chessBoard.setLayout(new GridLayout(8, 8));
        chessBoard.setPreferredSize(boardSize);
        chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

        for (int i = 0; i < 64; i++) {
                JPanel square = new JPanel(new BorderLayout());
                chessBoard.add(square);

                int row = (i / 8) % 2;
                if (row == 0)
                        square.setBackground(i % 2 == 0 ? Color.white : Color.gray);
                else
                        square.setBackground(i % 2 == 0 ? Color.gray : Color.white);
        }

        // Setting up the Initial Chess board.
        for (int i = 8; i < 16; i++) {
                pieces = new JLabel(new ImageIcon("WhitePawn.png"));
                panels = (JPanel) chessBoard.getComponent(i);
                panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(0);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(1);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKnight.png"));
        panels = (JPanel) chessBoard.getComponent(6);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishup.png"));
        panels = (JPanel) chessBoard.getComponent(2);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteBishup.png"));
        panels = (JPanel) chessBoard.getComponent(5);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteKing.png"));
        panels = (JPanel) chessBoard.getComponent(3);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
        panels = (JPanel) chessBoard.getComponent(4);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("WhiteRook.png"));
        panels = (JPanel) chessBoard.getComponent(7);
        panels.add(pieces);
        for (int i = 48; i < 56; i++) {
                pieces = new JLabel(new ImageIcon("BlackPawn.png"));
                panels = (JPanel) chessBoard.getComponent(i);
                panels.add(pieces);
        }
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(56);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(57);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKnight.png"));
        panels = (JPanel) chessBoard.getComponent(62);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishup.png"));
        panels = (JPanel) chessBoard.getComponent(58);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackBishup.png"));
        panels = (JPanel) chessBoard.getComponent(61);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackKing.png"));
        panels = (JPanel) chessBoard.getComponent(59);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
        panels = (JPanel) chessBoard.getComponent(60);
        panels.add(pieces);
        pieces = new JLabel(new ImageIcon("BlackRook.png"));
        panels = (JPanel) chessBoard.getComponent(63);
        panels.add(pieces);
}

/*
 * This method checks if there is a piece present on a particular square.
 */
private Boolean piecePresent(int x, int y) {
        Component c = chessBoard.findComponentAt(x, y);
        if (c instanceof JPanel) {
                return false;
        } else {
                return true;
        }
}

/*
 * This is a method to check if a piece is a Black piece.
 */
private Boolean checkWhiteOponent(int newX, int newY) {

        Boolean oponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        if (((tmp1.contains("Black")))) {
                oponent = true;
                /*
                   This method is used to dispaly the winner after the King is taken
                 */
                if (((tmp1.contains("Black")))) {
                        if (tmp1.contains("King")) {
                                JOptionPane.showMessageDialog(null, "White Wins");
                                System.exit(0);
                        }
                }
        }
        else
        {
                oponent = false;
        }
        return oponent;
}
/*
 * This is a method to check if a piece is a White piece.
 */
private Boolean checkBlackOponent(int newX, int newY) {
        Boolean oponent;
        Component c1 = chessBoard.findComponentAt(newX, newY);
        JLabel awaitingPiece = (JLabel) c1;
        String tmp1 = awaitingPiece.getIcon().toString();
        if (((tmp1.contains("White")))) {
                oponent = true;
                /*
                   This method is used to dispaly the winner after the King is taken
                 */
                if (((tmp1.contains("White")))) {
                        if (tmp1.contains("King")) {
                                JOptionPane.showMessageDialog(null, "Black Wins");
                                System.exit(0);
                        }
                }
        }
        else
        {
                oponent = false;
        }
        return oponent;
}

private String getPieceName(int pieceAtX, int pieceAtY){
        Component c1 = chessBoard.findComponentAt(pieceAtX, pieceAtY);
        if(c1 instanceof JPanel) {
                return "nothing";
        }
        else if (c1 instanceof JLabel) {
                JLabel awaitingPiece = (JLabel) c1;
                String tmp1 = awaitingPiece.getIcon().toString();
                return tmp1;
        }
        else{
                return "nothing";
        }
}

/*
 * This method is called when we press the Mouse. So we need to find out what
 * piece we have selected. We may also not have selected a piece!
 */
public void mousePressed(MouseEvent e) {
        chessPiece = null;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        if (c instanceof JPanel)
                return;

        Point parentLocation = c.getParent().getLocation();
        xAdjustment = parentLocation.x - e.getX();
        yAdjustment = parentLocation.y - e.getY();
        chessPiece = (JLabel) c;
        initialX = e.getX();
        initialY = e.getY();
        startX = (e.getX() / 75);
        startY = (e.getY() / 75);
        chessPiece.setLocation(e.getX() + xAdjustment, e.getY() + yAdjustment);
        chessPiece.setSize(chessPiece.getWidth(), chessPiece.getHeight());
        layeredPane.add(chessPiece, JLayeredPane.DRAG_LAYER);
}

public void mouseDragged(MouseEvent me) {
        if (chessPiece == null)
                return;
        chessPiece.setLocation(me.getX() + xAdjustment, me.getY() + yAdjustment);
}

/*
 * This method is used when the Mouse is released...we need to make sure the
 * move was valid before putting the piece back on the board.
 */
public void mouseReleased(MouseEvent e) {
        if (chessPiece == null)
                return;

        chessPiece.setVisible(false);
        Boolean success = false;
        Component c = chessBoard.findComponentAt(e.getX(), e.getY());
        String tmp = chessPiece.getIcon().toString();
        String pieceName = tmp.substring(0, (tmp.length() - 4));
        Boolean validMove = false;

        int landingX = (e.getX() / 75);
        int landingY = (e.getY() / 75);
        int xMovement = Math.abs((e.getX() / 75) - startX);
        int yMovement = Math.abs((e.getY() / 75) - startY);

        System.out.println("---------------------------------------------------");
        System.out.println("The piece being moved is : " + pieceName);
        System.out.println("The starting coordinates are: (" + startX + " ; " + startY + ")");
        System.out.println("The xMovement is: " + xMovement);
        System.out.println("The yMovement is: " + yMovement);
        System.out.println("The landing coordinates are: (" + landingX + " ; " + landingY + ")");
        System.out.println("---------------------------------------------------");

        // 75 is each Dimension of the square

        // Black Pawn and WhitePawn are similar when it comes to moving, +75(black) for moving up and -75 for moving down(white)

        if (landingX <= 7 && landingY <= 7) {

                // BlackPawn Movement
                if (pieceName.equals("BlackPawn")) {

                        if (startY == 6 && yMovement < 3 || landingY == startY - 1) {
                                if (xMovement == 0) {
                                        if ((!piecePresent(e.getX(), e.getY()))&&(!piecePresent(e.getX(), (e.getY()+75)))) {
                                                validMove = true;
                                        }
                                }
                                if (xMovement == 1) {
                                        if (piecePresent(e.getX(), e.getY())) {
                                                if (checkBlackOponent(e.getX(), e.getY())) {
                                                        validMove = true;
                                                }

                                        }
                                }
                                if (landingY == 0) {
                                        success = true;
                                }
                        }
                }
                // WhitePawn Movement
                if (pieceName.equals("WhitePawn")) {
                        if (startY == 1 && yMovement < 3 || landingY == startY + 1) {
                                if (xMovement == 0) {
                                        if ((!piecePresent(e.getX(), e.getY()))&&(!piecePresent(e.getX(), (e.getY()-75)))) {
                                                validMove = true;
                                        }
                                }
                                if (xMovement == 1) {
                                        if (piecePresent(e.getX(), e.getY())) {
                                                if (checkWhiteOponent(e.getX(), e.getY())) {
                                                        validMove = true;
                                                }

                                        }
                                }
                                if (landingY == 7) {
                                        success = true;
                                }
                        }
                }
                // Knight Movement
                if (pieceName.contains("Knight")) {
                        if ((xMovement == 1 && yMovement == 2) || (xMovement == 2 && yMovement == 1)) {
                                if (piecePresent(e.getX(), e.getY())) {
                                        if (pieceName.contains("White")) {
                                                if (checkWhiteOponent(e.getX(), e.getY())) {
                                                        validMove = true;
                                                }
                                        }
                                        if (pieceName.contains("Black")) {
                                                if (checkBlackOponent(e.getX(), e.getY())) {
                                                        validMove = true;
                                                }
                                        }
                                } else {
                                        if (!piecePresent(e.getX(), e.getY())) {
                                                validMove = true;
                                        }
                                }
                        }

                }
                // Kween Movement
                if (pieceName.contains("Queen")) {
                        int countPieces = 0;
                        int coordinateX = 0;
                        int coordinateY = 0;
                        if ((xMovement == yMovement) || (xMovement == 0 & yMovement > 0) || (xMovement > 0 && yMovement == 0)) {


                                // Checking Bishop Movement Type to move the queen
                                if (xMovement == yMovement) {
                                        for (int x = 1; x < xMovement + 1; x++) {
                                                if (startX < landingX && startY < landingY) {
                                                        if (piecePresent((startX + x) * 75, (startY + x) * 75)) {
                                                                countPieces += 1;
                                                                coordinateX = startX + x;
                                                                coordinateY = startY + x;
                                                        }
                                                }

                                                else if (startX > landingX && startY < landingY) {
                                                        if (piecePresent((startX - x) * 75, (startY + x) * 75)) {
                                                                countPieces += 1;
                                                                coordinateX = startX - x;
                                                                coordinateY = startY + x;
                                                        }
                                                } else if (startX < landingX && startY > landingY) {
                                                        if (piecePresent((startX + x) * 75, (startY - x) * 75)) {
                                                                countPieces += 1;
                                                                coordinateX = startX + x;
                                                                coordinateY = startY - x;
                                                        }
                                                }

                                                else {
                                                        if (piecePresent((startX - x) * 75, (startY - x) * 75)) {
                                                                countPieces += 1;
                                                                coordinateX = startX - x;
                                                                coordinateY = startY - x;
                                                        }
                                                }
                                        }
                                }

                                // RooksMovement
                                if (xMovement > 0 && yMovement == 0) {
                                        for (int i = 0; i < xMovement + 1; i++) {
                                                if (startX < landingX) {
                                                        if (piecePresent((startX + i) * 75, startY * 75)) {
                                                                countPieces += 1;
                                                                coordinateX = startX + i;
                                                                coordinateY = startY;
                                                        }
                                                        System.out.println("Checking xCoordinates: " + startX + i);
                                                } else {
                                                        if (piecePresent((startX - i) * 75, startY * 75)) {
                                                                countPieces += 1;
                                                                coordinateX = startX - i;
                                                                coordinateY = startY;
                                                        }
                                                        System.out.println("Checking xCoordinates: " + (startX - i));
                                                }

                                        }
                                }
                                //checking obastacles
                                if (xMovement == 0 && yMovement > 0) {
                                        for (int i = 0; i < yMovement + 1; i++) {
                                                if (startY < landingY) {
                                                        if (piecePresent(startX * 75, (startY + i) * 75)) {
                                                                countPieces += 1;
                                                                coordinateX = startX;
                                                                coordinateY = startY + i;
                                                        }
                                                }
                                                else {
                                                        if (piecePresent(startX * 75, (startY - i) * 75)) {
                                                                countPieces += 1;
                                                                coordinateX = startX;
                                                                coordinateY = startY - i;
                                                        }
                                                }
                                        }
                                }
                                // Checking for more obastacles(taking pieces)
                                if (countPieces > 0) {
                                        if (countPieces == 1) {
                                                if (coordinateX == landingX && coordinateY == landingY) {
                                                        if (pieceName.contains("Black")) {
                                                                if (checkBlackOponent(landingX * 75, landingY * 75)) {
                                                                        validMove = true;
                                                                }
                                                        } else {
                                                                if (checkWhiteOponent(landingX * 75, landingY * 75)) {
                                                                        validMove = true;
                                                                }
                                                        }
                                                }
                                                else {
                                                        validMove = false;
                                                }
                                        }
                                        else {
                                                validMove = false;
                                        }
                                }
                                else {
                                        validMove = true;
                                }
                        }
                        else {
                                System.out.println("Not a valid Move!");
                                validMove = false;
                        }
                }

                /*
                   The King is the most important piece in chess.
                   If the king is trapped so that its capture is unavoidable, the game is over and that player loses.
                   The king has little mobility, so it is also considered one of the weakest pieces in the game.
                   The king can move to any adjacent square. That is, it can move one square in any direction:
                   horizontally, vertically, or diagonally.
                   It cannot move onto a square occupied by a piece of the same color.

                   if(king is being placed){
                    if((xMovement ==1)||(yMovement ==1)&&(e.getX(), e.getY()*75)){

                   }
                   }

                 */

                //King Code Movement with Restrictions
                if(pieceName.contains("King")) {
                        if(((landingX < 0)|| (landingX > 7)) ||(landingY < 0) || (landingY > 7)) {
                                validMove = false;
                        }
                        else if (xMovement == 0 && yMovement == 0) {
                                validMove = false;
                        }

                        else if ((getPieceName((e.getX() + 75), e.getY()).contains("King")) ||
                                 (getPieceName((e.getX() - 75), e.getY()).contains("King")) ||
                                 (getPieceName((e.getX() ), (e.getY() + 75)).contains("King")) ||
                                 (getPieceName((e.getX() ), (e.getY() - 75)).contains("King")) ||
                                 (getPieceName((e.getX() + 75), (e.getY() + 75)).contains("King")) ||
                                 (getPieceName((e.getX() + 75), (e.getY() - 75)).contains("King")) ||
                                 (getPieceName((e.getX() - 75), (e.getY() + 75)).contains("King")) ||
                                 (getPieceName((e.getX() - 75), (e.getY() - 75)).contains("King"))) {
                                validMove = false;
                        }

                        else if((xMovement == 1) || (yMovement == 1) || ((xMovement == 1) && (yMovement == 1))) {
                                if (piecePresent(e.getX(), e.getY() )) {
                                        if (pieceName.contains("White")) {
                                                if(checkWhiteOponent(e.getX(), e.getY())) {
                                                        validMove = true;
                                                }
                                                else{
                                                        validMove = false;
                                                }
                                        }
                                        else{
                                                if (checkBlackOponent(e.getX(), e.getY())) {
                                                        validMove = true;
                                                }
                                                else{
                                                        validMove = false;
                                                }
                                        }
                                }
                                else{
                                        validMove = true;
                                }
                        }

                }
                //Old King Code
                // if(pieceName.contains("King")) {
                //         int countPieces = 0;
                //         int coordinateX = 0;
                //         int coordinateY = 0;
                //
                //         if(((xMovement == 1 || yMovement == 1) || (yMovement == 1 || xMovement == 1))) {
                //           if(((landingX < 0)|| (landingX > 7)) ||(landingY < 0) || (landingY > 7)){
                //                 // Checking Bishop Movement Type to move the queen
                //                 if (xMovement == yMovement) {
                //                   if((getPieceName((e.getX() + 75) , e.getY()).contains("King")) ||
                //                   (getPieceName((e.getX() + 75) , e.getY()).contains("King")) ||
                //                   (getPieceName((e.getX() + 75) , e.getY()).contains("King")) ||
                //                   (getPieceName((e.getX() + 75) , e.getY()).contains("King")) ||
                //                   (getPieceName((e.getX() + 75) , e.getY()).contains("King")) ||
                //                   (getPieceName((e.getX() + 75) , e.getY()).contains("King")) ||
                //                   (getPieceName((e.getX() + 75) , e.getY()).contains("King")) ||
                //                   (getPieceName((e.getX() + 75) , e.getY()).contains("King"))) {
                //                     validMove = false;
                //                   }
                //                         for (int x = 1; x < xMovement + 1; x++) {
                //                                 if (startX < landingX && startY < landingY) {
                //                                         if (piecePresent((startX + x) * 75, (startY + x) * 75)) {
                //                                                 countPieces += 1;
                //                                                 coordinateX = startX + x;
                //                                                 coordinateY = startY + x;
                //                                         }
                //                                 }
                //
                //                                 else if (startX > landingX && startY < landingY) {
                //                                         if (piecePresent((startX - x) * 75, (startY + x) * 75)) {
                //                                                 countPieces += 1;
                //                                                 coordinateX = startX - x;
                //                                                 coordinateY = startY + x;
                //                                         }
                //                                 } else if (startX < landingX && startY > landingY) {
                //                                         if (piecePresent((startX + x) * 75, (startY - x) * 75)) {
                //                                                 countPieces += 1;
                //                                                 coordinateX = startX + x;
                //                                                 coordinateY = startY - x;
                //                                         }
                //                                 }
                //
                //                                 else {
                //                                         if (piecePresent((startX - x) * 75, (startY - x) * 75)) {
                //                                                 countPieces += 1;
                //                                                 coordinateX = startX - x;
                //                                                 coordinateY = startY - x;
                //                                         }
                //                                 }
                //                         }
                //                 }
                //
                //                 // RooksMovement
                //                 if (xMovement > 0 && yMovement == 0) {
                //                         for (int i = 0; i < xMovement + 1; i++) {
                //                                 if (startX < landingX) {
                //                                         if (piecePresent((startX + i) * 75, startY * 75)) {
                //                                                 countPieces += 1;
                //                                                 coordinateX = startX + i;
                //                                                 coordinateY = startY;
                //                                         }
                //                                         System.out.println("Checking xCoordinates: " + startX + i);
                //                                 } else {
                //                                         if (piecePresent((startX - i) * 75, startY * 75)) {
                //                                                 countPieces += 1;
                //                                                 coordinateX = startX - i;
                //                                                 coordinateY = startY;
                //                                         }
                //                                         System.out.println("Checking xCoordinates: " + (startX - i));
                //                                 }
                //
                //                         }
                //                 }
                //
                //                 if (xMovement == 0 && yMovement > 0) {
                //                         for (int i = 0; i < yMovement + 1; i++) {
                //                                 if (startY < landingY) {
                //                                         if (piecePresent(startX * 75, (startY + i) * 75)) {
                //                                                 countPieces += 1;
                //                                                 coordinateX = startX;
                //                                                 coordinateY = startY + i;
                //                                         }
                //                                 }
                //                                 else {
                //                                         if (piecePresent(startX * 75, (startY - i) * 75)) {
                //                                                 countPieces += 1;
                //                                                 coordinateX = startX;
                //                                                 coordinateY = startY - i;
                //                                         }
                //                                 }
                //                         }
                //                 }
                //                 // Check if there is any obstacles
                //                 if (countPieces > 0) {
                //                         if (countPieces == 1) {
                //                                 if (coordinateX == landingX && coordinateY == landingY) {
                //                                         if (pieceName.contains("Black")) {
                //                                                 if (checkBlackOponent(landingX * 75, landingY * 75)) {
                //                                                         validMove = true;
                //                                                 }
                //                                         } else {
                //                                                 if (checkWhiteOponent(landingX * 75, landingY * 75)) {
                //                                                         validMove = true;
                //                                                 }
                //                                         }
                //                                 }
                //                                 else {
                //                                         validMove = false;
                //                                 }
                //                         }
                //                         else {
                //                                 validMove = false;
                //                         }
                //                 }
                //                 else {
                //                         validMove = true;
                //                 }
                //         }
                //         else {
                //                 System.out.println("Not a valid Move!");
                //                 validMove = false;
                //         }
                //       }
                // }
                // Moving Bishop
                if (pieceName.contains("Bishup")) {
                        int countPieces = 0;
                        int cordinateX = 0;
                        int cordinateY = 0;

                        // Checking Bishop Movement Type
                        if (xMovement == yMovement) {
                                for (int x = 1; x < xMovement + 1; x++) {
                                        if (startX < landingX && startY < landingY) {
                                                if (piecePresent((startX + x) * 75, (startY + x) * 75)) {
                                                        countPieces += 1;
                                                        cordinateX = startX + x;
                                                        cordinateY = startY + x;
                                                }
                                        } else if (startX > landingX && startY < landingY) {
                                                if (piecePresent((startX - x) * 75, (startY + x) * 75)) {
                                                        countPieces += 1;
                                                        cordinateX = startX - x;
                                                        cordinateY = startY + x;
                                                }
                                        } else if (startX < landingX && startY > landingY) {
                                                if (piecePresent((startX + x) * 75, (startY - x) * 75)) {
                                                        countPieces += 1;
                                                        cordinateX = startX + x;
                                                        cordinateY = startY - x;
                                                }
                                        } else {
                                                if (piecePresent((startX - x) * 75, (startY - x) * 75)) {
                                                        countPieces += 1;
                                                        cordinateX = startX - x;
                                                        cordinateY = startY - x;
                                                }
                                        }
                                }
                                // Check if there is any obstacles
                                if (countPieces > 0) {
                                        if (countPieces == 1) {
                                                if (cordinateX == landingX && cordinateY == landingY) {
                                                        if (pieceName.contains("Black")) {
                                                                if (checkBlackOponent(landingX * 75, landingY * 75)) {
                                                                        validMove = true;
                                                                }
                                                        } else {
                                                                if (checkWhiteOponent(landingX * 75, landingY * 75)) {
                                                                        validMove = true;
                                                                }
                                                        }

                                                } else {
                                                        validMove = false;
                                                }
                                        } else {
                                                validMove = false;
                                        }
                                } else {
                                        validMove = true;
                                }
                        } else {
                                System.out.println("Not a valid Move!");
                                validMove = false;
                        }
                }

                // Moving Rooks
                if (pieceName.contains("Rook")) {
                        int countPieces = 0;
                        int cordinateX = 0;
                        int cordinateY = 0;
                        if ((xMovement == 0 & yMovement > 0) || (xMovement > 0 && yMovement == 0)) {

                                // Check the Rooks Movement
                                if (xMovement > 0 && yMovement == 0) {
                                        for (int i = 0; i < xMovement + 1; i++) {
                                                if (startX < landingX) {

                                                        //Black Rook
                                                        if (piecePresent((startX + i) * 75, startY * 75)) {
                                                                countPieces += 1;
                                                                cordinateX = startX + i;
                                                                cordinateY = startY;
                                                        }
                                                        System.out.println("Checking xCoordinates: " + startX + i);
                                                } else {
                                                        //White Rook
                                                        if (piecePresent((startX - i) * 75, startY * 75)) {
                                                                countPieces += 1;
                                                                cordinateX = startX - i;
                                                                cordinateY = startY;
                                                        }
                                                        System.out.println("Checking xCoordinates: " + (startX - i));
                                                }

                                        }
                                }
                                if (xMovement == 0 && yMovement > 0) {
                                        for (int i = 0; i < yMovement + 1; i++) {
                                                if (startY < landingY) {
                                                        if (piecePresent(startX * 75, (startY + i) * 75)) {
                                                                countPieces += 1;
                                                                cordinateX = startX;
                                                                cordinateY = startY + i;
                                                        }
                                                } else {
                                                        if (piecePresent(startX * 75, (startY - i) * 75)) {
                                                                countPieces += 1;
                                                                cordinateX = startX;
                                                                cordinateY = startY - i;
                                                        }
                                                }
                                        }
                                }

                                // Checking for obstacles
                                if (countPieces > 0) {
                                        if (countPieces == 1) {
                                                if (cordinateX == landingX && cordinateY == landingY) {
                                                        if (pieceName.contains("Black")) {
                                                                if (checkBlackOponent(landingX * 75, landingY * 75)) {
                                                                        validMove = true;
                                                                }
                                                        }
                                                        else {
                                                                if (checkWhiteOponent(landingX * 75, landingY * 75)) {
                                                                        validMove = true;
                                                                }
                                                        }

                                                }
                                                else {
                                                        validMove = false;
                                                }
                                        }
                                        else {
                                                validMove = false;
                                        }
                                }
                                else {
                                        validMove = true;
                                }
                        }
                        else {
                                System.out.println("Not a valid Move!");
                                validMove = false;
                        }
                }
        }
        // Promoting the little bois to big bois
        if (!validMove) {
                int location = 0;
                if (startY == 0) {
                        location = startX;
                } else {
                        location = (startY * 8) + startX;
                }
                String pieceLocation = pieceName + ".png";
                pieces = new JLabel(new ImageIcon(pieceLocation));
                panels = (JPanel) chessBoard.getComponent(location);
                panels.add(pieces);
        }

        else {
                if (success) {
                        if (pieceName.equals("WhitePawn")) {
                                int location = 56 + (e.getX() / 75);
                                if (c instanceof JLabel) {
                                        Container parent = c.getParent();
                                        parent.remove(0);
                                        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                                        parent = (JPanel) chessBoard.getComponent(location);
                                        parent.add(pieces);
                                } else {
                                        Container parent = (Container) c;
                                        pieces = new JLabel(new ImageIcon("WhiteQueen.png"));
                                        parent = (JPanel) chessBoard.getComponent(location);
                                        parent.add(pieces);
                                }
                        }

                        else {
                                int location = landingX;
                                if (c instanceof JLabel) {
                                        Container parent = c.getParent();
                                        parent.remove(0);
                                        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
                                        parent = (JPanel) chessBoard.getComponent(location);
                                        parent.add(pieces);
                                } else {
                                        Container parent = (Container) c;
                                        pieces = new JLabel(new ImageIcon("BlackQueen.png"));
                                        parent = (JPanel) chessBoard.getComponent(location);
                                        parent.add(pieces);
                                }
                        }
                }

                else {
                        if (c instanceof JLabel) {
                                Container parent = c.getParent();
                                parent.remove(0);
                                parent.add(chessPiece);
                        } else {
                                Container parent = (Container) c;
                                parent.add(chessPiece);
                        }
                        chessPiece.setVisible(true);
                }
        }
}

public void mouseClicked(MouseEvent e) {
}

public void mouseMoved(MouseEvent e) {
}

public void mouseEntered(MouseEvent e) {
}

public void mouseExited(MouseEvent e) {
}

/*
 * Main method that gets the ball moving.
 */
public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

}
}
