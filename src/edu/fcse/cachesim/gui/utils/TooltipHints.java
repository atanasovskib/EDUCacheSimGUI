package edu.fcse.cachesim.gui.utils;

public class TooltipHints {

    public static String AssociativityHint = "";
    public static String AssociativityLink = "";
    
    public static String CacheSizeHint = "";
    public static String CacheSizeLink = "";
    
    public static String ReplacementPolicyHint = "<html>\n"
            + "In computing, cache algorithms (also frequently called<br/>\n"
            + "replacement algorithms or <b>replacement policies</b>)<br/>\n"
            + "are optimizing instructions – algorithms – that a computer<br/>\n"
            + "program or a hardware-maintained structure can follow to<br/>\n"
            + "manage a cache of information stored on the computer.<br/>\n"
            + "<i>When the cache is full, the algorithm must choose which<br/>\n"
            + "items to discard to make room for the new ones.</i>\n"
            + "</html>";
    public static String ReplacementPolicyLink = "";
    
    public static String LineWidthHint = "";
    public static String LineWidthLink = "";
    
    public static String UIDHint = "<html><b>UID (Unique Identifier)</b><br/>"
            + "is used by EDUCacheSim to tell the diference between the<br/>"
            + "instances of Cache Levels and Cache Cores creted. <b>It is NOT<br/>"
            + "a parameter that exists in real architectures</b></html>";
    
    public static String CoreHint = "<html>To create CPU cores you must<br/>"
            + "pick a Cache Level instance from the drop down lists for<br/>"
            + "each of the levels. To simulate Cache Level sharing, pick<br/>"
            + "the same instance for multiple cores<br/></html>";
}
