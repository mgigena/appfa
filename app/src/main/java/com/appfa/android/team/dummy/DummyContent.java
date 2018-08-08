package com.appfa.android.team.dummy;

import com.appfa.android.model.dto.TeamDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<TeamDTO> ITEMS = new ArrayList<>();

    private static final int COUNT = 0;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(TeamDTO item) {
        ITEMS.add(item);
    }

    private static TeamDTO createDummyItem(int position) {
        return new TeamDTO(String.valueOf(position), "Buby's", "");
    }
}
