package halmob.healthhub.EventListeners;

import java.util.List;

import halmob.healthhub.Models.ProspectusInfo;

/**
 * Created by gorkem on 6.12.2017.
 */

public interface ProspectusListener {
    public void prospectusRead(List<ProspectusInfo> prospectusList);
}
