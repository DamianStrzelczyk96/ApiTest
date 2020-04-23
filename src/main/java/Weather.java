import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    private double temp;

    private double temp_max;

    private double zachmurzenie;

    private double wiatr;



    private double cisnienie;

    private double widocznosc;

    private String opis;

    @Override
    public String toString() {
        return
                "\ntemp=" + temp + " F"+
                "\n temp_max=" + temp_max +" F"+
                "\n zachmurzenie=" + zachmurzenie + " %" +
                "\n wiatr=" + wiatr + " m/s"+
                "\n cisnienie=" + cisnienie + " hPa" +
                "\n widocznosc= " + widocznosc +
                "\n opis= " + opis + '\'' +
                '}';
    }

}
