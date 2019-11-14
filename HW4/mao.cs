using System;
using System.IO;

public class Mailmerge{
    public static void Main(String[] args){
			//set init varibles
        String path1 = args[0];
        String path2 = args[1];
	   //use streamReader from IO to create object 
        StreamReader tsvFile = new StreamReader(path1);
        StreamReader tmpFile = new StreamReader(path2);
		//list keys for content split by tab
        String[] keys = tsvFile.ReadLine().Split('\t');
        String temp = tmpFile.ReadToEnd();

        String line = "";
        while ((line = tsvFile.ReadLine()) != null){
            String[] result = line.Split('\t');
            int colID = Array.IndexOf(keys,"ID");

            using (StreamWriter sw = new StreamWriter(result[colID] + ".txt")){
                String temptxt = temp;
                for(int i = 0; i < keys.Length; ++i){
                    temptxt = temptxt.Replace("<<" + keys[i] + ">>", result[i]);
                }
                sw.WriteLine(temptxt);
            }
        }
		//end 
		//close those two IO
        tsvFile.Close();
        tmpFile.Close();
		//check if the command lane is wrong or not.
		if(args.Length < 1 && args.Length > 2){
		   Console.WriteLine("Wrong Parameter Entries");
	   }
    }
}