package OskarFurmanczuk.carrentapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import OskarFurmanczuk.carrentapp.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{


	public Client getClientByCarId(long id);
}
