package edu.indiana.p532.saver;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class StateSaver {

	    public void write(GameState game, String filename) throws Exception{
	        XMLEncoder encoder =
	           new XMLEncoder(
	              new BufferedOutputStream(
	                new FileOutputStream(filename)));
	        encoder.writeObject(game);
	        encoder.close();
	    }

	    public GameState read(String filename) throws Exception {
	        XMLDecoder decoder =
	            new XMLDecoder(new BufferedInputStream(
	                new FileInputStream(filename)));
	        GameState game = (GameState)decoder.readObject();
	        decoder.close();
	        return game;
	    }

}
