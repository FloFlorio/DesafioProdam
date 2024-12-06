package listaJogos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Game {
    private String name;
    private LocalDate completionDate;
    private int completionTime;

    public Game(String name, LocalDate completionDate, int completionTime) {
        this.name = name;
        this.completionDate = completionDate;
        this.completionTime = completionTime;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    @Override
    public String toString() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	String formattedDate = completionDate.format(formatter);
        return "Jogo: " + name + ", Data de Conclusão: " + formattedDate + ", Tempo de Jogo: " + completionTime + " minutos";
    }
}
