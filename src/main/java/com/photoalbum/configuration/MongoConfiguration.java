package com.photoalbum.configuration;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories({"com.photoalbum.repository"})
public class MongoConfiguration extends AbstractMongoConfiguration {
	@Override
	protected String getDatabaseName() {
		return "photo-album";
	}

	@Override
	public Mongo mongo() throws Exception {
		return new MongoClient();
	}
}
