package application;

import boardgame.Board;
import chess.ChessException;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        while(!chessMatch.getCheckMate()) {
            try {
                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.println("Origem: ");
                ChessPosition source = UI.readChessPosition(sc);

                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.printBoard(chessMatch.getPieces(), possibleMoves);

                System.out.println();
                System.out.println("Destino: ");
                ChessPosition target = UI.readChessPosition(sc);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);

                if(capturedPiece != null) {
                    captured.add(capturedPiece);
                }

                if(chessMatch.getPromoted() != null) {
                    System.out.println("Entre com uma peça para a promoção (B/N/R/Q)");
                    String type = sc.nextLine().toUpperCase();
                   while(!type.equals("B") && !type.equals("N") && !type.equals("R")&&!type.equals("Q")){
                       System.out.println("Valor invalido. Entre com uma peça para a promoção (B/N/R/Q)");
                       type = sc.nextLine().toUpperCase();
                   }
                    chessMatch.replacePromotedPiece(type);
                }
            }
            catch(ChessException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
            catch(InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
            }
        }
        UI.printMatch(chessMatch, captured);

    }
}