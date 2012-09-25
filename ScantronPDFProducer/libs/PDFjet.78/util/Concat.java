/**
 * Concat.java
 *
Copyright (c) 2007, Innovatics Inc.

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


public class Concat {

    private List<Country> countries = new ArrayList<Country>();

    public Concat(String[] args) throws IOException {
        BufferedReader reader =
                new BufferedReader(new FileReader("data/2119rank.txt"));
        String line = null;
        while((line = reader.readLine()) != null) {
            String[] info = line.split("\t");
            if (info.length != 4) continue;
            Country country = new Country();
            country.name = info[1].trim();
            country.population = info[2].trim();
            countries.add(country);
        }
        reader.close();

        reader = new BufferedReader(new FileReader("data/2004rank.txt"));
        while((line = reader.readLine()) != null) {
            String[] info = line.split("\t");
            if (info.length != 4) continue;

            for (int i = 0; i < countries.size(); i++) {
                Country country = countries.get(i);
                if (country.name.equals(info[1].trim())) {
                    country.gdp_per_capita = info[2].trim();
                }
            }
        }
        reader.close();

        reader = new BufferedReader(new FileReader("data/2038rank.txt"));
        while((line = reader.readLine()) != null) {
            String[] info = line.split("\t");
            if (info.length != 4) continue;

            for (int i = 0; i < countries.size(); i++) {
                Country country = countries.get(i);
                if (country.name.equals(info[1].trim())) {
                    country.electricity_production = info[2].trim();
                }
            }
        }
        reader.close();

        reader = new BufferedReader(new FileReader("data/2042rank.txt"));
        while((line = reader.readLine()) != null) {
            String[] info = line.split("\t");
            if (info.length != 4) continue;

            for (int i = 0; i < countries.size(); i++) {
                Country country = countries.get(i);
                if (country.name.equals(info[1].trim())) {
                    country.electricity_consumption = info[2].trim();
                }
            }
        }
        reader.close();

        reader = new BufferedReader(new FileReader("data/2150rank.txt"));
        while((line = reader.readLine()) != null) {
            String[] info = line.split("\t");
            if (info.length != 4) continue;

            for (int i = 0; i < countries.size(); i++) {
                Country country = countries.get(i);
                if (country.name.equals(info[1].trim())) {
                    country.telephones = info[2].trim();
                }
            }
        }
        reader.close();

        reader = new BufferedReader(new FileReader("data/2151rank.txt"));
        while((line = reader.readLine()) != null) {
            String[] info = line.split("\t");
            if (info.length != 4) continue;

            for (int i = 0; i < countries.size(); i++) {
                Country country = countries.get(i);
                if (country.name.equals(info[1].trim())) {
                    country.cellphones = info[2].trim();
                }
            }
        }
        reader.close();

        reader = new BufferedReader(new FileReader("data/2184rank.txt"));
        while((line = reader.readLine()) != null) {
            String[] info = line.split("\t");
            if (info.length != 4) continue;

            for (int i = 0; i < countries.size(); i++) {
                Country country = countries.get(i);
                if (country.name.equals(info[1].trim())) {
                    country.internet_hosts = info[2].trim();
                }
            }
        }
        reader.close();

        reader = new BufferedReader(new FileReader("data/2153rank.txt"));
        while((line = reader.readLine()) != null) {
            String[] info = line.split("\t");
            if (info.length != 4) continue;

            for (int i = 0; i < countries.size(); i++) {
                Country country = countries.get(i);
                if (country.name.equals(info[1].trim())) {
                    country.internet_users = info[2].trim();
                }
            }
        }
        reader.close();


        // Write the output files
        BufferedWriter writer = null;
        for (int i = 0; i < countries.size(); i++) {
            Country country = countries.get(i);
            String country_name = country.name.replace(" ", "_");
            country_name = country_name.replace("'", "_");
            country_name = country_name.replace(",", "_");
            country_name = country_name.replace("(", "_");
            country_name = country_name.replace(")", "_");
            writer = new BufferedWriter(new FileWriter(
                    "www/country/" + country_name + ".txt"));
            writer.write(country.name);
            writer.write("\n");
            writer.write("---------------------------");
            writer.write("\n");
            writer.write("Population\t" + country.population);
            writer.write("\n");
            writer.write("Telephones\t" + country.telephones);
            writer.write("\n");
            writer.write("Cell phones\t" + country.cellphones);
            writer.write("\n");
            writer.write("Internet hosts\t" + country.internet_hosts);
            writer.write("\n");
            writer.write("Internet users\t" + country.internet_users);
            writer.write("\n");
            writer.close();

/*
            System.out.print(country.name);
            System.out.print("|");
            System.out.print(country.population);
            System.out.print("|");
            if (country.electricity_production != null) {
                System.out.print(country.electricity_production);
            }
            System.out.print("|");
            if (country.electricity_consumption != null) {
                System.out.print(country.electricity_consumption);
            }
            System.out.print("|");
            if (country.telephones != null) {
                System.out.print(country.telephones);
            }
            System.out.print("|");
            if (country.cellphones != null) {
                System.out.print(country.cellphones);
            }
            System.out.print("|");
            if (country.internet_hosts != null) {
                System.out.print(country.internet_hosts);
            }
            System.out.print("|");
            if (country.internet_users != null) {
                System.out.print(country.internet_users);
            }
            System.out.println();
*/
        }
    }


    public static void main(String[] args) {
        try {
            new Concat(args);
        } catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

}   // End of Concat.java


class Country {
    protected String name = null;                       // 2119rank.txt
    protected String population = null;                 // 2119rank.txt

    protected String gdp_per_capita = null;             // 2004rank.txt
    protected String electricity_production = null;     // 2038rank.txt
    protected String electricity_consumption = null;    // 2042rank.txt
    protected String telephones = null;                 // 2150rank.txt
    protected String cellphones = null;                 // 2151rank.txt
    protected String internet_hosts = null;             // 2184rank.txt
    protected String internet_users = null;             // 2153rank.txt
}
