import java.io.IOException;
import java.net.URI;
import java.util.*;

class SearchHandler implements URLHandler {
    
    ArrayList<String> words = new ArrayList<>();

    public String handleRequest(URI url) {
        String path = url.getPath();
        if(path.contains("/add")) {
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")) {
                words.add(parameters[1]);
                return "Added " + parameters[1];
            }
        }
        else if(path.contains("/search")) {
            String toRet = "";
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")) {
                String key = parameters[1];
                for(String word : words) {
                    if(word.contains(key)) {
                        toRet += (word + "\n");
                    }
                }
            }
            return toRet;
        }

        return "Welcome to Generic Search Engine (TM)!";
    }
}

public class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new SearchHandler());
    }
}
