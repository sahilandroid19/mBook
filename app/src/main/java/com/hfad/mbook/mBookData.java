package com.hfad.mbook;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class mBookData extends SQLiteOpenHelper {
    private static final int DATA_VERSION = 1;
    private static final String DATA_NAME = "Data";

    public mBookData(Context context){
        super(context, DATA_NAME, null, DATA_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        create(db);
insertValues(db, 0, DATA_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
    }

    public void create(SQLiteDatabase db){
        db.execSQL("CREATE TABLE DATA(_id INTEGER PRIMARY KEY AUTOINCREMENT,"
        + "NAME TEXT,"
        + "TYPE INTEGER,"
        + "TITLE TEXT,"
        + "STORY TEXT,"
        + "VALUE INTEGER);");
    }

    public void insertValues(SQLiteDatabase db, int oldVersion, int newVersion){
        ContentValues values = new ContentValues();
        values.put("NAME", "Daniel in the lion's den");
        values.put("TYPE", R.drawable.action1);
        values.put("TITLE", "Action");
        values.put("STORY", "Daniel is raised to high office by his royal master Darius the Mede. Daniel's jealous rivals trick Darius into issuing a decree that for thirty days no prayers should be addressed to any god or man but Darius himself; any who break this are to be thrown to the lions. Daniel continues to pray to the God of Israel, and the king, although deeply distressed, must condemn Daniel to death, for the edicts of the Medes and Persians cannot be altered.\n"+"\n"+ " Hoping for Daniel's deliverance, he has him cast into the pit. At daybreak the king hurries to the place and cries out anxiously, asking if God had saved his friend. Daniel replies that his God had sent an angel to close up the jaws of the lions, \"because I was found blameless before him.\" The king commands that those who had conspired against Daniel should be thrown to the lions in his place, along with their wives and children, and writes to all the people of the whole world commanding that all should tremble and fear before the God of Daniel.");
        db.insert("DATA", null, values);

        ContentValues values1 = new ContentValues();
        values1.put("NAME", "Action Comics");
        values1.put("TYPE", R.drawable.action3);
        values1.put("TITLE", "Action");
        values1.put("STORY", "Action Comics is an American comic book series that introduced Superman, one of the first major superhero characters as the term is popularly defined.\n" + " The publisher was originally known as Detective Comics, Inc., and later as National Comics and as National Periodical Publications, before taking on its current name of DC Comics.\n"+"\n"+ "Its original incarnation ran from 1938 to 2011 and stands as one of the longest-running comic books with consecutively numbered issues; a second volume of Action Comics, beginning again with issue #1, was launched in 2011 and is currently in publication as of 2015.");
        db.insert("DATA", null, values1);

        ContentValues values2 = new ContentValues();
        values2.put("NAME", "My Big Book Of Action Stickers");
        values2.put("TYPE", R.drawable.action2);
        values2.put("TITLE", "Action");
        values2.put("STORY", "Rusty, a sixteen-year old Anglo-Indian boy, is orphaned and has to live with Mr. Harrison on the European area of Dehradun. Unhappy with Mr. Harrison's stern behavior and constant efforts of turning Rusty into an Englishman, he runs away from his home. He meets a group of Indian kids and is enchanted by the Indian customs, festivals and foods.\n"+ "\n"+" Soon he realizes that life on his own is not going to be as easy as he had perceived. He starts giving tuition to a boy Kishen in the neighbourhood and lives in the room of their attic. He develops a liking for Meena, Kishen’s mother.");
        db.insert("DATA", null, values2);

        ContentValues values3 = new ContentValues();
        values3.put("NAME", "Big Hero");
        values3.put("TYPE", R.drawable.action4);
        values3.put("TITLE", "Action");
        values3.put("Story", "Big Hero 6 is a 2014 American 3D computer-animated superhero comedy film produced by Walt Disney Animation Studios and released by Walt Disney Pictures—the first superhero film in Disney's animated features canon and the 54th overall. The film is inspired by the Marvel Comics superhero team of the same name.[6] Directed by Don Hall and Chris Williams, the film tells the story of a young robotics prodigy named Hiro Hamada who forms a superhero team to combat a masked villain. The film features the voices of Scott Adsit, Ryan Potter, Daniel Henney, T. J. Miller, Jamie Chung, Damon Wayans, Jr., Génesis Rodríguez, Alan Tudyk, James Cromwell, and Maya Rudolph.\n" +
                "\n" +
                "Big Hero 6 is the first Disney animated film to feature Marvel Comics characters, whose parent company was acquired by The Walt Disney Company in 2009.[7] Walt Disney Animation Studios created new software technology to produce the film's animated visuals.");
        db.insert("DATA", null, values3);
    }
}
