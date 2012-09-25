java util.ToCSharp com/pdfjet/

csc /out:PDFjet.dll /target:library net\pdfjet\*.cs

csc /out:Example_01.exe /target:exe Example_01.cs /reference:PDFjet.dll
csc /out:Example_02.exe /target:exe Example_02.cs /reference:PDFjet.dll
csc /out:Example_03.exe /target:exe Example_03.cs /reference:PDFjet.dll
csc /out:Example_04.exe /target:exe Example_04.cs /reference:PDFjet.dll
csc /out:Example_05.exe /target:exe Example_05.cs /reference:PDFjet.dll
csc /out:Example_08.exe /target:exe Example_08.cs /reference:PDFjet.dll
csc /out:Example_13.exe /target:exe Example_13.cs /reference:PDFjet.dll
csc /out:Example_19.exe /target:exe Example_19.cs /reference:PDFjet.dll

Example_01
Example_02
Example_03
Example_04
Example_05
Example_08
Example_13
REM Example_19
