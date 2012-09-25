#!/bin/sh

gmcs -warn:2 net/pdfjet/*.cs -reference:System.Drawing.dll -target:library -out:PDFjet.dll

gmcs -warn:2 Example_01.cs -reference:PDFjet.dll
gmcs -warn:2 Example_02.cs -reference:PDFjet.dll
gmcs -warn:2 Example_03.cs -reference:PDFjet.dll
gmcs -warn:2 Example_04.cs -reference:PDFjet.dll
gmcs -warn:2 Example_05.cs -reference:PDFjet.dll

./Example_01.exe
./Example_02.exe
./Example_03.exe
./Example_04.exe
./Example_05.exe
