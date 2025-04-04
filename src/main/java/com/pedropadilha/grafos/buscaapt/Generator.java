package com.pedropadilha.grafos.buscaapt;

import com.pedropadilha.grafos.ma.TGrafoRotuladoND;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author pedropadilha13
 */
public class Generator {

    public static void main(String[] args) {
        final int TOTAL = 60;

        try {
            File imoveisFile = new File("imoveis.csv");
            Scanner fileReader = new Scanner(imoveisFile);
            fileReader.nextLine(); //headers

            ArrayList<Apt> apts = new ArrayList<>(TOTAL);

            TGrafoRotuladoND grafo = new TGrafoRotuladoND(TOTAL);


            while (fileReader.hasNext()) {
                String row = fileReader.nextLine();
                Apt apt = fromRow(row);

                grafo.insertV(apt.getId());
                apts.add(apt);
            }


            for (int i = 0; i < TOTAL; i++) {
                for (int j = i + 1; j < TOTAL; j++) {
                    int score = compare(apts.get(i), apts.get(j));
                    grafo.insert(i, j, score);
                }
            }

//            grafo.print();

            grafo.exportToFile("grafo.txt");

            fileReader.close();
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao ler arquivo.");
        }

    }

    public static Apt fromRow(String row) {
        String[] fields = row.split(";");

        String id = fields[0];
        String[] rawCoords = fields[1].split(",");
        int area = Integer.parseInt(fields[2]);
        int quartos = Integer.parseInt(fields[3]);
        int suites = Integer.parseInt(fields[4]);
        int banheiros = Integer.parseInt(fields[5]);
        int vagas = Integer.parseInt(fields[6]);
        boolean isPetFriendly = Boolean.parseBoolean(fields[7]);
        Integer andar = Integer.valueOf(fields[8]);
        andar = andar != -1 ? andar : null;

        Apt apt = new Apt(id);

        double[] coords = {Double.parseDouble(rawCoords[0]), Double.parseDouble(rawCoords[1])};

        apt.setCoords(coords);
        apt.setArea(area);
        apt.setQuartos(quartos);
        apt.setSuites(suites);
        apt.setBanheiros(banheiros);
        apt.setVagas(vagas);
        apt.setPetFriendly(isPetFriendly);
        apt.setAndar(andar);

        return apt;
    }

    public static int compare(Apt apt1, Apt apt2) {
        double score = 1;

        double[] coords1 = apt1.getCoords();
        double[] coords2 = apt2.getCoords();

        double distance = distance(coords1[0], coords2[0], coords1[1], coords2[1], 0.0, 0.0) / 1000;
        double areaRatio = calculateSimilarity(apt1.getArea(), apt2.getArea());
        double quartosRatio = calculateSimilarity(apt1.getQuartos(), apt2.getQuartos());
        double suitesRatio = calculateSimilarity(apt1.getSuites(), apt2.getSuites());
        double banheirosRatio = calculateSimilarity(apt1.getBanheiros(), apt2.getBanheiros());
        double vagasRatio = calculateSimilarity(apt1.getVagas(), apt2.getVagas());
        double andarScore = 1;

        if (apt1.getAndar() != null && apt2.getAndar() != null) {
            int andar1 = getAndarClass(apt1.getAndar());
            int andar2 = getAndarClass(apt2.getAndar());

            andarScore = 1 - Math.abs(andar1 - andar2) * 0.1;
        }

        double petScore = apt1.getPetFriendly() == apt2.getPetFriendly() ? 1.0 : 0.9;

        score *= Math.min((10 - distance), 10) * 0.1; // [5]
        score *= areaRatio; // [4]
        score *= quartosRatio; // [4]
        score *= suitesRatio; // [2]
        score *= banheirosRatio; // [2]
        score *= vagasRatio; // [3]
        score *= andarScore; // [1]
        score *= petScore; // [3]

        return (int) Math.floor(score * 100);
    }

    // https://stackoverflow.com/questions/3694380/calculating-distance-between-two-points-using-latitude-longitude
    public static double distance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    public static int getAndarClass(int andar) {
        if (andar < 6) {
            return 1;
        } else if (andar < 13) {
            return 2;
        } else {
            return 3;
        }
    }

    public static double calculateSimilarity(double a, double b) {
        return calculateSimilarity(a, b, -0.01);
    }

    public static double calculateSimilarity(double a, double b, double alpha) {
        if (a == 0 && b == 0) return 1.0;
        if (a == 0 || b == 0) return 0.0;

        double relativeDifference = Math.abs(a - b) / Math.max(a, b);
        return Math.exp(-alpha * relativeDifference);
    }

}
