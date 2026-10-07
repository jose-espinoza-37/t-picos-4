package pe.edu.uni.fc.cc.cc0f7.substitution.services;

import java.util.Arrays;

import static pe.edu.uni.fc.cc.cc0f7.common.Utils.switchCase;

public class SubstitutionCipher {
    private final char[] charsMap;
    private final int[] indexMap = new int [128];

    public SubstitutionCipher(char[] charsMap) {
        this.charsMap = charsMap;
        createIndexMap();
    }

    private void createIndexMap(){
        Arrays.fill(indexMap, -1);
        for (int i = 0; i < charsMap.length; i++) {
            char c = charsMap[i];
            if (c < indexMap.length) {
                indexMap[c] = i;
            } else {
                System.out.println("Caracter invalido");
            }
        }
    }

    private int indexOfCharsMap(char c) {
        if (c > indexMap.length) {
            return -1;
        }
        return indexMap[c];
    }

    public String encrypt(String plainText) {
        StringBuilder result= new StringBuilder();
        for (int i = 0; i < plainText.length(); i++) {
            char c = plainText.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    c = switchCase(c);
                    c = charsMap[c - 'a'];
                    c = switchCase(c);
                    // result.append(c);
                } else {
                    c = charsMap[c - 'a'];
                    // result.append(c);
                }
            }
            result.append(c);
        }
        return result.toString();
    }

    public String decrypt(String cipherText) {
        StringBuilder result= new StringBuilder();
        for (int i = 0; i < cipherText.length(); i++) {
            char c = cipherText.charAt(i);
            if (Character.isLetter(c)) {
                if (Character.isUpperCase(c)) {
                    c = switchCase(c);
                    c = (char)('a' + indexOfCharsMap(c));
                    c = switchCase(c);
                } else {
                    c = (char) ('a' + indexOfCharsMap(c));
                }
            }
            result.append(c);
        }
        return  result.toString();
    }
}
