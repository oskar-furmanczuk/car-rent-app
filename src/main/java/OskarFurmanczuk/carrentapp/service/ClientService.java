package OskarFurmanczuk.carrentapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import OskarFurmanczuk.carrentapp.model.Client;

public interface ClientService {
	List<Client> getAllClients();
	void saveClient(Client client);
	Client getClientById(long id);
	void deleteClientById(long id);
	Page<Client> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
	Client getClientByCarId(long id);
}
