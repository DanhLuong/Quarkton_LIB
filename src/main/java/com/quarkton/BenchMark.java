package com.quarkton;

import java.util.HashMap;
import java.util.Map;

public class BenchMark {
    private final Map<String, Long> checkPoints = new HashMap<>();
    private final Map<String, Integer> sameChPName = new HashMap<>();
    private int unNamedCounter = 0;
    public BenchMark() {}
    public void track(String checkPointName) {
        if(checkPoints.containsKey(checkPointName)) {
            int count = sameChPName.get(checkPointName) + 1;
            sameChPName.put(checkPointName, count);
            checkPoints.put(checkPointName+"-"+count, System.currentTimeMillis());
        } else {
            unNamedCounter++;
            checkPoints.put(checkPointName, System.currentTimeMillis());
        }
    }

    public void track() {
        track("checkPoint-"+unNamedCounter);
    }

    public long checkInterval(String checkPointName1, String checkPointName2) {
        if(checkPointName1 == null || checkPointName2 == null) throw new IllegalArgumentException("Null check point");
        if(!checkPoints.containsKey(checkPointName1) || !checkPoints.containsKey(checkPointName2)) throw new IllegalArgumentException("check point does not exist");
        return checkPoints.get(checkPointName2) - checkPoints.get(checkPointName1);
    }
}
