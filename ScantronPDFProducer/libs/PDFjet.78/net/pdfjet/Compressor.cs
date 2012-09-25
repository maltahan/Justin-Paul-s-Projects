/**
 * Compressor.cs
 *
Copyright (c) 2010 Innovatics Inc.

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
public class Compressor {

    public const bool ORIGINAL_ZLIB = true;

    private MemoryStream buf1 = null;
    private OptionalDeflateStream ods1 = null;
    private DeflaterOutputStream dos1 = null;


    public Compressor(byte[] image) {

        buf1 = new MemoryStream();
        if (PDF.original_zlib) {
            ods1 = new OptionalDeflateStream(buf1);
            ods1.Write(image, 0, image.Length);
            ods1.Close();
        } else {
            dos1 = new DeflaterOutputStream(buf1, new Deflater());
            dos1.Write(image, 0, image.Length);
            dos1.Finish();
        }
    }


    public byte[] getCompressedData() {
        return buf1.ToArray();
    }

}   // End of Compressor.cs
}   // End of package PDFjet.NET
