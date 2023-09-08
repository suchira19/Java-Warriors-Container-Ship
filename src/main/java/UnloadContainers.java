import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Stack;

public class UnloadContainers {

    /*
     Starting container arrangement

                    [A] [L]     [J]
                [B] [Q] [R]     [D] [T]
                [G] [H] [H] [M] [N] [E]
            [J] [L] [D] [L] [J] [H] [B]
        [Q] [L] [W] [S] [V] [N] [F] [N]
    [W] [N] [H] [M] [L] [B] [R] [T] [Q]
    [L] [O] [C] [W] [D] [J] [W] [Z] [E]
    [S] [J] [S] [T] [O] [M] [D] [!] [H]
     1   2   3   4   5   6   7   8   9

     */

    private void printShip (String[][] container) {
        System.out.print("    |");
        for (int i = 0; i < container[0].length; i++){
            System.out.print("    ");
            System.out.print((char)('1' + i));
            System.out.print("    |");
        }
        System.out.println();
        for (int i = 0; i < container.length; i++){
            System.out.print("----+");
            for (int j = 0; j < container[0].length; j++){
                System.out.print("---------+");
            }
            System.out.println();
            System.out.print("  " + (i + 1) + " |");
            for (int j = 0; j < container[0].length; j++){
                if (container[i][j].length() < 10){
                    int spaces = (9 - container[i][j].length()) / 2;
                    for (int k = 0; k < spaces; k++){
                        System.out.print(" ");
                    }
                    System.out.print(container[i][j]);
                    for (int k = 0; k < (9 - container[i][j].length()) - spaces; k++){
                        System.out.print(" ");
                    }
                }
                else{
                    System.out.print(container[i][j].substring(0, 9));
                }
                System.out.print("|");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) throws IOException {

        String instructions = System.getProperty("user.dir")+"\\src\\main\\resources\\data.txt";

        System.out.println("Instructions-----" + instructions);

        String fileName = instructions;
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        Container container = new Container();
        Map<Integer, Stack> ship = container.createContainer();
        while((line = br.readLine()) != null){
            //process the line
            //System.out.println(line);
            String[] splited = line.split("\\s+");
            Integer[] parameters = new Integer[3];
            parameters[0] = Integer.parseInt(splited[1]);
            parameters[1] = Integer.parseInt(splited[3]);
            parameters[2] = Integer.parseInt(splited[5]);



            if(ship.containsKey(parameters[1]) && ship.containsKey(parameters[2])) {
                Stack<String> srcElements = ship.get(parameters[1]);
                Stack<String> destElements = ship.get(parameters[2]);
                for(int i = 0; i< parameters[0]; i++) {
                    if(!srcElements.empty()) {
                        destElements.push(srcElements.pop());
                    }
                }
            }
        }
        System.out.println("Container---" + ship);
        for (Map.Entry<Integer, Stack> set :
            ship.entrySet()) {
            Object[] arr = set.getValue().toArray();
            Integer[][] ship2DArray = new Integer[1][arr.length];

            for (int i = ship2DArray.length - 1; i > 0; i--) {
                for (int j = 0; j < ship2DArray[i].length; j++) {
                    //print value containers[i][j] in changing null into " " and surround with brackets
                    System.out.print("[" + (ship2DArray[i][j] == null ? " " : ship2DArray[i][j]) + "]");
                }
                System.out.println();
            }

            // Printing all elements of a Map
            System.out.println(set.getKey() + " = "
                + set.getValue());
        }


        //Create container ship

        //Process containers

        //Reveal message
    }
}
