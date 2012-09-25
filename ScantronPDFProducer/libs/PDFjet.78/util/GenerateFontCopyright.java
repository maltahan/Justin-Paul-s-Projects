package util;

import java.lang.*;
import java.io.*;
import java.util.*;


public class GenerateFontCopyright {


    public GenerateFontCopyright() {
        try {
            BufferedWriter bw = new BufferedWriter(
                    new FileWriter("com/pdfjet/DroidFontsCopyright.java"));

            bw.write("package com.pdfjet;\n\n");
            bw.write("import java.lang.*;\n\n\n");
            bw.write("//>>>>pdfjet {\n");
            bw.write("public class DroidFontsCopyright {\n\n");
            bw.write("    public static final String NOTICE = \"");
            bw.write("\\n---------------------------------------------------------------------------\\n\\n");
            bw.write("This PDF file contains embedded fonts from The Android Open Source Project:\\n\\n");
            bw.write("http://developer.android.com/index.html\\n\\n");
            bw.write("The fonts were MODIFIED by Innovatics Inc. and are licensed under the\\n\\n");
            bw.write("original Apache License, Version 2.0:\\n\\n");
            bw.write("---------------------------------------------------------------------------\\n\\n");

            FileInputStream fis =
                    new FileInputStream("fonts/DroidFonts/NOTICE");
            int ch;
            while ((ch = fis.read()) != -1) {
                if (ch == '\n') {
                    bw.write('\\');
                    bw.write('n');
                }
                else if (ch == '"') {
                    bw.write('\\');
                    bw.write('"');
                }
                else {
                    bw.write(ch);
                }
            }

            fis.close();

            bw.write("---------------------------------------------------------------------------\\n");
            bw.write("\";\n\n}\n");
            bw.write("//<<<<}");
            bw.close();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }


    public static void main(String[] args) {
        new GenerateFontCopyright();
    }

}
