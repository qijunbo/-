package com.db.crud;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import com.sun.istack.internal.Nullable;

/**
 * copy from google Gson.
 */
public final class DateTypeAdapter {

	private final DateFormat enUsFormat = DateFormat.getDateTimeInstance(
			DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.US);
	private final DateFormat localFormat = DateFormat.getDateTimeInstance(
			DateFormat.DEFAULT, DateFormat.DEFAULT);
	private final DateFormat iso8601Format = buildIso8601Format();

	private static DateFormat buildIso8601Format() {
		DateFormat iso8601Format = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US);
		iso8601Format.setTimeZone(TimeZone.getTimeZone("UTC"));
		return iso8601Format;
	}

	public synchronized Date deserializeToDate(String json,
			@Nullable String pattern) {
		DateFormat specified = new SimpleDateFormat(pattern);
		try {
			return specified.parse(json);
		} catch (ParseException ignored) {
		}
		try {
			return localFormat.parse(json);
		} catch (ParseException ignored) {
		}
		try {
			return enUsFormat.parse(json);
		} catch (ParseException ignored) {
		}
		try {
			return iso8601Format.parse(json);
		} catch (ParseException e) {
			throw new JsonSyntaxException(json, e);
		}
	}

}