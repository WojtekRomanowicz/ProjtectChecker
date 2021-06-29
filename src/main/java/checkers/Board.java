package checkers;

import javafx.scene.Group;

import static checkers.PieceType.RED;
import static checkers.PieceType.WHITE;

public class Board {

    public static final int TILE_SIZE = 100;
    public static final int WIDTH = 8;
    public static final int HEIGHT = 8;
    boolean player1Turn = true;

    private Tile[][] board = new Tile[WIDTH][HEIGHT];
    private Group tileGroup = new Group();
    private Group pieceGroup = new Group();


    public Group createBoard() {
        Group root = new Group();

        root.getChildren().addAll(tileGroup);
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {
                Tile tile = new Tile((x + y) % 2 == 0, x, y);
                board[x][y] = tile;

                tileGroup.getChildren().add(tile);


            }
        }

        return root;
    }

    public void newGame(Player player1, Player player2) {


    }

    public void saveGame() {

    }

    public void loadGame() {

    }

    private int toBoard(double pixel) {
        return (int) (pixel + TILE_SIZE / 2) / TILE_SIZE;
    }

    private MoveResult tryMove(Piece piece, int newX, int newY) {
        if (board[newX][newY].hasPiece() || (newX + newY) % 2 == 0) {
            return new MoveResult(MoveType.NONE);
        }

        if ((player1Turn && piece.getType() == WHITE) || (!player1Turn && piece.getType() == RED)) {

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            if (Math.abs(newX - x0) == 1 && newY - y0 == piece.getType().moveDir) {

                return new MoveResult(MoveType.NORMAL);
            } else if (Math.abs(newX - x0) == 2 && newY - y0 == piece.getType().moveDir * 2) {

                int x1 = x0 + (newX - x0) / 2;
                int y1 = y0 + (newY - y0) / 2;

                if (board[x1][y1].hasPiece() && board[x1][y1].getPiece().getType() != piece.getType()) {
                    return new MoveResult(MoveType.KILL, board[x1][y1].getPiece());
                }
            }
        }
        return new MoveResult(MoveType.NONE);
    }

    private Piece makePiece(PieceType type, int x, int y) {
        Piece piece = new Piece(type, x, y);

        piece.setOnMouseReleased(e -> {
            int newX = toBoard(piece.getLayoutX());
            int newY = toBoard(piece.getLayoutY());

            System.out.println("Wspolrzedne x: " + newX + "y: " + newX);

            MoveResult result;

            if (newX < 0 || newY < 0 || newX >= WIDTH || newY >= HEIGHT) {
                result = new MoveResult(MoveType.NONE);
            } else {
                result = tryMove(piece, newX, newY);

            }

            int x0 = toBoard(piece.getOldX());
            int y0 = toBoard(piece.getOldY());

            switch (result.getType()) {
                case NONE:
                    piece.abortMove();
                    break;
                case NORMAL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);
                    player1Turn = false;
                    break;
                case KILL:
                    piece.move(newX, newY);
                    board[x0][y0].setPiece(null);
                    board[newX][newY].setPiece(piece);

                    Piece otherPiece = result.getPiece();
                    board[toBoard(otherPiece.getOldX())][toBoard(otherPiece.getOldY())].setPiece(null);
                    pieceGroup.getChildren().remove(otherPiece);
                    break;
            }
//            if(!playerTurn){
//                System.out.println("Tutaj moze sie ruszac komputer");
//                aiMove();
//            }

        });

        return piece;
    }

    public Group putPiecesOnBoard() {
        for (int y = 0; y < HEIGHT; y++) {
            for (int x = 0; x < WIDTH; x++) {

                Piece piece = null;

                if (y <= 2 && (x + y) % 2 != 0) {
                    piece = makePiece(RED, x, y);

                }

                if (y >= 5 && (x + y) % 2 != 0) {
                    piece = makePiece(WHITE, x, y);

                }

                if (piece != null) {
                    board[x][y].setPiece(piece);
                    pieceGroup.getChildren().add(piece);
                }
            }
        }
        return pieceGroup;
    }
}


