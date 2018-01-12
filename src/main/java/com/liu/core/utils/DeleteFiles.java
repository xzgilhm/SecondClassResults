package com.liu.core.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeleteFiles {
    public void deleteFilesInMap(ArrayList<String> filesName, String filePath){
        File folder = new File(filePath);
        File[] files = folder.listFiles();
        for(int i=0;i<files.length;i++){
            for(int j=0;j<filesName.size();j++){
                if( files[i].getName().equals( filesName.get(j) ) ){
                    System.out.println("deleteFiles: " + files[i]);
                    files[i].delete();
                }
            }
        }
    }
}
