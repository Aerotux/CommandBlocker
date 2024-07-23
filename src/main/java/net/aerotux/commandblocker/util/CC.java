package net.aerotux.commandblocker.util;

import com.google.common.collect.Lists;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.apache.commons.lang3.StringEscapeUtils;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("deprecation")
public class CC
{
    public static String translate(String message)
    {
        Pattern pattern = Pattern.compile("#[a-fA-F0-9]{6}");

        if (message == null)
        {
            return "";
        }

        for(Matcher matcher = pattern.matcher(message); matcher.find(); matcher = pattern.matcher(message))
        {
            String hexCode = message.substring(matcher.start(), matcher.end());
            String replaceSharp = hexCode.replace('#', 'x');
            char[] ch = replaceSharp.toCharArray();
            StringBuilder builder = new StringBuilder();

            for(char c : ch){
                builder.append("&").append(c);
            }

            message = message.replace(hexCode, builder.toString());
        }


        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static Component translateComponent(String message){
        return LegacyComponentSerializer.legacy(LegacyComponentSerializer.SECTION_CHAR).deserialize(translate(message));
    }

    public static String getHexColor(String minecraftColorCode){
        char firstChar = minecraftColorCode.substring(1).toCharArray()[0];
        return switch (firstChar){
            case '0' -> "#000000";  //Black
            case '1' -> "#0000AA";  //Dark Blue
            case '2' -> "#00AA00";  //Dark Green
            case '3' -> "#00AAAA";  //Dark Aqua
            case '4' -> "#AA0000";  //Dark Red
            case '5' -> "#AA00AA";  //Dark Purple
            case '6' -> "#FFAA00";  //Gold
            case '7' -> "#AAAAAA";  //Gray
            case '8' -> "#555555";  //Dark Gray
            case '9' -> "#5555FF";  //Blue
            case 'a' -> "#55FF55";  //Green
            case 'b' -> "#55FFFF";  //Aqua
            case 'c' -> "#FF5555";  //Red
            case 'd' -> "#FF55FF";  //Light Purple
            case 'e' -> "#FFFF55";  //Yellow
            default -> "#FFFFFF";
        };
    }

    public static Component component(String message)
    {
        return MiniMessage.miniMessage().deserialize(message)
                .decoration(TextDecoration.ITALIC, TextDecoration.State.FALSE);
    }

    public static List<Component> component(List<String> list)
    {
        return list.stream()
                .map(CC::component)
                .toList();
    }

    public static List<String> formatList(List<String> strings)
    {
        int index = 0;
        for(String s : strings)
        {
            strings.set(index++, translate(s));
        }
        return strings;
    }

    public static List reverseArrayList(List list)
    {
        List revArrayList = Lists.newArrayList();
        for (int i = list.size() - 1; i >= 0; i--)
        {
            revArrayList.add(list.get(i));
        }
        return revArrayList;
    }

    public static short getWoolColor(String str)
    {
        if(str.contains("&1") || str.contains("&9")) return 11;
        if(str.contains("&2")) return 13;
        if(str.contains("&3")) return 9;
        if(str.contains("&4") || str.contains("&c")) return 14;
        if(str.contains("&5")) return 10;
        if(str.contains("&6")) return 1;
        if(str.contains("&7")) return 8;
        if(str.contains("&8")) return 7;
        if(str.contains("&a")) return 5;
        if(str.contains("&b")) return 3;
        if(str.contains("&d")) return 6;
        if(str.contains("&e")) return 4;
        return 0;
    }

    public static DyeColor getDyeColor(String str)
    {
        if(str.contains("&1") || str.contains("&9")) return DyeColor.BLUE;
        if(str.contains("&2")) return DyeColor.GREEN;
        if(str.contains("&3")) return DyeColor.CYAN;
        if(str.contains("&4") || str.contains("&c")) return DyeColor.RED;
        if(str.contains("&5")) return DyeColor.PURPLE;
        if(str.contains("&6")) return DyeColor.ORANGE;
        if(str.contains("&7")) return DyeColor.GRAY;
        if(str.contains("&8")) return DyeColor.LIGHT_GRAY;
        if(str.contains("&a")) return DyeColor.LIME;
        if(str.contains("&b")) return DyeColor.LIGHT_BLUE;
        if(str.contains("&d")) return DyeColor.PINK;
        if(str.contains("&e")) return DyeColor.YELLOW;
        return DyeColor.WHITE;
    }

    public static final String VERTICAL_STRAIGHT_LINE = StringEscapeUtils.unescapeJava("⎜");
    public static final String UNICODE_DOUBLE_ARROW = StringEscapeUtils.unescapeJava("»");
    public static final String UNICODE_CHECK_MARK = StringEscapeUtils.unescapeJava("✓");
    public static final String UNICODE_X_MARK = StringEscapeUtils.unescapeJava("✗");
    public static final String UNICODE_PIN = StringEscapeUtils.unescapeJava("\uD83D\uDCCD");
    public static final String UNICODE_GEM = StringEscapeUtils.unescapeJava("❁");
    public static final String UNICODE_HEART = StringEscapeUtils.unescapeJava("❤");
    public static final String UNICODE_SWORDS = StringEscapeUtils.unescapeJava("⚔");
    public static final String UNICODE_SHIELD = StringEscapeUtils.unescapeJava("⛊");
    public static final String UNICODE_SKULL = StringEscapeUtils.unescapeJava("☠");
    public static final String UNICODE_PICKAXE = StringEscapeUtils.unescapeJava("⛏");
    public static final String UNICODE_ARROW = StringEscapeUtils.unescapeJava("➠");
    public static final String UNICODE_ARROW_NEXT = StringEscapeUtils.unescapeJava("→");
    public static final String UNICODE_ARROW_LAST = StringEscapeUtils.unescapeJava("←");
    public static final String UNICODE_STAR = StringEscapeUtils.unescapeJava("✫");
    public static final String UNICODE_STAR_FILLED = StringEscapeUtils.unescapeJava("★");
    public static final String MENU_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "------------------------";
    public static final String CHAT_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "------------------------------------------------";
    public static final String SB_BAR = ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "----------------------";

    public static final String HEX_DARK_RED = translate("#FF3D43");
    public static final String HEX_LIGHT_RED = translate("#fc2803");
    public static final String HEX_LIGHT_RED_2 = translate("#e05858");
    public static final String HEX_GREEN = translate("#00CD00");
    public static final String HEX_LIGHT_GREEN = translate("#0dfa05");

}
