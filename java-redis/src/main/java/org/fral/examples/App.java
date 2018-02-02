package org.fral.examples;

import redis.clients.jedis.Jedis;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
    	try {
			Jedis jedis = new Jedis("localhost");
			System.out.println("Connection has been established...");
			//System.out.println("Server ping " + jedis.ping());
			//System.out.println("Server info " + jedis.info());
			
			System.out.println("List push one -> two: " + jedis.lpush("one", "two"));
			System.out.println("List pop one: " + jedis.lpop("one"));
		} catch (Exception e) {
			// TODO: handle exception
		}
    }
}
