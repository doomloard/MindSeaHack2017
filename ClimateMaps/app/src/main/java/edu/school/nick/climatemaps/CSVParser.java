package edu.school.nick.climatemaps;


import android.os.Debug;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.Externalizable;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by dooml on 6/10/2017.
 */

public class CSVParser {
    BufferedReader bufferedReader = null;
    String line;


    public ArrayList<ClimateData> ParseData(File csvFile) throws Exception {
        ArrayList<ClimateData> returnData = new ArrayList<ClimateData>();
        boolean firstLine = true;
        if(csvFile.canRead() && csvFile.exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader(csvFile));
                while ( (line = bufferedReader.readLine()) != null) {
                    if(firstLine) {
                        firstLine = false;
                        continue;
                    }

                    String[] data = line.split(",");
                    String[] param = null;

                    double unit = Double.parseDouble(data[4]);
                    String paramString = param[0].trim();
                    String subType = param[1].trim();
                    int rangeInt = Integer.parseInt(data[3].substring(0, (data[3].length() - 1)));

                    ClimateData row = new ClimateData(data[0], data[1], data[2], rangeInt, unit);

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
