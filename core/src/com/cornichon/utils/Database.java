package com.cornichon.utils;

import static com.mongodb.client.model.Sorts.descending;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.util.function.Consumer;
import org.bson.Document;

public class Database {

  private static MongoClient mongoClient;
  private static MongoDatabase mongoDatabase;
  private static MongoCollection<Document> collection;

  public static void initDatabase() {
    mongoClient = connectDatabase();
    mongoDatabase = getDatabase(mongoClient);
    collection = getCollection(mongoDatabase);
  }

  public static void insert(final String name, final int score) {
    insertDocument(name, score);
  }

  public static Array<String> read() {
    return readDocument();
  }

  private static MongoDatabase getDatabase(final MongoClient mongoClient) {
    final MongoDatabase database = mongoClient.getDatabase("cornichon");
    return database;
  }

  private static MongoCollection<Document> getCollection(final MongoDatabase mongoDatabase) {
    final MongoCollection<Document> collection = mongoDatabase.getCollection("scores");
    return collection;
  }

  private static MongoClient connectDatabase() {
    final MongoClientURI connectionString = new MongoClientURI(
      "mongodb+srv://cornichon:muriamuria@cluster0.w9imv.mongodb.net/cornichon?retryWrites=true&w=majority"
    );
    final MongoClient mongoClient = new MongoClient(connectionString);
    return mongoClient;
  }

  private static void insertDocument(final String name, final int score) {
    Document doc = new Document("name", name).append("score", score);
    collection.insertOne(doc);
  }

  private static Array<String> readDocument() {
    FindIterable<Document> findIterable = collection.find().sort(descending("score"));

    final Array<String> result = new Array<String>();

    Consumer<Document> printConsumer = new Consumer<Document>() {
      public void accept(final Document doc) {
        JsonReader jsonReader = new JsonReader();
        JsonValue data = jsonReader.parse(doc.toJson());

        String result1 = data.get(1).toString().split(":")[1];
        String result2 = data.get(2).toString().split(":")[1];
        try {
          result.add(result1);
          result.add(result2);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    };

    findIterable.forEach(printConsumer);
    return result;
  }
}
