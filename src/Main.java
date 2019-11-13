import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {

        regexPeek(getHtmlSourceCode("https://www.naver.com/"));
    }

    public static String[] regexFilterAlphabet(String[] input){
        return Stream.of(input)
                .filter(x -> Pattern.matches("^[a-zA-Z]*$", x))
                .toArray(String[]::new);
    }

    public static void regexPeek(String s){
        Pattern p = Pattern.compile("<span class=\"ah_r\">(..?)</span><span class=\"ah_k\">(.*?)</span>");
        Matcher m = p.matcher(s);
        while(m.find()){
            System.out.println(m.group(1) + " : " + m.group(2));
        }
    }

    public static String getHtmlSourceCode(String url) throws IOException {
        return new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream())).lines().collect(Collectors.joining());
    }

    public static List<String> getHtmlSourceCodeByList(String url) throws IOException {
        if(!url.contains("https://") && !url.contains("http://")){
            url ="https://" + url;
        }
        return new BufferedReader(new InputStreamReader(new URL(url).openConnection().getInputStream())).lines().collect(Collectors.toList());
    }

}