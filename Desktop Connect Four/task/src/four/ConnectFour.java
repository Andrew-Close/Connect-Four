package four;

import javax.swing.*;
import java.awt.*;

public class ConnectFour extends JFrame {
    private class ConnectFourCell extends JButton {
        private ConnectFourCell(int row, int col) {
            super();

            setText(" ");
            setFont(getFont().deriveFont(96f));
            setName(String.format("Button%s%d", convertColToLetter(col), row * -1 + 6));
            float[] color = Color.RGBtoHSB(186, 217, 219, null);
            setBackground(Color.getHSBColor(color[0], color[1], color[2]));
            setBorderPainted(false);
            setFocusPainted(false);
            addActionListener(e -> {
                ConnectFourCell cell;
                int column = 0;
                outer_loop:
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 7; j++) {
                        if (this.equals(board[i][j])) {
                            column = j;
                            break outer_loop;
                        }
                    }
                }
                for (int i = 5; i >= 0; i--) {
                    if (" ".equals(board[i][column].getText())) {
                        cell = board[i][column];
                        if (isXTurn) {
                            if (cell != null) {
                                cell.setText("X");
                            }
                        } else {
                            if (cell != null) {
                                cell.setText("O");
                            }
                        }
                        break;
                    }
                }
                checkForWinners();
                isXTurn = !isXTurn;
            });
        }

        private void checkForWinners() {
            if (isXTurn) {
                checkVertical("X");
                checkHorizontal("X");
                checkDiagonalLeftToRight("X");
                checkDiagonalRightToLeft("X");
            } else {
                checkVertical("O");
                checkHorizontal("O");
                checkDiagonalLeftToRight("O");
                checkDiagonalRightToLeft("O");
            }
        }

        private void checkVertical(String comparator) {
            int counter = 0;
            // Loop through the game board
            loop:
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 7; j++) {
                    // Finding a cell occupied by the player. Any occupied cells that are found are "active"
                    if (comparator.equals(board[i][j].getText())) {
                        // Looping through the 3 cells underneath the active one
                        for (int k = i; k < i + 4; k++) {
                            // Checking if the 3 cells underneath the active one are occupied by the same player.
                            if (comparator.equals(board[k][j].getText())) {
                                // Keeping track of how many cells in a row there are
                                ++counter;
                                // Win condition
                                if (counter == 4) {
                                    // Highlights the winning cells
                                    for (int l = i; l < i + 4; l++) {
                                        board[l][j].setBackground(Color.YELLOW);
                                    }
                                    for (i = 0; i < 6; i++) {
                                        for (j = 0; j < 7; j++) {
                                            board[i][j].setEnabled(false);
                                        }
                                    }
                                    break loop;
                                }
                            } else {
                                // Resets for the next iteration
                                counter = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }

        private void checkHorizontal(String comparator) {
            int counter = 0;
            // Loop through the game board
            loop:
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 4; j++) {
                    // Finding a cell occupied by the player. Any occupied cells that are found are "active"
                    if (comparator.equals(board[i][j].getText())) {
                        // Looping through the 3 cells to the right of the active one
                        for (int k = j; k < j + 4; k++) {
                            // Checking if the 3 cells to the right of the active one are occupied by the same player.
                            if (comparator.equals(board[i][k].getText())) {
                                // Keeping track of how many cells in a row there are
                                ++counter;
                                // Win condition
                                if (counter == 4) {
                                    // Highlights the winning cells
                                    for (int l = j; l < j + 4; l++) {
                                        board[i][l].setBackground(Color.YELLOW);
                                    }
                                    for (i = 0; i < 6; i++) {
                                        for (j = 0; j < 7; j++) {
                                            board[i][j].setEnabled(false);
                                        }
                                    }
                                    break loop;
                                }
                            } else {
                                // Resets for the next iteration
                                counter = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }

        private void checkDiagonalLeftToRight(String comparator) {
            int counter = 0;
            // Loop through the game board
            loop:
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 4; j++) {
                    // Finding a cell occupied by the player. Any occupied cells that are found are "active"
                    if (comparator.equals(board[i][j].getText())) {
                        // Looping through the 3 cells underneath the active one
                        for (int k = 0; k < 4; k++) {
                            // Checking if the 3 cells underneath the active one are occupied by the same player.
                            if (comparator.equals(board[i + k][j + k].getText())) {
                                // Keeping track of how many cells in a row there are
                                ++counter;
                                // Win condition
                                if (counter == 4) {
                                    // Highlights the winning cells
                                    for (int l = 0; l < 4; l++) {
                                        board[i + l][j + l].setBackground(Color.YELLOW);
                                    }
                                    for (i = 0; i < 6; i++) {
                                        for (j = 0; j < 7; j++) {
                                            board[i][j].setEnabled(false);
                                        }
                                    }
                                    break loop;
                                }
                            } else {
                                // Resets for the next iteration
                                counter = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }

        private void checkDiagonalRightToLeft(String comparator) {
            int counter = 0;
            // Loop through the game board
            loop:
            for (int i = 0; i < 3; i++) {
                for (int j = 3; j < 7; j++) {
                    // Finding a cell occupied by the player. Any occupied cells that are found are "active"
                    if (comparator.equals(board[i][j].getText())) {
                        // Looping through the 3 cells underneath the active one
                        for (int k = 0; k < 4; k++) {
                            // Checking if the 3 cells underneath the active one are occupied by the same player.
                            if (comparator.equals(board[i + k][j - k].getText())) {
                                // Keeping track of how many cells in a row there are
                                ++counter;
                                // Win condition
                                if (counter == 4) {
                                    // Highlights the winning cells
                                    for (int l = 0; l < 4; l++) {
                                        board[i + l][j - l].setBackground(Color.YELLOW);
                                    }
                                    for (i = 0; i < 6; i++) {
                                        for (j = 0; j < 7; j++) {
                                            board[i][j].setEnabled(false);
                                        }
                                    }
                                    break loop;
                                }
                            } else {
                                // Resets for the next iteration
                                counter = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }

        private String convertColToLetter(int column) {
            return switch (column) {
                case 0 -> "A";
                case 1 -> "B";
                case 2 -> "C";
                case 3 -> "D";
                case 4 -> "E";
                case 5 -> "F";
                default -> "G";
            };
        }
    }

    private class ButtonReset extends JButton {
        private ButtonReset() {
            super();

            setEnabled(true);
            setText("Reset");
            setName("ButtonReset");
            setFont(getFont().deriveFont(48f));
            float[] color = Color.RGBtoHSB(237, 187, 95, null);
            setBackground(Color.getHSBColor(color[0], color[1], color[2]));
            setPreferredSize(new Dimension(500, 100));
            setFocusPainted(false);
            addActionListener(e -> {
                for (int i = 0; i < 6; i++) {
                    for (int j = 0; j < 7; j++) {
                        board[i][j].setEnabled(true);
                        board[i][j].setText(" ");
                        float[] colorTemp = Color.RGBtoHSB(186, 217, 219, null);
                        board[i][j].setBackground(Color.getHSBColor(colorTemp[0], colorTemp[1], colorTemp[2]));
                    }
                }
                isXTurn = true;
            });
        }
    }
    private final ConnectFourCell[][] board = new ConnectFourCell[6][7];
    private boolean isXTurn = true;
    public ConnectFour() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(2560, 1440);
        setVisible(true);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setLocationRelativeTo(null);
        setTitle("Connect Four");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 7, 5, 5));
        add(panel);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                ConnectFourCell cell = new ConnectFourCell(i, j);
                board[i][j] = cell;
                panel.add(cell);
            }
        }

        ButtonReset buttonReset = new ButtonReset();
        add(buttonReset);
    }
}