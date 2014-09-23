package com.example.myfirstapp.datasource;

import java.util.List;

import android.database.Cursor;
import android.database.SQLException;

import com.example.myfirstapp.model.Comment;

public interface InterfaceDao {

	public abstract void open() throws SQLException;

	public abstract void close();

	public abstract Comment createComment(String comment);

	public abstract void deleteComment(Comment comment);

	public abstract List<Comment> getAllComments();

	public abstract Comment cursorToComment(Cursor cursor);

}
