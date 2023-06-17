package cat.urv.deim.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import cat.urv.deim.models.HashMapTitols;
import cat.urv.deim.models.ITitols;


public class FileLoaderHashTitol {
    public static void carregarFitxerTitols(String path, ITitols llista) {
        BufferedReader br = null;
        try {
            File file = new File(path);
            br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                String[] params = st.split(",");
                if (params.length != 3) {
                    continue;
                }
                String idP = params[0];
                String titol = params[2];
                llista.inserir(idP, titol);
            }
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
        if (br != null) {
            try {
                br.close();
            } catch (IOException ex) {

            }
        }        
    }

    public static void main(String[] args) {
        ITitols titols = new HashMapTitols();
        FileLoaderHashTitol.carregarFitxerTitols("movie_titles.csv", titols);
    }
}

 
