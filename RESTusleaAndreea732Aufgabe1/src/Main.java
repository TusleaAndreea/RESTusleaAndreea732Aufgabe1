import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {

        //a)
        File file = new File("ninja_events.json");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        Map<String, String> currentLog = new HashMap<>();
        List<Map<String, String>> logsList = new ArrayList<>();
        String line = bufferedReader.readLine();
        while(line != null)
        {
            if(line.contains("[") || line.contains("]") || line.contains("{")) {

                // mergem doar la urmatoarea linie
                line = bufferedReader.readLine();
                continue;
            }

            if(line.contains("}")) {
                logsList.add(currentLog);

                currentLog = new HashMap<>();

                line = bufferedReader.readLine();
                continue;
            }

            line = line.replace("\"", "");

            String[] data = line.split("[:,]");

            data[0] = data[0].trim();
            data[1] = data[1].trim();
            currentLog.put(data[0], data[1]);
            line = bufferedReader.readLine();

        }
        System.out.println("b) ");


        Scanner scanner = new Scanner(System.in);

        System.out.print("Bitte gib eine Zahl: ");
        Double input = Double.parseDouble(scanner.nextLine());

        logsList.stream()
                .filter( (log) -> Double.parseDouble(log.get("Kraftpunkte")) > input)
                .distinct()
                .forEach( (log) -> System.out.println(log.get("Charaktername")));
    }
}