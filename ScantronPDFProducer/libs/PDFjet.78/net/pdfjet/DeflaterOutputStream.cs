/**
 * DeflaterOutputStream.cs
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

using System;
using System.IO;
using System.IO.Compression;


namespace PDFjet.NET {
public class DeflaterOutputStream {

    private MemoryStream buf1 = null;
    private Deflater deflater = null;
    private MemoryStream buf2 = null;
    private DeflateStream ds1 = null;
    private const uint prime = 65521;
    private OptionalDeflateStream ds2 = null;


    public DeflaterOutputStream(MemoryStream buf1, Deflater deflater) {
        this.buf1 = buf1;
        this.deflater = deflater;

        if (PDF.original_zlib) {
            ds2 = new OptionalDeflateStream(buf1);
        } else {
            buf2 = new MemoryStream();
            buf2.WriteByte(0x58);   // These are the correct values for
            buf2.WriteByte(0x85);   // CMF and FLG according to Microsoft
            ds1 = new DeflateStream(buf2, CompressionMode.Compress, true);
        }
    }


    public void Write(byte[] buffer, int off, int len) {
        if (PDF.original_zlib) {
            ds2.Write(buffer, off, len);
            return;
        }

        // Compress the data in the buffer
        ds1.Write(buffer, off, len);
        ds1.Close();
        buf2.WriteTo(buf1);

        // Calculate the Adler-32 checksum
        ulong s1 = 1L;
        ulong s2 = 0L;
        for (int i = 0; i < len; i++) {
            s1 = (s1 + buffer[off + i]) % prime;
            s2 = (s2 + s1) % prime ;
        }
        appendAdler((s2 << 16) + s1);
    }


    public void Finish() {
        if (PDF.original_zlib) {
            ds2.Close();
        }
    }


    private void appendAdler(ulong adler) {
        buf1.WriteByte((byte) (adler >> 24));
        buf1.WriteByte((byte) (adler >> 16));
        buf1.WriteByte((byte) (adler >>  8));
        buf1.WriteByte((byte) (adler));
        buf1.Flush();
    }

}   // End of DeflaterOutputStream.cs
}   // End of package PDFjet.NET
