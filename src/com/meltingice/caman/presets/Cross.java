package com.meltingice.caman.presets;

import com.meltingice.caman.CamanJ;
import com.meltingice.caman.CamanPreset;

/**
 * Created by MrLoyal on 8/17/2015.
 */
public class Cross implements CamanPreset {
    @Override
    public void apply(CamanJ caman) {
        caman.filter(FilterNames.EXPOSURE).set(5);
        caman.filter(FilterNames.COLORIZE).set("#e87b22").set(4);
        caman.filter(FilterNames.SEPIA).set(20);
        caman.filter(FilterNames.CHANNELS).set(3).set(1).set(8); // R-G-B order
        caman.filter(FilterNames.CURVES).set("b")
                .set(new Integer[]{0, 0})
                .set(new Integer[]{100, 150})
                .set(new Integer[]{180, 180})
                .set(new Integer[]{255, 255});
        caman.filter(FilterNames.CONTRAST).set(15);
        caman.filter(FilterNames.VIBRANCE).set(75);
        caman.filter(FilterNames.GAMMA).set(1.6);
    }
}
