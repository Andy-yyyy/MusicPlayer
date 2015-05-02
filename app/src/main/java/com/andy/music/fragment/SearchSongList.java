package com.andy.music.fragment;

import android.app.ActionBar;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andy.music.data.CursorAdapter;
import com.andy.music.data.MusicScanner;
import com.andy.music.entity.Music;
import com.andy.music.entity.TagConstants;
import com.andy.music.function.MusicListManager;

import java.util.List;

/**
 * 搜索结果列表
 * Created by Andy on 2015/4/6.
 */
public class SearchSongList extends BaseSongList {
    @Override
    List<Music> getList() {
        // 获取传递进来的查询语句
        List<Music> list = MusicListManager.getInstance(MusicListManager.MUSIC_LIST_LOCAL).getList();
        Bundle bundle = getArguments();
        String selection = MediaStore.Audio.Media.TITLE + " LIKE ? or " + MediaStore.Audio.Media.ARTIST + " LIKE ? or " + MediaStore.Audio.Media.ALBUM + " LIKE ?";
        String[] selectionArgs = null;
        if (bundle!=null) {
            selectionArgs = bundle.getStringArray("selection_args");
            String[] args = new String[3];
            args[0] = "%"+selectionArgs[0]+"%";
            args[1] = "%"+selectionArgs[0]+"%";
            args[2] = "%"+selectionArgs[0]+"%";
            Cursor cursor = CursorAdapter.get(selection, args);
            list = MusicScanner.scan(cursor);
        }
        return list;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getActivity().getActionBar();
        if(actionBar!=null) {
            actionBar.setTitle("搜索");
            actionBar.setDisplayHomeAsUpEnabled(true);  // 是否显示返回图标
        }
        super.onCreate(savedInstanceState);
    }
}
