import java.util.Scanner;

public class MutantDetector {

    public static boolean isMutant(String[] dna) {
        int isMutant = 0;

        // Búsqueda horizontal
        for (String row : dna) {
            if (checkSequence(row)) {
                isMutant++;
            }
        }

        // Búsqueda vertical
        for (int col = 0; col < 6; col++) {
            StringBuilder colSequence = new StringBuilder();
            for (int row = 0; row < 6; row++) {
                colSequence.append(dna[row].charAt(col));
            }
            if (checkSequence(colSequence.toString())) {
                isMutant++;
            }
        }

        // Búsqueda diagonal de izquierda hacia abajo
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                StringBuilder diagonal = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    diagonal.append(dna[row + i].charAt(col + i));
                }
                if (checkSequence(diagonal.toString())) {
                    isMutant++;
                }
            }
        }

        // Búsqueda diagonal de derecha hacia abajo
        for (int row = 0; row < 3; row++) {
            for (int col = 3; col < 6; col++) {
                StringBuilder diagonal = new StringBuilder();
                for (int i = 0; i < 4; i++) {
                    diagonal.append(dna[row + i].charAt(col - i));
                }
                if (checkSequence(diagonal.toString())) {
                    isMutant++;
                }
            }
        }

        // Todas las comprobaciones arrojan un +1 a isMutant, para cumplir la condición de dos o más líneas
        return isMutant >= 2;
    }

    // Función que busca los 4 valores iguales en una secuencia
    private static boolean checkSequence(String sequence) {
        return sequence.contains("AAAA") || sequence.contains("TTTT") ||
                sequence.contains("CCCC") || sequence.contains("GGGG");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("Bienvenido a Magnus Lab, ingrese a su sujeto en la máquina de pruebas para determinar si es mutante.");

        while (!exit) {
            String[] dna = new String[6];

            System.out.println("Ingrese una por una las filas de ADN, con todas las letras juntas. ATGC son los únicos valores permitidos.");
            for (int i = 0; i < 6; i++) {
                boolean validRow = false;

                while (!validRow) {
                    System.out.print("Fila " + (i + 1) + ": ");
                    String row = scanner.next().toUpperCase();

                    if (row.length() != 6 || !row.matches("[ATCG]+")) {
                        System.out.println("La fila debe tener solo 6 letras y las únicas permitidas son A, T, G, C.");
                    } else {
                        dna[i] = row;
                        validRow = true;
                    }
                }
            }

            boolean isMutant = isMutant(dna);

            if (isMutant) {
                System.out.println("Felicidades, su sujeto pasó la prueba y su ADN coincide con los fenotipos, es un mutante.");
            } else {
                System.out.println("Lo lamentamos, su sujeto no pasó la prueba... Y está muerto.");
            }

            String exitChoice = "";
            while (!exitChoice.equals("SI") && !exitChoice.equals("NO")) {
                System.out.print("¿Desea intentar nuevamente con otro sujeto? Si/No: ");
                exitChoice = scanner.next().toUpperCase();

                if (exitChoice.equals("NO")) {
                    exit = true;
                    System.out.println("Gracias por usar la Mutanteando 3000, Adiós!");
                } else if (exitChoice.equals("SI")) {
                    System.out.println("Usted es un monstruo");
                }
            }
        }
    }
}