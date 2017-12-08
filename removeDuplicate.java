import java.sql.*;
import java.io.*;
import java.util.Scanner;
import java.lang.*;
import java.util.*;
class removeDuplicate
{
    public static void main(String[] args) {
        Vector<String> vec = new Vector<String>();
        HashSet<String> set = new HashSet<String>();
        FileRead obj = new FileRead("allPossiblePermission");
        int len = 0;
        while(obj.hasNextLine())
        {
            // System.out.println("aaaa");
            String line = obj.readNextLine();
            if(!set.contains(line) )
            {
                len++;
                // System.out.println("bbbb");
                set.add(line);
                vec.add(line);
            }
        }
        FileWrite o = new FileWrite("removeDuplicate.txt");
        for (int i=0;i < len ;i++ ) {
            System.out.println(vec.get(i));
            o.writeContent(vec.get(i)+"\n");
        }
        o.fileClose();
    }
}
class FileRead
{
    private String filePath;
    private File file;
    private Scanner inputFile;
    /**
     * This function returns a file pointer to desired file
     * @param filePath path of the desired file
     */
    FileRead(String filePath)
    {
        this.filePath=filePath;
        // Open the file.
        try
        {
            file = new File(filePath);
            inputFile = new Scanner(file);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found"+filePath);
        }
    }
    void fileClose()
    {
        inputFile.close();
        // file.close();
    }
    String readNextLine()
    {
            String line = inputFile.nextLine();
            line=line.trim();
            return line;
    }
    boolean hasNextLine()
    {
        return inputFile.hasNext();
    }
}
class FileWrite
{
    BufferedWriter bw = null;
    FileWriter fw = null;
    FileWrite(String filename)
    {
        try
        {
            fw = new FileWriter(filename);
            bw = new BufferedWriter(fw);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    void writeContent(String content)
    {
        try
        {
            bw.write(content);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    void fileClose()
    {
        try
        {
            if (bw != null)
                bw.close();
            if (fw != null)
                fw.close();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }
}