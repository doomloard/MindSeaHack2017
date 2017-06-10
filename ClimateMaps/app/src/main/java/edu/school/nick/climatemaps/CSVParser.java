package edu.school.nick.climatemaps;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Created by dooml on 6/10/2017.
 */

public class CSVParser {
    BufferedReader bufferedReader = null;
    String line;


    public ArrayList<ClimateData> ParseData() throws Exception {
        String csvString = "NS_Climate_Change_Data.csv";
        ArrayList<ClimateData> returnData = new ArrayList<ClimateData>();
        boolean firstLine = true;
        if(csvString != null) {
            try {
                bufferedReader = new BufferedReader(new FileReader(csvString));
                while ( (line = bufferedReader.readLine()) != null) {
                    if(firstLine) {
                        firstLine = false;
                        continue;
                    }

                    String[] data = line.split(",");

                    double unit = Double.parseDouble(data[4]);
                    int rangeInt = Integer.parseInt(data[3].substring(0, (data[3].length() - 1)));

                    ClimateData row = new ClimateData(data[0].trim(), data[1].trim(), data[2].trim(), rangeInt, unit);

                    returnData.add(row);
                }
                return  returnData;
            } catch (Exception exception)
            {
                throw exception;
            }
        } else {
            throw new Exception("Can Not read csvFile");
        }
    }
}
