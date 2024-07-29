package com.vignesh.utlity;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReaderFilter {
    public static void main(String[] args) throws IOException {
        File inputFile = new File("D:\\DevelopmentWorkspace\\github\\SpringBootMicro\\JavaInterviewPrep\\src\\com\\vignesh\\utlity\\CCstmt.txt");
        FileReader fileReader  = new FileReader(inputFile);
        BufferedReader br = new BufferedReader(fileReader);




        String str;
        while ((str = br.readLine())!=null){
            String[] strArr = str.split("\\s+");
            int len = strArr.length;

            if(str.contains("food")){
                System.out.println(str);
                System.out.println("Food "+strArr[len-1]);
            }

        }
    }
}
