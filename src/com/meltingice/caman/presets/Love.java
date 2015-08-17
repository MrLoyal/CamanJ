package com.meltingice.caman.presets;

import com.meltingice.caman.CamanJ;
import com.meltingice.caman.CamanPreset;

/**
 * Created by MrLoyal on 8/17/2015.
 */
public class Love implements CamanPreset {
    @Override
    public void apply(CamanJ caman) {
        caman.filter(FilterNames.BRIGHTNESS).set(5);
        caman.filter(FilterNames.EXPOSURE).set(8);
        caman.filter(FilterNames.CONTRAST).set(4);
        caman.filter(FilterNames.COLORIZE).set("#c42007").set(30);
        caman.filter(FilterNames.VIBRANCE).set(50);
        caman.filter(FilterNames.GAMMA).set(1.3);
    }
}
