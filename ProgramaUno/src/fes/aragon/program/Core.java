package fes.aragon.program;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Core {

    private List<String> sources;
    private List<Map<String,Boolean>> responses;


    public Core(List<String> sources) {
        this.sources = sources;
        this.responses = new ArrayList<>();
    }

    public List<Map<String,Boolean>> resolveSources() throws Exception {
        if (this.sources.isEmpty()) {
            throw new Exception();
        }

        this.sources.forEach(source -> {
            // initial state
            int statePairs = 0;
            int stateOdds = 0;
            int errorCol = -1;
            boolean error = false;

            for (int i = 0; i <= source.length() - 1; i++) {
                char c = source.charAt(i);

                if (c != '0' && c != '1') {
                    error = true;
                    errorCol = i;
                    break;
                }

                switch (statePairs) {
                    case 0:
                        if (c == '1') statePairs = 1;
                        else if (c == '0') statePairs = 0;
                        break;
                    case 1:
                        if (c == '1') statePairs = 0;
                        else if (c == '0') statePairs = 1;
                        break;
                }

                switch (stateOdds){
                    case 0:
                        if (c == '1') stateOdds = 0;
                        else if (c == '0') stateOdds = 1;
                        break;
                    case 1:
                        if (c == '1') stateOdds = 1;
                        else if (c == '0') stateOdds = 0;
                        break;
                }
            }

            HashMap<String,Boolean> map = new HashMap<>();

            if (statePairs == 0 && !error) {
                map.put("1 pairsTrue", true);
            } else if (statePairs != 0 || error) {
                map.put("1 pairsFalse", false);
                if (error && errorCol >= 0) {
                    System.out.printf("Invalid character at column %d", errorCol);
                }
            }

            if (stateOdds == 1 && !error) {
                map.put("0 oddsTrue", true);
            } else if (stateOdds != 1 || error) {
                map.put("0 oddsFalse", false);
                if (error && errorCol >= 0) {
                    System.out.printf("Invalid character at column %d", errorCol);
                }
            }


            this.responses.add(map);

        });

        return this.responses;
    }
}