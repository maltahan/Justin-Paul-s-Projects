package com.pdfjet;

import java.lang.*;

public class CP1257 {
public static int[] codes = {
0x0020,
0x20AC,
0x0020,
0x201A,
0x0020,
0x201E,
0x2026,
0x2020,
0x2021,
0x0020,
0x2030,
0x0020,
0x2039,
0x0020,
0x00A8,
0x02C7,
0x00B8,
0x0020,
0x2018,
0x2019,
0x201C,
0x201D,
0x2022,
0x2013,
0x2014,
0x0020,
0x2122,
0x0020,
0x203A,
0x0020,
0x00AF,
0x02DB,
0x0020,
0x00A0,
0x0020,
0x00A2,
0x00A3,
0x00A4,
0x0020,
0x00A6,
0x00A7,
0x00D8,
0x00A9,
0x0156,
0x00AB,
0x00AC,
0x00AD,
0x00AE,
0x00C6,
0x00B0,
0x00B1,
0x00B2,
0x00B3,
0x00B4,
0x00B5,
0x00B6,
0x00B7,
0x00F8,
0x00B9,
0x0157,
0x00BB,
0x00BC,
0x00BD,
0x00BE,
0x00E6,
0x0104,
0x012E,
0x0100,
0x0106,
0x00C4,
0x00C5,
0x0118,
0x0112,
0x010C,
0x00C9,
0x0179,
0x0116,
0x0122,
0x0136,
0x012A,
0x013B,
0x0160,
0x0143,
0x0145,
0x00D3,
0x014C,
0x00D5,
0x00D6,
0x00D7,
0x0172,
0x0141,
0x015A,
0x016A,
0x00DC,
0x017B,
0x017D,
0x00DF,
0x0105,
0x012F,
0x0101,
0x0107,
0x00E4,
0x00E5,
0x0119,
0x0113,
0x010D,
0x00E9,
0x017A,
0x0117,
0x0123,
0x0137,
0x012B,
0x013C,
0x0161,
0x0144,
0x0146,
0x00F3,
0x014D,
0x00F5,
0x00F6,
0x00F7,
0x0173,
0x0142,
0x015B,
0x016B,
0x00FC,
0x017C,
0x017E,
0x02D9,
};
public static String[] names = {
"/space",
"/Euro",
"/space",
"/quotesinglbase",
"/space",
"/quotedblbase",
"/ellipsis",
"/dagger",
"/daggerdbl",
"/space",
"/perthousand",
"/space",
"/guilsinglleft",
"/space",
"/dieresis",
"/caron",
"/cedilla",
"/space",
"/quoteleft",
"/quoteright",
"/quotedblleft",
"/quotedblright",
"/bullet",
"/endash",
"/emdash",
"/space",
"/trademark",
"/space",
"/guilsinglright",
"/space",
"/macron",
"/ogonek",
"/space",
"/space",
"/space",
"/cent",
"/sterling",
"/currency",
"/space",
"/brokenbar",
"/section",
"/Oslash",
"/copyright",
"/Rcommaaccent",
"/guillemotleft",
"/logicalnot",
"/hyphen",
"/registered",
"/AE",
"/degree",
"/plusminus",
"/twosuperior",
"/threesuperior",
"/acute",
"/mu",
"/paragraph",
"/periodcentered",
"/oslash",
"/onesuperior",
"/rcommaaccent",
"/guillemotright",
"/onequarter",
"/onehalf",
"/threequarters",
"/ae",
"/Aogonek",
"/Iogonek",
"/Amacron",
"/Cacute",
"/Adieresis",
"/Aring",
"/Eogonek",
"/Emacron",
"/Ccaron",
"/Eacute",
"/Zacute",
"/Edotaccent",
"/Gcommaaccent",
"/Kcommaaccent",
"/Imacron",
"/Lcommaaccent",
"/Scaron",
"/Nacute",
"/Ncommaaccent",
"/Oacute",
"/Omacron",
"/Otilde",
"/Odieresis",
"/multiply",
"/Uogonek",
"/Lslash",
"/Sacute",
"/Umacron",
"/Udieresis",
"/Zdotaccent",
"/Zcaron",
"/germandbls",
"/aogonek",
"/iogonek",
"/amacron",
"/cacute",
"/adieresis",
"/aring",
"/eogonek",
"/emacron",
"/ccaron",
"/eacute",
"/zacute",
"/edotaccent",
"/gcommaaccent",
"/kcommaaccent",
"/imacron",
"/lcommaaccent",
"/scaron",
"/nacute",
"/ncommaaccent",
"/oacute",
"/omacron",
"/otilde",
"/odieresis",
"/divide",
"/uogonek",
"/lslash",
"/sacute",
"/umacron",
"/udieresis",
"/zdotaccent",
"/zcaron",
"/dotaccent",
};
}
