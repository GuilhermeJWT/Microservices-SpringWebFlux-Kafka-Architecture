package br.com.systemsgs.repository;

import br.com.systemsgs.entity.UserTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTransactionRepository extends JpaRepository<UserTransaction, Integer> {

}
