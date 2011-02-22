package com.excilys.formation.formalys.client.mvp;

import com.excilys.formation.formalys.client.place.CreateTrainingPlace;
import com.excilys.formation.formalys.client.place.ListQuestionsPlace;
import com.excilys.formation.formalys.client.place.ListTrainingsPlace;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

/**
 * PlaceHistoryMapper interface is used to attach all places which the
 * PlaceHistoryHandler should be aware of. This is done via the @WithTokenizers
 * annotation or by extending PlaceHistoryMapperWithFactory and creating a
 * separate TokenizerFactory.
 */
@WithTokenizers( { CreateTrainingPlace.Tokenizer.class, ListTrainingsPlace.Tokenizer.class, ListQuestionsPlace.Tokenizer.class })
public interface AppPlaceHistoryMapper extends PlaceHistoryMapper {
}
