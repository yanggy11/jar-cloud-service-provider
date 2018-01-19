package com.yanggy.cloud.repository.elasticsearch;

import com.yanggy.cloud.entity.es.Person;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by derrick.yang on 1/19/18.
 */

@Repository
public interface PersonRepository extends ElasticsearchCrudRepository<Person,Long>{
}
