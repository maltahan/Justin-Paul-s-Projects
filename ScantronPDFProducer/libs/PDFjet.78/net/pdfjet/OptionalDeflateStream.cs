/**
 * OptionalDeflateStream.cs
 *
Copyright (c) 2007, 2008, 2009 Innovatics Inc.

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
using System.Runtime.InteropServices;


namespace PDFjet.NET {
public class OptionalDeflateStream {

    [DllImport("zlibwapi.dll", CallingConvention=CallingConvention.Winapi)]
    static extern int compress(
            byte[] compr,
            ref ulong comprLength,
            byte[] uncompr,
            ulong uncomprLength);

    MemoryStream buf1 = null;
    MemoryStream buf2 = null;


    public OptionalDeflateStream(MemoryStream buf1) {
        this.buf1 = buf1;
        this.buf2 = new MemoryStream();
    }


    public void Write(byte[] buffer, int off, int len) {
        buf2.Write(buffer, off, len);
    }


    public void Write(char[] buffer) {
        for (int i = 0; i < buffer.Length; i++) {
            buf2.WriteByte((byte) buffer[i]);
        }
    }


    public void Close() {
        byte[] uncompr = buf2.ToArray();
        ulong comprLength = (ulong) (uncompr.Length + uncompr.Length/100 + 12);
        byte[] compr = new byte[comprLength];
        int status = (int) compress(
                compr, ref comprLength, uncompr, (ulong) uncompr.Length);
        if (status == -1) {
            throw new Exception("Z_ERRNO");
        } else if (status == -2) {
            throw new Exception("Z_STREAM_ERROR");
        } else if (status == -3) {
            throw new Exception("Z_DATA_ERROR");
        } else if (status == -4) {
            throw new Exception("Z_MEM_ERROR");
        } else if (status == -5) {
            throw new Exception("Z_BUF_ERROR");
        } else if (status == -6) {
            throw new Exception("Z_VERSION_ERROR");
        }
        buf1.Write(compr, 0, (int) comprLength);
    }

}   // End of OptionalDeflateStream.cs
}   // End of package PDFjet.NET
