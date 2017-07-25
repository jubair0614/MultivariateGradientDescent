import java.io.BufferedReader;
import java.util.ArrayList;

/**
 * Created by jubair on 7/26/17.
 */
public class FileReader {
    private String fileName;
    public ArrayList<ArrayList<Double>> input;

    public FileReader(String fileName){
        this.fileName = fileName;
        loadData();
    }

    public void loadData() {
        int row = 0;

        input = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new java.io.FileReader(this.fileName));
            String line;
            while ((line = reader.readLine()) != null)
            {
                ArrayList<Double> temp = new ArrayList<>();
                String[] arr = line.split(",");
                for(int column = 0; column < arr.length; column++){
                    temp.add(Double.parseDouble(arr[column]));
                }

                input.add(row, temp);
            }
            reader.close();
        }
        catch (Exception e){
            System.err.format("Exception occurred trying to read '%s'.", this.fileName);
            e.printStackTrace();
        }
    }

    public double[][] getData(){
        double[][] data = new double[input.size()][input.get(0).size()];
        for (int i=0; i<input.size(); i++){
            for (int j=0; j<input.get(i).size(); j++){
                data[i][j] = input.get(i).get(j);
            }
        }
        return data;
    }

    public void printData(){
        for (int i=0; i<input.size(); i++){
            for (int j=0; j<input.get(i).size(); j++){
                System.out.print(input.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }
}
