/*
    this is Homework for CSE 465
    Author Bohan Feng
 */
using System;
using System.IO;

public class Homework04{

    /**
        this method create a file output
        Args:
            filename (str): out filename
            str (str): content of outputfilne
     */
    public static void fileOutput(String filename, String str){
        using(StreamWriter sw = new StreamWriter(filename+".txt")){
            sw.WriteLine(str);
        }
    }

    /**
        this method replace the '<<key>>' to "<<value>>"
        Args: 
            line (str): a string to be modified
            keys (String[]): a array the contain the header of tsv file
            values (Stirng[]) a array contain rows of tsv file
        Return:
            line (str): a modified string
     */
    public static string modifieLine(String line, String[] keys, String[] values) {
        for(int i = 0; i < keys.Length; ++i){
            line = line.Replace("<<" + keys[i] + ">>", values[i]);
        }
        return line;
    }

    /**
        this method reads files and get the content and make the content into specic format
        Args:
            filename1 (str): tsv file
            finename2 (str): tmp file
     */
    public static void getResult(String filename1, String filename2){
        //use streamReader from IO to create object 
        StreamReader tsvFile = new StreamReader(filename1);
        StreamReader tmpFile = new StreamReader(filename2);
		//list keys for content split by tab
        String[] keys = tsvFile.ReadLine().Split('\t');
        String temp = tmpFile.ReadToEnd();

        String line = "";
        while((line = tsvFile.ReadLine()) != null){
            String[] values = line.Split('\t');
            int idIndex = Array.IndexOf(keys,"ID");
            String re = temp;
            re = modifieLine(re, keys, values);
            fileOutput(values[idIndex], re);
        }
    }

    public static void Main(String[] args)
    {
        // file name
        String filename1 = args[0];
        String filename2 = args[1];
        // get the result
        getResult(filename1, filename2);
    } 
}