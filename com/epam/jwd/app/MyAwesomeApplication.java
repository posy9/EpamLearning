package com.epam.jwd.app;
import com.epam.jwd.model.Dot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyAwesomeApplication{

 private static final Logger LOG = LoggerFactory.getLogger(MyAwesomeApplication.class);
 public static void main(String[] args){

  Dot[] dots = new Dot[3];

  dots[0] = new Dot(0,0,0);
  dots[1] = new Dot(1,2,3);
  dots[2] = new Dot(3,5,6);

  for (Dot dot : dots) {
    LOG.info("{} added to the array",dot.toString());
  }


 }
	
}