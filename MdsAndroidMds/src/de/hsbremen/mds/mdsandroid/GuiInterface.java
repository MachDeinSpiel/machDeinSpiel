package de.hsbremen.mds.mdsandroid;

import de.hsbremen.mds.common.listener.AndroidListener;
import de.hsbremen.mds.common.valueobjects.MdsImage;
import de.hsbremen.mds.common.valueobjects.MdsMap;
import de.hsbremen.mds.common.valueobjects.MdsText;
import de.hsbremen.mds.common.valueobjects.MdsVideo;

public interface GuiInterface {
        void setAndroidListener(AndroidListener listener, double positionsIntervall);
        void nextFragment(MdsImage mds);
        void nextFragment(MdsVideo mds);
        void nextFragment(MdsText mds);
        void nextFragment(MdsMap mds);
}