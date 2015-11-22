package com.andy.music.util;

import android.util.Log;

import java.util.Comparator;

/**
 * 通过首字母比较字符串
 * Created by andy on 2015/11/21.
 */
public class StringComparator implements Comparator {
    @Override
    public int compare(Object lhs, Object rhs) {
        if (lhs == null || rhs == null) return 0;
        String ls = CharacterParser.getInstance().getSelling(lhs.toString());
        String rs = CharacterParser.getInstance().getSelling(rhs.toString());

        if (ls == null || rs == null || ls.length() <=0 || rs.length() <=0 ) return 0;
        
        char lc = Character.toUpperCase(ls.charAt(0));
        char rc = Character.toUpperCase(rs.charAt(0));
        if (lc < 'A' | lc > 'z') {
            return 1;
        }
        if (rc <'A' | rc > 'z') {
            return -1;
        }
        return lc - rc;
    }
}
