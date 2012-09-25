/**
 * AfmCompiler.java
 *
Copyright (c) 2007, 2008, 2009, 2010 Innovatics Inc.

All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
    * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/

package util;


import java.lang.*;
import java.io.*;
import java.util.*;


/**
 *  AfmCompiler.java
 *  Extracts information from .afm file and creates
 *  .java and .cs files containing the font metrics.
 */
public class AfmCompiler {

    public AfmCompiler(String path, String fontName, String lang) {

        boolean generateJava = false;
        if (lang.equals("Java")) {
            generateJava = true;
        }

        StringBuffer[] data = new StringBuffer[256];
        for (int i = 32; i < 256; i++) {
            data[i] = new StringBuffer();
        }

        Map<String, String> map = new HashMap<String, String>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(path + fontName));
            String className = fontName.substring(0, fontName.lastIndexOf('.'));
            className = className.replace('-', '_');
            BufferedWriter out = null;
            if (generateJava) {
                out = new BufferedWriter(new FileWriter(
                        "com/pdfjet/" + className + ".java"));
                out.write("package com.pdfjet;\n\n");
                out.write("public class " + className + " extends CoreFont {\n");
            } else {
                out = new BufferedWriter(new FileWriter(
                        "net/pdfjet/" + className + ".cs"));
                out.write("namespace PDFjet.NET {\n");
                out.write("public class " + className + " : CoreFont {\n");
            }

            List<String> list = new ArrayList<String>();
            String line = null;
            while ((line = in.readLine()) != null) {
                list.clear();
                StringTokenizer st = new StringTokenizer(line, " ;");
                while (st.hasMoreTokens()) list.add(st.nextToken());

                int index = 0;
                if (list.get(0).equals("FontBBox")) {
                    // FontBBox -166 -225 1000 931
                    out.write("    int bBoxLLx = " + list.get(1) + ";\n");
                    out.write("    int bBoxLLy = " + list.get(2) + ";\n");
                    out.write("    int bBoxURx = " + list.get(3) + ";\n");
                    out.write("    int bBoxURy = " + list.get(4) + ";\n");
                } else if (list.get(0).equals("UnderlinePosition")) {
                    out.write("    int underlinePosition = "
                            + list.get(1) + ";\n");
                } else if (list.get(0).equals("UnderlineThickness")) {
                    out.write("    int underlineThickness = "
                            + list.get(1) + ";\n");
                    if (generateJava) {
                        out.write("    protected int getBBoxLLx() {\n");
                        out.write("        return bBoxLLx;\n");
                        out.write("    }\n");
                        out.write("    protected int getBBoxLLy() {\n");
                        out.write("        return bBoxLLy;\n");
                        out.write("    }\n");
                        out.write("    protected int getBBoxURx() {\n");
                        out.write("        return bBoxURx;\n");
                        out.write("    }\n");
                        out.write("    protected int getBBoxURy() {\n");
                        out.write("        return bBoxURy;\n");
                        out.write("    }\n");
                        out.write("    protected int getUnderlinePosition() {\n");
                        out.write("        return underlinePosition;\n");
                        out.write("    }\n");
                        out.write("    protected int getUnderlineThickness() {\n");
                        out.write("        return underlineThickness;\n");
                        out.write("    }\n");
                    } else {
                        out.write("    internal override int getBBoxLLx() {\n");
                        out.write("        return bBoxLLx;\n");
                        out.write("    }\n");
                        out.write("    internal override int getBBoxLLy() {\n");
                        out.write("        return bBoxLLy;\n");
                        out.write("    }\n");
                        out.write("    internal override int getBBoxURx() {\n");
                        out.write("        return bBoxURx;\n");
                        out.write("    }\n");
                        out.write("    internal override int getBBoxURy() {\n");
                        out.write("        return bBoxURy;\n");
                        out.write("    }\n");
                        out.write("    internal override int getUnderlinePosition() {\n");
                        out.write("        return underlinePosition;\n");
                        out.write("    }\n");
                        out.write("    internal override int getUnderlineThickness() {\n");
                        out.write("        return underlineThickness;\n");
                        out.write("    }\n");
                    }
                } else if (list.get(0).equals("Notice")) {
                    if (generateJava) {
                        out.write("    protected static final String notice = \"");
                    } else {
                        out.write("    protected const string notice = \"");
                    }
                    out.write(line.substring(7));
                    out.write("\";\n");
                } else if (list.get(0).equals("StartCharMetrics")) {
                    out.write("    int[][] data = {\n");
                } else if (list.get(0).equals("C")) {
                    // C 95 ; WX 556 ; N underscore ; B 0 -125 556 -75 ;
                    String name = list.get(5);

                    boolean found = false;
                    for (int i = 32; i < 256; i++) {
                        if (name.equals(com.pdfjet.Glyph.names[i - 32])) {
                            index = i;
                            found = true;
                            break;
                        }
                    }
                    if (!found) continue;

                    map.put(name, String.valueOf(index));
                    if (generateJava) {
                        data[index].append("        {");
                    } else {
                        data[index].append("        new int[] {");
                    }
                    data[index].append(String.valueOf(index));
                    data[index].append(',');
                    data[index].append(list.get(3));
                    data[index].append(',');
                } else if (list.get(0).equals("KPX")) {
                    // KPX o v -15
                    if (!map.containsKey(list.get(1)) ||
                            !map.containsKey(list.get(2))) {
                        continue;
                    }
                    index = Integer.parseInt(map.get(list.get(1)));
                    data[index].append(map.get(list.get(2)));
                    data[index].append(',');
                    data[index].append(list.get(3));
                    data[index].append(',');
                }
            }

            for (int i = 32; i < data.length; i++) {
                if (data[i].length() == 0) {
                    if (generateJava) {
                        out.write("        {");
                    } else {
                        out.write("        new int[] {");
                    }
                    out.write(String.valueOf(i));
                    String bullet = data[183].toString();
                    out.write(bullet.substring(bullet.indexOf("183") + 3));
                } else {
                    out.write(data[i].toString());
                }
                out.write("},\n");
            }
            out.write("    };\n\n");

            if (generateJava) {
                out.write("    protected int[][] getMetrics() {\n");
            } else {
                out.write("    internal override int[][] getMetrics() {\n");
            }
            out.write("        return data;\n");
            out.write("    }\n");
            out.write("}\n");

            if (lang.equals("C#")) {
                out.write("}   // End of namespace PDFjet.NET\n");
            }

            in.close();
            out.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }


    // Special compiler for the Symbol.afm and ZapfDingbats.afm
    public AfmCompiler(String path, String fontName, String lang, String symbol) {

        boolean generateJava = false;
        if (lang.equals("Java")) {
            generateJava = true;
        }

        StringBuffer[] data = new StringBuffer[256];
        for (int i = 32; i < 256; i++) {
            data[i] = new StringBuffer();
        }

        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(path + fontName));
            String className =
                    fontName.substring(0, fontName.lastIndexOf('.'));

            BufferedWriter out = null;
            if (generateJava) {
                out = new BufferedWriter(new FileWriter(
                        "com/pdfjet/" + className + ".java"));
                out.write("package com.pdfjet;\n\n");
                out.write("public class " + className + " extends CoreFont {\n");
            } else {
                out = new BufferedWriter(new FileWriter(
                        "net/pdfjet/" + className + ".cs"));
                out.write("namespace PDFjet.NET {\n");
                out.write("public class " + className + " : CoreFont {\n");
            }

            List<String> list = new ArrayList<String>();
            String line = null;
            while ((line = in.readLine()) != null) {
                list.clear();
                StringTokenizer st = new StringTokenizer(line, " ;");
                while (st.hasMoreTokens()) list.add(st.nextToken());

                if (list.get(0).equals("FontBBox")) {
                    // FontBBox -166 -225 1000 931
                    out.write("    int bBoxLLx = " + list.get(1) + ";\n");
                    out.write("    int bBoxLLy = " + list.get(2) + ";\n");
                    out.write("    int bBoxURx = " + list.get(3) + ";\n");
                    out.write("    int bBoxURy = " + list.get(4) + ";\n");
                } else if (list.get(0).equals("UnderlinePosition")) {
                    out.write("    int underlinePosition = "
                            + list.get(1) + ";\n");
                } else if (list.get(0).equals("UnderlineThickness")) {
                    out.write("    int underlineThickness = "
                            + list.get(1) + ";\n");
                    if (generateJava) {
                        out.write("    protected int getBBoxLLx() {\n");
                        out.write("        return bBoxLLx;\n");
                        out.write("    }\n");
                        out.write("    protected int getBBoxLLy() {\n");
                        out.write("        return bBoxLLy;\n");
                        out.write("    }\n");
                        out.write("    protected int getBBoxURx() {\n");
                        out.write("        return bBoxURx;\n");
                        out.write("    }\n");
                        out.write("    protected int getBBoxURy() {\n");
                        out.write("        return bBoxURy;\n");
                        out.write("    }\n");
                        out.write("    protected int getUnderlinePosition() {\n");
                        out.write("        return underlinePosition;\n");
                        out.write("    }\n");
                        out.write("    protected int getUnderlineThickness() {\n");
                        out.write("        return underlineThickness;\n");
                        out.write("    }\n");
                    } else {
                        out.write("    internal override int getBBoxLLx() {\n");
                        out.write("        return bBoxLLx;\n");
                        out.write("    }\n");
                        out.write("    internal override int getBBoxLLy() {\n");
                        out.write("        return bBoxLLy;\n");
                        out.write("    }\n");
                        out.write("    internal override int getBBoxURx() {\n");
                        out.write("        return bBoxURx;\n");
                        out.write("    }\n");
                        out.write("    internal override int getBBoxURy() {\n");
                        out.write("        return bBoxURy;\n");
                        out.write("    }\n");
                        out.write("    internal override int getUnderlinePosition() {\n");
                        out.write("        return underlinePosition;\n");
                        out.write("    }\n");
                        out.write("    internal override int getUnderlineThickness() {\n");
                        out.write("        return underlineThickness;\n");
                        out.write("    }\n");
                    }
                } else if (list.get(0).equals("Notice")) {
                    if (generateJava) {
                        out.write("    protected static final String notice = \"");
                    } else {
                        out.write("    protected const string notice = \"");
                    }
                    out.write(line.substring(7));
                    out.write("\";\n");
                } else if (list.get(0).equals("StartCharMetrics")) {
                    out.write("    int[][] data = {\n");
                } else if (list.get(0).equals("C")) {
                    // C 95 ; WX 556 ; N underscore ; B 0 -125 556 -75 ;
                    String ch = list.get(1);
                    String wx = list.get(3);

                    if (ch.equals("32")) {
                        for (int i = 32; i < 256; i++) {
                            if (generateJava) {
                                data[i].append("        {");
                            } else {
                                data[i].append("        new int[] {");
                            }
                            data[i].append(i);
                            data[i].append(',');
                            data[i].append(wx);
                            data[i].append("},\n");
                        }
                    } else {
                        int i = Integer.parseInt(ch);
                        if (i > 0) {
                            data[i] = new StringBuffer();
                            if (generateJava) {
                                data[i].append("        {");
                            } else {
                                data[i].append("        new int[] {");
                            }
                            data[i].append(ch);
                            data[i].append(',');
                            data[i].append(wx);
                            data[i].append("},\n");
                        }
                    }
                }
            }

            for (int i = 32; i < 256; i++) {
                out.write(data[i].toString());
            }

            out.write("    };\n\n");

            if (generateJava) {
                out.write("    protected int[][] getMetrics() {\n");
            } else {
                out.write("    internal override int[][] getMetrics() {\n");
            }
            out.write("        return data;\n");
            out.write("    }\n");
            out.write("}\n");

            if (lang.equals("C#")) {
                out.write("}\n");
            }

            in.close();
            out.close();
        } catch (Exception e) {
            System.err.println(e);
        }
    }


    public static void main(String[] args) {
        File file = new File("./afm/");
        String[] fileNames = file.list(new util.AfmFilenameFilter());
        for (int i = 0; i < fileNames.length; i++) {
            if (fileNames[i].equals("Symbol.afm") ||
                    fileNames[i].equals("ZapfDingbats.afm")) {
                new AfmCompiler("./afm/", fileNames[i], "Java", "Symbol");
                new AfmCompiler("./afm/", fileNames[i], "C#", "Symbol");
            } else {
                new AfmCompiler("./afm/", fileNames[i], "Java");
                new AfmCompiler("./afm/", fileNames[i], "C#");
            }
        }
    }

}   // End of AfmCompiler.java


class AfmFilenameFilter implements FilenameFilter {

    public boolean accept(File dir, String name) {
        if (name.endsWith(".afm")) {
            return true;
        }
        return false;
    }

}   // End of AfmFilenameFilter.java
