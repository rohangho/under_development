package com.example.myapplication2.entities;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class HistoryDao_Impl implements HistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfHistory;

  public HistoryDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHistory = new EntityInsertionAdapter<History>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `History`(`uid`,`name`,`otp`,`time`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, History value) {
        stmt.bindLong(1, value.uid);
        if (value.Name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.Name);
        }
        stmt.bindLong(3, value.otp);
        if (value.timestamp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.timestamp);
        }
      }
    };
  }

  @Override
  public void insertAppointment(final History abc) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfHistory.insert(abc);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<History> getAll() {
    final String _sql = "SELECT * FROM History";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = DBUtil.query(__db, _statement, false);
    try {
      final int _cursorIndexOfUid = CursorUtil.getColumnIndexOrThrow(_cursor, "uid");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfOtp = CursorUtil.getColumnIndexOrThrow(_cursor, "otp");
      final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
      final List<History> _result = new ArrayList<History>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final History _item;
        _item = new History();
        _item.uid = _cursor.getInt(_cursorIndexOfUid);
        _item.Name = _cursor.getString(_cursorIndexOfName);
        _item.otp = _cursor.getInt(_cursorIndexOfOtp);
        _item.timestamp = _cursor.getString(_cursorIndexOfTimestamp);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
