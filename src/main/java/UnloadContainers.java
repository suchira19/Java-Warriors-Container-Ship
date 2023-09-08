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

    public static void main(String[] args) throws IOException {

        String instructions = System.getProperty("user.dir")+"\\src\\main\\resources\\data.txt";

        System.out.println("Instructions-----" + instructions);

        String fileName = instructions;
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        Container container = new Container();
        while((line = br.readLine()) != null){
            //process the line
            //System.out.println(line);
            String[] splited = line.split("\\s+");
            Integer[] parameters = new Integer[3];
            parameters[0] = Integer.parseInt(splited[1]);
            parameters[1] = Integer.parseInt(splited[3]);
            parameters[2] = Integer.parseInt(splited[5]);

            Map<Integer, Stack> ship = container.createContainer();

            if(ship.containsKey(parameters[1]) && ship.containsKey(parameters[2])) {
                Stack<String> srcElements = ship.get(parameters[1]);
                Stack<String> destElements = ship.get(parameters[2]);
                for(int i = 0; i< parameters[0]; i++) {
                    if(!srcElements.empty()) {
                        destElements.push(srcElements.pop());
                    }
                }
            }

            System.out.println("Container---" + ship);
        }

        //Create container ship

        //Process containers

        //Reveal message
    }
}
