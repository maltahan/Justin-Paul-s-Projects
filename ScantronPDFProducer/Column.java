import java.lang.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.*;

import com.pdfjet.*;

public class Column
{
    // instance variables - replace the example below with your own
    //Linelength needs to come from strings in the array - how does we get outz?
    private int lineLength = 0;
    
    public Column(int numCol)
    {
        assert numCol != 0;
        this.lineLength =  85/numCol;
    }
    
    public String arrayToString(LinkedList<String> array){
        String stri = "";
        
        for (String x : array){
            stri = stri + x + "\n" + "\n";
        }
        
        return stri;
    }

    public String create(String question)
    {
        LinkedList<String> col = new LinkedList<String>();
        while(question.length() > lineLength){
            col.add(question.substring(0,lineLength));
            question = question.substring(lineLength);
        }
        col.add(question);
        
        String end = new String();
        
        for(String x : col){
            end = end + "\n" + x;
        }
        
        return end;
    }
}
