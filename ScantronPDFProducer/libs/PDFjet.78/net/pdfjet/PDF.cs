/**
 *  PDF.cs
 *
Copyright (c) 2007, 2008, 2009, 2010 Innovatics Inc.

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

    * Redistributions of source code must retain the above copyright notice,
      this list of conditions and the following disclaimer.
 
    * Redistributions in binary form must reproduce the above copyright notice,
      this list of conditions and the following disclaimer in the documentation
      and / or other materials provided with the distribution.

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



using System;
using System.IO;
using System.Text;
using System.Collections;using System.Collections.Generic;
using System.IO.Compression;


namespace PDFjet.NET {
public class PDF {

    internal static bool original_zlib = false;

    private int compliance = 0;
    
	private Stream buf = null;
    internal int objNumber = 0;
    internal int metadataObjNumber = 0;
    internal int outputIntentObjNumber = 0;

    internal List<Font> fonts = new List<Font>();
    internal List<Image> images = new List<Image>();
    internal List<Page> pages = new List<Page>();

    private List<Int32> objOffset = new List<Int32>();

    private String producer = "PDFjet v2.77 (http://pdfjet.com)";
    private String creationDate;
    private String createDate;
    private String title = "";
    private String subject = "";
    private String author = "";

    private int byte_count = 0;

    private bool CR_LF = false;
    private List< PDFobj > objects = null;
    private List< PDFobj > objects2 = null;
    private List< PDFobj > objects3 = null;
    private List< PDFobj > objects4 = null;


    public PDF(Stream buf) : this(buf, 0) {}


    // Here is the layout of the PDF document:
    //
    // Metadata Object
    // Output Intent Object
    // Fonts
    // Images
    // Resources Object
    // Pages
    // Page1
    // Page2
    // ...
    // PageN
    // Info
    // Root
    // xref table
    // Trailer
    public PDF(Stream buf, int compliance) {

        this.buf = buf;
        this.compliance = compliance;

        DateTime date = new DateTime(DateTime.Now.Ticks);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss'Z'");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        creationDate = sdf1.Format(date);
        createDate = sdf2.Format(date);

        Append("%PDF-1.4\n");
        Append('%');
        Append((byte) 0x00F2);
        Append((byte) 0x00F3);
        Append((byte) 0x00F4);
        Append((byte) 0x00F5);
        Append((byte) 0x00F6);
        Append('\n');

        if (compliance == Compliance.PDF_A_1B) {
            metadataObjNumber = addMetadataObject();
            outputIntentObjNumber = addOutputIntentObject();
        }

    }


    internal void newobj() {
        objOffset.Add(byte_count);
        Append(++objNumber);
        Append(" 0 obj\n");
    }


    internal void endobj() {
        Append("endobj\n");
    }


    private int addMetadataObject() {

        StringBuilder sb = new StringBuilder();
        sb.Append("<?xpacket begin='\uFEFF' id=\"W5M0MpCehiHzreSzNTczkc9d\"?>\n");
        sb.Append("<x:xmpmeta xmlns:x=\"adobe:ns:meta/\">\n");
        sb.Append("<rdf:RDF xmlns:rdf=\"http://www.w3.org/1999/02/22-rdf-syntax-ns#\">\n");

        sb.Append("<rdf:Description rdf:about=\"\" xmlns:pdf=\"http://ns.adobe.com/pdf/1.3/\" pdf:Producer=\"");
        sb.Append(producer);
        sb.Append("\"></rdf:Description>\n");

        sb.Append("<rdf:Description rdf:about=\"\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\">\n");
        sb.Append("<dc:format>application/pdf</dc:format>\n");
        sb.Append("<dc:title><rdf:Alt><rdf:li xml:lang=\"x-default\">");
        sb.Append(title);
        sb.Append("</rdf:li></rdf:Alt></dc:title>\n");

        sb.Append("<dc:creator><rdf:Seq><rdf:li>");
        sb.Append(producer);
        sb.Append("</rdf:li></rdf:Seq></dc:creator>\n");

        sb.Append("<dc:description><rdf:Alt><rdf:li xml:lang=\"en-US\">");
        sb.Append(DroidFontsCopyright.NOTICE);
        sb.Append("</rdf:li></rdf:Alt></dc:description>\n");
/*
        sb.Append("<dc:rights><rdf:Alt><rdf:li>");
        sb.Append(DroidFontsCopyright.NOTICE);
        sb.Append("</rdf:li></rdf:Alt></dc:rights>\n");
*/
        sb.Append("</rdf:Description>\n");

        sb.Append("<rdf:Description rdf:about=\"\" xmlns:pdfaid=\"http://www.aiim.org/pdfa/ns/id/\">");
        sb.Append("<pdfaid:part>1</pdfaid:part>");
        sb.Append("<pdfaid:conformance>B</pdfaid:conformance>");
        sb.Append("</rdf:Description>");

        sb.Append("<rdf:Description rdf:about=\"\" xmlns:xmp=\"http://ns.adobe.com/xap/1.0/\">\n");
        sb.Append("<xmp:CreateDate>");
        sb.Append(createDate);
        sb.Append("</xmp:CreateDate>\n");
        sb.Append("</rdf:Description>\n");
        sb.Append("</rdf:RDF>\n");
        sb.Append("</x:xmpmeta>\n");

        // Add the recommended 2000 bytes padding
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                sb.Append("          ");
            }
            sb.Append("\n");
        }

        sb.Append("<?xpacket end=\"w\"?>");

        byte[] xml = (new System.Text.UTF8Encoding()).GetBytes(sb.ToString());

        // This is the metadata object
        newobj();
        Append("<<\n");
        Append("/Type /Metadata\n");
        Append("/Subtype /XML\n");
        Append("/Length ");
        Append(xml.Length);
        Append("\n");
        Append(">>\n");
        Append("stream\n");
        for (int i = 0; i < xml.Length; i++) {
            Append(xml[i]);
        }
        Append("\nendstream\n");
        endobj();

        return objNumber;
    }


    internal int addOutputIntentObject() {

        MemoryStream baos = new MemoryStream();
        BufferedStream bis = new BufferedStream(
                new FileStream("icc-profiles/sRGB_IEC61966-2-1_black_scaled.icc", FileMode.Open));
        int ch;
        while ((ch = bis.ReadByte()) != -1) {
            baos.WriteByte((byte) ch);
        }
        bis.Close();

        byte[] sRGB = baos.ToArray();

        MemoryStream baos2 = new MemoryStream();
        DeflaterOutputStream dos =
                new DeflaterOutputStream(baos2, new Deflater());
        dos.Write(sRGB, 0, sRGB.Length);
        dos.Finish();

        newobj();
        Append("<<\n");
        Append("/N 3\n");

        Append("/Length ");
        Append(baos2.Length);
        Append("\n");

        Append("/Filter /FlateDecode\n");
        Append(">>\n");
        Append("stream\n");
        Append(baos2);
        Append("\nendstream\n");
        endobj();

        // OutputIntent object
        newobj();
        Append("<<\n");
        Append("/Type /OutputIntent\n");
        Append("/S /GTS_PDFA1\n");
        Append("/OutputCondition (sRGB IEC61966-2.1)\n");
        Append("/OutputConditionIdentifier (sRGB IEC61966-2.1)\n");
        Append("/Info (sRGB IEC61966-2.1)\n");
        Append("/DestOutputProfile ");
        Append(objNumber - 1);
        Append(" 0 R\n");
        Append(">>\n");
        endobj();

        return objNumber;
    }


    private int addResourcesObject() {

        newobj();
        Append("<<\n");
        Append("/Font\n");
        Append("<<\n");
        for (int i = 0; i < fonts.Count; i++) {
            Font font = fonts[i];
            Append("/F");
            Append(font.objNumber);
            Append(" ");
            Append(font.objNumber);
            Append(" 0 R\n");
        }
        Append(">>\n");
        Append("/XObject\n");
        Append("<<\n");
        for (int i = 0; i < images.Count; i++) {
            Image image = images[i];
            Append("/Im");
            Append(image.objNumber);
            Append(" ");
            Append(image.objNumber);
            Append(" 0 R\n");
        }
        Append(">>\n");
        Append(">>\n");
        endobj();
        return objNumber;
    }


    internal int addPagesObject() {
        newobj();
        Append("<<\n");
        Append("/Type /Pages\n");
        Append("/Kids [ ");
        int pageObjNumber = objNumber + 1;
        for (int i = 0; i < pages.Count; i++) {
            Page page = pages[i];
            Append(pageObjNumber);
            Append(" 0 R ");
            pageObjNumber += 2;
            pageObjNumber += page.annots.Count;
        }
        Append("]\n");
        Append("/Count ");
        Append(pages.Count);
        Append('\n');
        Append(">>\n");
        endobj();
        return objNumber;
    }


    internal int addInfoObject() {
        // This is the info object
        newobj();
        Append("<<\n");
        Append("/Title (");
        Append(title);
        Append(")\n");
        Append("/Subject (");
        Append(subject);
        Append(")\n");
        Append("/Author (");
        Append(author);
        Append(")\n");
        Append("/Producer (");
        Append(producer);
        Append(")\n");

        if (compliance != Compliance.PDF_A_1B) {
            Append("/CreationDate (D:");
            Append(creationDate);
            Append(")\n");
        }

        Append(">>\n");
        endobj();
        return objNumber;
    }


    internal void addAllPages(int pagesObjNumber, int resObjNumber) {
        for (int i = 0; i < pages.Count; i++) {
            Page page = pages[i];

            // Page object
            newobj();
            Append("<<\n");
            Append("/Type /Page\n");
            Append("/Parent ");
            Append(pagesObjNumber);
            Append(" 0 R\n");
            Append("/MediaBox [0.0 0.0 ");
            Append(page.width);
            Append(" ");
            Append(page.height);
            Append("]\n");
            Append("/Resources ");
            Append(resObjNumber);
            Append(" 0 R\n");
            Append("/Contents ");
            Append(objNumber + 1);
            Append(" 0 R\n");
            if (page.annots.Count > 0) {
                Append("/Annots [ ");
                for (int j = 0; j < page.annots.Count; j++) {
                    Append(objNumber + 2 + j);
                    Append(" 0 R ");
                }
                Append("]\n");
            }
            Append(">>\n");
            endobj();

            MemoryStream baos = new MemoryStream();
            DeflaterOutputStream dos =
                    new DeflaterOutputStream(baos, new Deflater());
            dos.Write(page.buf.ToArray(), 0, page.buf.ToArray().Length);
            dos.Finish();

            // Page contents
            newobj();
            Append("<<\n");
            Append("/Filter /FlateDecode\n");
            Append("/Length ");
            Append(baos.Length);
            Append("\n");
            Append(">>\n");
            Append("stream\n");
            Append(baos);
            Append("\nendstream\n");
            endobj();

            addAnnotDictionaries(page);
        }
    }


    internal void addAnnotDictionaries(Page page) {
        for (int i = 0; i < page.annots.Count; i++) {
            Annotation annot = page.annots[i];
            newobj();
            Append("<<\n");
            Append("/Type /Annot\n");
            Append("/Subtype /Link\n");
            Append("/Rect [");
            Append(annot.x1);
            Append(' ');
            Append(annot.y1);
            Append(' ');
            Append(annot.x2);
            Append(' ');
            Append(annot.y2);
            Append("]\n");
            Append("/Border[0 0 0]\n");
            Append("/F 4\n");
            Append("/A <<\n");
            Append("/S /URI\n");
            Append("/URI (");
            Append(annot.uri);
            Append(")\n");
            Append(">>\n");
            Append(">>\n");
            endobj();
        }
    }


    public void Flush() {

        int resObjNumber = addResourcesObject();
        int infoObjNumber = addInfoObject();
        int pagesObjNumber = addPagesObject();
        addAllPages(pagesObjNumber, resObjNumber);

        // This is the root object
        newobj();
        Append("<<\n");
        Append("/Type /Catalog\n");

        Append("/Pages ");
        Append(pagesObjNumber);
        Append(" 0 R\n");

        if (compliance == Compliance.PDF_A_1B) {
            Append("/Metadata ");
            Append(metadataObjNumber);
            Append(" 0 R\n");

            Append("/OutputIntents [");
            Append(outputIntentObjNumber);
            Append(" 0 R]\n");
        }

        Append(">>\n");
        endobj();

        int startxref = byte_count;
        // Create the xref table
        Append("xref\n");
        Append("0 ");
        Append(objNumber + 1);
        Append('\n');
        Append("0000000000 65535 f \n");
        for (int i = 0; i < objOffset.Count; i++) {
            int offset = objOffset[i];
            String str = offset.ToString();
            for (int j = 0; j < 10 - str.Length; j++) {
                Append('0');
            }
            Append(str);
            Append(" 00000 n \n");
        }
        Append("trailer\n");
        Append("<<\n");
        Append("/Size ");
        Append(objNumber + 1);
        Append('\n');

        String id = (new Salsa20()).getID();
        Append("/ID[<");
        Append(id);
        Append("><");
        Append(id);
        Append(">]\n");

        Append("/Root ");
        Append(objNumber);
        Append(" 0 R\n");
        Append("/Info ");
        Append(infoObjNumber);
        Append(" 0 R\n");
        Append(">>\n");
        Append("startxref\n");
        Append(startxref);
        Append('\n');
        Append("%%EOF\n");
    }


    public void SetTitle(String title) {
        this.title = title;
    }


    public void SetSubject(String subject) {
        this.subject = subject;
    }


    public void SetAuthor(String author) {
        this.author = author;
    }


    internal void Append(int num) {
        Append(num.ToString());
    }


    internal void Append(double val) {
        Append(val.ToString().Replace(',', '.'));
    }


    internal void Append(String str) {
        int len = str.Length;
        for (int i = 0; i < len; i++) {
            buf.WriteByte((byte) str[i]);
        }
        byte_count += len;
    }


    internal void Append(char ch) {
        Append((byte) ch);
    }


    internal void Append(byte b) {
        buf.WriteByte(b);
        byte_count += 1;
    }


    internal void Append(byte[] buf2, int off, int len) {
        buf.Write(buf2, off, len);
        byte_count += len;
    }


    internal void Append(MemoryStream baos) {
        baos.WriteTo(buf);
        byte_count += (int) baos.Length;
    }


    public void setCompressor(bool original_zlib) {
        PDF.original_zlib = original_zlib;
    }


    private int indexOfStartXRef(byte[] buf) {
        for (int i = ( buf.Length - 10 ); i >= 0; i--) {
            if ( buf[i] == 's' &&
                    buf[i + 1] == 't' &&
                    buf[i + 2] == 'a' &&
                    buf[i + 3] == 'r' &&
                    buf[i + 4] == 't' &&
                    buf[i + 5] == 'x' &&
                    buf[i + 6] == 'r' &&
                    buf[i + 7] == 'e' &&
                    buf[i + 8] == 'f' ) {
                return i;
            }
        }
        return -1;
    }


    public List< PDFobj > read(Stream inputStream) {

        MemoryStream baos = new MemoryStream();
        int ch;
        while ( ( ch = inputStream.ReadByte() ) != -1 ) {
            baos.WriteByte((byte) ch);
        }

        byte[] pdf = baos.ToArray();

        int startxref = indexOfStartXRef(pdf);

        if ( pdf[ startxref + 9 ] == 0x0D &&
                pdf[ startxref + 10 ] == 0x0A ) {
            // Console.WriteLine( "This PDF file is using CR/LF for EOL." );
            CR_LF = true;
        }

        objects = new List< PDFobj >();

        PDFobj obj = getObject( pdf, startxref, pdf.Length );

        String xref = obj.dict[1];

        obj = getObject( pdf, Int32.Parse( xref ), pdf.Length );

        if ( obj.dict[0].Equals( "xref" ) ) {
            for ( int i = 3; i < obj.dict.Count; i += 3 ) {
                String token = obj.dict[i];
                if ( !token.Equals( "trailer" ) ) {
                    int off = Int32.Parse( token );
                    if ( off != 0 ) {
                        objects.Add( getObject( pdf, off, pdf.Length ) );
                    }
                }
            }
        }
        else {
            getObjOffsets( pdf, xref );
        }

        for ( int i = 0; i < objects.Count; i++ ) {
            obj = objects[i];
            int offset = obj.dict.Count - 1;
            if ( obj.dict[offset].Equals( "stream" ) ) {
                obj.setStream( pdf, obj.getLength( objects ) );
            }
        }

        objects2 = new List< PDFobj >();

        for ( int i = 0; i < objects.Count; i++ ) {
            obj = objects[i];
            if ( obj.getValue( "/Type" ).Equals( "/XRef" ) ||
                    !obj.getValue( "/S" ).Equals( "" ) ) {  // Hint Table
                continue;
            }

            if ( obj.getValue( "/Type" ).Equals( "/XObject" ) ||
                    obj.getValue( "/Subtype" ).Equals( "/Type1C" ) ||
                    obj.getValue( "/Subtype" ).Equals( "/CIDFontType0C" ) ) {
                objects2.Add( obj );
                continue;
            }

            if ( obj.getValue( "/Filter" ).Equals( "/FlateDecode" ) ) {
                Decompressor decompressor = new Decompressor(obj.stream);
                obj.data = decompressor.getDecompressedData();
                objects2.Add( obj );
            }
        }


        objects3 = new List< PDFobj >();

        for ( int i = 0; i < objects2.Count; i++ ) {
            obj = objects2[i];
            if ( obj.getValue( "/Type" ).Equals( "/ObjStm" ) ) {
                int first = Int32.Parse( obj.getValue( "/First" ) );
                int n = Int32.Parse( obj.getValue( "/N" ) );
                PDFobj o2 = getObject( obj.data, 0, first );
                for ( int j = 0; j < o2.dict.Count; j += 2 ) {
                    int num = Int32.Parse( o2.dict[j] );
                    int off = Int32.Parse( o2.dict[j + 1] );
                    int end = obj.data.Length;
                    if ( j <= o2.dict.Count - 4 ) {
                        end = first + Int32.Parse( o2.dict[j + 3] );
                    }

                    PDFobj o3 = getObject( obj.data, first + off, end );
                    o3.dict.Insert(0, "obj");
                    o3.dict.Insert(0, "0");
                    o3.dict.Insert(0, num.ToString());
                    objects3.Add( o3 );
                }
            }
            else {
                objects3.Add( obj );
            }
        }


        objects4 = new List< PDFobj >();

        for ( int i = 0; i < objects.Count; i++ ) {
            obj = objects[i];
            if ( !obj.getValue( "/Type" ).Equals( "/ObjStm" ) &&
                    !obj.getValue( "/Type" ).Equals( "/XRef" ) ) {
                obj.number = Int32.Parse( obj.dict[0] );
                objects4.Add(obj);
            }
        }

        for ( int i = 0; i < objects3.Count; i++ ) {
            obj = objects3[i];
            obj.number = Int32.Parse( obj.dict[0] );
            objects4.Add(obj);
        }

        return objects4;
    }


    private bool Append(PDFobj obj, StringBuilder sb, int offset) {
        String token = sb.ToString().Trim();
        if ( !token.Equals( "" ) ) {
            obj.dict.Add( token );
        }
        sb.Length = 0;
        if ( token.Equals( "stream" ) ||
                token.Equals( "endobj" ) ||
                token.Equals( "trailer" ) ) {
            if ( token.Equals( "stream" ) ) {
                if ( CR_LF ) {
                    obj.stream_offset = offset + 1;
                }
                else {
                    obj.stream_offset = offset;
                }
            }
            return false;
        }
        return true;
    }


    private PDFobj getObject( byte[] buf, int off, int end ) {

        PDFobj obj = new PDFobj( off );

        StringBuilder sb = new StringBuilder();

        int n = 0;
        char c1 = ' ';
        while ( true ) {
            if ( off == end ) {
                Append( obj, sb, off );
                break;
            }

            char c2 = (char) buf[off++];
            if ( c2 == '(' ) {
                if ( n == 0 ) {
                    if ( !Append( obj, sb, off ) ) break;
                }
                sb.Append( c2 );
                ++n;
            }
            else if ( c2 == ')' ) {
                sb.Append( c2 );
                --n;
                if ( n == 0 ) {
                    if ( !Append( obj, sb, off ) ) break;
                }
            }
            else if ( n > 0 ) {
                sb.Append( c2 );
            }
            else if (  c2 == 0x00       // Null
                    || c2 == 0x09       // Horizontal Tab
                    || c2 == 0x0A       // Line Feed (LF)
                    || c2 == 0x0C       // Form Feed
                    || c2 == 0x0D       // Carriage Return (CR)
                    || c2 == 0x20 ) {   // Space
                if ( !Append( obj, sb, off ) ) break;
                c1 = ' ';
            }
            else if ( c2 == '/' ) {
                if ( !Append( obj, sb, off ) ) break;
                sb.Append( c2 );
                c1 = c2;
            }
            else if ( c2 == '<' || c2 == '>' ) {
                if ( c2 != c1 ) {
                    if ( !Append( obj, sb, off ) ) break;
                    sb.Append( c2 );
                    c1 = c2;
                }
                else {
                    sb.Append( c2 );
                    if ( !Append( obj, sb, off ) ) break;
                    c1 = ' ';
                }
            }
            else if ( c2 == '[' || c2 == ']' ) {
                if ( !Append( obj, sb, off ) ) break;
                obj.dict.Add( c2.ToString() );
                c1 = c2;
            }
            else if ( c2 == '{' || c2 == '}' ) {
                if ( !Append( obj, sb, off ) ) break;
                obj.dict.Add( c2.ToString() );
                c1 = c2;
            }
            else if ( c2 == '%' ) {
                if ( c2 != c1 ) {
                    if ( !Append( obj, sb, off ) ) break;
                    sb.Append( c2 );
                    c1 = c2;
                }
                else {
                    sb.Append( c2 );
                    if ( !Append( obj, sb, off ) ) break;
                    c1 = ' ';
                }
            }
            else {
                sb.Append( c2 );
                c1 = c2;
            }

        }

        return obj;
    }


    /**
     * Converts an array of bytes to an integer.
     * @param buf byte[]
     * @return int
     */
    public int toInt( byte[] buf, int off, int len ) {
        int i = 0;
        for ( int j = 0; j < len; j++ ) {
            i |= buf[off + j] & 0xFF;
            if ( j < len - 1 ) {
                i <<= 8;
            }
        }
        return i;
    }


    private void getObjOffsets( byte[] pdf, String xref ) {

        PDFobj obj = getObject( pdf, Int32.Parse( xref ), pdf.Length );
        obj.setStream( pdf, obj.getLength( null ) );

        xref = obj.getValue( "/Prev" );
        if ( !xref.Equals( "" ) ) {
            getObjOffsets( pdf, xref );
        }

        Decompressor decompressor = new Decompressor(obj.stream);
        byte[] data = decompressor.getDecompressedData();

        int p1 = 0; // Predictor byte
        int f1 = 0; // Field 1
        int f2 = 0; // Field 2
        int f3 = 0; // Field 3
        for ( int i = 0; i < obj.dict.Count; i++ ) {
            String token = obj.dict[i];
            if ( token.Equals( "/Predictor" ) ) {
                if ( obj.dict[i + 1].Equals("12") ) {
                    p1 = 1;
                }
                else {
                    // TODO:
                }
            }

            if ( token.Equals( "/W" ) ) {
                // "/W [ 1 3 1 ]"
                f1 = Int32.Parse(obj.dict[i + 2]);
                f2 = Int32.Parse(obj.dict[i + 3]);
                f3 = Int32.Parse(obj.dict[i + 4]);
            }

        }

        int n = p1 + f1 + f2 + f3;   // Number of bytes per entry

        byte[] entry = new byte[n];

        for ( int i = 0; i < data.Length; i += n ) {

            // Apply the 'Up' filter.
            for ( int j = 0; j < n; j++ ) {
                entry[j] += data[i + j];
            }

            if ( entry[1] == 0x01 ) {
                int off = toInt( entry, p1 + f1, f2 );
                objects.Add( getObject( pdf, off, pdf.Length ) );
            }

        }

    }

}   // End of PDF.cs
}   // End of namespace PDFjet.NET
