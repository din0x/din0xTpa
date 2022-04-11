package me.din0x.din0xTpa.storage;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public abstract class TpaRequestManager {

    public static ArrayList<TpaRequest> Requests = new ArrayList<>(32);

    public static void NewRequest(Player CmdUser, Player Target){

        for (TpaRequest r : Requests) {
            if (r.CmdUser == CmdUser && r.Target == Target) {
                r.Time = 6000;
            }else{
            }
        }

        Requests.add(new TpaRequest(CmdUser, Target));
    }

    public static String SearchReq(Player CmdUser, Player Target) {
        for (TpaRequest r:
                Requests) {

            if (r.CmdUser == CmdUser && r.Target == Target) {
                if (r.Time > 0) {
                    r.Time = -1;
                    return "Success";
                }else {
                    return "Expired";}
            }
        }
        return "NoReq";
    }

    public static void UpdateTime(int ticks) {
        for (TpaRequest r:
                Requests) {

            r.Time = r.Time - ticks;
        }
    }
}
