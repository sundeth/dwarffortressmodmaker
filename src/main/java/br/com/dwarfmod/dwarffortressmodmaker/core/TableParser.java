/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.core;

import br.com.dwarfmod.dwarffortressmodmaker.data.RawFileTypeEnum;
import br.com.dwarfmod.dwarffortressmodmaker.data.library.Token;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ander
 */
public class TableParser {

    public static void main(String[] args) throws IOException {
        final RawFileTypeEnum testTarget = RawFileTypeEnum.BIOME;
        List<Token> tokens = parseHtmlForTokens(testTarget, testTarget.getUrl(), testTarget.getTitles());
        for (Token token : tokens) {
            System.out.println(token);
        }
    }

    public static List<Token> parseHtmlForTokens(RawFileTypeEnum type, String siteUrl, String... titles) throws IOException {
        List<Token> tokens = new ArrayList<>();

        if (type == RawFileTypeEnum.BODYGLOSS) {
            final Token token = Token.builder().token("BODYGLOSS")
                    .arguments("NAME:target_singular:replacement_singular:target_plural:replacement_singular")
                    .description("A bodygloss is a token in the raw body tokens and creature tokens which performs a simple word substitution on the creature's body parts")
                    .build();
            tokens.add(token);
            return tokens;
        }
        
        
        Document doc = Jsoup.connect(siteUrl).get();
        Elements tables = doc.select("table");

        for (Element table : tables) {
            int skipColumn = 0;
            Elements rows = table.select("tr");
            for (int i = 1; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements columns = row.select("td");

                String tokenName = null;
                String tokenArgs = null;
                String tokenDesc = null;
                String tokenCont = null;
                
                if (i == 1 && columns.size() > 1 && columns.get(0).text().equals("0")) {
                    skipColumn = 1;
                }
                
                if (columns.size() > 0 && columns.get(0).text().equals("LIQUID")) {
                    System.out.println("br.com.dwarfmod.dwarffortressmodmaker.core.TableParser.parseHtmlForTokens()");
                }
                
                if (type == RawFileTypeEnum.MATERIAL_DEFINITION && columns.size() == 1) {
                    tokenName = columns.get(0 + skipColumn).text();
                    if (Arrays.asList(titles).contains(tokenName)) {
                        Element row2 = rows.get(0);
                        Elements columns2 = row2.select("td");
                        final Token token = Token.builder().token(columns2.get(0).text()).type(type).build();
                        tokens.add(token);
                    }
                } else if (type == RawFileTypeEnum.PERSONALITY_TRAIT && columns.size() > 0) {
                    Elements columns2 = row.select("th");
                    if (columns2.size() == 1) {
                        tokenName = columns2.get(0).text();
                    } else if (tokens.size() > 0) {
                        tokenName = tokens.get(tokens.size()-1).getToken();
                    } else {
                        continue;
                    }
                    tokenArgs = columns.get(0 + skipColumn).text();
                    if (columns.size() > 1) {
                        tokenDesc = columns.get(1 + skipColumn).text();
                    }
                    
                } else if (columns.size() - skipColumn == 2) {
                    tokenName = columns.get(0 + skipColumn).text();
                    tokenDesc = columns.get(1 + skipColumn).text();
                } else if (columns.size() - skipColumn == 3) {
                    tokenName = columns.get(0 + skipColumn).text();
                    tokenArgs = columns.get(1 + skipColumn).text();
                    tokenDesc = columns.get(2 + skipColumn).text();
                } else if (columns.size() - skipColumn == 4) {
                    tokenName = columns.get(0 + skipColumn).text();
                    tokenCont = columns.get(1 + skipColumn).text();
                    tokenArgs = columns.get(2 + skipColumn).text();
                    tokenDesc = columns.get(3 + skipColumn).text();
                } else if (columns.size()- skipColumn > 4) {
                    tokenName = columns.get(0 + skipColumn).text();
                    tokenCont = columns.get(1 + skipColumn).text();
                    tokenDesc = columns.get(2 + skipColumn).text();
                }
                
                if (i == 1 && titles.length > 0) {
                    if (type == RawFileTypeEnum.ITEM_FOOD || type == RawFileTypeEnum.ITEM_SIEGEAMMO || type == RawFileTypeEnum.ITEM_TOY) {
                        Element row2 = rows.get(2);
                        Elements columns2 = row2.select("td");
                        if (!Arrays.asList(titles).contains(columns2.get(0).text())) {
                            break;
                        }
                    } else if (type == RawFileTypeEnum.TISSUE_TEMPLATE) {
                        if (!Arrays.asList(titles).contains(tokenCont)) {
                            break;// skip this table if it doesn't match any of the given titles
                        }
                    } else if (!Arrays.asList(titles).contains(tokenName)) {
                        break;// skip this table if it doesn't match any of the given titles
                    }
                }
                
                
                Token token = Token.builder()
                        .type(type)
                        .token(tokenName)
                        .arguments(tokenArgs)
                        .description(tokenDesc)
                        .build();

                if (token.getToken() != null) {
                    tokens.add(token);
                }
            }
        }

        return tokens;
    }
}
