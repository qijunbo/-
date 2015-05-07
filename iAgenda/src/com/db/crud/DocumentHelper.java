package com.db.crud;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.types.Binary;
import org.bson.types.ObjectId;

import com.db.crud.anno.DateAdapter;
import com.db.crud.anno.Id;
import com.db.crud.anno.Required;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class DocumentHelper {

	public static final DateTypeAdapter DATE_TYPE_ADAPTER = new DateTypeAdapter();

	public static final String ID = "_id";

	private static Gson gson;

	private static final List<?> BSONType = Arrays.asList(Boolean.class,
			Character.class, Byte.class, Short.class, Integer.class,
			Long.class, Binary.class, ObjectId.class, Float.class,
			Double.class, Void.class, String.class, List.class, Date.class);

	public static Document buildDocument(Object pojo) {

		Document doc = Document.parse(gson.toJson(pojo));
		return doc;
	}

	public static Document buildDocument(Class<?> template,
			Map<String, ?> values) {

		Document doc = new Document();
		// count the number of @Id
		int idCount = 0;
		for (Field f : template.getDeclaredFields()) {

			Id id = f.getAnnotation(Id.class);

			if (id != null) {
				idCount++;

				// only one @Id is allowed in one document
				if (idCount > 1) {
					throw new RuntimeException(
							"More than one @Id annotation defined in "
									+ template.getName() + "." + f.getName());
				}
			}

			String name = id == null ? f.getName() : ID;
			Object value = null;

			// if the type of f is primitive or is a wrapper of primitive, or
			// java.util.Date.
			if (f.getType().isPrimitive() || isPrimitiveWrapper(f.getType())) {
				value = getFieldValue(f, values);
			} else {
				value = buildDocument(f.getType(), values);
			}

			if (value != null && value.toString().trim().length() > 0) {

				if (f.getType().equals(Date.class)) {
					DateAdapter adapter = f.getAnnotation(DateAdapter.class);
					value = DATE_TYPE_ADAPTER.deserializeToDate(
							value.toString(),
							adapter == null ? null : adapter.pattern());
				}

				doc.append(name, id == null ? value : newObjectId(value));
			}

		}

		return doc;
	}

	/**
	 * 
	 * @param doc
	 * @param clazz
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public static <T> T parse(Document doc, Class<T> clazz) {
		T t = gson.fromJson(doc.toJson(), clazz);

		// ObjectId objectId = doc.getObjectId("_id");
		//
		//
		//
		// for (Field f : clazz.getDeclaredFields()) {
		// Id id = f.getAnnotation(Id.class);
		// if( id != null){
		// f.set(t, objectId.toHexString());
		// }
		// }
		return t;
	}

	private static Object getFieldValue(Field field, Map<String, ?> values) {

		Object value = values.get(field.getName());

		// get Annotation of this field, if this field is required, then
		// throw a FieldMissingException.
		Required required = field.getAnnotation(Required.class);
		if (required != null && value == null) {
			throw new FieldMissingException(field.getName());
		}

		if (value != null && value.getClass().isArray()) {
			return ((Object[]) value)[0];
		} else {
			return value;
		}

	}

	public static boolean isPrimitiveWrapper(Class<?> clazz) {
		return BSONType.indexOf(clazz) > 0;
	}

	private static Object newObjectId(Object value) {
		ObjectId id = null;
		if (value == null) {
			return null;
		}
		if (value instanceof CharSequence) {
			id = new ObjectId(String.valueOf(value));
		} else if (value instanceof Date) {
			id = new ObjectId((Date) value);
		} else {
			id = new ObjectId(value.toString());
		}

		return id;
	}

	static {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.create();
	}
}
