package com.meltingice.caman.presets;

import com.meltingice.caman.CamanJ;
import com.meltingice.caman.CamanPreset;

/**
 * Created by MrLoyal on 8/17/2015.
 */
public class OldPaper implements CamanPreset {
    @Override
    public void apply(CamanJ caman) {
        PinHole pinHole = new PinHole();
        pinHole.apply(caman);

        caman.filter(FilterNames.NOISE).set(10);

        OrangePeel orangePeel = new OrangePeel();
        orangePeel.apply(caman);
    }
}
