package com.jason.common_resources.constants;

public enum DimenTypes {
    DP_sw__360(360),
    DP_sw__375(375),
    DP_sw__390(390),
    DP_sw__420(420),
    DP_sw__450(450),
    DP_sw__560(560),
    DP_sw__640(640),
    DP_sw__720(720);

    private int swWidthDp;

    private DimenTypes(int swWidthDp) {
        this.swWidthDp = swWidthDp;
    }

    public int getSwWidthDp() {
        return this.swWidthDp;
    }

    public void setSwWidthDp(int swWidthDp) {
        this.swWidthDp = swWidthDp;
    }
}

