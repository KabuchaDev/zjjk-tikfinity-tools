package vn.local.jjkarena;

import java.util.Map;

/** Per-unit gift value in the configured roster, independent of team or combo size. */
final class CameraPriority {
    private static final Map<String,Integer> COINS=Map.ofEntries(
        Map.entry("miwa",0),Map.entry("nobara",0),Map.entry("yuji_school",1),
        Map.entry("inumaki",5),Map.entry("panda",20),Map.entry("todo",30),
        Map.entry("gojo_school",99),Map.entry("hakari",100),Map.entry("maki_culling",149),
        Map.entry("jogo",149),Map.entry("yuji_shinjuku",199),Map.entry("gojo",249),
        Map.entry("sukuna_itadori",249),Map.entry("sukuna_megumi",299),Map.entry("sukuna_heian",349),
        Map.entry("basic",0),Map.entry("elite",0),Map.entry("boss",249));
    static int coins(String kind) {return COINS.getOrDefault(kind,0);}
    static boolean outranks(int coins,long order,int otherCoins,long otherOrder) {
        return coins>otherCoins||(coins==otherCoins&&order>otherOrder);
    }
}
