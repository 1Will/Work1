package main.service;

import main.pojo.EventNew;

public interface ChangStateService {

	void setStateByProject(EventNew event);

	void setStateByContract(EventNew event);

	void setStateByFinancial(EventNew event);


}
