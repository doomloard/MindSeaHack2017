package edu.school.nick.climatemaps;


import android.os.Debug;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.Externalizable;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
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


    public void ParseData(File csvFile) throws Exception {
        List<Object> returnData = new List<Object>();
        boolean firstLine = true;
        if(csvFile.canRead() && csvFile.exists()) {
            try {
                bufferedReader = new BufferedReader(new FileReader(csvFile));
                while (line = bufferedReader.readLine()) {
                    if(firstLine) {
                        firstLine = false;
                        continue;
                    }

                    String[] data = line.split(",");
                    Object row = new Object();
                    row.parameter = data[1];

                    returnData.add(row);


                }
            } catch (Exception exception)
            {
                throw exception;
            }
        } else {
            throw new Exception("Can Not read csvFile");
        }
    }
}
