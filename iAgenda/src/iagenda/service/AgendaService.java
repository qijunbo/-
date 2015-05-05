package iagenda.service;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.db.crud.MongoCRUDService;
import com.db.model.Agenda;
import com.db.model.User;

public class AgendaService extends MongoCRUDService {

	public void addAgendaToUser(Bson filter,  Document agenda){
		
		Document user = getCollection(Agenda.class).find(filter).first();
	 
		
	}
}
