package com.shimels.mobugs.models.data;


import com.shimels.mobugs.models.Bug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BugRepository extends JpaRepository<Bug, Integer> {

}
