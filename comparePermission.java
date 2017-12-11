import java.sql.*;
import java.io.*;
import java.util.Scanner;
import java.lang.*;
import java.util.*;
class comparePermission
{
    public static void main(String[] args) {
        Vector<String> vec = new Vector<String>();
        FileRead obj = new FileRead("removeDuplicate.txt");
        while(obj.hasNextLine())
        {
            String line = obj.readNextLine();
            vec.add(line);
        }
        obj.fileClose();
        if(args.length != 1)
        {
            System.out.println("ERROR : INVALID COMMAND LINE ARGUMENT ARE PASSED TO comparePermission.java");
            return;
        }
        obj = new FileRead(args[0]);
        String text = new String();
        while(obj.hasNextLine())
        {
            String line = obj.readNextLine();
            text += line;
        }
        Vector<String> res = new Vector<String>();
        System.out.println(vec.size() + "  " + text.length());
        for(int k=0;k< vec.size() ; k++)
        {
            for (int i=0; i < text.length() ; i++ )
            {
                boolean match = true;
                if(text.charAt(i) == vec.get(k).charAt(0))
                {
                    int temp = i;
                    for (int j=0;j < vec.get(k).length(); j++,temp++)
                    {
                        if(vec.get(k).charAt(j) != text.charAt(temp))
                        {
                            match = false;
                            break;
                        }
                    }
                    if(match)
                    {
                        res.add(vec.get(k));
                        break;
                    }
                }
            }
        }
        BufferedWriter bw = null;
        try {
            // APPEND MODE SET HERE
            bw = new BufferedWriter(new FileWriter("matchedPermissions", true));
            // bw.write("400:08311998:Inprise Corporation:249.95");
            for (int i=0;i < res.size() ;i++ )
            {
                bw.write(res.get(i)+"\n");
            }
            bw.write("******\n");
            bw.flush();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally {                       // always close the file
             if (bw != null) try {
                bw.close();
             } catch (IOException ioe2) {
                // just ignore it
             }
              } // end try/catch/finally

           } // end test()

        // FileWrite o = new FileWrite("matchedPermissions");
        // System.out.println(res.size());
        // for (int i=0;i < res.size() ;i++ )
        // {
        //     o.writeContent(res.get(i)+"\n");
        // }
        // o.fileClose();
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