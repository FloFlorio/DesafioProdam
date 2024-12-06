package listaJogos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GameApp {
    private GameManager gameManager;
    private JFrame frame;
    private DefaultListModel<String> gameListModel;

    public GameApp() {
        gameManager = new GameManager();
        gameListModel = new DefaultListModel<>();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Lista de Jogos Zerados");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JList<String> list = new JList<>(gameListModel);
        JScrollPane scrollPane = new JScrollPane(list);
        frame.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Adicionar Jogo");
        JButton removeButton = new JButton("Remover Jogo");
        JButton editButton = new JButton("Editar Jogo");

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog("Nome do jogo:");
                String date = JOptionPane.showInputDialog("Data de Conclusão (dd/MM/yyyy):");
                String time = JOptionPane.showInputDialog("Tempo de conclusão (em minutos):");

                if (name != null && date != null && time != null) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    try {
                        LocalDate completionDate = LocalDate.parse(date, formatter); // Parsing com formato correto
                        int completionTime = Integer.parseInt(time);
                        Game newGame = new Game(name, completionDate, completionTime);
                        gameManager.addGame(newGame);
                        gameListModel.addElement(newGame.toString());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, "Data inválida. Utilize o formato dd/MM/yyyy.");
                    }
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    Game selectedGame = gameManager.getGameList().get(selectedIndex);
                    gameManager.removeGame(selectedGame);
                    gameListModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione um jogo para remover.");
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = list.getSelectedIndex();
                if (selectedIndex != -1) {
                    Game selectedGame = gameManager.getGameList().get(selectedIndex);

                    String name = JOptionPane.showInputDialog("Nome do jogo:", selectedGame.getName());
                    String date = JOptionPane.showInputDialog("Data de Conclusão (dd/MM/yyyy):", selectedGame.getCompletionDate());
                    String time = JOptionPane.showInputDialog("Tempo de conclusão (em minutos):", selectedGame.getCompletionTime());

                    if (name != null && date != null && time != null) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        try {
                            LocalDate completionDate = LocalDate.parse(date, formatter); // Parsing com formato correto
                            int completionTime = Integer.parseInt(time);
                            Game newGame = new Game(name, completionDate, completionTime);
                            gameManager.editGame(selectedIndex, newGame);
                            gameListModel.set(selectedIndex, newGame.toString());
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Data inválida. Utilize o formato dd/MM/yyyy.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Selecione um jogo para editar.");
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GameApp();
    }
}
