

using System;
using System.Collections;using System.Collections.Generic;


namespace PDFjet.NET {
public class PDFobj {

    internal const String TYPE = "/Type";
    internal const String SUBTYPE = "/Subtype";
    internal const String FILTER = "/Filter";
    internal const String WIDTH = "/Width";
    internal const String HEIGHT = "/Height";
    internal const String COLORSPACE = "/ColorSpace";
    internal const String BITSPERCOMPONENT = "/BitsPerComponent";

    internal int offset;
    internal int number;
    public List< String > dict;

    public byte[] stream;
    internal int stream_offset;

    public byte[] data;


    public PDFobj( int offset ) {
        this.offset = offset;
        this.dict = new List< String >();
    }


    public String getValue( String key ) {

        for ( int i = 0; i < dict.Count; i++ ) {
            String token = dict[i];
            if ( token.Equals( "stream" ) ||
                    token.Equals( "endobj" ) ) {
                break;
            }
            
            if ( token.Equals( key ) ) {
                return dict[i + 1];
            }
        }

        return "";
    }


    public int getLength( List< PDFobj> objects ) {

        for ( int i = 0; i < dict.Count; i++ ) {
            String token = dict[i];
            if ( token.Equals( "/Length" ) ) {
                int number = Int32.Parse( dict[i + 1] );
                if ( dict[i + 2].Equals( "0" ) &&
                        dict[i + 3].Equals( "R" ) ) {
                    return getLength( objects, number );
                }
                else {
                    return number;
                }
            }
        }

        return 0;
    }


    public int getLength( List< PDFobj> objects, int number ) {

        for ( int i = 0; i < objects.Count; i++ ) {
            PDFobj obj = objects[i];
            if ( obj.number == number ) {
                return Int32.Parse( obj.dict[3] );
            }
        }

        return 0;
    }


    public void setStream( byte[] pdf, int length ) {
        stream = new byte[ length ];
        for ( int i = 0; i < length; i++ ) {
            stream[ i ] = pdf[ this.stream_offset + i ];
        }
    }

}
}   // End of namespace PDFjet.NET
